package ec.edu.epn;

import java.util.Calendar;

import ec.edu.epn.modelo.Prestamo;

public class RegistroDeFacturas {

	public static void generarFactura(int restaDeDias, Prestamo prestamo) {
		CatalogoDeVehiculos.actualizarVehiculo(prestamo.getVehiculo());
		RegistroDePrestamos.eliminarPrestamo(prestamo);
		
		int diasX = Math.abs(restaDeDias);
		double descuento = 0;
		double recargo = 0;
		
		Calendar fechaDeRenta = Calendar.getInstance();
		fechaDeRenta.setTime(prestamo.getFechaDeRenta());
		int diaDeRenta = fechaDeRenta.get(Calendar.DAY_OF_MONTH);
		
		Calendar fechaPosibleDeRetorno = Calendar.getInstance();
		fechaPosibleDeRetorno.setTime(prestamo.getFechaPosibleDeRetorno());
		int diaDePosibleRetorno = fechaPosibleDeRetorno.get(Calendar.DAY_OF_MONTH);
		
		int diasDeUso = Math.abs(diaDePosibleRetorno - diaDeRenta);
		
		double total = diasDeUso * prestamo.getVehiculo().getTipoDeCosto().getTarifaDiaria();
		
		if(restaDeDias < 0) {
			descuento = diasX * prestamo.getVehiculo().getTipoDeCosto().getDescuentoDiario();
		} else {
			recargo = diasX * prestamo.getVehiculo().getTipoDeCosto().getRecargoDiario();
		}
		
		double totalAPagar = total + descuento + recargo;
		System.out.println("Descuento: " + descuento);
		System.out.println("Recargo: " + recargo);
		System.out.println("INFO: Total a pagar: " + totalAPagar);
	}
}
