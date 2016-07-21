package ec.edu.epn;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class RegistroDePrestamos {
	private static final String NOMBRE_DE_ARCHIVO = "registro_de_prestamos";

	private static List<Prestamo> registroDePrestamos = new ArrayList<Prestamo>();

	public static void registrarPrestamo(Prestamo prestamo) {
		registroDePrestamos.add(prestamo);
		actualizarRegistroDePrestamos();// actualiza el archivo de registro de
										// prestamos
		CatalogoDeVehiculos.actualizarEstadoDeVehiculo(prestamo.getVehiculo()
				.getPlaca(), EstadoDeVehiculo.RENTADO);// actualiza el estado de
														// un vehiculo en el
														// catalogo

		// TODO:ingresar en archivo
		almacenarRegistroEnArchivo();
		CatalogoDeVehiculos.cambiarEstadoEnVehiculo(prestamo.getVehiculo()
				.getPlaca(), EstadoDeVehiculo.RENTADO);
		CatalogoDeVehiculos.actualizarCatalogoEnArchivo();
	}

	private static void actualizarRegistroDePrestamos() {
		ServiciosDeArchivo.escribirPrestamosEnArchivo(registroDePrestamos,
				NOMBRE_DE_ARCHIVO);

	}

	public static void imprimirRegistro() {
		// TODO:leer desde archivo
		for (Prestamo p : registroDePrestamos) {
			System.out.println(p);
		}
	}

	private static void almacenarRegistroEnArchivo() {
		try {
			FileOutputStream archivoOut = new FileOutputStream(
					"registro_de_prestamos");
			ObjectOutputStream escritor = new ObjectOutputStream(archivoOut);
			escritor.writeObject(registroDePrestamos);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block:error al leer archivo
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block: error al intentar escribir
			e.printStackTrace();
		}
	}
}
