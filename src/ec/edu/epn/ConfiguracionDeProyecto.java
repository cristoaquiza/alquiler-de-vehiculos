package ec.edu.epn;

public class ConfiguracionDeProyecto {
	public static void init() {
		CatalogoDeVehiculos.init();
		CatalogoDeVehiculos.imprimirCatalogo();
		//TODO: cargar todas las listas :D
	}
	
	public static void close() {
		RegistroDePrestamos.imprimirRegistro();
		CatalogoDeVehiculos.imprimirCatalogo();
	}
}
