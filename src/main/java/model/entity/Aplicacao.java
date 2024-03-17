package model.entity;

import java.time.LocalDate;

public class Aplicacao {
	
	private int idAplicacao;
	private LocalDate dataAplicacao;
	private Vacina vacinaAplicada;
	 private Pessoa pessoaQueRecebeu;
	private int avaliacaoReacao;
	
	public int getIdAplicacao() {
		return idAplicacao;
	}
	public void setIdAplicacao(int idAplicacao) {
		this.idAplicacao = idAplicacao;
	}
	public LocalDate getDataAplicacao() {
		return dataAplicacao;
	}
	public void setDataAplicacao(LocalDate dataAplicacao) {
		this.dataAplicacao = dataAplicacao;
	}
	public Vacina getVacinaAplicada() {
		return vacinaAplicada;
	}
	public void setVacinaAplicada(Vacina vacinaAplicada) {
		this.vacinaAplicada = vacinaAplicada;
	}
	public Pessoa getPessoaQueRecebeu() {
		return pessoaQueRecebeu;
	}
	public void setPessoaQueRecebeu(Pessoa pessoaQueRecebeu) {
		this.pessoaQueRecebeu = pessoaQueRecebeu;
	}
	public int getAvaliacaoReacao() {
		return avaliacaoReacao;
	}
	public void setAvaliacaoReacao(int avaliacaoReacao) {
		this.avaliacaoReacao = avaliacaoReacao;
	}

}
