package com.alexoliveira.redesocial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alexoliveira.redesocial.dto.CredenciaisDTO;
import com.alexoliveira.redesocial.model.Usuario;
import com.alexoliveira.redesocial.security.CurrentUser;
import com.alexoliveira.redesocial.security.JWTUtil;
import com.alexoliveira.redesocial.service.UsuarioService;

@RestController
@CrossOrigin(origins = "*")
public class AuthenticationRestController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private UsuarioService service;

	@PostMapping(value = "/api/auth")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody CredenciaisDTO credenciaisDTO)
			throws AuthenticationException {
		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(credenciaisDTO.getEmail(), credenciaisDTO.getSenha()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		final UserDetails userDetails = userDetailsService.loadUserByUsername(credenciaisDTO.getEmail());
		final String token = jwtUtil.generateToken(userDetails.getUsername());
		final Usuario usuario = service.findByEmail(userDetails.getUsername());
		usuario.setId(null);
		return ResponseEntity.ok(new CurrentUser(token, usuario));
	}

}


