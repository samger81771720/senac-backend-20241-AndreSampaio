package model.seletor;

import java.time.LocalDate;
import model.entity.Vacina;

public class AplicacaoSeletor {
	
	private String vacinaUsadaNaAplicacao;
	private LocalDate dataAplicacaoDaVacina;
	private int avaliacaoReacaoVacina;
	
	public String getVacinaUsadaNaAplicacao() {
		return vacinaUsadaNaAplicacao;
	}

	public void setVacinaUsadaNaAplicacao(String vacinaUsadaNaAplicacao) {
		this.vacinaUsadaNaAplicacao = vacinaUsadaNaAplicacao;
	}

	public LocalDate getDataAplicacaoDaVacina() {
		return dataAplicacaoDaVacina;
	}

	public void setDataAplicacaoDaVacina(LocalDate dataAplicacaoDaVacina) {
		this.dataAplicacaoDaVacina = dataAplicacaoDaVacina;
	}

	public int getAvaliacaoReacaoVacina() {
		return avaliacaoReacaoVacina;
	}

	public void setAvaliacaoReacaoVacina(int avaliacaoReacaoVacina) {
		this.avaliacaoReacaoVacina = avaliacaoReacaoVacina;
	}

	public boolean temFiltro() {
		return (this.vacinaUsadaNaAplicacao != null)
					|| (this.dataAplicacaoDaVacina != null)
					|| (this.avaliacaoReacaoVacina != 0);
	}
	
}

