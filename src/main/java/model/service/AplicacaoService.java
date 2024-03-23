package model.service;

import model.entity.Aplicacao;
import model.repository.AplicacaoRepository;

public class AplicacaoService {
	
	private AplicacaoRepository repository = new AplicacaoRepository();
	
	public Aplicacao salvar(Aplicacao registroDaAplicacaoDaVacina) {
		return repository.salvar(registroDaAplicacaoDaVacina);
	}
	
	public Aplicacao consultarPorId(int id) {
		return repository.consultarPorId(id);
	}
	
}
