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

import com.example.desafio.model.Funcionario;
import com.example.desafio.model.Gft;
import com.example.desafio.model.Tecnologia;
import com.example.desafio.repository.FuncionarioRepository;
import com.example.desafio.repository.GftRepository;
import com.example.desafio.repository.filter.FuncionarioFilter;
import com.example.desafio.service.FuncionarioService;
import com.example.desafio.service.GftService;
import com.example.desafio.service.TecnologiaService;

@Controller
@RequestMapping
public class FuncionarioController {

	@Autowired
	FuncionarioService funcionarioService;

	@Autowired
	TecnologiaService tecnologiaService;

	@Autowired
	GftService gftService;
	
	@Autowired
	GftRepository gftRepository;
	
	@Autowired
	FuncionarioRepository funcionarioRepository;

	@GetMapping()
	public ModelAndView wa() {
		ModelAndView mv = new ModelAndView("wa");
		return mv;
	}

	@GetMapping("/funcionarios/cadastrar")
	public ModelAndView cadastrar(Funcionario funcionario) {
		ModelAndView mv = new ModelAndView("cadastrarFunc");
		List<Tecnologia> tecnologiaLista = tecnologiaService.listar();
		mv.addObject("tecnologiaLista", tecnologiaLista);
		List<Gft> gftLista = gftService.listar();
		mv.addObject("gftLista", gftLista);
		return mv;
	}

	@PostMapping("/funcionarios")
	public ModelAndView salvar(@Validated Funcionario funcionario, Errors errors,
			RedirectAttributes attributes, Tecnologia tecnologia) {
		ModelAndView mv = new ModelAndView("redirect:/funcionarios/cadastrar");
		if (errors.hasErrors()) {
			return cadastrar(funcionario);
		}
		try {
			funcionarioService.calculaTerminoWa(funcionario);
			funcionarioService.salvar(funcionario); 
			attributes.addFlashAttribute("mensagem", "Salvo com sucesso!");
			return mv;
		} catch (IllegalArgumentException e) {
			errors.rejectValue("inicioWa", null, e.getMessage());
			return cadastrar(funcionario);
		}
	}

	@GetMapping("/funcionarios")
	public ModelAndView pesquisar(@ModelAttribute("filtro") FuncionarioFilter filtro) {
		List<Funcionario> todosFuncionarios = funcionarioService.filtrar(filtro);
		ModelAndView mv = new ModelAndView("funcionarios");

		mv.addObject("funcionarios", todosFuncionarios);
		return mv;

	}
}
