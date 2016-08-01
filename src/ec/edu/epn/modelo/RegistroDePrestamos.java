package ec.edu.epn.modelo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import ec.edu.epn.utils.ServiciosDeArchivo;

public class RegistroDePrestamos {
	public static final String ARCHIVO_DE_REGISTRO_DE_PRESTAMOS = "registro_de_prestamos";

	private List<Prestamo> registroDePrestamos;

	private ServiciosDeArchivo serviciosDeArchivo = new ServiciosDeArchivo();

	public RegistroDePrestamos() throws ClassNotFoundException, IOException {
		cargarPrestamos();
	}

	private void cargarPrestamos() throws IOException, ClassNotFoundException {
		this.registroDePrestamos = new ArrayList<Prestamo>();

		File archivo = new File(ARCHIVO_DE_REGISTRO_DE_PRESTAMOS);

		if (!archivo.exists()) {
			crearRegistro(archivo);
		} else {
			cargarPrestamos(archivo);
		}
	}

	private void cargarPrestamos(File archivo) throws ClassNotFoundException,
			IOException {
		this.registroDePrestamos = serviciosDeArchivo
				.leerRegistroDePrestamosDesdeArchivo();
	}

	private void crearRegistro(File archivo) throws IOException {
		try {
			archivo.createNewFile();
			this.registroDePrestamos = null;
			serviciosDeArchivo
					.escribirRegistroDePrestamosEnArchivo(this.registroDePrestamos);
		} catch (IOException e) {
			throw new IOException(
					">ERROR: Se produjo un error al crear el archivo "
							+ ARCHIVO_DE_REGISTRO_DE_PRESTAMOS);
		}
	}

	public void imprimirRegistroDePrestamos() {
		if (this.registroDePrestamos == null) {
			System.out.println(">>>> INFO: Registro de préstamos vacío <<<<");
		} else {
			System.out.println("\t\t*** REGISTRO DE PRÉSTAMOS ***");
			for (Prestamo p : this.registroDePrestamos) {
				System.out.println(p);
			}
			System.out.println();
		}
	}

	public void registrarPrestamo(Prestamo prestamo) throws IOException {
		if (this.registroDePrestamos == null) {
			this.registroDePrestamos = new ArrayList<Prestamo>();
		}
		this.registroDePrestamos.add(prestamo);
		serviciosDeArchivo
				.escribirRegistroDePrestamosEnArchivo(this.registroDePrestamos);
	}

	public Prestamo buscarPrestamoPorPlaca(String placa) throws Exception {
		int indice = -1, contador = 0;
		for (Prestamo p : this.registroDePrestamos) {
			if (p.getVehiculo().getPlaca().compareTo(placa) == 0) {
				indice = contador;
				break;
			} else {
				contador++;
			}
		}
		if (indice != 1) {
			return this.registroDePrestamos.get(indice);
		} else {
			throw new Exception(">ERROR: El vehículo no se encuentra prestado");
		}
	}

	private int buscarIndiceDePrestamoPorPlaca(String placa) throws Exception {
		int indice = -1, contador = 0;
		for (Prestamo p : this.registroDePrestamos) {
			if (p.getVehiculo().getPlaca().compareTo(placa) == 0) {
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
					">ERROR: El vehículo no se encuentra en préstamo");
		}
	}

	public void eliminarPrestamo(Prestamo prestamo) throws Exception {
		int indice = buscarIndiceDePrestamoPorPlaca(prestamo.getVehiculo()
				.getPlaca());
		this.registroDePrestamos.remove(indice);
		serviciosDeArchivo
				.escribirRegistroDePrestamosEnArchivo(this.registroDePrestamos);
	}

	public void generarPago(Calendar fechaDeRetorno, Prestamo prestamo) {
		long end = fechaDeRetorno.getTimeInMillis();
		Calendar posibleRetorno = Calendar.getInstance();
		posibleRetorno.setTime(prestamo.getFechaPosibleDeRetorno());
		long start = posibleRetorno.getTimeInMillis();
		long restaDeDias = TimeUnit.MILLISECONDS.toDays(end - start);

		end = posibleRetorno.getTimeInMillis();
		Calendar renta = Calendar.getInstance();
		renta.setTime(prestamo.getFechaDeRenta());
		start = renta.getTimeInMillis();
		int diasDeUso = (int) TimeUnit.MILLISECONDS.toDays(Math
				.abs(end - start));

		double descuento = 0;
		double recargo = 0;
		double total = diasDeUso
				* prestamo.getVehiculo().getTipoDeCosto().getTarifaDiaria();

		if (restaDeDias < 0) {
			descuento = Math.abs(restaDeDias)
					* prestamo.getVehiculo().getTipoDeCosto()
							.getDescuentoDiario();
		} else {
			recargo = Math.abs(restaDeDias)
					* prestamo.getVehiculo().getTipoDeCosto()
							.getRecargoDiario();
		}

		double totalAPagar = total + descuento + recargo;

		System.out.println("Descuento: " + descuento);
		System.out.println("Recargo: " + recargo);
		System.out.println(">>>> INFO: Total a pagar: " + totalAPagar + " <<<<");
	}
}
