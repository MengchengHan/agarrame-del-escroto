package Generador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

import DataTypes.Respuesta;

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

	public static String cajaRespuestas (String pregunta, ArrayList<Respuesta> respuestas, String [] respondidas){
		ArrayList<String>out = new ArrayList<String>();
		if(respondidas != null) {
			ArrayList<Integer> respondidasLista = new ArrayList<Integer>();
			for(String str : respondidas) {
				respondidasLista.add(Integer.parseInt(str));
			}
			
			for (Respuesta respuesta : respuestas) { 
				String clase = respuesta.isValida() ? "valida" : "noValida";
				if (respondidasLista.contains(respuesta.getId())) {
					clase = "seleccionada" + clase;
				}
				out.add("<p class=" + clase + ">" + respuesta.getEnunciado() + "</p>" + "\n");
			}
			out.add(0, "<div class=pregResp><div class='pregunta'><p>" + pregunta + "<p></div>");
			out.add(out.size(), "</div>");
		}
		return String.join("", out);
	}
	
}
