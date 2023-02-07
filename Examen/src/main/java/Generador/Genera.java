package Generador;

import java.util.Iterator;
import java.util.Map;

public class Genera {
	public static String Pregunta(String id, String texto) {
		return "<div id= " + id + ">" + "<big>" + texto + "</big>" + "</div>";
	}

	public static String checkboxes(String nombre, Map<Integer, String> claveValor, Integer [] valoresSeleccionados) {
		String salida = "";
		if (valoresSeleccionados != null && valoresSeleccionados.length > 0) { // hay algún valor seleccionado
			int contadorValoresSeleccionados = 0; // cu�ntos valores seleccionados ya se han recorrido
			Iterator<Integer> iteradorConjuntoClaves = claveValor.keySet().iterator();
			while (iteradorConjuntoClaves.hasNext()) {
				Integer clave = iteradorConjuntoClaves.next(); // respuesta
				String valor = claveValor.get(clave); // true or false
				if (((contadorValoresSeleccionados < valoresSeleccionados.length) && (valoresSeleccionados[contadorValoresSeleccionados] == (clave)))) {
					salida += "<input type=\"checkbox\" name=\"" + nombre + "\" value=\"" + clave + "\" checked=\"checked\" />" + "<label>" + valor + "</label>" + "<br>";
					contadorValoresSeleccionados++;
				} else {
					salida += "<input type=\"checkbox\" name=\"" + nombre + "\" value=\"" + clave + "\" />" + "<label>" + valor + "</label>" + "<br>";
				}
			}
		} else {
			Iterator<Integer> iteradorConjuntoClaves = claveValor.keySet().iterator();
			while (iteradorConjuntoClaves.hasNext()) {
				Integer clave = iteradorConjuntoClaves.next();
				String valor = claveValor.get(clave);
				salida += "<input type=\"checkbox\" name=\"" + nombre + "\" value=\"" + clave + "\" />" + "<label>" + valor + "</label>" + "<br>";
			}
		}
		return salida;
	}
}
