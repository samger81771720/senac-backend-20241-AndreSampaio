package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Aplicacao;
import model.entity.Pais;
import model.entity.Pessoa;
import model.entity.Vacina;
/*
 "PreparedStatement" - � o agente que encapsula e em seguida executa a consulta no banco de dados.
 */

public class PessoaRepository implements BaseRepository<Pessoa>{
	
	// OK!
	public boolean verificar_CPF_Para_Cadastro(Pessoa novaPessoa) {
		 Connection conn = Banco.getConnection();
		 Statement stmt = Banco.getStatement(conn);
		 ResultSet resultado = null;
		 boolean cpfExiste = false;
		 String query = "select count(id_Pessoa) as contagem from VACINACAO.PESSOA where	cpf ="+novaPessoa.getCpf();
		 try {
			 resultado = stmt.executeQuery(query);
			 if(resultado.next()){
				 int contagemDoNumeroDeRegistros = resultado.getInt("contagem");
				 cpfExiste = contagemDoNumeroDeRegistros  > 0;
			 }
		 } catch(SQLException erro){
			 System.out.println("Erro na tentativa de verificar se o cpf existe.");
			 System.out.println("Erro: " + erro.getMessage());
		 } finally {
			 Banco.closeResultSet(resultado);
			 Banco.closeStatement(stmt);
			 Banco.closeConnection(conn);
		 }
		return cpfExiste;
	}
	
	public boolean verificar_CPF_Para_Atualizar(Pessoa novaPessoa) {
		 Connection conn = Banco.getConnection();
		 Statement stmt = Banco.getStatement(conn);
		 ResultSet resultado = null;
		 boolean cpfExiste = false;
		 String query = "select PESSOA.cpf as CPF from VACINACAO.PESSOA where PESSOA.id_pessoa ="+novaPessoa.getIdPessoa();
		 try {
			 resultado = stmt.executeQuery(query);
			 if(resultado.next() && resultado.getString("CPF").equals(novaPessoa.getCpf())){
				 cpfExiste = true;
			 }
		 } catch(SQLException erro){
			 System.out.println("Erro na tentativa de verificar se o cpf existe para atualizar.");
			 System.out.println("Erro: " + erro.getMessage());
		 } finally {
			 Banco.closeResultSet(resultado);
			 Banco.closeStatement(stmt);
			 Banco.closeConnection(conn);
		 }
		return cpfExiste;
	}
	
	// OK!
	@Override
	 public Pessoa salvar(Pessoa novaPessoa) {
		 String query = "insert into VACINACAO.PESSOA (id_Pais, tipo, nome, dataNascimento, sexo, cpf)values(?,?,?,?,?,?);";
		 Connection conn = Banco.getConnection();
		 PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		 try {
			 preencherParametrosParaInsertOuUpdate(pstmt, novaPessoa);
			 pstmt.execute();
			 ResultSet resultado = pstmt.getGeneratedKeys();
			 if(resultado.next()) {
				 novaPessoa.setIdPessoa(resultado.getInt(1));
			 }
		 } catch(SQLException erro){
			 System.out.println("Erro na tentativa de salvar uma nova pessoa no banco de dados.");
			 System.out.println("Erro: " + erro.getMessage());
		 } finally {
			 Banco.closeStatement(pstmt);
			 Banco.closeConnection(conn);
		 }
		return novaPessoa; 
	}
	
	@Override
	public boolean alterar(Pessoa pessoaParaAlterar) {
	    boolean alterou = false;
	    String query = "UPDATE VACINACAO.PESSOA SET "
	            + "id_Pais = ?, tipo = ?, nome = ?, dataNascimento = ?, sexo = ?, cpf = ? WHERE id_Pessoa = ?";
	    Connection conn = Banco.getConnection();
	    PreparedStatement pstmt = Banco.getPreparedStatement(conn, query);
	    try {
	    	pstmt.setInt(1, pessoaParaAlterar.getPais().getId_Pais());
	        pstmt.setInt(2, pessoaParaAlterar.getTipo());
	        pstmt.setString(3, pessoaParaAlterar.getNome());
	        if(pessoaParaAlterar.getDataNascimento()!=null) {
	        	pstmt.setDate(4, Date.valueOf(pessoaParaAlterar.getDataNascimento()));
	        }
	        pstmt.setString(5, pessoaParaAlterar.getSexo());
	        pstmt.setString(6, pessoaParaAlterar.getCpf());
	        
	        pstmt.setInt(7, pessoaParaAlterar.getIdPessoa());
	        alterou = pstmt.executeUpdate() > 0;
	    } catch (SQLException erro) {
	        System.out.println("Erro ao atualizar Pessoa");
	        System.out.println("Erro: " + erro.getMessage());
	    } finally {
	        Banco.closeStatement(pstmt);
	        Banco.closeConnection(conn);
	    }
	    return alterou;
	}

		private void preencherParametrosParaInsertOuUpdate(PreparedStatement pstmt, Pessoa novaPessoa) throws SQLException {
	     pstmt.setInt(1, novaPessoa.getPais().getId_Pais());
		 pstmt.setInt(2, novaPessoa.getTipo());
		 pstmt.setString(3, novaPessoa.getNome());
		 pstmt.setDate(4, Date.valueOf(novaPessoa.getDataNascimento()));
		 pstmt.setString(5,String.valueOf(novaPessoa.getSexo()));
		 pstmt.setString(6, novaPessoa.getCpf());
	}
	
	public boolean verificarSePessoaFoiVacinada(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		boolean recebeu = false;
		String query="select * from VACINACAO.APLICACAO_VACINA where VACINACAO.APLICACAO_VACINA.id_Pessoa = "+id;
		try {
			resultado = stmt.executeQuery(query);
			if(resultado.next()) {
				recebeu = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao tentar consultar a(s) aplica��o(s) da pessoa de id "+id);
			System.out.println("Erro: "+erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return recebeu;
	}
		
	@Override
	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;
		String query = "delete from VACINACAO.PESSOA where PESSOA.id_Pessoa = " + id;
		try {
			if(stmt.executeUpdate(query) == 1) {
				excluiu = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao tentar excluir a pessoa do cadastro.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return excluiu;
	}
	
	@Override
	public Pessoa consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		Pessoa pessoa = null;
		String query="SELECT * FROM VACINACAO.PESSOA WHERE PESSOA.id_Pessoa = "+id;
		try {
			resultado = stmt.executeQuery(query);
			PaisRepository paisRepository = new PaisRepository();
			AplicacaoRepository listaDeAplicacoesDaPessoaRepository = new AplicacaoRepository();
			if(resultado.next()) {
				pessoa = new Pessoa();
				pessoa.setIdPessoa(resultado.getInt("Id_Pessoa"));
				pessoa.setTipo(resultado.getInt("tipo"));
				pessoa.setNome(resultado.getString("nome"));
				if(resultado.getDate("dataNascimento")!=null) {
					pessoa.setDataNascimento(resultado.getDate("dataNascimento").toLocalDate());
				}
				pessoa.setSexo(resultado.getString("sexo"));
				pessoa.setCpf(resultado.getString("cpf"));
				pessoa.setPais(paisRepository.consultarPorId(resultado.getInt("id_Pais")));
				pessoa.setAplicacoesNaPessoa(listaDeAplicacoesDaPessoaRepository.consultarTodasAplicacoesDaPessoa(resultado.getInt("id_Pessoa")));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao tentar consultar a pessoa de id "+id);
			System.out.println("Erro: "+erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return pessoa;
	}
	
	/*
	 * O m�todo Integer.parseInt em Java converte uma string 
	 * em um n�mero inteiro. Ele analisa a string fornecida como 
	 * entrada e retorna o valor inteiro correspondente. 
	
	Por exemplo:

	String numeroString = "123";
	int numeroInteiro = Integer.parseInt(numeroString);
*/
	
	//OK
	@Override
	public ArrayList<Pessoa> consultarTodos() {
		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		String query = " SELECT * FROM VACINACAO.PESSOA";
		try{
			resultado = stmt.executeQuery(query);
			PaisRepository paisRepository = new PaisRepository();
			while(resultado.next()){
				Pessoa pessoa = new Pessoa();
				pessoa.setIdPessoa(resultado.getInt("id_Pessoa"));
				Pais paisDaPessoa = paisRepository.consultarPorId(resultado.getInt("id_Pais"));
				pessoa.setPais(paisDaPessoa);
				pessoa.setTipo(Integer.parseInt(resultado.getString("tipo")));
				pessoa.setNome(resultado.getString("nome"));
				if(resultado.getDate("dataNascimento")!=null) {
					pessoa.setDataNascimento(resultado.getDate("dataNascimento").toLocalDate());
				}
				pessoa.setSexo(resultado.getString("sexo"));
				pessoa.setCpf(resultado.getString("cpf"));
				pessoas.add(pessoa);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao executar consultar todas as pessoas");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			
		}
		return pessoas;
	}
	
}

