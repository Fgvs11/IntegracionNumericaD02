import java.util.InputMismatchException;
import java.util.Scanner;
/**
  *
  * <h1>Clase Integracion</h1>
  * <p> Esta es la clase donde todo el proceso interno se ejecuta, <br>
  * ya que aqui estan presentes los metodos de integracion,<br>
  * ademas de toda la entrada de datos. <br><br>
  * </p>
  * @author Fernando Genaro Vizcaino Sanches / Alejandro Estrada Ponce
  * @version Ver 1.22474487139
  *
  */
public class Integracion {
    /** El objeto necesario para la entrada de datos */
    Scanner leer = new Scanner(System.in);
    /** Codigo para que el mensaje este en color verde */
    public static final String ANSI_GREEN = "\u001B[32m";
    /** Codigo para que el mensaje este en color amarillo */
    public static final String ANSI_YELLOW = "\u001B[33m";
    /** Codigo para que el mensaje este en color rojo */
    public static final String ANSI_REED = "\u001B[31m";
    /** Codigo para que el mensaje este en color predefinido */
    public static final String ANSI_RESET = "\u001B[0m";
    /** Almacena el incremento entre valores de x */
    private float incremento;
    /** Este es el valor inicial de x */
    private float inicial;
    /** Todos los valores que toma x almacenados en un arreglo */
    private double[] filaX;
    /** Todos los valores que toma y almacenados en un arreglo */
    private double[] filaY;

    /** Es el constructor de la clase, el cual inicializa los arreglos
     * @param cantidad Cantidad de datos a guardar
     */
    public Integracion(int cantidad) {
        filaX = new double[cantidad];
        filaY = new double[cantidad];
    }

    /** Metodo que lleva a cabo todo el proceso de ingreso de datos,<br>
     * llevando a cabo los metodos necesarios
     * 
     */
    public void ingresoDatos() {
        incremento = validarIncremento();
        inicial = validarInicial();
        llenarTablaX(incremento, inicial);
        llenarTablaY();
    }
    /**
     * Llena todo el arreglo de filaX con los valores respectivos de x
     * @param incre Es el incremento entre cada valor
     * @param ini Es el valor de la primera x
     */
    public void llenarTablaX(float incre, float ini) {
        filaX[0] = ini;
        for (int i = 1; i < filaX.length; i++) {
            filaX[i] = filaX[i - 1] + incre;
        }
    }
    /**Llena todo el arreglo de filaX con los valores respectivos de y */
    public void llenarTablaY() {
        System.out.print(ANSI_YELLOW + "AVISO!!! LOS DATOS CON MAS DE 2 DECIMALES SE MOSTRARAN REDONDEADOS\n" + ANSI_RESET);
        System.out.println("Ingresa los datos de [Y]...");
        llenado:
        for (int i = 0; i < filaY.length; i++) {
            try {
                System.out.printf("Y[%d]: ", i);
                filaY[i] = leer.nextFloat();
            } catch (InputMismatchException e) {
                System.out.println(ANSI_REED + "Solo se aceptan numeros... Vuelve a intentarlo" + ANSI_RESET);
                leer.nextLine();
                i--;
                continue llenado;
            }
        }
    }
    /** Valida quer el incremento si sea un número positivo
     * @return Regresa el valor del incremento
     */
    public float validarIncremento () {
        do {
        try {
            System.out.print("Ingrese el incremento: ");
            incremento = leer.nextFloat();
            if (incremento > 0) {
                return incremento;
            } else {
                System.out.println(ANSI_REED + "El incremento debe ser positivo...Vuelve a intentarlo" + ANSI_RESET);
            }
        } catch (InputMismatchException e) {
            System.out.println(ANSI_REED + "Solo se aceptan numeros... Vuelve a intentarlo" + ANSI_RESET);
            leer.nextLine();
        }
    }while (true);
    }
    /**
     * Verifica que el valor inicial ingresado si sea un numero
     * @return regresa el valor inicial
     */
    public float validarInicial () {
        do {
            try {
                System.out.print("Ingrese el valor incial de [X]: ");
                return leer.nextFloat();
            } catch (InputMismatchException e) {
                System.out.println(ANSI_REED + "Solo se aceptan numeros... Vuelve a intentarlo" + ANSI_RESET);
                leer.nextLine();
            }
        }while (true);
    }
    /**
     * Imprime en formato de tabla los valores obtenidos
     */
    public void imprimirTabla() {
        System.out.println();
        System.out.printf("+-----------------------------------+%n");
        System.out.printf("|          " + ANSI_GREEN + "TABLA REGISTRADA" + ANSI_RESET +  "         |%n");
        System.out.printf("+-----------------------------------+%n");
        System.out.printf("|     " + ANSI_YELLOW + "I"+ ANSI_RESET + "     |     " + ANSI_YELLOW + "X" + ANSI_RESET + "     |     " + ANSI_YELLOW + "Y" + ANSI_RESET + "     |%n");
        System.out.printf("+-----------+-----------+-----------+%n");
        for (int i = 0; i < filaX.length; i++) {
            System.out.printf("|     %-4d  | %-9.2f | %-9.2f |%n", i , filaX[i], filaY[i]);
        }
        System.out.printf("+-----------+-----------+-----------+%n");
    }
    /**
     * Contiene toda la logica atras de la separacion de intervalos para la aplicacion de las formulas, recordando que un numero arriba de 3 siempre puede ser descompuestos en la suma de multiplos de 3 y de 2
     * @return regresa el resultado de la integral total
     */
    public double IntegracionNumerica(){
        if (filaY.length == 2){
            return Trapecios(0, filaY.length -1);
        }
        int n = filaY.length - 1;
        int x = (int) n / 3;
        int lim = x * 3;
        while((n - lim) %2 != 0){
            x--;
            lim = x * 3;
        }
        double ans1 = 0;
        double ans2 = 0;
        if(x != 0){
            ans1 = Simpson38(0, lim);
        }
        if(lim != n){
            ans2 = Simpson13(lim, n);
        }
        return ans1 + ans2;
    }
    /**
     * Es la formula de trapecios, no recomendable usarse, por lo cual solo se usa en caso de ingresar solo 2 numeros
     * @param i Es en que parte del arreglo inicia a tomar en cuenta
     * @param f Es en que parte del arreglo detenerse
     * @return  Regresa el resultado de la integral en el intervalo que se especifico con i y f
     */
    public double Trapecios(int i, int f){
        double ans = 0;
        for(int j = 1; j < f-i;j++ ){
            ans += filaY[j+i];
        }
        ans *= 2;
        ans += filaY[i] + filaY[f];
        ans = (incremento/2) * ans;
        return ans;
    }
    /**
     * Es la formula de simpson 1/3, esta solo es aplicable cuando n %2 ==0
     * @param i Es en que parte del arreglo inicia a tomar en cuenta
     * @param f Es en que parte del arreglo detenerse
     * @return  Regresa el resultado de la integral en el intervalo que se especifico con i y f
     */
    public double Simpson13(int i, int f){
        double ans = 0;
        for(int j = 1; j < f - i;j++ ){
            if(j%2 == 0){
                ans += 2* filaY[j + i];
            }else{
                ans += 4* filaY[j+i];
            }
        }
        ans += filaY[i] + filaY[f];
        ans *= incremento/3;
        return ans;
    }
    /**
     * Es la formula de simpson 3/8, esta solo es aplicable cuando n %3 ==0
     * @param i Es en que parte del arreglo inicia a tomar en cuenta
     * @param f Es en que parte del arreglo detenerse
     * @return  Regresa el resultado de la integral en el intervalo que se especifico con i y f
     */
    public double Simpson38(int i, int f){
        double ans = 0;
        for(int j = 1; j < f - i;j++ ){
            if(j%3 == 0){
                ans += 2* filaY[j + i];
            }else{
                ans += 3* filaY[j + i];
            }
        }
        ans += filaY[i] + filaY[f];
        ans *= 3*incremento/8;
        return ans;
    }
    
}
