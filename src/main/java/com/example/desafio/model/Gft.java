package com.example.desafio.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "gft")
public class Gft {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Campo obrigatório")
	private String cep;

	@NotBlank(message = "Campo obrigatório")
	private String endereco;

	@NotBlank(message = "Campo obrigatório")
	private String estado;

	@NotBlank(message = "Campo obrigatório")
	private String nome;

	@NotBlank(message = "Campo obrigatório")
	private String telefone;


	@NotBlank(message = "Campo obrigatório")
	@OneToMany(mappedBy = "gft", cascade = CascadeType.ALL)
	private List<Funcionario> listaFuncionarios = new ArrayList<>();
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

//	@Override
//	public String toString() {
//		return "Gft [id=" + id + ", cep=" + cep + ", endereco=" + endereco + ", estado=" + estado + ", nome=" + nome
//				+ ", telefone=" + telefone + ", listaFuncionarios=" + listaFuncionarios + "]";
//	}

}