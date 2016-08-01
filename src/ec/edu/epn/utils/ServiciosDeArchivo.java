package ec.edu.epn.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import ec.edu.epn.modelo.CatalogoDeVehiculos;
import ec.edu.epn.modelo.Prestamo;
import ec.edu.epn.modelo.RegistroDePrestamos;
import ec.edu.epn.modelo.Vehiculo;

public class ServiciosDeArchivo {

	public void escribirCatalogoDeVehiculosEnArchivo(
			List<Vehiculo> catalogoDeVehiculos) throws IOException,
			FileNotFoundException {
		try {
			FileOutputStream archivo = new FileOutputStream(
					CatalogoDeVehiculos.ARCHIVO_DE_CATALOGO_DE_VEHICULOS);
			ObjectOutputStream escritor = new ObjectOutputStream(archivo);
			escritor.writeObject(catalogoDeVehiculos);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException(
					">ERROR: No se puede encontrar el archivo "
							+ CatalogoDeVehiculos.ARCHIVO_DE_CATALOGO_DE_VEHICULOS);
		} catch (IOException e) {
			throw new IOException(
					">ERROR: No se pudo escribir el catálogo en el archivo "
							+ CatalogoDeVehiculos.ARCHIVO_DE_CATALOGO_DE_VEHICULOS);
		}
	}

	public List<Vehiculo> leerCatalogoDeVehiculosDesdeArchivo()
			throws FileNotFoundException, IOException, ClassNotFoundException {
		List<Vehiculo> vehiculos = null;
		try {
			FileInputStream archivo = new FileInputStream(
					CatalogoDeVehiculos.ARCHIVO_DE_CATALOGO_DE_VEHICULOS);
			ObjectInputStream lector = new ObjectInputStream(archivo);
			vehiculos = (List<Vehiculo>) lector.readObject();
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException(
					">ERROR: No se puede encontrar el archivo "
							+ CatalogoDeVehiculos.ARCHIVO_DE_CATALOGO_DE_VEHICULOS);
		} catch (IOException e) {
			throw new IOException(
					">ERROR: No se pudo leer el catálogo del archivo "
							+ CatalogoDeVehiculos.ARCHIVO_DE_CATALOGO_DE_VEHICULOS);
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException(">ERROR: El archivo "
					+ CatalogoDeVehiculos.ARCHIVO_DE_CATALOGO_DE_VEHICULOS
					+ " no contiene un objeto del tipo CatalogoDeVehiculos");
		}
		return vehiculos;
	}

	public void escribirRegistroDePrestamosEnArchivo(
			List<Prestamo> registroDePrestamos) throws IOException {
		try {
			FileOutputStream archivo = new FileOutputStream(
					RegistroDePrestamos.ARCHIVO_DE_REGISTRO_DE_PRESTAMOS);
			ObjectOutputStream escritor = new ObjectOutputStream(archivo);
			escritor.writeObject(registroDePrestamos);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException(
					">ERROR: No se puede encontrar el archivo "
							+ RegistroDePrestamos.ARCHIVO_DE_REGISTRO_DE_PRESTAMOS);
		} catch (IOException e) {
			throw new IOException(
					">ERROR: No se pudo escribir los préstamos en el archivo "
							+ RegistroDePrestamos.ARCHIVO_DE_REGISTRO_DE_PRESTAMOS);
		}
	}

	public List<Prestamo> leerRegistroDePrestamosDesdeArchivo()
			throws IOException, ClassNotFoundException {
		List<Prestamo> prestamos = null;
		try {
			FileInputStream archivo = new FileInputStream(
					RegistroDePrestamos.ARCHIVO_DE_REGISTRO_DE_PRESTAMOS);
			ObjectInputStream lector = new ObjectInputStream(archivo);
			prestamos = (List<Prestamo>) lector.readObject();
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException(
					">ERROR: No se puede encontrar el archivo "
							+ RegistroDePrestamos.ARCHIVO_DE_REGISTRO_DE_PRESTAMOS);
		} catch (IOException e) {
			throw new IOException(
					">ERROR: No se pudo leer el catálogo del archivo "
							+ RegistroDePrestamos.ARCHIVO_DE_REGISTRO_DE_PRESTAMOS);
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException(">ERROR: El archivo "
					+ RegistroDePrestamos.ARCHIVO_DE_REGISTRO_DE_PRESTAMOS
					+ " no contiene un objeto del tipo CatalogoDeVehiculos");
		}
		return prestamos;
	}
}
