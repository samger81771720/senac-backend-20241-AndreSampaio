/*REGRAS:

  Salvar nova pessoa: 

- "Todos os campos s�o obrigat�rios, caso algum n�o tenha sido preenchido o 
servi�o deve retornar uma ControleVacinasException, com uma mensagem 
amig�vel"

"CPF deve ser �nico no banco"

"Listar todas e excluir n�o possuem regras espec�ficas"
 
 */

/*throw: Usado para lan�ar explicitamente uma exce��o dentro de um m�todo.
throws: Usado na assinatura de um m�todo para indicar que esse m�todo pode 
lan�ar uma ou mais exce��es e que m�todos que o chamam devem lidar com essas 
exce��es ou tamb�m declarar que podem lan��-las.*/


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
	
	private PessoaRepository repository = new PessoaRepository();
	
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
		validarCamposPreenchidosDePessoa(novaPessoa);
	}
	
	/**
	 * "Verifica se o cpf informado no cadastro j� existe  no sistema".
	 * @param id
	 * @throws ControleVacinasException
	 * @return void
	 */
	// OK !
	private void verificar_CPF_Para_Cadastro(Pessoa pessoa) throws ControleVacinasException{
       if(repository.verificar_CPF_Para_Cadastro(pessoa)!=false) {
        	throw new ControleVacinasException("O cpf "+pessoa.getCpf()+" de "+pessoa.getNome()+" j� se encontra cadastrado no sistema.");
        } 
	}
	//OK !
	private void verificar_CPF_Para_Atualizar(Pessoa novaPessoa) throws ControleVacinasException{
	       if(repository.verificar_CPF_Para_Atualizar(novaPessoa)!=true) {
	        	throw new ControleVacinasException("N�o � poss�vel alterar o n�mero de cpf no cadastro.");
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
	   String mensagemValidacao = "";
	   String cpf = novaPessoa.getCpf();
		if (novaPessoa.getTipo() != PESQUISADOR  && novaPessoa.getTipo() != VOLUNTARIO && novaPessoa.getTipo() != PUBLICO_GERAL) {
			mensagemValidacao += " - A pessoa para ser cadastrada precisa ser um \"PESQUISADOR\", um \"VOLUNT�RIO\" ou pertencer "
					+ "ao \"P�BLICO GERAL\", ou seja, \"1\", \"2\" ou \"3\". \n";
	    }
	    if (novaPessoa.getNome() == null || novaPessoa.getNome().isEmpty()) {
	        mensagemValidacao += " - O campo nome precisa ser preenchido. \n";
	    }
	    if(novaPessoa.getNome().length()<2) {
	    	mensagemValidacao += " - O campo nome precisa ter ao menos duas letras. \n";
	    }
	    if(novaPessoa.getNome().matches("[a-zA-Z]+")) {
	    	mensagemValidacao += "O campo nome precisa ser preenchido apenas com letras. \n";
	    }
	    if (novaPessoa.getDataNascimento() == null) {
	        mensagemValidacao += " - O campo data de nascimento precisa ser preenchido. \n";
	    }
	    if (!novaPessoa.getSexo().toLowerCase().equals(SEXO_MASCULINO) && !novaPessoa.getSexo().toLowerCase().equals(SEXO_FEMININO)) {
	        mensagemValidacao += " - O campo sexo precisa ser informado apenas com um dois valores que s�o: \"M\" ou \"F\". \n";
	    }
	   if(novaPessoa.getNome().trim().isEmpty()) {
		   mensagemValidacao += " - O campo nome n�o pode ser preenchido apenas com espa�os. \n";
	    }
	    if(novaPessoa.getCpf().isBlank()) {
	    	mensagemValidacao += " - O campo \"cpf\" precisa ser preenchido. \n";
	    }
	    if(novaPessoa.getCpf().length()!=11) {
	    	mensagemValidacao += " - O campo \"cpf\" precisa ter 11 n�meros. \n";
	    }
	    if(!cpf.matches("[0-9]+")) {
	    	mensagemValidacao += " - O campo \"cpf\" precisa ser preenchido apenas com n�meros. \n";
	    	/*O m�todo matches() � um m�todo fornecido pela classe String em Java, 
	    	 que � usado para verificar se uma string corresponde a um determinado 
	    	 padr�o especificado por uma express�o regular. Ele retorna true se a string 
	    	 corresponder ao padr�o especificado. Ele retorna false se a string n�o corresponder 
	    	 ao padr�o. Express�o regular para verificar se o CPF cont�m apenas n�meros:

	    	A express�o regular "[0-9]+" � uma maneira de descrever um padr�o de texto em que estamos 
	    	procurando por um ou mais d�gitos de 0 a 9. Aqui est� o que cada parte significa:
	    	[0-9]: Este � um conjunto de caracteres que inclui todos os d�gitos de 0 a 9.
	    	+: Este � um quantificador que indica que o padr�o anterior (no nosso caso, [0-9]) deve aparecer 
	    	uma ou mais vezes.
	    	Portanto, a express�o regular "[0-9]+" corresponde a qualquer sequ�ncia de um ou mais d�gitos 
	    	de 0 a 9 em uma string. Quando aplicada � string do CPF, se ela contiver apenas d�gitos de 0 a 9,
	    	a express�o regular corresponder� a toda a string. Se houver outros caracteres, a correspond�ncia falhar�.
	    	*/
	    }
	    if(!mensagemValidacao.isEmpty()) {
	    	throw new ControleVacinasException("As observa��e(s) a seguir precisa(m) ser atendida(s): \n"+mensagemValidacao);
	    }
	}
	
	public boolean excluir(int id) throws ControleVacinasException{
		verificarSeTomouPrimeiraDose(id); 

		return repository.excluir(id);
	}
	
	// Pessoa n�o pode ser exclu�da caso j� tenha recebido pelo menos uma dose vacina; 
	private void verificarSeTomouPrimeiraDose(int id) throws ControleVacinasException {
		if(repository.verificarSePessoaFoiVacinada(id)) {
			throw new ControleVacinasException("N�o � poss�vel excluir a pessoa, pois j� possui um registro de vacina.");
		} 
	}
	
	// OK - FUNCIONANDO!
	public Pessoa consultarPorId(int id) throws ControleVacinasException{
		if(repository.consultarPorId(id)==null) {
			throw new ControleVacinasException("N�o foi poss�vel encontrar a pessoa com o id informado.");
		}
		return repository.consultarPorId(id);
	}
	
	// OK - FUNCIONANDO!
	public List<Pessoa> consultarTodos() {
		return repository.consultarTodos();
	}

}
