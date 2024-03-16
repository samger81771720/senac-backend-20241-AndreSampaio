package model.service;

import model.entity.Vacina;
import model.repository.VacinaRepository;

public class VacinaService {
	
	/*FALTA INSERIR OS "TROWS"  */
	
	private VacinaRepository repository = new VacinaRepository();
	
	// OK!
	public Vacina salvar(Vacina novaVacina) {
		return repository.salvar(novaVacina);
	}
	
	// OK!
	public boolean excluir(int id) {
		return repository.excluir(id);
	}
	
	// OK!
	public boolean alterar(Vacina vacinaParaAlterar) {
		return repository.alterar(vacinaParaAlterar);
	}
	
}
