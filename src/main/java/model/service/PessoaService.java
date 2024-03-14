/*REGRAS:

  Salvar nova pessoa: 

- "Todos os campos s�o obrigat�rios, caso algum n�o tenha sido preenchido o 
servi�o deve retornar uma ControleVacinasException, com uma mensagem 
amig�vel"

"CPF deve ser �nico no banco"

"Listar todas e excluir n�o possuem regras espec�ficas"
 
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
	 * " Verifica se todos todos os m�todos necess�rios para o cadastro da pessoa foram validados corretamente. "
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
	 * " Verifica se todos os m�todos necess�rios  para atualizar o cadastro da pessoa foram validados corretamente. "
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
	 * "Verifica se o cpf informado no cadastro j� existe  no sistema".
	 * @param id
	 * @throws ControleVacinasException
	 * @return void
	 */
	// OK !
	private void verificar_CPF_Para_Cadastro(Pessoa novaPessoa) throws ControleVacinasException{
       if(repository.verificar_CPF_Para_Cadastro(novaPessoa)!=false) {
        	throw new ControleVacinasException("Pessoa j� cadastrada no sistema");
        } 
	}
	
	
	private void verificar_CPF_Para_Atualizar(Pessoa novaPessoa) throws ControleVacinasException{
	       if(repository.verificar_CPF_Para_Atualizar(novaPessoa)!=true) {
	        	throw new ControleVacinasException("N�o � poss�vel alterar o n�mero do cpf.");
	        } 
		}

	/**
	 * "Garante que o cpf da pessoa seja preenchido 
	 * com n�meros de 0 � 9 e que sejam 11 n�meros. "
	 * @param novaPessoa
	 * @return void
	 */
	// OK!
	private  void validarNumerosCPF(Pessoa novaPessoa)throws ControleVacinasException {
		if(!novaPessoa.getCpf().matches("\\d{11}")) {
			throw new ControleVacinasException("O campo CPF precisa ser preenchido apenas com n�meros e deve ter 11 d�gitos");
		}
	}
	
	/**
	 * "Verifica se todos os campos foram preenchidos conforme os crit�rios estabelecidos pelo sistema."
	 * @param novaPessoa
	 * @throws ControleVacinasException
	 * @return void
	 */
	// OK!
	private void validarCamposPreenchidosDePessoa(Pessoa novaPessoa) throws ControleVacinasException {
	    if (novaPessoa.getTipo() != PESQUISADOR && novaPessoa.getTipo() != VOLUNTARIO && novaPessoa.getTipo() != PUBLICO_GERAL) {
	        throw new ControleVacinasException("Tipo de pessoa inv�lido. Deve ser Pesquisador, Volunt�rio ou P�blico Geral.");
	    }
	    if (novaPessoa.getNome() == null || novaPessoa.getNome().isEmpty()) {
	        throw new ControleVacinasException("O campo Nome � obrigat�rio.");
	    }
	    if (novaPessoa.getDataNascimento() == null) {
	        throw new ControleVacinasException("O campo Data de Nascimento � obrigat�rio.");
	    }
	    if (!novaPessoa.getSexo().toLowerCase().equals(SEXO_MASCULINO) && !novaPessoa.getSexo().toLowerCase().equals(SEXO_FEMININO)) {
	        throw new ControleVacinasException("O campo Sexo � inv�lido. Deve ser 'm' para masculino ou 'f' para feminino.");
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
O m�todo cps.matches("\\d{11}") faz parte da classe String em Java e � 
usado para verificar se a String atende a um padr�o espec�fico, chamado 
de express�o regular.

cps: Esta � a vari�vel que armazena o CPS (Cadastro de Pessoas F�sicas) 
inserido pelo usu�rio. Assume-se que seja do tipo String.

.matches(): Este � um m�todo em Java que � utilizado para verificar se a 
String dada corresponde � express�o regular especificada.

"\\d{11}": Esta � a express�o regular:

	\\d: Essa parte do padr�o corresponde a qualquer d�gito (0-9).
	{11}: Isso especifica que exatamente 11 d�gitos devem ser correspondidos.
	Ent�o, a express�o completa \\d{11} assegura que a string cps consista exatamente em 11 d�gitos.

	Em resumo, cps.matches("\\d{11}") retorna true se a string cps contiver exatamente 11 d�gitos e false caso contr�rio. � uma maneira simples, mas eficaz, de validar se o CPS inserido est� no formato especificado.
*/
