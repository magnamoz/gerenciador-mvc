package com.example.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.desafio.model.Funcionario;
import com.example.desafio.model.Vaga;
import com.example.desafio.repository.FuncionarioRepository;
import com.example.desafio.repository.VagaRepository;
import com.example.desafio.service.AlocarService;
import com.example.desafio.service.FuncionarioService;
import com.example.desafio.service.TecnologiaService;
import com.example.desafio.service.VagaService;

@Controller
@RequestMapping
public class AlocarController {

	@Autowired
	TecnologiaService tecnologiaService;

	@Autowired
	FuncionarioService funcionarioService;

	@Autowired
	VagaService vagaService;

	@Autowired
	VagaRepository vagaRepository;
	
	@Autowired
	AlocarService alocarService;
	
	@Autowired
	FuncionarioRepository funcionarioRepository;

	@GetMapping("/alocar")
	public ModelAndView alocar(Funcionario funcionario, Vaga vaga) {
		ModelAndView mv = new ModelAndView("alocar");

		List<Funcionario> funcionarios = funcionarioService.naoAlocados();
		mv.addObject("funcionarios", funcionarios);

		List<Vaga> vagas = vagaService.listar();
		mv.addObject("vagas", vagas);

		return mv;
	}
	
	@PostMapping("/alocar")
	public String alocar(@ModelAttribute("vaga") @Validated Vaga vaga, Model model,
			@Validated Funcionario funcionario, Errors errors) throws Throwable {
		model.addAttribute("vaga", vaga);
		try {
			alocarService.alocar(vaga, funcionario);
			return "redirect:/historico";
		}  catch (Exception e) {
			errors.reject("Não foi possível alocar, tente novamente");
			return "redirect:/alocar";
		}
	}
}