package com.example.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.desafio.model.Tecnologia;

public interface TecnologiaRepository extends JpaRepository<Tecnologia, Long> {

	public List<Tecnologia> findByNome(String nome);

}
