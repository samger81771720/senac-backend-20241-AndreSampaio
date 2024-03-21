/*REGRAS:

  Salvar nova pessoa: 

- "Todos os campos são obrigatórios, caso algum não tenha sido preenchido o 
serviço deve retornar uma ControleVacinasException, com uma mensagem 
amigável"

"CPF deve ser único no banco"

"Listar todas e excluir não possuem regras específicas"
 
 */

/*throw: Usado para lançar explicitamente uma exceção dentro de um método.
throws: Usado na assinatura de um método para indicar que esse método pode 
lançar uma ou mais exceções e que métodos que o chamam devem lidar com essas 
exceções ou também declarar que podem lançá-las.*/


package model.service;

import java.util.ArrayList;
import java.util.List;
import exception.ControleVacinasException;
import model.entity.Aplicacao;
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
	 * " Verifica se todos todos os métodos necessários para o cadastro da pessoa foram validados corretamente. "
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
	 * " Verifica se todos os métodos necessários  para atualizar o cadastro da pessoa foram validados corretamente. "
	 * @param novaPessoa
	 * @throws ControleVacinasException
	 * @return void
	 */
	private void validarPessoaParaAtualizarCadastro(Pessoa novaPessoa) throws ControleVacinasException {
		verificar_CPF_Para_Atualizar(novaPessoa);
		validarCamposPreenchidosDePessoa(novaPessoa);
	}
	
	/**
	 * "Verifica se o cpf informado no cadastro já existe  no sistema".
	 * @param id
	 * @throws ControleVacinasException
	 * @return void
	 */
	// OK !
	private void verificar_CPF_Para_Cadastro(Pessoa pessoa) throws ControleVacinasException{
       if(repository.verificar_CPF_Para_Cadastro(pessoa)!=false) {
        	throw new ControleVacinasException("O cpf "+pessoa.getCpf()+" de "+pessoa.getNome()+" já se encontra cadastrado no sistema.");
        } 
	}
	//OK !
	private void verificar_CPF_Para_Atualizar(Pessoa novaPessoa) throws ControleVacinasException{
	       if(repository.verificar_CPF_Para_Atualizar(novaPessoa)!=true) {
	        	throw new ControleVacinasException("Não é possível alterar o número de cpf no cadastro.");
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
	   String mensagemValidacao = "";
	   String cpf = novaPessoa.getCpf();
		if (novaPessoa.getTipo() != PESQUISADOR  && novaPessoa.getTipo() != VOLUNTARIO && novaPessoa.getTipo() != PUBLICO_GERAL) {
			mensagemValidacao += " - A pessoa para ser cadastrada precisa ser um \"PESQUISADOR\", um \"VOLUNTÁRIO\" ou pertencer "
					+ "ao \"PÚBLICO GERAL\", ou seja, \"1\", \"2\" ou \"3\". \n";
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
	        mensagemValidacao += " - O campo sexo precisa ser informado apenas com um dois valores que são: \"M\" ou \"F\". \n";
	    }
	   if(novaPessoa.getNome().trim().isEmpty()) {
		   mensagemValidacao += " - O campo nome não pode ser preenchido apenas com espaços. \n";
	    }
	    if(novaPessoa.getCpf().isBlank()) {
	    	mensagemValidacao += " - O campo \"cpf\" precisa ser preenchido. \n";
	    }
	    if(novaPessoa.getCpf().length()!=11) {
	    	mensagemValidacao += " - O campo \"cpf\" precisa ter 11 números. \n";
	    }
	    if(!cpf.matches("[0-9]+")) {
	    	mensagemValidacao += " - O campo \"cpf\" precisa ser preenchido apenas com números. \n";
	    	/*O método matches() é um método fornecido pela classe String em Java, 
	    	 que é usado para verificar se uma string corresponde a um determinado 
	    	 padrão especificado por uma expressão regular. Ele retorna true se a string 
	    	 corresponder ao padrão especificado. Ele retorna false se a string não corresponder 
	    	 ao padrão. Expressão regular para verificar se o CPF contém apenas números:

	    	A expressão regular "[0-9]+" é uma maneira de descrever um padrão de texto em que estamos 
	    	procurando por um ou mais dígitos de 0 a 9. Aqui está o que cada parte significa:
	    	[0-9]: Este é um conjunto de caracteres que inclui todos os dígitos de 0 a 9.
	    	+: Este é um quantificador que indica que o padrão anterior (no nosso caso, [0-9]) deve aparecer 
	    	uma ou mais vezes.
	    	Portanto, a expressão regular "[0-9]+" corresponde a qualquer sequência de um ou mais dígitos 
	    	de 0 a 9 em uma string. Quando aplicada à string do CPF, se ela contiver apenas dígitos de 0 a 9,
	    	a expressão regular corresponderá a toda a string. Se houver outros caracteres, a correspondência falhará.
	    	*/
	    }
	    if(!mensagemValidacao.isEmpty()) {
	    	throw new ControleVacinasException("As observaçõe(s) a seguir precisa(m) ser atendida(s): \n"+mensagemValidacao);
	    }
	}
	
	// VERIFICAR
	public boolean excluir(int id) throws ControleVacinasException{
		verificarSePessoaFoiVacinada(id); 

		return repository.excluir(id);
	}
	
	// VERIFICAR
	public void verificarSePessoaFoiVacinada(int id) throws ControleVacinasException {
		if(repository.verificarSePessoaFoiVacinada(id)) {
			throw new ControleVacinasException("Não é possível excluir a pessoa, pois já possui registro(s) de vacina(s)");
		} 
	}
	
	// OK - FUNCIONANDO!
	public Pessoa consultarPorId(int id) throws ControleVacinasException{
		if(repository.consultarPorId(id)==null) {
			throw new ControleVacinasException("Não foi possível encontrar a pessoa com o id informado.");
		}
		return repository.consultarPorId(id);
	}
	
	// OK - FUNCIONANDO!
	public List<Pessoa> consultarTodos() {
		return repository.consultarTodos();
	}

}
