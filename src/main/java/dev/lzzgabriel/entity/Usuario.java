package dev.lzzgabriel.entity;

import java.time.Instant;

import dev.lzzgabriel.utils.StringUtils;

public class Usuario {

	private Integer id;
	private String nome;
	private String email;
	private String endereco;
	private String numero;
	private String municipio;
	private String uf;
	private Instant momentoCadastro;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Instant getMomentoCadastro() {
		return momentoCadastro;
	}

	public void setMomentoCadastro(Instant momentoCadastro) {
		this.momentoCadastro = momentoCadastro;
	}

	public String getEnderecoNumero() {
		if (StringUtils.isNullOrEmpty(endereco))
			return null;
		if (StringUtils.isNullOrEmpty(numero))
			return null;
		return endereco + ", " + numero;
	}

	public String getCidadeEstado() {
		if (StringUtils.isNullOrEmpty(municipio))
			return null;
		if (StringUtils.isNullOrEmpty(uf))
			return null;
		return municipio + " - " + uf;
	}

}
