package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Aplicacao;
import model.entity.Pessoa;
import model.entity.Vacina;

public class VacinaRepository implements BaseRepository<Vacina>{
	
	// OK !
	public int validarPesquisador(Vacina vacina) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		int tipoDaPessoa = 0;
		String query ="select \r\n"
				+ "	VACINACAO.PESSOA.tipo \r\n"
				+ "from\r\n"
				+ "	VACINACAO.PESSOA\r\n"
				+ "where 	\r\n"
				+ "	VACINACAO.PESSOA.nome ='"
				+vacina.getPesquisadorResponsavel().getNome()+"'";
		try {
			resultado = stmt.executeQuery(query);
			if(resultado.next()) {
				tipoDaPessoa = resultado.getInt("tipo");
				}
			}catch (SQLException erro) {
				System.out.println("Erro ao tentar consultar o pesquisador "
						+vacina.getPesquisadorResponsavel().getNome());
				System.out.println("Erro: "+erro.getMessage());
			} finally {
				Banco.closeResultSet(resultado);
				Banco.closeStatement(stmt);
				Banco.closeConnection(conn);
			}
		return tipoDaPessoa;
}
	
	//OK!
	@Override
	public Vacina salvar(Vacina novaVacina) {
	   String queryPesquisador = "SELECT PESSOA.id_Pessoa FROM VACINACAO.PESSOA WHERE PESSOA.nome = ?";
	   String queryInsert = "INSERT INTO VACINACAO.VACINA (id_Pesquisador, nome, pais_Origem, estagio_Da_Pesquisa, dataInicioDaPesquisa) "
	    		+ "VALUES (?, ? , ? , ?, ? );";
	    Connection conn = Banco.getConnection();
	    PreparedStatement pstmtPesquisador = Banco.getPreparedStatement(conn, queryPesquisador);
	    PreparedStatement pstmtInsert = Banco.getPreparedStatementWithPk(conn, queryInsert);
	    try {
	    	pstmtPesquisador.setString(1, novaVacina.getPesquisadorResponsavel().getNome());
	    	ResultSet resultadoPesquisador = pstmtPesquisador.executeQuery();
	        if(resultadoPesquisador.next()) {
	        	int pesquisador_id = resultadoPesquisador.getInt(1);
	            preencherParametrosParaInsertOuUpdate(pstmtInsert, novaVacina, pesquisador_id);
	            pstmtInsert.execute();
	            ResultSet resultado = pstmtInsert.getGeneratedKeys();
	            if(resultado.next()) {
	            	novaVacina.setIdVacina(resultado.getInt(1));
	            }
	        }
	    } catch(SQLException erro){
	        System.out.println("Erro na tentativa de salvar uma nova vacina no banco de dados.");
	        System.out.println("Erro: " + erro.getMessage());
	    } finally {
	        Banco.closeStatement(pstmtPesquisador);
	        Banco.closeStatement(pstmtInsert);
	        Banco.closeConnection(conn);
	    }
	    return novaVacina; 
	}

	// OK!
	private void preencherParametrosParaInsertOuUpdate(PreparedStatement pstmt, Vacina novaVacina, int pesquisador_id) throws SQLException  {
	    pstmt.setInt(1, pesquisador_id);
	    pstmt.setString(2, novaVacina.getNome());
	    pstmt.setString(3, novaVacina.getPais_De_Origem());
	    pstmt.setInt(4, novaVacina.getEstagioDaVacina());
	    pstmt.setDate(5, Date.valueOf(novaVacina.getDataInicioPesquisa()));
	}
	
	// OK!
	@Override
	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;
		String query = "delete from VACINACAO.VACINA where VACINA.id_Vacina = " + id;
		try {
			if(stmt.executeUpdate(query) == 1) {
				excluiu = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao tentar excluir a vacina do cadastro.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return excluiu;
	}
	
	// OK!
	@Override
	public boolean alterar(Vacina vacinaParaAlterar) {
	    boolean alterou = false;
	    String query = "update \r\n"
	    		+ "	VACINACAO.VACINA \r\n"
	    		+ "set \r\n"
	    		+ "	VACINA.id_Pesquisador = ?, \r\n"
	    		+ "	VACINA.nome = ?, \r\n"
	    		+ "	VACINA.pais_Origem = ?, \r\n"
	    		+ "	VACINA.estagio_Da_Pesquisa = ?, \r\n"
	    		+ "	VACINA.dataInicioDaPesquisa=?\r\n"
	    		+ "where \r\n"
	    		+ "	VACINA.id_Vacina = ?;\r\n"
	    		+ "";
	    Connection conn = Banco.getConnection();
	    PreparedStatement pstmt = Banco.getPreparedStatement(conn, query);
	    try {
	    	pstmt.setInt(1, vacinaParaAlterar.getPesquisadorResponsavel().getIdPessoa());
	        pstmt.setString(2, vacinaParaAlterar.getNome());
	        pstmt.setString(3, vacinaParaAlterar.getPais_De_Origem());
	        pstmt.setInt(4, vacinaParaAlterar.getEstagioDaVacina());
	        pstmt.setDate(5, Date.valueOf(vacinaParaAlterar.getDataInicioPesquisa()));
	        pstmt.setInt(6, vacinaParaAlterar.getIdVacina());
	        alterou = pstmt.executeUpdate() > 0;
	    } catch (SQLException erro) {
	        System.out.println("Erro ao atualizar Vacina");
	        System.out.println("Erro: " + erro.getMessage());
	    } finally {
	        Banco.closeStatement(pstmt);
	        Banco.closeConnection(conn);
	    }
	    return alterou;
	}
	
	// OK!
	@Override
	public Vacina consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		Vacina vacina = new Vacina();
		Pessoa pesquisadorResponsavelVacina = new Pessoa();
		String query="select\r\n"
				+ "	VACINACAO.VACINA.*,\r\n"
				+ "	VACINACAO.PESSOA.nome as nomePesquisador,\r\n"
				+ "	VACINACAO.PESSOA.dataNascimento as dataNascimento,\r\n"
				+ "	VACINACAO.PESSOA.sexo as sexo,\r\n"
				+ "	VACINACAO.PESSOA.cpf as cpf,\r\n"
				+ "	VACINACAO.PESSOA.tipo as tipoPesqusiador\r\n"
				+ "from\r\n"
				+ "	VACINACAO.VACINA\r\n"
				+ "join\r\n"
				+ "	VACINACAO.PESSOA on	VACINACAO.PESSOA.id_Pessoa = VACINACAO.VACINA.id_Pesquisador\r\n"
				+ "where\r\n"
				+ "	VACINACAO.VACINA.id_Vacina  ="+id;
		try {
			resultado = stmt.executeQuery(query);
			if(resultado.next()) {
				vacina.setIdVacina(resultado.getInt("id_Vacina"));
				pesquisadorResponsavelVacina.setIdPessoa(resultado.getInt("id_Pesquisador"));
				pesquisadorResponsavelVacina.setNome(resultado.getString("nomePesquisador"));
				if(resultado.getDate("dataNascimento")!=null) {
					pesquisadorResponsavelVacina.setDataNascimento(resultado.getDate("dataNascimento").toLocalDate());
				}
				pesquisadorResponsavelVacina.setSexo(resultado.getString("sexo"));
				pesquisadorResponsavelVacina.setCpf(resultado.getString("cpf"));
				pesquisadorResponsavelVacina.setTipo(resultado.getInt("tipoPesqusiador"));
				vacina.setPesquisadorResponsavel(pesquisadorResponsavelVacina);
				vacina.setNome(resultado.getString("nome"));
				vacina.setPais_De_Origem(resultado.getString("pais_Origem"));
				vacina.setEstagioDaVacina(resultado.getInt("estagio_Da_Pesquisa"));
				if(resultado.getDate("dataInicioDaPesquisa")!=null) {
					vacina.setDataInicioPesquisa(resultado.getDate("dataInicioDaPesquisa").toLocalDate());
				}
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao tentar consultar a vacina de id "+id);
			System.out.println("Erro: "+erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return vacina;
	}
	
	@Override
	public ArrayList<Vacina> consultarTodos() {
		ArrayList<Vacina> vacinas = new ArrayList<Vacina>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		String query = "select\r\n"
				+ "	VACINACAO.VACINA.*,\r\n"
				+ "	VACINACAO.PESSOA.id_Pessoa as idPesquisador,\r\n"
				+ "	VACINACAO.PESSOA.nome as nomePesquisador,\r\n"
				+ "	VACINACAO.PESSOA.dataNascimento as dataNascimento,\r\n"
				+ "	VACINACAO.PESSOA.sexo as sexo,\r\n"
				+ "	VACINACAO.PESSOA.cpf as cpf,\r\n"
				+ "	VACINACAO.PESSOA.tipo as tipoPesquisador\r\n"
				+ "from\r\n"
				+ "	VACINACAO.VACINA\r\n"
				+ "join\r\n"
				+ "	VACINACAO.PESSOA on	VACINACAO.PESSOA.id_Pessoa = VACINACAO.VACINA.id_Pesquisador;";
		try{
			resultado = stmt.executeQuery(query);
			while(resultado.next()){
				Vacina vacina = new Vacina();
				Pessoa pesquisadorResponsavel = new Pessoa();
				vacina.setIdVacina(resultado.getInt("id_Vacina"));
				pesquisadorResponsavel.setIdPessoa(resultado.getInt("idPesquisador"));
				pesquisadorResponsavel.setNome(resultado.getString("nomePesquisador"));
				if(resultado.getDate("dataNascimento")!=null) {
					pesquisadorResponsavel.setDataNascimento(resultado.getDate("dataNascimento").toLocalDate());
				}
				pesquisadorResponsavel.setSexo(resultado.getString("sexo"));
				pesquisadorResponsavel.setCpf(resultado.getString("cpf"));
				pesquisadorResponsavel.setTipo(resultado.getInt("tipoPesquisador"));
				vacina.setPesquisadorResponsavel(pesquisadorResponsavel);
				
				vacina.setNome(resultado.getString("nome"));
				vacina.setPais_De_Origem(resultado.getString("pais_Origem"));
				vacina.setEstagioDaVacina(resultado.getInt("estagio_Da_Pesquisa"));
				if(resultado.getDate("dataInicioDaPesquisa")!=null) {
					vacina.setDataInicioPesquisa(resultado.getDate("dataInicioDaPesquisa").toLocalDate());
				}
				vacinas.add(vacina);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao executar consultar todas as vacinas.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return vacinas;
	}
	
	public ArrayList<Aplicacao> consultarTodasAplicacoesDaVacina(int id){
		return null;
	}
	
}
