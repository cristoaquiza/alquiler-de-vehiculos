package ec.edu.epn.modelo;

import java.io.Serializable;
import java.util.List;

import ec.edu.epn.modelo.enums.EstadoDeVehiculo;
import ec.edu.epn.modelo.enums.TipoDeCosto;
import ec.edu.epn.utils.ServiciosDeArchivo;

public class Vehiculo implements Serializable {
	private String marca;
	private String color;
	private String placa;
	private EstadoDeVehiculo estadoDeVehiculo;
	private TipoDeCosto tipoDeCosto;

	public Vehiculo(String marca, String color, String placa,
			EstadoDeVehiculo estadoDeVehiculo, TipoDeCosto tipoDeCosto) {
		super();
		this.marca = marca;
		this.color = color;
		this.placa = placa;
		this.estadoDeVehiculo = estadoDeVehiculo;
		this.tipoDeCosto = tipoDeCosto;
	}

	@Override
	public String toString() {
		return "-INFORMACION DEL VEHICULO: " + "Marca: " + marca + ", Color: "
				+ color + ", Placa: " + placa + ", Estado: "
				+ estadoDeVehiculo.getNombre() + ", Tipo de Costo: "
				+ tipoDeCosto.getNombre();
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public EstadoDeVehiculo getEstado() {
		return estadoDeVehiculo;
	}

	public void setEstado(EstadoDeVehiculo estadoDeVehiculo) {
		this.estadoDeVehiculo = estadoDeVehiculo;
	}

	public TipoDeCosto getTipoDeCosto() {
		return tipoDeCosto;
	}

	public void setTipoDeCosto(TipoDeCosto tipoDeCosto) {
		this.tipoDeCosto = tipoDeCosto;
	}
}
