package ec.edu.epn;

import java.io.Serializable;
import java.util.Date;

public class Prestamo implements Serializable {
	private Vehiculo vehiculo;
	private Date fechaDeRenta;
	private Date FechaDeRetorno;
	private double total;
	private double recargo;
	private double descuento;
	private double totalAPagar;

	public Prestamo(Vehiculo vehiculo, Date fechaDeRenta, Date fechaDeRetorno) {
		super();
		this.vehiculo = vehiculo;
		this.fechaDeRenta = fechaDeRenta;
		FechaDeRetorno = fechaDeRetorno;
	}

	@Override
	public String toString() {
		return vehiculo + "\t" + fechaDeRenta;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Date getFechaDeRenta() {
		return fechaDeRenta;
	}

	public void setFechaDeRenta(Date fechaDeRenta) {
		this.fechaDeRenta = fechaDeRenta;
	}

	public Date getFechaDeRetorno() {
		return FechaDeRetorno;
	}

	public void setFechaDeRetorno(Date fechaDeRetorno) {
		FechaDeRetorno = fechaDeRetorno;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getRecargo() {
		return recargo;
	}

	public void setRecargo(double recargo) {
		this.recargo = recargo;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public double getTotalAPagar() {
		return totalAPagar;
	}

	public void setTotalAPagar(double totalAPagar) {
		this.totalAPagar = totalAPagar;
	}

}
