package model.seletor;

public class VacinaSeletor extends BaseSeletor{	
	
	private String nomePais;
	private String nomePesquisador;
	private String nomeVacina;
	
	public VacinaSeletor() {
		
	}

	public String getNomePais() {
		return nomePais;
	}

	public void setNomePais(String nomePais) {
		this.nomePais = nomePais;
	}

	public String getNomePesquisador() {
		return nomePesquisador;
	}

	public void setNomePesquisador(String nomePesquisador) {
		this.nomePesquisador = nomePesquisador;
	}

	public String getNomeVacina() {
		return nomeVacina;
	}

	public void setNomeVacina(String nomeVacina) {
		this.nomeVacina = nomeVacina;
	}
	
	public boolean temFiltro() {
		return (this.nomePais != null && this.nomePais.trim().length() > 0)
					|| (this.nomePesquisador != null && this.nomePesquisador.trim().length()>0)
					|| (this.nomeVacina != null && this.nomeVacina.trim().length() > 0);
	}
	
}
