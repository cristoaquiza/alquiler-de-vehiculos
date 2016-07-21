package ec.edu.epn;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		ConfiguracionDeProyecto.init();
		
		System.out.print("Ingrese información: ");
		System.out.flush();
		Scanner entrada = new Scanner(System.in);
		String comando = entrada.next();
		String placa = entrada.next();
		String dias = entrada.next();
		
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

	private static void retornar(String placa) {
		System.out.println("se ingreso a retornar");
		Calendar fechaDeRetorno = Calendar.getInstance();
		
	}

	private static void prestar(String placa, String d) {
		int dias = Integer.parseInt(d);
		Vehiculo vehiculo = CatalogoDeVehiculos.buscarVehiculoPorPlaca(placa);
		if (vehiculo != null) {
			vehiculo.setEstado(EstadoDeVehiculo.RENTADO);
			Calendar fechaActual = Calendar.getInstance();
			Calendar fechaPosibleDeRetorno = Calendar.getInstance();
			fechaPosibleDeRetorno.add(Calendar.DAY_OF_MONTH, dias);
			//TODO:poner dentro de registrar prestamo la funcion que cambie el estado del vehiculo que se vaya a prestar
			RegistroDePrestamos.registrarPrestamo(new Prestamo(vehiculo,
					fechaActual.getTime(), fechaPosibleDeRetorno.getTime()));
			ConfiguracionDeProyecto.close();
		} else {
			System.out.println("no se encontro el vehiculo con esa placa");
		}
	}

}
