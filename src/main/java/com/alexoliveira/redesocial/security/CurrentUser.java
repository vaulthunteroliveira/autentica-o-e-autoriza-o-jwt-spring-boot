package com.alexoliveira.redesocial.security;

import com.alexoliveira.redesocial.model.Usuario;

public class CurrentUser {
	
	
	private String token;
	private Usuario usuario;

	public CurrentUser(String token, Usuario usuario) {
		this.usuario = usuario;
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

}
