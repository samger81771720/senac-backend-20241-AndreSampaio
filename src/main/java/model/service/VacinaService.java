package model.service;

import java.util.ArrayList;

import exception.ControleVacinasException;
import model.entity.Vacina;
import model.repository.VacinaRepository;

public class VacinaService {
	
	private static final int PESQUISADOR = 1;
	
	/*FALTA INSERIR OS "TROWS"////////////////////////////////////////////////////////////////////////////////////////  */
	/*FALTA INSERIR OS "TROWS"////////////////////////////////////////////////////////////////////////////////////////  */
	/*FALTA INSERIR OS "TROWS"////////////////////////////////////////////////////////////////////////////////////////  */
	/*FALTA INSERIR OS "TROWS"////////////////////////////////////////////////////////////////////////////////////////  */
	/*FALTA INSERIR OS "TROWS"////////////////////////////////////////////////////////////////////////////////////////  */
	/*FALTA INSERIR OS "TROWS"////////////////////////////////////////////////////////////////////////////////////////  */
	
	
	private VacinaRepository repository = new VacinaRepository();
	
	// OK!
	public Vacina salvar(Vacina novaVacina) throws ControleVacinasException{
		validarPesquisador(novaVacina);
		return repository.salvar(novaVacina);
	}
	
	public void validarPesquisador(Vacina vacina) throws ControleVacinasException{
		if(repository.validarPesquisador(vacina)!=PESQUISADOR) {
			throw new ControleVacinasException("O id da pessoa informada para cadastrar essa vacina não é o id de um pesquisador.");
		}
	}
	
	// OK!
	public Vacina consultarPorId(int id) {
		return repository.consultarPorId(id);
	}
	
	public ArrayList<Vacina> consultarTodos() {
		return repository.consultarTodos();
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
