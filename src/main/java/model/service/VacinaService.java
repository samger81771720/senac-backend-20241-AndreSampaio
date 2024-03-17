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
	
	public Vacina consultarPorId(int id) {
		return repository.consultarPorId(id);
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
