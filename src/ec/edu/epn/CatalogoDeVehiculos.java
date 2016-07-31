package ec.edu.epn;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ec.edu.epn.modelo.Vehiculo;
import ec.edu.epn.modelo.enums.EstadoDeVehiculo;
import ec.edu.epn.modelo.enums.TipoDeCosto;

public class CatalogoDeVehiculos {
	public static final String NOMBRE_DE_ARCHIVO_VEHICULOS = "catalogo_de_vehiculos";

	public static void init() {
		File archivo = new File(NOMBRE_DE_ARCHIVO_VEHICULOS);
		if (!archivo.exists()) {
//			System.out.println("INFO: Creando archivo de vehiculos...");
			crearCatalogo(archivo);
		}
//		System.out.println("INFO: Leyendo vehiculos desde el archivo...");
		imprimirCatalogo();
	}

	private static void crearCatalogo(File archivo) {
		try {
			archivo.createNewFile();
			List<Vehiculo> catalogo = new ArrayList<Vehiculo>();
			catalogo = preCargarVehiculos();
			ServiciosDeArchivo.escribirVehiculosEnArchivo(catalogo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static List<Vehiculo> preCargarVehiculos() {
		List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();

		vehiculos.add(new Vehiculo("Chevrolet", "azul", "PZA-435",
				EstadoDeVehiculo.LIBRE, TipoDeCosto.SEGUNDACLASE));

		vehiculos.add(new Vehiculo("Mazda", "blanco", "PZB-123",
				EstadoDeVehiculo.ENMANTENIMIENTO, TipoDeCosto.PRIMERACLASE));

		vehiculos.add(new Vehiculo("Hyundai", "verde", "PZC-789",
				EstadoDeVehiculo.LIBRE, TipoDeCosto.TERCERACLASE));
		return vehiculos;
	}

	public static void imprimirCatalogo() {
		List<Vehiculo> catalogo = ServiciosDeArchivo.leerVehiculosDeArchivo();
		if (catalogo == null) {
			System.out.println("INFO: Catálogo de vehículos vacío");
		} else {
			System.out.println("***CATALOGO DE VEHICULOS***");
			for (Vehiculo v : catalogo) {
				System.out.println(v);
			}
			System.out.println();
		}
	}

	public static Vehiculo buscarVehiculoPorPlaca(String placa) {
		List<Vehiculo> catalogo = ServiciosDeArchivo.leerVehiculosDeArchivo();
		int indice = -1, contador = 0;
		for (Vehiculo v : catalogo) {
			if (v.getPlaca().compareTo(placa) == 0) {
				indice = contador;
				break;
			} else {
				contador++;
			}
		}
		if (indice != -1) {
			return catalogo.get(indice);
		} else {
			return null;
		}
	}

	public static void actualizarVehiculo(Vehiculo vehiculo) {
		List<Vehiculo> catalogo = ServiciosDeArchivo.leerVehiculosDeArchivo();
		int indice = buscarIndiceDeVehiculo(vehiculo.getPlaca());
		if (indice != -1) {
			catalogo.set(indice, vehiculo);
			ServiciosDeArchivo.escribirVehiculosEnArchivo(catalogo);
		} else {
			System.out
					.println("ERROR: No existe el vehículo que se intenta actualizar");
		}
	}

	private static int buscarIndiceDeVehiculo(String placa) {
		List<Vehiculo> catalogo = ServiciosDeArchivo.leerVehiculosDeArchivo();
		int indice = -1, contador = 0;
		for (Vehiculo v : catalogo) {
			if (v.getPlaca().compareTo(placa) == 0) {
				indice = contador;
				break;
			} else {
				contador++;
			}
		}
		return indice;
	}
}
