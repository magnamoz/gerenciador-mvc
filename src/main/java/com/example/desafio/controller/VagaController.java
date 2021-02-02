package com.example.desafio.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.desafio.model.Tecnologia;
import com.example.desafio.model.Vaga;
import com.example.desafio.repository.filter.VagaFilter;
import com.example.desafio.service.TecnologiaService;
import com.example.desafio.service.VagaService;

@Controller
@RequestMapping
public class VagaController {

	@Autowired
	VagaService vagaService;
	
	@Autowired
	TecnologiaService tecnologiaService;

	@GetMapping("/vagas/cadastrar")
	public ModelAndView cadastrar(Vaga vaga) {
		ModelAndView mv = new ModelAndView("cadastrarVaga");
		List<Tecnologia> tecnologiaLista = tecnologiaService.listar();
		mv.addObject("tecnologiaLista", tecnologiaLista );
		return mv;
	}

	
	@PostMapping("/vagas")
	public ModelAndView salvar(@Validated Vaga vaga, Errors errors, RedirectAttributes attributes,  Tecnologia tecnologia ) {
		ModelAndView mv = new ModelAndView("redirect:/vagas/cadastrar");
		if (errors.hasErrors()) {
			return  cadastrar(vaga);
		}
		try {
			vagaService.salvar(vaga);
			attributes.addFlashAttribute("mensagem", "Salvo com sucesso!");
			return mv;
		} catch (IllegalArgumentException e) {
			errors.rejectValue("aberturaVaga", null, e.getMessage());
			return cadastrar(vaga);
		}
	}
	
	@GetMapping("/vagas")
	public   ModelAndView pesquisar(@ModelAttribute("filtro") VagaFilter filtro) {
		List<Vaga> todasVagas = vagaService.filtrar(filtro);
		ModelAndView mv = new ModelAndView("vagas");
		mv.addObject("vagas",  todasVagas);
		return mv;

	}

}
