package br.gov.serpro.inscricao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.stereotype.Controller;
import br.gov.frameworkdemoiselle.util.ResourceBundle;
import br.gov.serpro.inscricao.config.InscricaoConfig;
import br.gov.serpro.inscricao.entity.Aluno;
import br.gov.serpro.inscricao.exception.TurmaException;

@Controller
public class Turma {

	@Inject
	private ResourceBundle bundle;

	@Inject
	private InscricaoConfig config;

	@Inject
	private Logger logger;

	private List<Aluno> alunosMatriculados = new ArrayList<Aluno>();

	public void matricular(Aluno aluno) {
		if (estaMatriculado(aluno) || alunosMatriculados.size() == config.getCapacidadeTurma()) {
			throw new TurmaException();
		}
		alunosMatriculados.add(aluno);
		String string = bundle.getString("matricula.sucesso", aluno.getNome());
		logger.info(string);
	}

	public boolean estaMatriculado(Aluno aluno) {

		boolean contains = alunosMatriculados.contains(aluno);
		return contains;
	}

	@ExceptionHandler
	public void tratar(TurmaException e) {
		logger.warn(bundle.getString("matricula.erro"));
		throw e;
	}
}