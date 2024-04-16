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
	
	//"obtenha o deslocamento"
	public int getOffSet() {
		return this.limite * (this.pagina - 1);
	} 
	//Explica��o abaixo
	
}

/*
  
 O m�todo getOffSet() � respons�vel por calcular o deslocamento necess�rio para uma consulta paginada em um banco de dados.

Vamos entender como ele funciona:

this.pagina - 1: Isso subtrai 1 do n�mero da p�gina atual. Isso � feito porque, em uma consulta paginada, geralmente as p�ginas 
s�o indexadas a partir de 1 (a primeira p�gina � a p�gina 1), mas em programa��o, os �ndices geralmente come�am em 0. Portanto, 
subtrair 1 ajusta a contagem da p�gina para que corresponda ao �ndice correto.

this.limite * (this.pagina - 1): Multiplica o n�mero de itens por p�gina (limite) pelo n�mero da p�gina ajustado. Isso resulta 
no n�mero de itens que devem ser pulados antes de come�ar a recuperar os resultados para a p�gina atual.

Por exemplo, se voc� estiver na p�gina 3 e tiver 10 itens por p�gina, o c�lculo seria:

makefile
Copy code
Deslocamento = 10 * (3 - 1) = 10 * 2 = 20
Isso significa que, para obter os resultados da p�gina 3, voc� precisa pular os 20 primeiros resultados. 
Esse � o prop�sito do m�todo getOffSet(): determinar quantos itens devem ser pulados na consulta para 
obter os resultados da p�gina desejada.

*/