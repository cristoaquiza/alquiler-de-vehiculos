package ec.edu.epn;

public enum EstadoDeVehiculo {
	RENTADO("r", "rentado"), LIBRE("l", "libre"), ENMANTENIMIENTO("m", "en mantenimiento");
	
	private String codigo;
	private String nombre;
	
	private EstadoDeVehiculo(String codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}
	public String getCodigo() {
		return codigo;
	}
	public String getNombre() {
		return nombre;
	}
	
	
}
