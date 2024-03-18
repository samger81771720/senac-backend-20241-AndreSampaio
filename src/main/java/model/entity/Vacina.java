package model.entity;

import java.time.LocalDate;

public class Vacina {
	
	private int idVacina;
	private String nome; 
	private String pais_De_Origem;
	private Pessoa pesquisadorResponsavel;
	private  int estagioDaVacina;
	private LocalDate dataInicioPesquisa;
	
	public Vacina() {
		super();
	}

	public int getIdVacina() {
		return idVacina;
	}

	public void setIdVacina(int idVacina) {
		this.idVacina = idVacina;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPais_De_Origem() {
		return pais_De_Origem;
	}

	public void setPais_De_Origem(String pais_De_Origem) {
		this.pais_De_Origem = pais_De_Origem;
	}

	public Pessoa getPesquisadorResponsavel() {
		return pesquisadorResponsavel;
	}

	public void setPesquisadorResponsavel(Pessoa pesquisadorResponsavel) {
		this.pesquisadorResponsavel = pesquisadorResponsavel;
	}

	public int getEstagioDaVacina() {
		return estagioDaVacina;
	}

	public void setEstagioDaVacina(int estagioDaVacina) {
		this.estagioDaVacina = estagioDaVacina;
	}

	public LocalDate getDataInicioPesquisa() {
		return dataInicioPesquisa;
	}

	public void setDataInicioPesquisa(LocalDate dataInicioPesquisa) {
		this.dataInicioPesquisa = dataInicioPesquisa;
	}

}
