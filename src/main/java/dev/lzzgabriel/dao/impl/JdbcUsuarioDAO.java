package dev.lzzgabriel.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import dev.lzzgabriel.application.DataSource;
import dev.lzzgabriel.dao.UsuarioDAO;
import dev.lzzgabriel.entity.Usuario;
import dev.lzzgabriel.utils.StringUtils;
import jakarta.enterprise.inject.Model;

@Model
public class JdbcUsuarioDAO implements UsuarioDAO {

	@Override
	public Usuario find(Integer id) throws Exception {
		try (Connection conn = DataSource.getInstance().openConnection();
				PreparedStatement statement = conn.prepareStatement("SELECT * FROM usuario WHERE id = ?");) {

			statement.setInt(1, id);

			ResultSet res = statement.executeQuery();

			if (res.next()) {
				return fetch(res);
			} else
				throw new NoSuchElementException();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Usuario> findAll() throws Exception {
		try (Connection conn = DataSource.getInstance().openConnection();
				PreparedStatement statement = conn.prepareStatement("SELECT * FROM usuario")) {

			ResultSet res = statement.executeQuery();

			var list = new ArrayList<Usuario>();
			while (res.next()) {
				list.add(fetch(res));
			}

			return list;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void save(Usuario usuario) throws Exception {
		try (Connection conn = DataSource.getInstance().openConnection();
				CallableStatement statement = conn.prepareCall(
						"INSERT INTO usuario (nome, email, endereco, numero, municipio, uf) values ( ? , ? , ? , ? , ? , ? )")) {
			int param = 1;
			
			statement.setString(param++, usuario.getNome());
			statement.setString(param++, usuario.getEmail());
			
			statement.setString(param++, StringUtils.isNullOrEmpty(usuario.getEndereco()) ? null : usuario.getEndereco());
			statement.setString(param++, usuario.getNumero());
			statement.setString(param++, usuario.getMunicipio());
			statement.setString(param++, usuario.getUf());
			
			conn.commit();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void edit(Usuario usuario) throws Exception {
		try (Connection conn = DataSource.getInstance().openConnection();
				CallableStatement statement = conn.prepareCall(
						"UPDATE usuario SET nome = ?, email = ?, endereco = ?, numero = ?, municipio = ?, uf = ? where id = ?")) {
			int param = 1;
			
			statement.setString(param++, usuario.getNome());
			statement.setString(param++, usuario.getEmail());
			
			statement.setString(param++, StringUtils.isNullOrEmpty(usuario.getEndereco()) ? null : usuario.getEndereco());
			statement.setString(param++, usuario.getNumero());
			statement.setString(param++, usuario.getMunicipio());
			statement.setString(param++, usuario.getUf());
			
			int rowsAffected = statement.executeUpdate();
			
			if (rowsAffected == 0) {
				throw new Exception("Nenhum dado alterado");
			} else if (rowsAffected > 1) {
				conn.rollback();
				throw new Exception("Inconsistência de dados");
			}
			
			conn.commit();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void delete(Usuario usuario) throws Exception {
		try (Connection conn = DataSource.getInstance().openConnection();
				CallableStatement statement = conn.prepareCall("DELETE FROM usuario WHERE id = ?")) {
			int param = 1;
			
			statement.setInt(param, usuario.getId());
			
			int rowsAffected = statement.executeUpdate();
			
			if (rowsAffected != 1) {
				conn.rollback();
				throw new Exception("Erro de operação.");
			}
			
			conn.commit();
		} catch (Exception e) {
			throw e;
		}
	}

	private Usuario fetch(ResultSet res) throws SQLException {
		var usuario = new Usuario();

		usuario.setId(res.getInt("id"));
		usuario.setNome(res.getString("nome"));
		usuario.setEmail(res.getString("email"));
		usuario.setEndereco(res.getString("endereco"));
		usuario.setNumero(res.getString("numero"));
		usuario.setMunicipio(res.getString("municipio"));
		usuario.setUf(res.getString("uf"));
		usuario.setMomentoCadastro(res.getTimestamp("momento_cadastro").toInstant());

		return usuario;
	}

}
