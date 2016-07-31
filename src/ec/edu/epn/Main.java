package ec.edu.epn;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ec.edu.epn.modelo.Prestamo;
import ec.edu.epn.modelo.Vehiculo;
import ec.edu.epn.modelo.enums.EstadoDeVehiculo;

public class Main {

	public static void main(String[] args) {
		iniciarProyecto();

		try {
			System.out.print("Ingrese información: ");
			System.out.flush();
			Scanner entrada = new Scanner(System.in);
			String accion = entrada.next();
			String placa = entrada.next();
			String dias = "";
			if (accion.compareTo("P") == 0) {
				dias = entrada.next();
			}

			while (comprobarIngreso(accion, placa, dias) == false) {
				entrada = new Scanner(System.in);
				accion = entrada.next();
				placa = entrada.next();
				dias = "";
				if (accion.compareTo("P") == 0) {
					dias = entrada.next();
				}
			}

			switch (accion) {
			case "P":
				Vehiculo vehiculo = Vehiculo.buscarVehiculoPorPlaca(placa);
				Prestamo prestamo = new Prestamo(vehiculo, fechaDeRenta,
						fechaPosibleDeRetorno);
				break;
			case "R":
				retornar(placa);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private static boolean comprobarIngreso(String accion, String placa,
			String dias) throws Exception {

		boolean accionOk = false, placaOk = false, diasOk = false;

		Pattern patron = Pattern.compile("[PR]");
		Matcher coincidencia = patron.matcher(accion);
		if (coincidencia.find()) {
			accionOk = true;
		} else {
			throw new Exception(">ERROR: La letra " + accion
					+ " no es válida como comando, solo se acepta P o R");
		}

		patron = Pattern.compile("[A-Z][A-Z][A-Z]-[0-9][0-9][0-9]");
		coincidencia = patron.matcher(placa);
		if (coincidencia.find()) {
			placaOk = true;
		} else {
			throw new Exception(">ERROR: La placa " + placa + " no es válida");
		}

		patron = Pattern.compile("[0-9]");
		coincidencia = patron.matcher(dias);
		if (coincidencia.find()) {
			diasOk = true;
		} else {
			throw new Exception(">ERROR: El número de días " + dias
					+ " no son válidos");
		}

		if (accionOk && placaOk && diasOk) {
			return true;
		} else {
			return false;
		}
	}

	private static void iniciarProyecto() {
		CatalogoDeVehiculos.init();
		RegistroDePrestamos.init();
	}

	private static void prestar(String placa, String d) {
		int dias = Integer.parseInt(d);
		Vehiculo vehiculo = CatalogoDeVehiculos.buscarVehiculoPorPlaca(placa);
		if (vehiculo != null) {
			if (vehiculo.getEstado().getNombre()
					.compareTo(EstadoDeVehiculo.RENTADO.getNombre()) == 0) {
				System.out.println("ERROR: El auto ya se encuentra rentado");
			} else {
				vehiculo.setEstado(EstadoDeVehiculo.RENTADO);
				Calendar fechaActual = Calendar.getInstance();
				Calendar fechaPosibleDeRetorno = Calendar.getInstance();
				fechaPosibleDeRetorno.add(Calendar.DAY_OF_MONTH, dias);
				DateFormat formato = DateFormat
						.getDateInstance(DateFormat.MEDIUM);//
				System.out.println(formato.format(fechaPosibleDeRetorno
						.getTime()));//
				RegistroDePrestamos
						.registrarPrestamo(new Prestamo(vehiculo, fechaActual
								.getTime(), fechaPosibleDeRetorno.getTime()));
				RegistroDePrestamos.imprimirRegistro();
			}
		} else {
			System.out
					.println("ERROR: El vehículo no se encuentra en el catalogo");
		}
	}

	private static void retornar(String placa) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 27);
		calendar.set(Calendar.MONTH, 6);
		calendar.set(Calendar.YEAR, 2016);
		Date fechaDeRetorno = calendar.getTime();

		Prestamo prestamo = RegistroDePrestamos.buscarPrestamoPorPlaca(placa);
		if (prestamo != null) {
			System.out.println(fechaDeRetorno);
			System.out.println(prestamo);

			Calendar retorno = Calendar.getInstance();
			retorno.setTime(fechaDeRetorno);
			int diaDeRetorno = retorno.get(Calendar.DAY_OF_MONTH);

			Calendar retornoPosible = Calendar.getInstance();
			retornoPosible.setTime(prestamo.getFechaPosibleDeRetorno());
			int diaPosibleDeRetorno = retornoPosible.get(Calendar.DAY_OF_MONTH);

			// TODO:Resta esta mal, si fuera de diferentes meses falla

			int restaDeDias = diaDeRetorno - diaPosibleDeRetorno;

			prestamo.getVehiculo().setEstado(EstadoDeVehiculo.LIBRE);
			RegistroDeFacturas.generarFactura(restaDeDias, prestamo);
		} else {
			System.out
					.println("ERROR: No existe un vehículo rentado con esa placa");
		}
	}
}
