package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.entity.Pessoa;
/*
 "PreparedStatement" - É o agente que encapsula e em seguida executa a consulta no banco de dados.
 */

public class PessoaRepository implements BaseRepository<Pessoa>{
	
	public boolean verificar_CPF(Pessoa novaPessoa) {
		 Connection conn = Banco.getConnection();
		 Statement stmt = Banco.getStatement(conn);
		 ResultSet resultado = null;
		 boolean cpfExiste = false;
		 String query = "select  *  from VACINACAO.PESSOA where PESSOA.id_Pessoa ="+novaPessoa.getCpf();
		 try {
			 resultado = stmt.executeQuery(query);
			 cpfExiste = resultado.next();
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
	
	// OK!
	@Override
	 public Pessoa salvar(Pessoa novaPessoa) {
		 String query = "INSERT INTO VACINACAO.PESSOA (tipo,nome, dataNascimento, sexo, cpf)VALUES(?,?,?,?,?);";
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
	            + "tipo = ?, nome = ?, dataNascimento = ?, sexo = ?, cpf = ? WHERE id_Pessoa = ?";
	    Connection conn = Banco.getConnection();
	    PreparedStatement pstmt = Banco.getPreparedStatement(conn, query);

	    try {
	        pstmt.setInt(1, pessoaParaAlterar.getTipo());
	        pstmt.setString(2, pessoaParaAlterar.getNome());
	        pstmt.setDate(3, Date.valueOf(pessoaParaAlterar.getDataNascimento()));
	        pstmt.setString(4, pessoaParaAlterar.getSexo());
	        pstmt.setString(5, pessoaParaAlterar.getCpf());
	        pstmt.setInt(6, pessoaParaAlterar.getIdPessoa());

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
	     pstmt.setInt(1, novaPessoa.getTipo());
		 pstmt.setString(2, novaPessoa.getNome());
		 pstmt.setDate(3, Date.valueOf(novaPessoa.getDataNascimento()));
		 pstmt.setString(4,String.valueOf(novaPessoa.getSexo()));
		 pstmt.setString(5, novaPessoa.getCpf());
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
		Pessoa pessoa = new Pessoa();
		String query="SELECT * FROM VACINACAO.PESSOA WHERE PESSOA.id_Pessoa = "+id;
		try {
			resultado = stmt.executeQuery(query);
			if(resultado.next()) {
				/*A utilização do termo "Integer" no método Integer.parseInt pode causar 
				  confusão, mas é importante entender que, neste contexto, "Integer" se
				   refere ao tipo de dados em Java que representa números inteiros, não 
				   a objetos do tipo Integer. O método parseInt faz parte da classe utilitária 
				   Integer em Java, que contém métodos estáticos para operações relacionadas 
				   a números inteiros. Apesar de ser chamado "parseInt," o resultado do método 
				   é um valor primitivo do tipo int, não um objeto Integer. A escolha do nome
				    Integer.parseInt pode parecer um pouco enganosa, mas é um remanescente 
				    da época em que a programação orientada a objetos era mais enfatizada 
				    em Java, e a conversão de tipos primitivos para objetos (como Integer) 
				    era comum. Ainda assim, o método retorna um valor primitivo int. Se fosse 
				    projetado nos dias de hoje, poderia ter um nome mais descritivo, como 
				    convertStringToInt.
				 */
				pessoa.setIdPessoa(Integer.parseInt(resultado.getString("Id_Pessoa")));
				pessoa.setTipo(resultado.getInt("tipo"));
				pessoa.setNome(resultado.getString("nome"));
				if(resultado.getDate("dataNascimento")!=null) {
					pessoa.setDataNascimento(resultado.getDate("dataNascimento").toLocalDate());
				}
				pessoa.setSexo(resultado.getString("sexo"));
				pessoa.setCpf(resultado.getString("cpf"));
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
	
	@Override
	public ArrayList<Pessoa> consultarTodos() {
		ArrayList<Pessoa> pessoas = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		String query = " SELECT * FROM VACINACAO.PESSOA";
		try{
			resultado = stmt.executeQuery(query);
			while(resultado.next()){
				Pessoa pessoa = new Pessoa();
				pessoa.setIdPessoa(Integer.parseInt(resultado.getString("id_Pessoa")));
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
			System.out.println("Erro ao executar consultar todas as cartas");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return pessoas;
	}
	
}

