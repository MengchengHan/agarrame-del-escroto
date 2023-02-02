package paquete;

import java.util.LinkedHashMap;

public class Datos {
	@SuppressWarnings("serial")
	public static LinkedHashMap<String, String> Paises = new LinkedHashMap<String, String>() {
		{
			put("ES", "España");
			put("FR", "Francia");
			put("NK", "Corea del Norte");
			put("CN", "China");
			put("SK", "Corea del Sur");
			put("UK", "Reino unido");
			put("US", "Estados Unidos");
			put("DE", "Alemania");
		}
	};

	@SuppressWarnings("serial")
	public static LinkedHashMap<String, String> Colores = new LinkedHashMap<String, String>() {
		{
			put("red", "Rojo");
			put("blue", "Azul");
			put("yellow", "Amarillo");
			put("green", "Verde");
			put("black", "Negro");
			put("white", "Blanco");
		}
	};

	@SuppressWarnings("serial")
	public static LinkedHashMap<String, String> Sexo = new LinkedHashMap<String, String>() {
		{
			put("Hombre", "Hombre");
			put("Mujer", "Mujer");
		}
	};
	
	@SuppressWarnings("serial")
	public static LinkedHashMap<String, String> Credenciales = new LinkedHashMap<String, String>() {
		{
			put("admin", "admin");
			put("root", "root");
		}
	};
}
