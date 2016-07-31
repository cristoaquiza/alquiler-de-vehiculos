package ec.edu.epn.modelo.enums;

public enum EstadoDeVehiculo {
	RENTADO("rentado"), LIBRE("libre"), ENMANTENIMIENTO("en mantenimiento");

	private String nombre;

	private EstadoDeVehiculo(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

}
