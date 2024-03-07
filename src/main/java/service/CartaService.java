package service;
import exception.VemNoX1Exception;
import model.entity.vemnox1.Carta;
import repository.CartaRepository;

public class CartaService {

	/**
	 * A palavra-chave "static" indica que a constante MAXIMO_ATRIBUTOS_PERMITIDO 
	 * pertence � classe CartaService, n�o a uma inst�ncia espec�fica dessa classe.
	 *  Isso significa que a constante � compartilhada por todas as inst�ncias (objetos) 
	 *  da classe e pode ser acessada diretamente atrav�s da classe, sem a necessidade 
	 *  de criar uma inst�ncia espec�fica.
	 */
	private static final int MAXIMO_ATRIBUTOS_PERMITIDO = 10;
	private CartaRepository repository = new CartaRepository();
	
/**
 * A palavra-chave "throws" na assinatura do m�todo indica que o m�todo pode lan�ar 
 * uma exce��o espec�fica, neste caso, VemNoX1Exception. Isso significa que quem usar 
 * esse m�todo deve lidar com a possibilidade dessa exce��o ocorrer.
 */
	public Carta salvar(Carta novaCarta) throws VemNoX1Exception{
		int totalPontosAtributos = novaCarta.getForca()+novaCarta.getInteligencia()+novaCarta.getVelocidade();
		if(totalPontosAtributos>MAXIMO_ATRIBUTOS_PERMITIDO) {
			throw new VemNoX1Exception("Excedeu o total de "+MAXIMO_ATRIBUTOS_PERMITIDO+" atributos.");
			/**
			 * Ao utilizar "new", voc� est� instanciando um objeto da classe VemNoX1Exception e o lan�ando como uma exce��o.
			 */
		}
		return repository.salvar(novaCarta);
	}
}

/**
*VemNoX1Exception � uma classe que voc� definiu anteriormente, que 
*herda de Exception. Isso significa que � uma classe de exce��o personalizada.

O construtor de VemNoX1Exception recebe uma mensagem como par�metro, 
e ele chama o construtor da classe pai (Exception) usando super(mensagem). 
Isso configura a mensagem associada � exce��o.

No m�todo salvar da classe CartaService, h� uma verifica��o para garantir 
que o total de pontos de atributos n�o exceda um limite m�ximo (MAXIMO_ATRIBUTOS_PERMITIDO). 
Se essa condi��o n�o for atendida, uma exce��o VemNoX1Exception � lan�ada.

A mensagem espec�fica passada para o construtor � 
"Excedeu o total de " + MAXIMO_ATRIBUTOS_PERMITIDO + " atributos." 
Isso ser� associado � inst�ncia de VemNoX1Exception que est� sendo lan�ada.
*/