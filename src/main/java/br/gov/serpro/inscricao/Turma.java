package br.gov.serpro.inscricao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.stereotype.Controller;
import br.gov.frameworkdemoiselle.transaction.Transactional;
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
	
	@Inject
	private EntityManager em;

	@Transactional
	public void matricular(Aluno aluno) {
		if (estaMatriculado(aluno) || obterAlunosMatriculados().size() == config.getCapacidadeTurma()) {
			throw new TurmaException();
		}
		
		em.persist(aluno);
		
		String string = bundle.getString("matricula.sucesso", aluno.getNome());
		logger.info(string);
	}

	public boolean estaMatriculado(Aluno aluno) {

		boolean contains = obterAlunosMatriculados().contains(aluno);
		return contains;
	}

	public List<Aluno> obterAlunosMatriculados() {
		return em.createQuery("select a from Aluno a").getResultList();
	}

	@ExceptionHandler
	public void tratar(TurmaException e) {
		logger.warn(bundle.getString("matricula.erro"));
		throw e;
	}
}