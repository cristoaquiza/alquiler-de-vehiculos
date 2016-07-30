package ec.edu.epn;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		iniciarProyecto();

		System.out.print("Ingrese información: ");
		System.out.flush();
		Scanner entrada = new Scanner(System.in);
		String comando = entrada.next();
		String placa = entrada.next();
		String dias = "";
		if (comando.compareTo("P") == 0) {
			dias = entrada.next();
		}
		switch (comando) {
		case "P":
			prestar(placa, dias);
			break;
		case "R":
			retornar(placa);
			break;
		default:
			break;
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
			if(vehiculo.getEstado().getNombre().compareTo(EstadoDeVehiculo.RENTADO.getNombre()) == 0) {
				System.out.println("ERROR: El auto ya se encuentra rentado");
			} else {
				vehiculo.setEstado(EstadoDeVehiculo.RENTADO);
				Calendar fechaActual = Calendar.getInstance();
				Calendar fechaPosibleDeRetorno = Calendar.getInstance();
				fechaPosibleDeRetorno.add(Calendar.DAY_OF_MONTH, dias);
				DateFormat formato = DateFormat.getDateInstance(DateFormat.MEDIUM);//
				System.out.println(formato.format(fechaPosibleDeRetorno.getTime()));//
				RegistroDePrestamos.registrarPrestamo(new Prestamo(vehiculo, fechaActual.getTime(), fechaPosibleDeRetorno.getTime()));
				RegistroDePrestamos.imprimirRegistro();				
			}
		} else {
			System.out.println("ERROR: El vehículo no se encuentra en el catalogo");
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
			
			//TODO:Resta esta mal, si fuera de diferentes meses falla
			
			int restaDeDias = diaDeRetorno - diaPosibleDeRetorno;
			
			prestamo.getVehiculo().setEstado(EstadoDeVehiculo.LIBRE);			
			RegistroDeFacturas.generarFactura(restaDeDias, prestamo);
		} else {
			System.out.println("ERROR: No existe un vehículo rentado con esa placa");
		}
	}
}
