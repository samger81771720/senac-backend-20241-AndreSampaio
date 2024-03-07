package exception;

/**
 * No Java, a palavra-chave extends � usada para criar uma 
 * rela��o de heran�a entre classes. Quando uma classe herda
 *  de outra, ela adquire os membros (m�todos e campos) da 
 *  classe pai. No contexto de exce��es, a palavra-chave extends 
 *  � utilizada para criar uma nova classe de exce��o que � uma
 *   subclasse da classe base Exception.
 */

public class VemNoX1Exception extends Exception {

	public VemNoX1Exception(String message) {
		super(message);
		// TODO Stub de construtor gerado automaticamente
	}

	/**
	 * Logo abaixo est� declarado um construtor da classe VemNoX1Exception. Em Java, um 
	 * construtor � um bloco de c�digo especial chamado quando um 
	 * objeto � criado. O objetivo deste construtor � inicializar uma inst�ncia 
	 * da classe VemNoX1Exception com uma mensagem espec�fica.

	A chamada super(mensagem); dentro do construtor invoca o construtor
	 da classe pai, que � a classe Exception. Neste contexto, super refere-se 
	 � classe pai. Isso significa que estamos chamando o construtor da classe 
	 Exception e passando a mensagem fornecida como argumento.
	 */

	
	/*public VemNoX1Exception(String mensagem) {
		super(mensagem);
	}*/
	
}
