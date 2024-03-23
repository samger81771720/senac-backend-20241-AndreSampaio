package model.service;

import java.util.ArrayList;

import model.entity.Aplicacao;
import model.repository.AplicacaoRepository;

public class AplicacaoService {
	
	private AplicacaoRepository repository = new AplicacaoRepository();
	
	public Aplicacao salvar(Aplicacao novoRegistroDaAplicacaoDaVacina) {
		return repository.salvar(novoRegistroDaAplicacaoDaVacina);
	}
	
	public Aplicacao consultarPorId(int id) {
		return repository.consultarPorId(id);
	}
	
	public boolean alterar(Aplicacao registroDaAplicacaoDaVacinaAlterado) {
		return repository.alterar(registroDaAplicacaoDaVacinaAlterado);
	}
	
	public boolean excluir(int id) {
		return repository.excluir(id);
	}
	
	public ArrayList<Aplicacao> consultarTodos(){
		return repository.consultarTodos();
	}
}
