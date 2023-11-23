package dev.lzzgabriel.dao;

import java.util.List;

import dev.lzzgabriel.entity.Usuario;

public interface UsuarioDAO {
	
	Usuario findById(Integer id) throws Exception;

	List<Usuario> findAll() throws Exception;

	void save(Usuario usuario) throws Exception;

	void edit(Usuario usuario) throws Exception;

	void delete(Usuario usuario) throws Exception;
}
