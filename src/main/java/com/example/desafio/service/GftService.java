package com.example.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.desafio.model.Gft;
import com.example.desafio.repository.FuncionarioRepository;
import com.example.desafio.repository.GftRepository;

@Service
public class GftService {

	@Autowired
	GftRepository gftRepository;
	
	@Autowired
	FuncionarioRepository funcionarioRepository;

	public List<Gft> listar() {
		return gftRepository.findAll();
	}

	public void salvar(Gft gft) {
		gftRepository.save(gft);
	}
	
}
