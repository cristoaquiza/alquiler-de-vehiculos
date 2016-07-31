package ec.edu.epn.modelo;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class Prestamo implements Serializable {
	private Vehiculo vehiculo;
	private Date fechaDeRenta;
	private Date fechaPosibleDeRetorno;
	private double total;
	private double recargo;
	private double descuento;
	private double totalAPagar;
	private List<Vehiculo> catalogoDeVehiculos;
	private List<Prestamo> prestamos;

	public Prestamo(Vehiculo vehiculo, Date fechaDeRenta,
			Date fechaPosibleDeRetorno) {
		super();
		this.vehiculo = vehiculo;
		this.fechaDeRenta = fechaDeRenta;
		this.fechaPosibleDeRetorno = fechaPosibleDeRetorno;
		this.catalogoDeVehiculos = cargarVehiculos();
		this.prestamos = cargarPrestamos();
	}

	private List<Prestamo> cargarPrestamos() {
		// TODO Auto-generated method stub
		return null;
	}

	private List<Vehiculo> cargarVehiculos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		DateFormat formato = DateFormat.getDateInstance(DateFormat.MEDIUM);
		return vehiculo + "\n FECHA DE RENTA: " + formato.format(fechaDeRenta)
				+ "\n FECHA POSIBLE DE RETORNO: "
				+ formato.format(fechaPosibleDeRetorno) + "\n";
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

	public Date getFechaPosibleDeRetorno() {
		return fechaPosibleDeRetorno;
	}

	public void setFechaPosibleDeRetorno(Date fechaPosibleDeRetorno) {
		this.fechaPosibleDeRetorno = fechaPosibleDeRetorno;
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
