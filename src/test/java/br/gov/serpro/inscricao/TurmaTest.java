package br.gov.serpro.inscricao;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import br.gov.serpro.inscricao.business.TurmaBC;
import br.gov.serpro.inscricao.entity.Aluno;
import br.gov.serpro.inscricao.exception.TurmaException;

@RunWith(DemoiselleRunner.class)
public class TurmaTest {
	
	@Inject
	private TurmaBC turma;

	@Test
	public void matricularAlunoComSucesso(){
		turma.matricular(new Aluno("Santos Dumont"));
		Assert.assertTrue(turma.estaMatriculado(new Aluno("Santos Dumont")));
	}
 
	@Test(expected = TurmaException.class) 
	public void falhaAoTentarMatricularAlunoDuplicado(){
		turma.matricular(new Aluno("Santos Dumont"));
		turma.matricular(new Aluno("Santos Dumont"));
	}
 
	@Test(expected = TurmaException.class)
	public void falhaAoTentarMatricularAlunoNaTurmaCheia() {
		for (int i = 1; i <= 5; i++) {
			turma.matricular(new Aluno("Aluno " + i));
		}
	 
		turma.matricular(new Aluno("Aluno 6"));
	}
	
	
}