package model.service;

import exception.ControleVacinasException;
import model.entity.Pais;
import model.repository.PaisRepository;

public class PaisService {
	
	private PaisRepository repository = new PaisRepository();
	
	public Pais consultarPorId(int id) throws ControleVacinasException{
		if(repository.consultarPorId(id)==null) {
			throw new ControleVacinasException("Não foi possível encontrar o país com o id informado.");
		}
		return repository.consultarPorId(id);
	}
}
