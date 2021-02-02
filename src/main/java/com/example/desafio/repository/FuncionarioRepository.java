package com.example.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.desafio.model.Funcionario;


public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	public List<Funcionario> findByNomeContainingAndVagaIsNull(String nome);
	
	public List<Funcionario> findByNomeContainingAndVagaNotNull(String nome);
	
	public List<Funcionario> findByVagaNotNull();
	
	public List<Funcionario> findByVagaIsNull();
}
