package br.gov.serpro.inscricao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.util.ResourceBundle;

public class Turma {
	
	@Inject private ResourceBundle bundle;

	@Inject 
	private Logger logger; 

	private List<String> alunosMatriculados = new ArrayList<String>();

	public void matricular(String aluno){
		if (estaMatriculado(aluno) || alunosMatriculados.size() == 5) { 
			throw new RuntimeException(); 
		}
		alunosMatriculados.add(aluno);
		String string = bundle.getString("matricula.sucesso", aluno);
		logger.info(string);
	}
	 
	public boolean estaMatriculado(String aluno) {
		
		boolean contains = alunosMatriculados.contains(aluno);
		return contains;
	}
}