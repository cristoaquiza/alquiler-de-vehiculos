package ec.edu.epn;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ec.edu.epn.modelo.CatalogoDeVehiculos;
import ec.edu.epn.modelo.Prestamo;
import ec.edu.epn.modelo.RegistroDePrestamos;
import ec.edu.epn.modelo.Vehiculo;
import ec.edu.epn.modelo.enums.EstadoDeVehiculo;

public class Main {

	public static void main(String[] args) {
		try {
			CatalogoDeVehiculos catalogoDeVehiculos = new CatalogoDeVehiculos();
			RegistroDePrestamos registroDePrestamos = new RegistroDePrestamos();
			catalogoDeVehiculos.imprimirCatalogoDeVehiculos();
			registroDePrestamos.imprimirRegistroDePrestamos();

			System.out.print("\nIngrese información: ");
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
				Vehiculo vehiculo = catalogoDeVehiculos
						.buscarVehiculoPorPlaca(placa);

				if (vehiculo.getEstado().getNombre()
						.compareTo(EstadoDeVehiculo.RENTADO.getNombre()) == 0) {
					System.out
							.println(">ERROR: El auto ya se encuentra rentado");
				} else {
					vehiculo.setEstado(EstadoDeVehiculo.RENTADO);
					catalogoDeVehiculos.actualizarCatalogoDeVehiculos(vehiculo);

					int numeroDeDias = Integer.parseInt(dias);
					Calendar fechaDeRenta = Calendar.getInstance();
					Calendar fechaPosibleDeRetorno = Calendar.getInstance();
					fechaPosibleDeRetorno.add(Calendar.DAY_OF_MONTH,
							numeroDeDias);
					Prestamo prestamo = new Prestamo(vehiculo,
							fechaDeRenta.getTime(),
							fechaPosibleDeRetorno.getTime());
					registroDePrestamos.registrarPrestamo(prestamo);
					System.out
							.println(">>>> INFO: Préstamo registrado con éxito <<<<");
				}
				break;
			case "R":
				Calendar fechaDeRetorno = Calendar.getInstance();
				fechaDeRetorno.set(Calendar.DAY_OF_MONTH, 2);
				fechaDeRetorno.set(Calendar.MONTH, 7);
				fechaDeRetorno.set(Calendar.YEAR, 2016);
				System.out.println("Fecha de retorno seteada: "
						+ fechaDeRetorno.getTime());

				Prestamo prestamo = registroDePrestamos
						.buscarPrestamoPorPlaca(placa);
				prestamo.getVehiculo().setEstado(EstadoDeVehiculo.LIBRE);
				catalogoDeVehiculos.actualizarCatalogoDeVehiculos(prestamo
						.getVehiculo());

				registroDePrestamos.generarPago(fechaDeRetorno, prestamo);

				registroDePrestamos.eliminarPrestamo(prestamo);
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

		patron = Pattern.compile("[0-9]*");
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
}
