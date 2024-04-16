package model.seletor;

public abstract class BaseSeletor {
	
	private int pagina;
	private int limite;
	
	public BaseSeletor() {
		this.pagina = 0;
		this.limite = 0;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}
	
	public boolean temPaginacao() {
		return this.limite > 0 && this.pagina > 0;
	}
	
	// Explicação abaixo
	public int getOffSet() {
		return this.limite * (this.pagina - 1);
	}
	
}


/*
  
 O método getOffset() da classe BaseSeletor é utilizado para calcular o deslocamento (offset) 
 dos registros a serem consultados em uma operação de paginação. Vamos analisar o método 
 linha por linha para entender seu funcionamento:
 

return this.limite * (this.pagina - 1);
this.limite se refere à quantidade máxima de registros que devem ser retornados em cada página.

this.pagina é o número da página que está sendo solicitada.

(this.pagina - 1) é subtraído de this.pagina para ajustar a indexação de página para começar de 0 em vez de 1. 
Isso é necessário porque a indexação de lista ou array geralmente começa em 0 em muitas linguagens de programação.

this.limite * (this.pagina - 1) calcula o número de registros que devem ser pulados (o deslocamento) para chegar à página desejada. 
Por exemplo, se estivermos na página 1 (this.pagina = 1), o deslocamento será 0, o que significa que não precisamos pular nenhum registro. 
Se estivermos na página 2 (this.pagina = 2), o deslocamento será this.limite, o que significa que devemos pular this.limite registros
 para alcançar a página 2.

Essencialmente, getOffset() calcula o número de registros que devem ser pulados para alcançar a página desejada, 
com base no número de registros por página e no número da página. Isso é útil ao construir consultas SQL ou ao 
trabalhar com outras formas de acesso a dados paginados.

*/