package br.gov.serpro.inscricao.security;

import br.gov.frameworkdemoiselle.security.Authorizer;

public class Autorizador implements Authorizer {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean hasRole(String role) {
		return true;
	}

	@Override
	public boolean hasPermission(String resource, String operation) {
		return true;
	}

}
