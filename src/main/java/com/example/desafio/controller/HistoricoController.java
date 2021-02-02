package com.example.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.desafio.model.Funcionario;
import com.example.desafio.repository.filter.FuncionarioFilter;
import com.example.desafio.service.FuncionarioService;

@Controller
@RequestMapping
public class HistoricoController {
	
	@Autowired
	FuncionarioService funcionarioService;

	@GetMapping("/historico")
	public ModelAndView cadastrar(@ModelAttribute("filtro") FuncionarioFilter filtro) {
		ModelAndView mv = new ModelAndView("historico");
		
		List<Funcionario> funcionarios = funcionarioService.filtrarAlocados(filtro);
		
		mv.addObject("funcionarios", funcionarios);
		
		return mv;
	}
	
}
