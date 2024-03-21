package model.service;

import exception.ControleVacinasException;
import model.entity.Pais;
import model.repository.PaisRepository;

public class PaisService {
	
	private PaisRepository repository = new PaisRepository();
	
	public Pais consultarPorId(int id) throws ControleVacinasException{
		if(repository.consultarPorId(id)==null) {
			throw new ControleVacinasException("N�o foi poss�vel encontrar o pa�s com o id informado.");
		}
		return repository.consultarPorId(id);
	}
}
