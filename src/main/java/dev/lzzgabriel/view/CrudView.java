package dev.lzzgabriel.view;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import dev.lzzgabriel.dao.UsuarioDAO;
import dev.lzzgabriel.entity.Usuario;
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

	private List<Usuario> usuarios;

	private Usuario selectedUsuario;

	@PostConstruct
	public void init() {
		try {
			usuarios = dao.findAll();
			selectedUsuario = new Usuario();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void openNew() {
		if (selectedUsuario.getId() != null)
			selectedUsuario = new Usuario();
	}

	public void saveUsuario() {
		try {
			if (selectedUsuario.getId() == null) {
				dao.save(selectedUsuario);
			} else {
				dao.edit(selectedUsuario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getCurrentTimestamp() {
		return Instant.now().toString();
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getSelectedUsuario() {
		return selectedUsuario;
	}

	public void setSelectedUsuario(Usuario selectedUsuario) {
		this.selectedUsuario = selectedUsuario;
	}

}
