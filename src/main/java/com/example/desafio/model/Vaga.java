package com.example.desafio.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "vaga")
public class Vaga {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Campo obrigatório")
	private LocalDate aberturaVaga = LocalDate.now();

	@NotBlank(message = "Campo obrigatório")
	private String codigoVaga;

	@NotBlank(message = "Campo obrigatório")
	private String descricaoVaga;

	@NotBlank(message = "Campo obrigatório")
	private String projeto;

	@NotNull(message = "Campo obrigatório")
	private int qtdVaga;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "vaga_tecnologia", 
		joinColumns = @JoinColumn(name = "vaga_id"), 
		inverseJoinColumns = @JoinColumn(name = "tecnologia_id"))
	private List<Tecnologia> tecnologias  = new ArrayList<>();

	//@NotBlank(message = "Campo obrigatório")
	@OneToMany(mappedBy = "vaga", cascade = CascadeType.ALL)
	private List<Funcionario> listaFuncionarios = new ArrayList<>();
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getAberturaVaga() {
		return aberturaVaga;
	}

	public void setAberturaVaga(LocalDate aberturaVaga) {
		this.aberturaVaga = aberturaVaga;
	}

	public String getCodigoVaga() {
		return codigoVaga;
	}

	public void setCodigoVaga(String codigoVaga) {
		this.codigoVaga = codigoVaga;
	}

	public String getDescricaoVaga() {
		return descricaoVaga;
	}

	public void setDescricaoVaga(String descricaoVaga) {
		this.descricaoVaga = descricaoVaga;
	}

	public String getProjeto() {
		return projeto;
	}

	public void setProjeto(String projeto) {
		this.projeto = projeto;
	}

	public int getQtdVaga() {
		return qtdVaga;
	}

	public void setQtdVaga(int qtdVaga) {
		this.qtdVaga = qtdVaga;
	}
	
	public List<Tecnologia> getTecnologias() {
		return tecnologias;
	}

	public void setTecnologias(List<Tecnologia> tecnologias) {
		this.tecnologias = tecnologias;
	}
	
	

	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vaga other = (Vaga) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vaga [id=" + id + ", aberturaVaga=" + aberturaVaga + ", codigoVaga=" + codigoVaga + ", descricaoVaga="
				+ descricaoVaga + ", projeto=" + projeto + ", qtdVaga=" + qtdVaga + ", tecnologias=" + tecnologias
				+ "]";
	}

}