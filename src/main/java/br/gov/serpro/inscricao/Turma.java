package br.gov.serpro.inscricao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;

public class Turma {

	@Inject 
	private Logger logger; 

	private List<String> alunosMatriculados = new ArrayList<String>();

	public void matricular(String aluno){
		logger.info("Aluno matriculado com sucesso");
	}
	 
	public boolean estaMatriculado(String aluno) {
		return alunosMatriculados.contains(aluno);
	}
}