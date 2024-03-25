package model.service;

import java.time.LocalDate;
import java.util.ArrayList;
import exception.ControleVacinasException;
import model.entity.Aplicacao;
import model.repository.AplicacaoRepository;

public class AplicacaoService {
	
	private static final int NOTA_MAXIMA = 5;
	
	private AplicacaoRepository repository = new AplicacaoRepository();
	
	public Aplicacao salvar(Aplicacao novoRegistroDaAplicacaoDaVacina) throws ControleVacinasException{
		validarCamposPreenchidosDeAplicacao(novoRegistroDaAplicacaoDaVacina);
		return repository.salvar(novoRegistroDaAplicacaoDaVacina);
	}

	public boolean alterar(Aplicacao registroDaAplicacaoDaVacinaAlterado) throws ControleVacinasException{
		validarCampoIdAplicacao(registroDaAplicacaoDaVacinaAlterado);
		validarCamposPreenchidosDeAplicacao(registroDaAplicacaoDaVacinaAlterado);
		return repository.alterar(registroDaAplicacaoDaVacinaAlterado);
	}
	
	private void validarCamposPreenchidosDeAplicacao(Aplicacao novoRegistroDaAplicacaoDaVacina) throws ControleVacinasException{
		String mensagem = "";
		if(novoRegistroDaAplicacaoDaVacina.getIdPessoa()==0) {
			mensagem += "O campo \"id\" da pessoa vacinada é obrigatório.";
		}
		if(novoRegistroDaAplicacaoDaVacina.getVacinaAplicada().getIdVacina()==0 || novoRegistroDaAplicacaoDaVacina.getVacinaAplicada()==null) {
			mensagem += "O campo \"id\" da vacina aplicada é obrigatório.";
		}
		novoRegistroDaAplicacaoDaVacina.setDataAplicacao(LocalDate.now());
		if(novoRegistroDaAplicacaoDaVacina.getAvaliacaoReacao() == 0) {
			novoRegistroDaAplicacaoDaVacina.setAvaliacaoReacao(NOTA_MAXIMA);
		}
		if(!mensagem.isEmpty()) {
			throw new ControleVacinasException("A(s) observaçõe(s) listada(s) precisa(m) ser atendida)s) "
					+ "para que o cadastro do registro da aplicação da vacina seja efetuado com sucesso. Segue: \n"+mensagem);
		}
	}
	
	private void validarCampoIdAplicacao(Aplicacao registroDaAplicacaoDaVacinaAlterado) throws ControleVacinasException{
		if(registroDaAplicacaoDaVacinaAlterado.getIdAplicacao()==0) {
			throw new ControleVacinasException("O campo \"id\" da aplicação da vacina é obrigatório.");
		}
	}
	
	public Aplicacao consultarPorId(int id) {
		return repository.consultarPorId(id);
	}
	
	public boolean excluir(int id) {
		return repository.excluir(id);
	}
	
	public ArrayList<Aplicacao> consultarTodos(){
		return repository.consultarTodos();
	}

}
