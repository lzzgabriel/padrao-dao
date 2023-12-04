package dev.lzzgabriel.utils;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.application.FacesMessage.Severity;
import jakarta.faces.context.FacesContext;

public class FacesMessageUtils {

	public static void add(String summary, String detail, Severity severity) {
		var message = new FacesMessage(severity, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
