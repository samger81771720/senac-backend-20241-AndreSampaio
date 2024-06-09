package model.service;

import java.util.ArrayList;

import exception.ControleVacinasException;
import model.entity.Pais;
import model.repository.PaisRepository;

public class PaisService {
	
	private PaisRepository paisRepository = new PaisRepository();
	
	public Pais consultarPorId(int id) throws ControleVacinasException{
		if(paisRepository.consultarPorId(id)==null) {
			throw new ControleVacinasException("Não foi possível encontrar o país com o id informado.");
		}
		return paisRepository.consultarPorId(id);
	}
	
	public Pais salvar(Pais novoPais) {
		return paisRepository.salvar(novoPais);
	}
	
	public ArrayList<Pais> consultarTodos() {
		return paisRepository.consultarTodos();
	}
	
	public boolean excluir(int id) {
		return paisRepository.excluir(id);
	}
	
	public boolean alterar(Pais pais) {
		return paisRepository.alterar(pais);
	}

}
