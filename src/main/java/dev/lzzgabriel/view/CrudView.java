package dev.lzzgabriel.view;

import java.io.Serializable;

import dev.lzzgabriel.dao.UsuarioDAO;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class CrudView implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioDAO dao;
	
	@PostConstruct
	public void init() {}

}
