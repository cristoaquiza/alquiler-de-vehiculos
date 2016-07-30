package ec.edu.epn;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegistroDePrestamos {
	public static final String NOMBRE_DE_ARCHIVO_PRESTAMOS = "registro_de_prestamos";

	public static void init() {
		File archivo = new File(NOMBRE_DE_ARCHIVO_PRESTAMOS);
		if (!archivo.exists()) {
//			System.out.println("INFO: Creando archivo de prestamos...");
			crearRegistro(archivo);
			imprimirRegistro();
		} else {
//			System.out.println("INFO: Leyendo préstamos desde el archivo...");
			imprimirRegistro();
		}
	}

	private static void crearRegistro(File archivo) {
		try {
			archivo.createNewFile();
			List<Prestamo> prestamos = null;
			ServiciosDeArchivo.escribirPrestamosEnArchivo(prestamos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void imprimirRegistro() {
		List<Prestamo> prestamos = ServiciosDeArchivo.leerPrestamosDeArchivo();
		if (prestamos == null) {
			System.out.println("INFO: Registro de préstamos vacío");
		} else {
			System.out.println("***REGISTRO DE PRESTAMOS***");
			for (Prestamo p : prestamos) {
				System.out.println(p);
			}
			System.out.println();
		}
	}

	public static void registrarPrestamo(Prestamo prestamo) {
		List<Prestamo> prestamos = ServiciosDeArchivo.leerPrestamosDeArchivo();
		if (prestamos == null) {
			prestamos = new ArrayList<Prestamo>();
		}
		prestamos.add(prestamo);
		ServiciosDeArchivo.escribirPrestamosEnArchivo(prestamos);
		CatalogoDeVehiculos.actualizarVehiculo(prestamo.getVehiculo());
	}

	public static Prestamo buscarPrestamoPorPlaca(String placa) {
		List<Prestamo> prestamos = ServiciosDeArchivo.leerPrestamosDeArchivo();
		int indice = -1, contador = 0;
		for (Prestamo p : prestamos) {
			if (p.getVehiculo().getPlaca().compareTo(placa) == 0) {
				indice = contador;
				break;
			} else {
				contador++;
			}
		}
		if (indice != 1) {
			return prestamos.get(indice);
		} else {
			return null;
		}
	}
	
	public static int buscarIndiceDePrestamoPorPlaca(String placa) {
		List<Prestamo> prestamos = ServiciosDeArchivo.leerPrestamosDeArchivo();
		int indice = -1, contador = 0;
		for (Prestamo p : prestamos) {
			if (p.getVehiculo().getPlaca().compareTo(placa) == 0) {
				indice = contador;
				break;
			} else {
				contador++;
			}
		}
		return indice;
	}

	public static void eliminarPrestamo(Prestamo prestamo) {
		List<Prestamo> prestamos = ServiciosDeArchivo.leerPrestamosDeArchivo();
		int indice = buscarIndiceDePrestamoPorPlaca(prestamo.getVehiculo().getPlaca());
		prestamos.remove(indice);
		ServiciosDeArchivo.escribirPrestamosEnArchivo(prestamos);
	}

}
