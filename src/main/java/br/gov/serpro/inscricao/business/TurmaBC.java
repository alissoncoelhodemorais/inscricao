package br.gov.serpro.inscricao.business;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.ResourceBundle;
import br.gov.serpro.inscricao.config.InscricaoConfig;
import br.gov.serpro.inscricao.entity.Aluno;
import br.gov.serpro.inscricao.exception.TurmaException;

@BusinessController
public class TurmaBC {

	@Inject
	private ResourceBundle bundle;

	@Inject
	private InscricaoConfig config;

	@Inject
	private Logger logger;

	@Inject
	private AlunoBC alunoBC;

	@Transactional
	public void matricular(Aluno aluno) {
		if (estaMatriculado(aluno) || obterAlunosMatriculados().size() == config.getCapacidadeTurma()) {
			throw new TurmaException();
		}
		
		alunoBC.insert(aluno);
		
		String string = bundle.getString("matricula.sucesso", aluno.getNome());
		logger.info(string);
	}

	public boolean estaMatriculado(Aluno aluno) {

		boolean contains = obterAlunosMatriculados().contains(aluno);
		return contains;
	}

	public List<Aluno> obterAlunosMatriculados() {
		return alunoBC.findAll();
	}

	@ExceptionHandler
	public void tratar(TurmaException e) {
		logger.warn(bundle.getString("matricula.erro"));
		throw e;
	}
	
	@Startup
	public void iniciar() {
		logger.info("iniciando a app inscricao ...");
	}
}