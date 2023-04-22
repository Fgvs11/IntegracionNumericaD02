import java.util.Scanner;

public class PruebaIntegracion{
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static void main(String args[]){

		Scanner leer = new Scanner(System.in);

		System.out.print("Ingrese la cantidad de datos de la tabla: ");
		Integracion inte = new Integracion(leer.nextInt());
		inte.ingresoDatos();
		inte.imprimirTabla();

	}
}
