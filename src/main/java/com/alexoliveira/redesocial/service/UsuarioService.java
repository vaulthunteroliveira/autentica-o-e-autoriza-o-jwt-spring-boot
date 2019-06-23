package com.alexoliveira.redesocial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.alexoliveira.redesocial.dto.NewUsuarioDTO;
import com.alexoliveira.redesocial.enums.Perfil;
import com.alexoliveira.redesocial.model.Usuario;
import com.alexoliveira.redesocial.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UsuarioRepository repo;

	public void salvar(NewUsuarioDTO dto) {
		Usuario usuario = new Usuario(null, dto.getNome(), dto.getEmail(), bCryptPasswordEncoder.encode(dto.getSenha()),
				dto.getDt_nascimento(), dto.getFoto_perfil(), dto.getSexo());
		usuario.addPerfil(Perfil.ADMIN);
		repo.save(usuario);

	}

	public Usuario findByEmail(String email) {
		return repo.findByEmail(email);
	}
}
