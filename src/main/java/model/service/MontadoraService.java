package model.service;

import java.time.LocalDate;

import exception.ControleVacinasException;
import model.entity.Montadora;
import model.entity.Pessoa;
import model.repository.MontadoraRepository;

public class MontadoraService {

	private MontadoraRepository montadoraRepository = new MontadoraRepository();
	
	public Montadora salvar(Montadora montadora) throws ControleVacinasException{
		validarCamposPreenchidos(montadora);
		return montadoraRepository.salvar(montadora);
	}
	
	public Montadora consultarPorId(int id) {
		return montadoraRepository.consultarPorId(id);
	}
	
	private void validarCamposPreenchidos(Montadora montadora) throws ControleVacinasException {
		String mensagemValidacao = "";
		   
	    if (montadora.getNome() == null ||
	    	 montadora.getNome().trim().length() == 0) {
	         mensagemValidacao += " - O campo nome, referente a montadora, precisa ser preenchido. ";
	    }
	    if (montadora.getPaisFundacao() == null ||
	    	 montadora.getPaisFundacao().trim().length() == 0) {
	         mensagemValidacao += " - O campo país, referente ao país da montadora, precisa ser preenchido. ";
	    }
		if (montadora.getNomePresidente() == null || 
			 montadora.getNomePresidente().trim().length() == 0) {
		    mensagemValidacao += " O campo nome do presidente, o qual se refere ao presidente da montadora, precisa ser preenchido.";
		}
		if(montadora.getDataFundacao() == null) {
			mensagemValidacao += " - O campo data da fundação, o qual se refere a data da fundação da montadora, precisa ser preenchido. ";
		}
		if(!mensagemValidacao.isEmpty()) {
			throw new ControleVacinasException("As observaçõe(s) a seguir precisa(m) ser atendida(s): "+mensagemValidacao);
		}
	}
	
}
