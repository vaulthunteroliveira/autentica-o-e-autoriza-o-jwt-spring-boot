package com.alexoliveira.redesocial.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alexoliveira.redesocial.model.Usuario;
import com.alexoliveira.redesocial.service.UsuarioService;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UsuarioService service;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = service.findByEmail(email);
		if(usuario == null) {
			throw new UsernameNotFoundException(email);
		}
		return new User(usuario.getId(),usuario.getEmail(), usuario.getSenha(), usuario.getPerfis());
	}

}
