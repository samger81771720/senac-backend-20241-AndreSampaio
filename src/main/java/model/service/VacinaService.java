package model.service;

import java.util.ArrayList;

import exception.ControleVacinasException;
import model.entity.Aplicacao;
import model.entity.Vacina;
import model.repository.AplicacaoRepository;
import model.repository.PessoaRepository;
import model.repository.VacinaRepository;

public class VacinaService {
	
	private static final int PESQUISADOR = 1;
	
	private VacinaRepository repository = new VacinaRepository();
	
	private AplicacaoRepository aplicacaoRepository = new AplicacaoRepository(); 
	
	public Vacina salvar(Vacina novaVacina) throws ControleVacinasException{
		validarPesquisador(novaVacina);
		return repository.salvar(novaVacina);
	}
	
	public Vacina consultarPorId(int id) {
		return repository.consultarPorId(id);
	}
	
	public ArrayList<Vacina> consultarTodos() {
		return repository.consultarTodos();
	}
	
	public boolean excluir(int id) throws ControleVacinasException{
		validarAplicacaoDeVacinaPorId(id);
		return repository.excluir(id);
	}
	
	public boolean alterar(Vacina vacinaParaAlterar) {
		return repository.alterar(vacinaParaAlterar);
	}
	
	private void validarPesquisador(Vacina vacina) throws ControleVacinasException{
		if(repository.validarPesquisador(vacina)!=PESQUISADOR) {
			throw new ControleVacinasException("O id da pessoa informada para cadastrar essa vacina não é o id de um pesquisador.");
		}
	}
	
	// Vacina não pode ser excluída caso já tenha sido aplicada em pelo menos uma pessoa	
	private void validarAplicacaoDeVacinaPorId(int id) throws ControleVacinasException{	
		if (aplicacaoRepository.validarAplicacaoDeVacinaPorId(id)) {
			throw new ControleVacinasException("Não é possível excluir a vacina do sistema, pois a mesma já foi aplicada em uma pessoa.");
		}
	}
}
