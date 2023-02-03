package paquete;

import java.util.Iterator;
import java.util.Map;

public class Scriptlets {

	/*
	 * nombreControl: valor del atributo name de los radios arrayValoresYEtiquetas:
	 * array asociativo de pares (valor, etiqueta) para el atributo value de cada
	 * radio y para el label de cada radio valorSeleccionado: valor del atributo
	 * value del elemento seleccionado, si es vac�o no se selecciona ninguno
	 */
	public static String generaBotonesRadio(String nombreControl, Map<String, String> arrayValoresYEtiquetas,
			String valorSeleccionado) {
		if (valorSeleccionado == null) {valorSeleccionado = "";}
		String salida = "";
		Iterator<String> iteradorConjuntoClaves = arrayValoresYEtiquetas.keySet().iterator();
		while (iteradorConjuntoClaves.hasNext()) {
			String clave = iteradorConjuntoClaves.next();
			String valor = arrayValoresYEtiquetas.get(clave);
			if (valorSeleccionado.equals(clave)) {
				salida += "<label>" + valor + "</label><input type=\"radio\" name=\"" + nombreControl + "\" value=\""
						+ clave + "\" checked=\"checked\" />" + "\n";
			} else {
				salida += "<label>" + valor + "</label><input type=\"radio\" name=\"" + nombreControl + "\" value=\""
						+ clave + "\" />" + "\n";
			}
		}
		return salida;
	}

	/*
	 * arrayValoresYEtiquetas: array asociativo de pares (valor, etiqueta) para el
	 * atributo value de cada checkbox y para el nombre y label de cada checkbox
	 * valoresSeleccionados: un array de valores que son los value de los ckeckbox
	 * seleccionados
	 */
	public static String generaCajasChequeo(Map<String, String> arrayValoresYEtiquetas, String[] valoresSeleccionados) {
		String salida = "";
		int numerovaloresSeleccionados = valoresSeleccionados.length; // cuantos valores seleccionados se han recibido
		if (numerovaloresSeleccionados > 0) { // hay alg�n valor seleccionado
			int contadorValoresSeleccionados = 0; // cu�ntos valores seleccionados ya se han recorrido
			Iterator<String> iteradorConjuntoClaves = arrayValoresYEtiquetas.keySet().iterator();
			while (iteradorConjuntoClaves.hasNext()) {
				String clave = iteradorConjuntoClaves.next();
				String valor = arrayValoresYEtiquetas.get(clave);
				if ((contadorValoresSeleccionados < numerovaloresSeleccionados)
						&& (valoresSeleccionados[contadorValoresSeleccionados].equals(clave))) {
					salida += "<label>" + valor + "</label><input type=\"checkbox\" name=\"" + valor + "\" value=\""
							+ clave + "\" checked=\"checked\" />" + "\n";
					contadorValoresSeleccionados++;
				} else {
					salida += "<label>" + valor + "</label><input type=\"checkbox\" name=\"" + valor + "\" value=\""
							+ clave + "\" />" + "\n";
				}
			}
		} else {
			Iterator<String> iteradorConjuntoClaves = arrayValoresYEtiquetas.keySet().iterator();
			while (iteradorConjuntoClaves.hasNext()) {
				String clave = iteradorConjuntoClaves.next();
				String valor = arrayValoresYEtiquetas.get(clave);
				salida += "<label>" + valor + "</label><input type=\"checkbox\" name=\"" + valor + "\" value=\"" + clave
						+ "\" />" + "\n";
			}
		}
		return salida;
	}

	/*
	 * arrayValoresYEtiquetas: array asociativo de pares (valor, etiqueta) para el
	 * atributo value de cada checkbox y para el nombre y label de cada checkbox
	 * valoresSeleccionados: un array de valores que son los value de los ckeckbox
	 * seleccionados
	 */
	public static String generaArrayCajasChequeo(String nombreControl, Map<String, String> arrayValoresYEtiquetas,
			String[] valoresSeleccionados) {
		String salida = "";
		int numerovaloresSeleccionados = valoresSeleccionados.length; // cu�ntos valores seleccionados se han recibido
		if (numerovaloresSeleccionados > 0) { // hay alg�n valor seleccionado
			int contadorValoresSeleccionados = 0; // cu�ntos valores seleccionados ya se han recorrido
			Iterator<String> iteradorConjuntoClaves = arrayValoresYEtiquetas.keySet().iterator();
			while (iteradorConjuntoClaves.hasNext()) {
				String clave = iteradorConjuntoClaves.next(); //respuesta
				String valor = arrayValoresYEtiquetas.get(clave); //true or false
				if ((contadorValoresSeleccionados < numerovaloresSeleccionados)
						&& (valoresSeleccionados[contadorValoresSeleccionados].equals(clave))) {
					salida += "<label>" + valor + "</label><input type=\"checkbox\" name=\"" + nombreControl
							+ "\" value=\"" + clave + "\" checked=\"checked\" />" + "\n";
					contadorValoresSeleccionados++;
				} else {
					salida += "<label>" + valor + "</label><input type=\"checkbox\" name=\"" + nombreControl
							+ "\" value=\"" + clave + "\" />" + "\n";
				}
			}
		} else {
			Iterator<String> iteradorConjuntoClaves = arrayValoresYEtiquetas.keySet().iterator();
			while (iteradorConjuntoClaves.hasNext()) {
				String clave = iteradorConjuntoClaves.next();
				String valor = arrayValoresYEtiquetas.get(clave);
				salida += "<label>" + valor + "</label><input type=\"checkbox\" name=\"" + nombreControl + "\" value=\""
						+ clave + "\" />" + "\n";
			}
		}
		return salida;
	}

	/*
	 * nombreControl: valor del atributo name del select arrayValoresYTextos: array
	 * asociativo de pares (valor, texto) para el atributo value de cada option y
	 * para el texto de cada otion valorSeleccionado: value del option seleccionado
	 */
	public static String generaSelectSimple(String nombreControl, Map<String, String> arrayValoresYTextos,
			String valorSeleccionado) {
//		String valorSeleccionado = "";
		String salida = "";
		salida += "<select name=\"" + nombreControl + "\">" + "\n";
		Iterator<String> iteradorConjuntoClaves = arrayValoresYTextos.keySet().iterator();
		while (iteradorConjuntoClaves.hasNext()) {
			String clave = iteradorConjuntoClaves.next();
			String valor = arrayValoresYTextos.get(clave);
			if (valorSeleccionado != null && valorSeleccionado.equals(clave)) {
				salida += "  <option value=\"" + clave + "\" selected=\"selected\">" + valor + "</option>" + "\n";
			} else {
				salida += "  <option value=\"" + clave + "\">" + valor + "</option>" + "\n";
			}
		}
		salida += "</select>" + "\n";
		return salida;
	}

	/*
	 * nombreControl: valor del atributo name del select arrayValoresYTextos: array
	 * asociativo de pares (valor, texto) para el atributo value de cada option y
	 * para el texto de cada otion valoresSeleccionados: un array de valores, los
	 * value de los option seleccionados opcionesVisibles: n�mero de opciones
	 * visibles del select m�ltiple
	 */
	public static String generaSelectMultiple(String nombreControl, Map<String,String> arrayValoresYTextos, String[] valoresSeleccionados, int opcionesVisibles) {
		if (valoresSeleccionados == null) {valoresSeleccionados = new String [0];}
		String salida = "";
        salida += "<select name=\"" + nombreControl + "\" multiple=\"multiple\" size=\"" + opcionesVisibles + "\">" + "\n";
        int contadorValoresSeleccionados = 0;  // cu�ntos valores seleccionados ya se han recorrido
        int numeroValoresSeleccionados = valoresSeleccionados.length;  // cu�ntos valores seleccionados se han recibido
        Iterator<String> iteradorConjuntoClaves = arrayValoresYTextos.keySet().iterator();
        while (iteradorConjuntoClaves.hasNext()) {
            String clave = iteradorConjuntoClaves.next();
            String valor = arrayValoresYTextos.get(clave);
            if ( (contadorValoresSeleccionados < numeroValoresSeleccionados) &&
                 (valoresSeleccionados[contadorValoresSeleccionados].equals(clave)) ) {
                salida += "  <option value=\"" + clave + "\" selected=\"selected\">" + valor + "</option>" + "\n";
                contadorValoresSeleccionados++;
            } else {
                salida += "  <option value=\"" + clave + "\">" + valor + "</option>" + "\n";
            }
        }
        salida += "</select>" + "\n";
        return salida;
    }
}

