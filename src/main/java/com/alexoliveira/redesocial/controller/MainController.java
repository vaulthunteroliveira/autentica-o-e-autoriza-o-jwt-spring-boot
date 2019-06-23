package com.alexoliveira.redesocial.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexoliveira.redesocial.dto.NewUsuarioDTO;
import com.alexoliveira.redesocial.model.Usuario;
import com.alexoliveira.redesocial.service.UsuarioService;

@RestController
@RequestMapping("/")
public class MainController {
	
	@Autowired
	private UsuarioService service;

	@GetMapping(value = "teste/{nome}")
	private String teste(@PathVariable String nome) {
		return "Olá " + nome;
	}
	
	@GetMapping(value = "/")
	private String teste2() {
		return "teste ok";

	}
	
	@GetMapping("teste3")
	private void salvar() {
		service.salvar(new NewUsuarioDTO("sandro", "alexsandro@email.com", "123", new Date(System.currentTimeMillis()), "default", 'm'));
	}

}
