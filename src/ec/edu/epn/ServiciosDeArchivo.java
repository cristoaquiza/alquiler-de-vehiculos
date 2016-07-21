package ec.edu.epn;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ServiciosDeArchivo {

	public static void escribirPrestamosEnArchivo(List<Prestamo> registroDePrestamos, String nombreDeArchivo) {
		try {
			FileOutputStream archivo = new FileOutputStream(nombreDeArchivo);
			ObjectOutputStream escritor = new ObjectOutputStream(archivo);
			escritor.writeObject(registroDePrestamos);
			escritor.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<Prestamo> leerPrestamosDeArchivo(String nombreDeArchivo) {
		List<Prestamo> prestamos = new ArrayList<Prestamo>();
		try {
			FileInputStream archivo = new FileInputStream(nombreDeArchivo);
			ObjectInputStream lector = new ObjectInputStream(archivo);
			prestamos = (List<Prestamo>) lector.readObject();
			lector.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prestamos;
	}

	public static void escribirVehiculosEnArchivo(List<Vehiculo> catalogoDeVehiculos, String nombreDeArchivo) {
		try {
			FileOutputStream archivo = new FileOutputStream(nombreDeArchivo);
			ObjectOutputStream escritor = new ObjectOutputStream(archivo);
			escritor.writeObject(catalogoDeVehiculos);
			escritor.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<Vehiculo> leerVehiculosDeArchivo(String nombreDeArchivo) {
		List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		try {
			FileInputStream archivo = new FileInputStream(nombreDeArchivo);
			ObjectInputStream lector = new ObjectInputStream(archivo);
			vehiculos = (List<Vehiculo>) lector.readObject();
			lector.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vehiculos;
	}

}
