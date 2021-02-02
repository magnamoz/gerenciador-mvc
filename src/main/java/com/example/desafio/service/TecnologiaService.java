package com.example.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.desafio.model.Tecnologia;
import com.example.desafio.repository.FuncionarioRepository;
import com.example.desafio.repository.TecnologiaRepository;

@Service
public class TecnologiaService {

	@Autowired
	private TecnologiaRepository tecnologiaRepository;

	@Autowired
	FuncionarioRepository func;

	public void salvar(Tecnologia tecnologia) {
		tecnologiaRepository.save(tecnologia);
	}

	public List<Tecnologia> listar() {
		return tecnologiaRepository.findAll();
	}
}
