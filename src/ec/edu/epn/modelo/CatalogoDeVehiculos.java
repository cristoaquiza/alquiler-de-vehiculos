package ec.edu.epn.modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ec.edu.epn.modelo.enums.EstadoDeVehiculo;
import ec.edu.epn.modelo.enums.TipoDeCosto;
import ec.edu.epn.utils.ServiciosDeArchivo;

public class CatalogoDeVehiculos {
	public static final String ARCHIVO_DE_CATALOGO_DE_VEHICULOS = "catalogo_de_vehiculos";

	private List<Vehiculo> catalogoDeVehiculos;

	private ServiciosDeArchivo serviciosDeArchivo = new ServiciosDeArchivo();

	public CatalogoDeVehiculos() throws ClassNotFoundException, IOException {
		cargarVehiculos();
	}

	public void cargarVehiculos() throws IOException, ClassNotFoundException {
		this.catalogoDeVehiculos = new ArrayList<Vehiculo>();

		File archivo = new File(ARCHIVO_DE_CATALOGO_DE_VEHICULOS);

		if (!archivo.exists()) {
			crearCatalogo(archivo);
		} else {
			cargarCatalogo(archivo);
		}
	}

	private void cargarCatalogo(File archivo) throws FileNotFoundException,
			ClassNotFoundException, IOException {
		this.catalogoDeVehiculos = serviciosDeArchivo
				.leerCatalogoDeVehiculosDesdeArchivo();
	}

	private void crearCatalogo(File archivo) throws IOException {
		try {
			archivo.createNewFile();

			this.catalogoDeVehiculos = new ArrayList<Vehiculo>();

			this.catalogoDeVehiculos
					.add(new Vehiculo("Chevrolet", "azul", "PZA-435",
							EstadoDeVehiculo.LIBRE, TipoDeCosto.SEGUNDACLASE));
			this.catalogoDeVehiculos.add(new Vehiculo("Mazda", "blanco",
					"PZB-123", EstadoDeVehiculo.ENMANTENIMIENTO,
					TipoDeCosto.PRIMERACLASE));
			this.catalogoDeVehiculos
					.add(new Vehiculo("Hyundai", "verde", "PZC-789",
							EstadoDeVehiculo.LIBRE, TipoDeCosto.TERCERACLASE));

			serviciosDeArchivo
					.escribirCatalogoDeVehiculosEnArchivo(this.catalogoDeVehiculos);

		} catch (IOException e) {
			throw new IOException(
					">ERROR: Se produjo un error al crear el archivo " + ARCHIVO_DE_CATALOGO_DE_VEHICULOS);
		}
	}

	public void imprimirCatalogoDeVehiculos() throws Exception {
		if (this.catalogoDeVehiculos == null) {
			throw new Exception(">ERROR: Catálogo de vehículos vacío");
		} else {
			System.out.println("\t\t*** CATÁLOGO DE VEHICULOS ***");
			for (Vehiculo v : this.catalogoDeVehiculos) {
				System.out.println(v);
			}
			System.out.println();
		}
	}

	public Vehiculo buscarVehiculoPorPlaca(String placa) throws Exception {
		int indice = -1, contador = 0;
		for (Vehiculo v : this.catalogoDeVehiculos) {
			if (v.getPlaca().compareTo(placa) == 0) {
				indice = contador;
				break;
			} else {
				contador++;
			}
		}
		if (indice != -1) {
			return this.catalogoDeVehiculos.get(indice);
		} else {
			throw new Exception(
					">ERROR: El vehículo no se encuentra en el catálogo");
		}
	}

	public void actualizarCatalogoDeVehiculos(Vehiculo vehiculo)
			throws Exception {
		int indice = buscarIndiceDeVehiculo(vehiculo.getPlaca());
		this.catalogoDeVehiculos.set(indice, vehiculo);
		serviciosDeArchivo
				.escribirCatalogoDeVehiculosEnArchivo(this.catalogoDeVehiculos);
	}

	private int buscarIndiceDeVehiculo(String placa) throws Exception {
		int indice = -1, contador = 0;
		for (Vehiculo v : this.catalogoDeVehiculos) {
			if (v.getPlaca().compareTo(placa) == 0) {
				indice = contador;
				break;
			} else {
				contador++;
			}
		}
		if (indice != -1) {
			return indice;
		} else {
			throw new Exception(
					">ERROR: El vehículo no se encuentra en el catálogo");
		}
	}
}
