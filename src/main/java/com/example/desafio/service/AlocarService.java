package com.example.desafio.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.desafio.model.Funcionario;
import com.example.desafio.model.Vaga;

@Service
public class AlocarService {

	@Autowired
	FuncionarioService funcionarioService;

	@Autowired
	VagaService vagaService;
						
	public void alocar(Vaga vaga, Funcionario funcionario) throws Throwable {

		Long vagaId = funcionario.getVaga().getId();
		
		vaga = vagaService.encontrarVaga(vagaId)
				.orElseThrow(() -> new EntityNotFoundException(String.format("Não existe Vaga com id %d", vagaId)));
		
		funcionario.setVaga(vaga);
		vaga.setQtdVaga(vaga.getQtdVaga()-1);
		
		funcionarioService.salvar(funcionario);
		
	}
}
