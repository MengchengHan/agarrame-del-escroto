package Generador;

import java.util.Iterator;
import java.util.Map;

public class Genera {

	public static String checkboxes(String nombre, Map<Integer, String> claveValor, int [] checked) {
		String salida = "";
		if (checked != null && checked.length > 0) { // hay algún valor seleccionado
			int count = 0; // cu�ntos valores seleccionados ya se han recorrido
			Iterator<Integer> it = claveValor.keySet().iterator();
			while (it.hasNext()) {
				int clave = it.next();
				String valor = claveValor.get(clave); 
				if (((count < checked.length) && (checked[count] == clave))) {
					salida += "<div class='inputsLabels'><input type=\"checkbox\" name=\"" + nombre + "\" value=\"" + clave + "\" checked=\"checked\" />" + "<label>" + valor + "</label></div>" + "<br>" ;
					count++;
				} else {
					salida += "<div class='inputsLabels'><input type=\"checkbox\" name=\"" + nombre + "\" value=\"" + clave + "\" />" + "<label>" + valor + "</label></div>" + "<br>";
				}
			}
		} else {
			Iterator<Integer> iteradorConjuntoClaves = claveValor.keySet().iterator();
			while (iteradorConjuntoClaves.hasNext()) {
				int clave = iteradorConjuntoClaves.next();
				String valor = claveValor.get(clave);
				salida += "<div class='inputsLabels'><input type=\"checkbox\" name=\"" + nombre + "\" value=\"" + clave + "\" />" + "<label>" + valor + "</label></div>" + "<br>";
			}
		}
		return salida;
	}
}
