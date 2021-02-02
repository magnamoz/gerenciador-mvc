package com.example.desafio.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "funcionario")
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Campo obrigatório")
	private String cargo;

	@NotNull(message = "Campo obrigatório")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date inicioWa;

	@NotBlank(message = "Campo obrigatório")
	private String matricula;

	@NotBlank(message = "Campo obrigatório")
	private String nome;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date terminoWa;

	//@ManyToOne by default is FetchType.EAGER strategy 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gft_id")
	private Gft gft;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vaga_id")
	private Vaga vaga;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "funcionario_tecnologia", 
		joinColumns = @JoinColumn(name = "funcionario_id"), 
		inverseJoinColumns = @JoinColumn(name = "tecnologia_id"))
	private List<Tecnologia> tecnologias = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Date getInicioWa() {
		return inicioWa;
	}

	public void setInicioWa(Date inicioWa) {
		this.inicioWa = inicioWa;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getTerminoWa() {
		return terminoWa;
	}

	public void setTerminoWa(Date terminoWa) {
		this.terminoWa = terminoWa;
	}

	public Gft getGft() {
		return gft;
	}

	public void setGft(Gft gft) {
		this.gft = gft;
	}

	public Vaga getVaga() {
		return vaga;
	}

	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}

	public List<Tecnologia> getTecnologias() {
		return tecnologias;
	}

	public void setTecnologias(List<Tecnologia> tecnologias) {
		this.tecnologias = tecnologias;
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", cargo=" + cargo + ", inicioWa=" + inicioWa + ", matricula=" + matricula
				+ ", nome=" + nome + ", terminoWa=" + terminoWa + ", gft=" + gft + ", vaga=" + vaga + ", tecnologias="
				+ tecnologias + "]";
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
		Funcionario other = (Funcionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}