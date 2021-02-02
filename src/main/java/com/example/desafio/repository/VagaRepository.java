package com.example.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.desafio.model.Vaga;


public interface VagaRepository extends JpaRepository<Vaga, Long>{

	public List<Vaga> findByProjetoContaining(String projeto);
}
