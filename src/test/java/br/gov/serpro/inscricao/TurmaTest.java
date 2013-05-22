package br.gov.serpro.inscricao;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;

@RunWith(DemoiselleRunner.class)
public class TurmaTest {
	
	@Inject
	private Turma turma;
	 
	@Test
	public void matricularAlunoComSucesso(){
		turma.matricular("Santos Dumont");
		Assert.assertTrue(turma.estaMatriculado("Santos Dumont"));
	}
 
	@Test
	public void falhaAoTentarMatricularAlunoDuplicado(){
		org.junit.Assert.fail();
	}
 
	@Test
	public void falhaAoTentarMatricularAlunoNaTurmaCheia(){
	}
	
}