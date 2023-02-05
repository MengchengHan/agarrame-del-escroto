package Generador;

import java.util.Iterator;
import java.util.Map;

public class Genera {
	public static String Pregunta(String id, String texto) {
		return "<div id= " + id + ">" + "<big>" + texto + "</big>" + "</div>";
	}

	public static String checkboxes(String nombre, Map<String, String> claveValor, String[] valoresSeleccionados) {
		String salida = "";
		int numerovaloresSeleccionados = valoresSeleccionados.length; // cuántos valores seleccionados se han recibido
		if (numerovaloresSeleccionados > 0) { // hay algún valor seleccionado
			int contadorValoresSeleccionados = 0; // cu�ntos valores seleccionados ya se han recorrido
			Iterator<String> iteradorConjuntoClaves = claveValor.keySet().iterator();
			while (iteradorConjuntoClaves.hasNext()) {
				String clave = iteradorConjuntoClaves.next(); // respuesta
				String valor = claveValor.get(clave); // true or false
				if ((contadorValoresSeleccionados < numerovaloresSeleccionados)
						&& (valoresSeleccionados[contadorValoresSeleccionados].equals(clave))) {
					salida += "<label>" + valor + "</label><input type=\"checkbox\" name=\"" + nombre
							+ "\" value=\"" + clave + "\" checked=\"checked\" />" + "\n";
					contadorValoresSeleccionados++;
				} else {
					salida += "<label>" + valor + "</label><input type=\"checkbox\" name=\"" + nombre
							+ "\" value=\"" + clave + "\" />" + "\n";
				}
			}
		} else {
			Iterator<String> iteradorConjuntoClaves = claveValor.keySet().iterator();
			while (iteradorConjuntoClaves.hasNext()) {
				String clave = iteradorConjuntoClaves.next();
				String valor = claveValor.get(clave);
				salida += "<label>" + valor + "</label><input type=\"checkbox\" name=\"" + nombre + "\" value=\"" + clave + "\" />" + "\n";
			}
		}
		return salida;
	}
}
