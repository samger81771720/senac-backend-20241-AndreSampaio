/*REGRAS:

  Salvar nova pessoa: 

- "Todos os campos são obrigatórios, caso algum não tenha sido preenchido o 
serviço deve retornar uma ControleVacinasException, com uma mensagem 
amigável"

"CPF deve ser único no banco"

"Listar todas e excluir não possuem regras específicas"
 
 */

package model.service;

import java.util.List;
import exception.ControleVacinasException;
import model.entity.Pessoa;
import model.repository.PessoaRepository;

public class PessoaService {
	
	private static final String SEXO_MASCULINO = "m";
	private static final String SEXO_FEMININO = "f";
	private static final int PESQUISADOR = 1 ;
	private static final int VOLUNTARIO = 2 ;
	private static final int PUBLICO_GERAL = 3;
	
	PessoaRepository repository = new PessoaRepository();
	
	// OK!
	public Pessoa salvar(Pessoa novaPessoa) throws ControleVacinasException{
		validarPessoaParaCadastro(novaPessoa);
		return repository.salvar(novaPessoa);
	}
	
	
	public boolean alterar(Pessoa pessoaParaAlterar) throws ControleVacinasException{
		validarPessoaParaAtualizarCadastro(pessoaParaAlterar);
		return repository.alterar(pessoaParaAlterar);
	}
	
	/**
	 * " Verifica se todos todos os métodos necessários para o cadastro da pessoa foram validados corretamente. "
	 * @param novaPessoa
	 * @throws ControleVacinasException
	 * @return void
	 */
	// OK!
	private void validarPessoaParaCadastro(Pessoa novaPessoa) throws ControleVacinasException {
		verificar_CPF_Para_Cadastro(novaPessoa);
		validarNumerosCPF(novaPessoa);
		validarCamposPreenchidosDePessoa(novaPessoa);
	}
	
	/**
	 * " Verifica se todos os métodos necessários  para atualizar o cadastro da pessoa foram validados corretamente. "
	 * @param novaPessoa
	 * @throws ControleVacinasException
	 * @return void
	 */
	private void validarPessoaParaAtualizarCadastro(Pessoa novaPessoa) throws ControleVacinasException {
		verificar_CPF_Para_Atualizar(novaPessoa);
		validarNumerosCPF(novaPessoa);
		validarCamposPreenchidosDePessoa(novaPessoa);
	}
	
	/**
	 * "Verifica se o cpf informado no cadastro já existe  no sistema".
	 * @param id
	 * @throws ControleVacinasException
	 * @return void
	 */
	// OK !
	private void verificar_CPF_Para_Cadastro(Pessoa novaPessoa) throws ControleVacinasException{
       if(repository.verificar_CPF_Para_Cadastro(novaPessoa)!=false) {
        	throw new ControleVacinasException("Pessoa já cadastrada no sistema");
        } 
	}
	
	
	private void verificar_CPF_Para_Atualizar(Pessoa novaPessoa) throws ControleVacinasException{
	       if(repository.verificar_CPF_Para_Atualizar(novaPessoa)!=true) {
	        	throw new ControleVacinasException("Não é possível alterar o número do cpf.");
	        } 
		}

	/**
	 * "Garante que o cpf da pessoa seja preenchido 
	 * com números de 0 à 9 e que sejam 11 números. "
	 * @param novaPessoa
	 * @return void
	 */
	// OK!
	private  void validarNumerosCPF(Pessoa novaPessoa)throws ControleVacinasException {
		if(!novaPessoa.getCpf().matches("\\d{11}")) {
			throw new ControleVacinasException("O campo CPF precisa ser preenchido apenas com números e deve ter 11 dígitos");
		}
	}
	
	/**
	 * "Verifica se todos os campos foram preenchidos conforme os critérios estabelecidos pelo sistema."
	 * @param novaPessoa
	 * @throws ControleVacinasException
	 * @return void
	 */
	// OK!
	private void validarCamposPreenchidosDePessoa(Pessoa novaPessoa) throws ControleVacinasException {
	    if (novaPessoa.getTipo() != PESQUISADOR && novaPessoa.getTipo() != VOLUNTARIO && novaPessoa.getTipo() != PUBLICO_GERAL) {
	        throw new ControleVacinasException("Tipo de pessoa inválido. Deve ser Pesquisador, Voluntário ou Público Geral.");
	    }
	    if (novaPessoa.getNome() == null || novaPessoa.getNome().isEmpty()) {
	        throw new ControleVacinasException("O campo Nome é obrigatório.");
	    }
	    if (novaPessoa.getDataNascimento() == null) {
	        throw new ControleVacinasException("O campo Data de Nascimento é obrigatório.");
	    }
	    if (!novaPessoa.getSexo().toLowerCase().equals(SEXO_MASCULINO) && !novaPessoa.getSexo().toLowerCase().equals(SEXO_FEMININO)) {
	        throw new ControleVacinasException("O campo Sexo é inválido. Deve ser 'm' para masculino ou 'f' para feminino.");
	    }
	}

	// OK - FUNCIONANDO!
	public boolean excluir(int id) {
		return repository.excluir(id);
	}
	
	// OK - FUNCIONANDO!
	public Pessoa consultarPorId(int id) {
		return repository.consultarPorId(id);
	}
	
	// OK - FUNCIONANDO!
	public List<Pessoa> consultarTodas() {
		return repository.consultarTodos();
	}

}

/*
O método cps.matches("\\d{11}") faz parte da classe String em Java e é 
usado para verificar se a String atende a um padrão específico, chamado 
de expressão regular.

cps: Esta é a variável que armazena o CPS (Cadastro de Pessoas Físicas) 
inserido pelo usuário. Assume-se que seja do tipo String.

.matches(): Este é um método em Java que é utilizado para verificar se a 
String dada corresponde à expressão regular especificada.

"\\d{11}": Esta é a expressão regular:

	\\d: Essa parte do padrão corresponde a qualquer dígito (0-9).
	{11}: Isso especifica que exatamente 11 dígitos devem ser correspondidos.
	Então, a expressão completa \\d{11} assegura que a string cps consista exatamente em 11 dígitos.

	Em resumo, cps.matches("\\d{11}") retorna true se a string cps contiver exatamente 11 dígitos e false caso contrário. É uma maneira simples, mas eficaz, de validar se o CPS inserido está no formato especificado.
*/
