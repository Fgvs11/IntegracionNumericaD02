import java.util.InputMismatchException;
import java.util.Scanner;

public class PruebaIntegracion {
	public static int cant;
	public static Scanner leer = new Scanner(System.in);
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_REED = "\u001B[31m";

	public static void main(String args[]) {

		Integracion inte = new Integracion(validarCant());
		inte.ingresoDatos();
		inte.imprimirTabla();

	}

	public static int validarCant() {
		do {
			try {
				System.out.print("Ingrese la cantidad de datos de la tabla: ");
				cant = leer.nextInt();
				if (cant > 0) {
					return cant;
				} else {
					System.out.println(ANSI_REED + "La cantidad debe ser positiva...Vuelve a intentarlo" + ANSI_RESET);
				}
			} catch (InputMismatchException e) {
				System.out.println(ANSI_REED + "Solo se aceptan numeros... Vuelve a intentarlo" + ANSI_RESET);
				leer.nextLine();
			}
		} while (true);
	}

}
