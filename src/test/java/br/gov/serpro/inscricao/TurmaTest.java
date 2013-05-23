package br.gov.serpro.inscricao;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import br.gov.serpro.inscricao.entity.Aluno;
import br.gov.serpro.inscricao.exception.TurmaException;

@RunWith(DemoiselleRunner.class)
public class TurmaTest {
	
	@Inject
	private Turma turma;

	@Test
	public void aabraoDesejaUtilizarAltF11() {
		org.junit.Assert.fail("sempre falhara, para usar o Alt+F11");
	}
		
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