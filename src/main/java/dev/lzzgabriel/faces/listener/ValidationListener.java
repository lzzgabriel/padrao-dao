package dev.lzzgabriel.faces.listener;

import dev.lzzgabriel.utils.FacesMessageUtils;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;

public class ValidationListener implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}
	
	@Override
	public void beforePhase(PhaseEvent event) {
		if (FacesContext.getCurrentInstance().isValidationFailed()) {
			System.err.println("Erro de validação");
			FacesMessageUtils.add("Erro de validação", "Confira os dados inseridos", FacesMessage.SEVERITY_ERROR);
		}
	}

}
