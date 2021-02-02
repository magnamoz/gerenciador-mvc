package com.example.desafio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.desafio.model.Vaga;
import com.example.desafio.repository.VagaRepository;
import com.example.desafio.repository.filter.VagaFilter;

@Service
public class VagaService {

	@Autowired
	private VagaRepository vagaRepository;

	public void salvar(Vaga vaga) {
		vagaRepository.save(vaga);

	}

	public List<Vaga> listar() {
		return vagaRepository.findAll();
	}

	public List<Vaga> filtrar(VagaFilter filtro) {
		String projeto = filtro.getProjeto() == null ? "" : filtro.getProjeto();
		return vagaRepository.findByProjetoContaining(projeto);
	}

	public Optional<Vaga> encontrarVaga(Long id) {
		return vagaRepository.findById(id);
	}
}
