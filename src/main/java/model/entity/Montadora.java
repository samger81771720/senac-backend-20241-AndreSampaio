package model.entity;

import java.time.LocalDate;

public class Montadora {
	
	private Integer id;
	private String nome;
	private String paisFundacao;
	private String nomePresidente;
	private LocalDate dataFundacao;
	
	public Montadora() {
		
	}
	
	private Montadora(Integer id, String nome, String paisFundacao, String nomePresidente, LocalDate dataFundacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.paisFundacao = paisFundacao;
		this.nomePresidente = nomePresidente;
		this.dataFundacao = dataFundacao;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPaisFundacao() {
		return paisFundacao;
	}
	public void setPaisFundacao(String paisFundacao) {
		this.paisFundacao = paisFundacao;
	}

	public String getNomePresidente() {
		return nomePresidente;
	}

	public void setNomePresidente(String nomePresidente) {
		this.nomePresidente = nomePresidente;
	}

	public LocalDate getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(LocalDate dataFundacao) {
		this.dataFundacao = dataFundacao;
	}
	
}
