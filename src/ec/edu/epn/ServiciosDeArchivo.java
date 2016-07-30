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

	public static void escribirPrestamosEnArchivo(List<Prestamo> prestamos) {
		try {
			FileOutputStream archivo = new FileOutputStream(
					RegistroDePrestamos.NOMBRE_DE_ARCHIVO_PRESTAMOS);
			ObjectOutputStream escritor = new ObjectOutputStream(archivo);
			escritor.writeObject(prestamos);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<Prestamo> leerPrestamosDeArchivo() {
		List<Prestamo> prestamos = null;
		try {
			FileInputStream archivo = new FileInputStream(RegistroDePrestamos.NOMBRE_DE_ARCHIVO_PRESTAMOS);
			ObjectInputStream lector = new ObjectInputStream(archivo);
			prestamos = (List<Prestamo>) lector.readObject();
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

	public static void escribirVehiculosEnArchivo(List<Vehiculo> vehiculos) {
		try {
			FileOutputStream archivo = new FileOutputStream(CatalogoDeVehiculos.NOMBRE_DE_ARCHIVO_VEHICULOS);
			ObjectOutputStream escritor = new ObjectOutputStream(archivo);
			escritor.writeObject(vehiculos);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<Vehiculo> leerVehiculosDeArchivo() {
		List<Vehiculo> vehiculos = null;//aqui
		try {
			FileInputStream archivo = new FileInputStream(CatalogoDeVehiculos.NOMBRE_DE_ARCHIVO_VEHICULOS);
			ObjectInputStream lector = new ObjectInputStream(archivo);
			vehiculos = (List<Vehiculo>) lector.readObject();
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
