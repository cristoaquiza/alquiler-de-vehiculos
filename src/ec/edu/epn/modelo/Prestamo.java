package ec.edu.epn.modelo;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

public class Prestamo implements Serializable {
	private Vehiculo vehiculo;
	private Date fechaDeRenta;
	private Date fechaPosibleDeRetorno;

	public Prestamo(Vehiculo vehiculo, Date fechaDeRenta,
			Date fechaPosibleDeRetorno) {
		super();
		this.vehiculo = vehiculo;
		this.fechaDeRenta = fechaDeRenta;
		this.fechaPosibleDeRetorno = fechaPosibleDeRetorno;
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
}
