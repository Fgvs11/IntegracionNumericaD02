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
        for (int i = 0; i < filaY.length; i++) {
            System.out.printf("Y[%d]: ", i);
            filaY[i] = leer.nextFloat();
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
                inicial = leer.nextFloat();
                if (inicial >= 0) {
                    return inicial;
                } else {
                    System.out.println(ANSI_REED + "El valor inicial debe ser positivo...Vuelve a intentarlo" + ANSI_RESET);
                }
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
            System.out.printf("|    %-5d  |    %-5.2f  |    %-5.2f  |%n", i , filaX[i], filaY[i]);
        }
        System.out.printf("+-----------+-----------+-----------+%n");
    }
    
}
