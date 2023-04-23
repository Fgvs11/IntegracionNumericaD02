import java.util.InputMismatchException;
import java.util.Scanner;
/**
  *
  * <h1>Clase Integracion</h1>
  * <p> Esta es la clase donde todo el proceso externo se ejecuta, <br>
  * ya que aqui estan presentes los metodos de validacion,<br>
  * ademas del metodo main. <br><br>
  * </p>
  * @author Fernando Genaro Vizcaino Sanches / Alejandro Estrada Ponce
  * @version Ver 1.22474487139
  *
  */
public class PruebaIntegracion {
	/** Ingresa la cantidad de datos a ingresar */
	public static int cant;
	/** El objeto necesario para la entrada de datos */
	public static Scanner leer = new Scanner(System.in);
	/** Almacena el incremento entre valores de x */
	public static final String ANSI_RESET = "\u001B[0m";
	/** Codigo para que el mensaje este en color amarillo */
	public static final String ANSI_YELLOW = "\u001B[33m";
	/** Codigo para que el mensaje este en color rojo */
	public static final String ANSI_REED = "\u001B[31m";
	/** Codigo para que el mensaje este en color verde */
	public static final String ANSI_GREEN = "\u001B[32m";
	/**
    * Metodo principal de la clase <b>PruebaIntegracion</b>.
    * <br>
    * @param args recibe informacion al momento de que se ejecuta la clase, es de tipo <i>String</i>
    *
    */
	public static void main(String args[]) {

		Integracion inte = new Integracion(validarCant());
		inte.ingresoDatos();
		inte.imprimirTabla();
		double ans = inte.IntegracionNumerica();
		System.out.printf(ANSI_GREEN + "El resultado es: %.4f" +ANSI_RESET,ans);
	}
	/**
	 * Valida que el numero ingresado en cantidad coincida en ser un numero entero positivo 
	 * @return regresa el numero que va en cantidad
	 */
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
