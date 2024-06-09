package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.entity.Pais;
import model.entity.Pessoa;
import model.entity.Vacina;


public class PaisRepository implements BaseRepository<Pais> {
	
	
	public Pais consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		Pais pais = new Pais();
		String query="SELECT * FROM VACINACAO.PAIS WHERE PAIS.id_Pais = "+id;
		try {
			resultado = stmt.executeQuery(query);
			if(resultado.next()) {
				pais.setId_Pais(resultado.getInt("id_Pais"));
				pais.setNome(resultado.getString("nome"));
				pais.setSigla(resultado.getString("sigla"));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao tentar consultar o país de id "+id);
			System.out.println("Erro: "+erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return pais;
	}

	@Override
	public Pais salvar(Pais novoPais) {
		String sql = "insert into VACINACAO.PAIS (nome, sigla) values(?, ?)";
		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		try {
			stmt.setString(1, novoPais.getNome());
			stmt.setString(2, novoPais.getSigla());
			stmt.execute();
			ResultSet resultado = stmt.getGeneratedKeys();
			if(resultado.next()) {
				novoPais.setId_Pais(resultado.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao salvar novo país");
			System.out.println("Erro: " + e.getMessage());
		}
		return novoPais;
	}

	@Override
	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;
		String query = "delete from VACINACAO.PAIS where PAIS.id_Pais = " + id;
		try {
			if(stmt.executeUpdate(query) == 1) {
				excluiu = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao tentar excluir o país do cadastro.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return excluiu;
	}

	@Override
	public boolean alterar(Pais pais) {
	    boolean alterou = false;
	    String query = " update "
								    		+ " VACINACAO.PAIS "
								    		+ " set "
								    		+ " nome = ?, "
								    		+ " sigla = ? "
								    		+ " where "
								    		+ " id_Pais = ? ";
	    Connection conn = Banco.getConnection();
	    PreparedStatement pstmt = Banco.getPreparedStatement(conn, query);
	    try {
	    	pstmt.setString(1, pais.getNome());
	    	pstmt.setString(2, pais.getSigla());
	        pstmt.setInt(3, pais.getId_Pais());
	        alterou = pstmt.executeUpdate() > 0;
	    } catch (SQLException erro) {
	        System.out.println("Erro ao atualizar pais.");
	        System.out.println("Erro: " + erro.getMessage());
	    } finally {
	        Banco.closeStatement(pstmt);
	        Banco.closeConnection(conn);
	    }
	    return alterou;
	}

	@Override
	public ArrayList<Pais> consultarTodos() {
		ArrayList<Pais> paises = new ArrayList<Pais>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		String query = "select * from VACINACAO.PAIS";
		try{
			resultado = stmt.executeQuery(query);
			while(resultado.next()){
				Pais pais = new Pais();
				pais.setId_Pais(resultado.getInt("id_Pais"));
				pais.setNome(resultado.getString("nome"));
				pais.setSigla(resultado.getString("sigla"));
				paises.add(pais);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao executar consultar todos os países.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return paises;
	}
	
	
	
}
