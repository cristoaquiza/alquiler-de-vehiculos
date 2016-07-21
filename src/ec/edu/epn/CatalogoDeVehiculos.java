package ec.edu.epn;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class CatalogoDeVehiculos {
	public static final String NOMBRE_DE_ARCHIVO = "catalogo_de_vehiculos";

	private static List<Vehiculo> catalogo = new ArrayList<Vehiculo>();

	public static void init() {
		File archivo = new File(NOMBRE_DE_ARCHIVO);
		if (archivo.exists()) {
			cargarCatalogo(archivo);
		} else {
			crearCatalogo(archivo);
		}
	}

	private static void crearCatalogo(File archivo) {
		try {
			archivo.createNewFile();
			catalogo = cargarObjetosEnLista();
			FileOutputStream archivoOut = new FileOutputStream(archivo);
			ObjectOutputStream escritor = new ObjectOutputStream(archivoOut);
			escritor.writeObject(catalogo);
		} catch (IOException e) {
			// TODO Auto-generated catch block: error al crear el archivo
			e.printStackTrace();
		}
	}

	private static List<Vehiculo> cargarObjetosEnLista() {
		List<Vehiculo> listaDeObjetos = new ArrayList<Vehiculo>();
		listaDeObjetos.add(new Vehiculo("Chevrolet", "azul", "PZA-435",
				EstadoDeVehiculo.LIBRE, TipoDeCosto.SEGUNDACLASE));
		listaDeObjetos.add(new Vehiculo("Mazda", "blanco", "PZB-123",
				EstadoDeVehiculo.ENMANTENIMIENTO, TipoDeCosto.PRIMERACLASE));
		listaDeObjetos.add(new Vehiculo("Hyundai", "verde", "PZC-789",
				EstadoDeVehiculo.LIBRE, TipoDeCosto.TERCERACLASE));
		return listaDeObjetos;
	}

	private static void cargarCatalogo(File archivo) {
		try {
			FileInputStream archivoIn = new FileInputStream(archivo);
			ObjectInputStream lector = new ObjectInputStream(archivoIn);
			catalogo = (List<Vehiculo>) lector.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block: error en archivo
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block: error en lector
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block:error al castear el objeto del
			// archivo
			e.printStackTrace();
		}
	}

	public static Vehiculo buscarVehiculoPorPlaca(String placa) {
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
	
	public static int buscarIndiceDeVehiculoPorPlaca(String placa) {
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
	
	public static void imprimirCatalogo() {
		for(Vehiculo v: catalogo) {
			System.out.println(v);
		}
	}

	public static void cambiarEstadoEnVehiculo(String placa, EstadoDeVehiculo estado) {
		int indice = buscarIndiceDeVehiculoPorPlaca(placa);
		catalogo.get(indice).setEstado(estado);
		//TODO:actualizar catalogo?
	}

	public static void actualizarCatalogoEnArchivo() {
		// TODO Auto-generated method stub
		
	}

	public static void actualizarEstadoDeVehiculo(String placa,
			EstadoDeVehiculo estado) {
		
	}

}
