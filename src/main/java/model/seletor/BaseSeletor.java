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
	
	// Explica��o abaixo
	public int getOffSet() {
		return this.limite * (this.pagina - 1);
	}
	
}


/*
  
 O m�todo getOffset() da classe BaseSeletor � utilizado para calcular o deslocamento (offset) 
 dos registros a serem consultados em uma opera��o de pagina��o. Vamos analisar o m�todo 
 linha por linha para entender seu funcionamento:
 

return this.limite * (this.pagina - 1);
this.limite se refere � quantidade m�xima de registros que devem ser retornados em cada p�gina.

this.pagina � o n�mero da p�gina que est� sendo solicitada.

(this.pagina - 1) � subtra�do de this.pagina para ajustar a indexa��o de p�gina para come�ar de 0 em vez de 1. 
Isso � necess�rio porque a indexa��o de lista ou array geralmente come�a em 0 em muitas linguagens de programa��o.

this.limite * (this.pagina - 1) calcula o n�mero de registros que devem ser pulados (o deslocamento) para chegar � p�gina desejada. 
Por exemplo, se estivermos na p�gina 1 (this.pagina = 1), o deslocamento ser� 0, o que significa que n�o precisamos pular nenhum registro. 
Se estivermos na p�gina 2 (this.pagina = 2), o deslocamento ser� this.limite, o que significa que devemos pular this.limite registros
 para alcan�ar a p�gina 2.

Essencialmente, getOffset() calcula o n�mero de registros que devem ser pulados para alcan�ar a p�gina desejada, 
com base no n�mero de registros por p�gina e no n�mero da p�gina. Isso � �til ao construir consultas SQL ou ao 
trabalhar com outras formas de acesso a dados paginados.

*/