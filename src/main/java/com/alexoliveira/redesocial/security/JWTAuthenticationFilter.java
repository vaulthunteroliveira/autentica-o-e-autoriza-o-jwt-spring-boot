package com.alexoliveira.redesocial.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.alexoliveira.redesocial.dto.CredenciaisDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	private JWTUtil jwtUil;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUil) {
		this.authenticationManager = authenticationManager;
		this.jwtUil = jwtUil;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			CredenciaisDTO credenciais = new ObjectMapper().readValue(request.getInputStream(), CredenciaisDTO.class);

			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(credenciais.getEmail(),
					credenciais.getSenha(), new ArrayList<>());

			return authenticationManager.authenticate(token);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		String username = ((UserDetailsImpl) auth.getPrincipal()).getUsername();
		String token = jwtUil.generateToken(username);
		response.addHeader("Authorization", "Bearer " + token);
	}
}
