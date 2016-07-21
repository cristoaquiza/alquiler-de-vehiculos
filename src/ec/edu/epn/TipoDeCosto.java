package ec.edu.epn;

public enum TipoDeCosto {
	PRIMERACLASE("Primera clase", 50.45, 25.00, 15.00), SEGUNDACLASE(
			"Segunda clase", 40.45, 20.00, 10.00), TERCERACLASE(
			"Tercera clase", 30.45, 15.00, 5.00);

	private String nombre;
	private double tarifaDiaria;
	private double recargoDiario;
	private double descuentoDiario;

	private TipoDeCosto(String nombre, double tarifaDiaria,
			double recargoDiario, double descuentoDiario) {
		this.nombre = nombre;
		this.tarifaDiaria = tarifaDiaria;
		this.recargoDiario = recargoDiario;
		this.descuentoDiario = descuentoDiario;
	}

	public String getNombre() {
		return nombre;
	}

	public double getTarifaDiaria() {
		return tarifaDiaria;
	}

	public double getRecargoDiario() {
		return recargoDiario;
	}

	public double getDescuentoDiario() {
		return descuentoDiario;
	}
}
