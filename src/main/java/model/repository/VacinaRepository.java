package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Pessoa;
import model.entity.Vacina;

public class VacinaRepository implements BaseRepository<Vacina>{
	
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
	
	@Override
	public Vacina consultarPorId(int id) {
		return null;
	}
	
	@Override
	public ArrayList<Vacina> consultarTodos(){
		return null;
	};

}
