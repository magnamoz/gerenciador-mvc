package com.example.desafio.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.desafio.model.Funcionario;
import com.example.desafio.model.Gft;
import com.example.desafio.repository.FuncionarioRepository;
import com.example.desafio.repository.GftRepository;
import com.example.desafio.repository.filter.FuncionarioFilter;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private GftRepository gftRepository;

	public Funcionario salvar(Funcionario funcionario) {

		try {
			Long gftId = funcionario.getGft().getId();

			Gft gft = gftRepository.findById(gftId)
					.orElseThrow(() -> new EntityNotFoundException(String.format("Não existe Gft com id %d", gftId)));
			funcionario.setGft(gft);

			return funcionarioRepository.save(funcionario);

		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data inválido");
		}
	}

	
	public List<Funcionario> filtrar(FuncionarioFilter filtro) {
		String nome = filtro.getNome() == null ? "" : filtro.getNome();
		return funcionarioRepository.findByNomeContainingAndVagaIsNull(nome);
	}
	
	public List<Funcionario> filtrarAlocados(FuncionarioFilter filtro) {
		String nome = filtro.getNome() == null ? "" : filtro.getNome();
		return funcionarioRepository.findByNomeContainingAndVagaNotNull(nome);
	}
	
	public List<Funcionario> listar () {
		return funcionarioRepository.findAll();
	}
	
	public List<Funcionario> naoAlocados() {
		return funcionarioRepository.findByVagaIsNull();
	}
	
	public void calculaTerminoWa(Funcionario funcionario) {
		Date data = funcionario.getInicioWa();

		LocalDate localDate = Instant.ofEpochMilli(data.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate dataTermino = localDate.plusDays(15);
		funcionario.setTerminoWa(Date.from(dataTermino.atStartOfDay(ZoneId.systemDefault()).toInstant()));
	}

}
