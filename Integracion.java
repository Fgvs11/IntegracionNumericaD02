import java.util.InputMismatchException;
import java.util.Scanner;

public class Integracion {
    Scanner leer = new Scanner(System.in);
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_REED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    private float incremento, inicial;
    private double[] filaX;
    private double[] filaY;

    public Integracion(int cantidad) {
        filaX = new double[cantidad];
        filaY = new double[cantidad];
    }

    public void ingresoDatos() {
        incremento = validarIncremento();
        inicial = validarInicial();
        llenarTablaX(incremento, inicial);
        llenarTablaY();
    }

    public void llenarTablaX(float incre, float ini) {
        filaX[0] = ini;
        for (int i = 1; i < filaX.length; i++) {
            filaX[i] = filaX[i - 1] + incre;
        }
    }

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
            ans1 = Simsom38(0, lim);
        }
        if(lim != n){
            ans2 = Simsom13(lim, n);
        }
        return ans1 + ans2;
    }

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

    public double Simsom13(int i, int f){
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

    public double Simsom38(int i, int f){
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
