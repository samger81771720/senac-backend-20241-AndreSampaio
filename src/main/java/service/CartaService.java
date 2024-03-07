package service;
import exception.VemNoX1Exception;
import model.entity.vemnox1.Carta;
import repository.CartaRepository;

public class CartaService {

	/**
	 * A palavra-chave "static" indica que a constante MAXIMO_ATRIBUTOS_PERMITIDO 
	 * pertence à classe CartaService, não a uma instância específica dessa classe.
	 *  Isso significa que a constante é compartilhada por todas as instâncias (objetos) 
	 *  da classe e pode ser acessada diretamente através da classe, sem a necessidade 
	 *  de criar uma instância específica.
	 */
	private static final int MAXIMO_ATRIBUTOS_PERMITIDO = 10;
	private CartaRepository repository = new CartaRepository();
	
/**
 * A palavra-chave "throws" na assinatura do método indica que o método pode lançar 
 * uma exceção específica, neste caso, VemNoX1Exception. Isso significa que quem usar 
 * esse método deve lidar com a possibilidade dessa exceção ocorrer.
 */
	public Carta salvar(Carta novaCarta) throws VemNoX1Exception{
		int totalPontosAtributos = novaCarta.getForca()+novaCarta.getInteligencia()+novaCarta.getVelocidade();
		if(totalPontosAtributos>MAXIMO_ATRIBUTOS_PERMITIDO) {
			throw new VemNoX1Exception("Excedeu o total de "+MAXIMO_ATRIBUTOS_PERMITIDO+" atributos.");
			/**
			 * Ao utilizar "new", você está instanciando um objeto da classe VemNoX1Exception e o lançando como uma exceção.
			 */
		}
		return repository.salvar(novaCarta);
	}
}

/**
*VemNoX1Exception é uma classe que você definiu anteriormente, que 
*herda de Exception. Isso significa que é uma classe de exceção personalizada.

O construtor de VemNoX1Exception recebe uma mensagem como parâmetro, 
e ele chama o construtor da classe pai (Exception) usando super(mensagem). 
Isso configura a mensagem associada à exceção.

No método salvar da classe CartaService, há uma verificação para garantir 
que o total de pontos de atributos não exceda um limite máximo (MAXIMO_ATRIBUTOS_PERMITIDO). 
Se essa condição não for atendida, uma exceção VemNoX1Exception é lançada.

A mensagem específica passada para o construtor é 
"Excedeu o total de " + MAXIMO_ATRIBUTOS_PERMITIDO + " atributos." 
Isso será associado à instância de VemNoX1Exception que está sendo lançada.
*/