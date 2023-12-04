package dev.lzzgabriel.view;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import org.primefaces.event.RowEditEvent;

import dev.lzzgabriel.dao.UsuarioDAO;
import dev.lzzgabriel.entity.Usuario;
import dev.lzzgabriel.utils.FacesMessageUtils;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
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
			load();
			selectedUsuario = new Usuario();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void load() throws Exception {
		usuarios = dao.findAll();
	}

	public void openNew() {
		if (selectedUsuario.getId() != null)
			selectedUsuario = new Usuario();
	}

	public void saveUsuario() {
		try {
			dao.save(selectedUsuario);
			FacesMessageUtils.add("Sucesso", "Usuário inserido com êxito", FacesMessage.SEVERITY_INFO);
			load();
		} catch (Exception e) {
			FacesMessageUtils.add("Falha ao salvar", e.getMessage(), FacesMessage.SEVERITY_ERROR);
			e.printStackTrace();
		}
	}

	public void onRowEdit(RowEditEvent<Usuario> event) {
		try {
			dao.edit(event.getObject());
			FacesMessageUtils.add("Sucesso", "Usuário alterado com êxito", FacesMessage.SEVERITY_INFO);
		} catch (Exception e) {
			FacesMessageUtils.add("Falha ao alterar", e.getMessage(), FacesMessage.SEVERITY_ERROR);
			e.printStackTrace();
		}
	}
	
	public void deleteUsuario(Usuario usuario) {
		try {
			dao.delete(usuario);
			FacesMessageUtils.add("Sucesso", "Usuário excluído com êxito", FacesMessage.SEVERITY_INFO);
		} catch (Exception e) {
			FacesMessageUtils.add("Falha ao excluir", e.getMessage(), FacesMessage.SEVERITY_ERROR);
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
