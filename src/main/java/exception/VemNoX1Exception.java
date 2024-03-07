package exception;

/**
 * No Java, a palavra-chave extends é usada para criar uma 
 * relação de herança entre classes. Quando uma classe herda
 *  de outra, ela adquire os membros (métodos e campos) da 
 *  classe pai. No contexto de exceções, a palavra-chave extends 
 *  é utilizada para criar uma nova classe de exceção que é uma
 *   subclasse da classe base Exception.
 */

public class VemNoX1Exception extends Exception {

	public VemNoX1Exception(String message) {
		super(message);
		// TODO Stub de construtor gerado automaticamente
	}

	/**
	 * Logo abaixo está declarado um construtor da classe VemNoX1Exception. Em Java, um 
	 * construtor é um bloco de código especial chamado quando um 
	 * objeto é criado. O objetivo deste construtor é inicializar uma instância 
	 * da classe VemNoX1Exception com uma mensagem específica.

	A chamada super(mensagem); dentro do construtor invoca o construtor
	 da classe pai, que é a classe Exception. Neste contexto, super refere-se 
	 à classe pai. Isso significa que estamos chamando o construtor da classe 
	 Exception e passando a mensagem fornecida como argumento.
	 */

	
	/*public VemNoX1Exception(String mensagem) {
		super(mensagem);
	}*/
	
}
