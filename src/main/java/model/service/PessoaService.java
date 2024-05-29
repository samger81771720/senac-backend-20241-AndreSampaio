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
import model.entity.Pessoa;
import model.repository.AplicacaoRepository;
import model.repository.PessoaRepository;
import model.seletor.PessoaSeletor;

public class PessoaService {
	
	public static final String SEXO_MASCULINO = "m";
	public static final String SEXO_FEMININO = "f";
	public static final String SEXO_OUTRO = "o";
	public static final int PESQUISADOR = 1 ;
	public static final int VOLUNTARIO = 2 ;
	public static final int PUBLICO_GERAL = 3;
	
	private PessoaRepository pessoaRepository = new PessoaRepository();
	
	private AplicacaoRepository aplicacaoRepository = new AplicacaoRepository();
	
	// OK!
	public Pessoa salvar(Pessoa novaPessoa) throws ControleVacinasException{
		validarPessoaParaCadastro(novaPessoa);
		return pessoaRepository.salvar(novaPessoa);
	}
	
	
	public boolean alterar(Pessoa pessoaParaAlterar) throws ControleVacinasException{
		validarPessoaParaAtualizarCadastro(pessoaParaAlterar);
		return pessoaRepository.alterar(pessoaParaAlterar);
	}
	
	/**
	 * " Verifica se todos todos os métodos necessários para o cadastro da pessoa foram validados corretamente. "
	 * @param novaPessoa
	 * @throws ControleVacinasException
	 * @return void
	 */
	// OK!
	private void validarPessoaParaCadastro(Pessoa novaPessoa) throws ControleVacinasException {
		validarCamposPreenchidosDePessoa(novaPessoa);
		verificar_CPF_Para_Cadastro(novaPessoa);
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
       if(pessoaRepository.verificar_CPF_Para_Cadastro(pessoa)) {
        	throw new ControleVacinasException("O cpf "+pessoa.getCpf()+" de "+pessoa.getNome()+" já se encontra cadastrado no sistema.");
        } 
	}
	//OK !
	private void verificar_CPF_Para_Atualizar(Pessoa novaPessoa) throws ControleVacinasException{
	       if(!pessoaRepository.verificar_CPF_Para_Atualizar(novaPessoa)) {
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
	    if (
	    		!novaPessoa.getSexo().toLowerCase().equals(SEXO_MASCULINO) && 
	    		!novaPessoa.getSexo().toLowerCase().equals(SEXO_FEMININO) &&
	    		!novaPessoa.getSexo().toLowerCase().equals(SEXO_OUTRO)
	    		) {
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
	    if(!novaPessoa.getNome().matches("^[a-zA-Z]+$")) {
	    	mensagemValidacao += " - O campo \"cpf\" precisa ser preenchido apenas com números. \n";
	    }
	    if(!mensagemValidacao.isEmpty()) {
	    	throw new ControleVacinasException("As observaçõe(s) a seguir precisa(m) ser atendida(s): \n"+mensagemValidacao);
	    }
	}
	
	public boolean excluir(int id) throws ControleVacinasException{
		consultarTipoDePessoa(id);
		verificarSeTomouPrimeiraDose(id); 
		return pessoaRepository.excluir(id);
	}
	
	// Pessoa não pode ser excluída caso já tenha recebido pelo menos uma dose vacina; 
	private void verificarSeTomouPrimeiraDose(int id) throws ControleVacinasException {
		if(aplicacaoRepository.consultarTodasAplicacoesDaPessoa(id).size()>0) {
			throw new ControleVacinasException("Não é possível excluir a pessoa, pois já possui um registro de vacina.");
		} 
	}
	
	private void consultarTipoDePessoa(int id) throws ControleVacinasException{
		if(pessoaRepository.consultarPorId(id).getTipo()==PESQUISADOR) {
			throw new ControleVacinasException("Não é possível excluir um pesquisador do cadastro no banco de dados.");
		}
	}
	
	// OK - FUNCIONANDO!
	public Pessoa consultarPorId(int id) throws ControleVacinasException{
		if(pessoaRepository.consultarPorId(id)==null) {
			throw new ControleVacinasException("Não foi possível encontrar a pessoa com o id informado.");
		}
		return pessoaRepository.consultarPorId(id);
	}
	
	// OK - FUNCIONANDO!
	public List<Pessoa> consultarTodos() {
		return pessoaRepository.consultarTodos();
	}
	
	public  ArrayList<Pessoa> consultarPesquisadores() {
		return pessoaRepository.consultarPesquisadores();
	}
	
	public ArrayList<Pessoa> consultarComFiltros(PessoaSeletor seletor){
		return pessoaRepository.consultarComFiltros(seletor);
	}
	
	public int contarTotalRegistros(PessoaSeletor seletor) {
		return pessoaRepository.contarTotalRegistros(seletor);
	}
	
	public int contarPaginas(PessoaSeletor seletor) {
		return pessoaRepository.contarPaginas(seletor);
	}

}
