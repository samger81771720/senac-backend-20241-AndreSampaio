package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.entity.Montadora;

public class MontadoraRepository {
	
	public Montadora salvar(Montadora montadora) {
		   String query = " INSERT INTO VEICULOS.MONTADORA ( nome, paisFundacao, nomePresidente, dataFundacao ) "
		   		   					 + " values ( ? , ? , ? , ? ) ";
		    Connection conn = Banco.getConnection();
		    PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		    try {
		    	    this.preencherParametrosParaInsert(pstmt, montadora);
		            pstmt.execute();
		            ResultSet resultado = pstmt.getGeneratedKeys();
		            if(resultado.next()) {
		            	montadora.setId(resultado.getInt(1));
		        }
		    } catch(SQLException erro){
		        System.out.println("Erro na tentativa de salvar uma nova montadora no banco de dados.");
		        System.out.println("Erro: " + erro.getMessage());
		    } finally {
		        Banco.closeStatement(pstmt);
		        Banco.closeConnection(conn);
		    }
		    return montadora; 
	}
	
	public Montadora consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		Montadora montadora = null;
		String query = " select * from VEICULOS.MONTADORA where id = " + id;
		try {
			resultado = stmt.executeQuery(query);
			if(resultado.next()) {
				montadora = this.converterParaObjeto(resultado);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao tentar consultar a montadora de id "+id);
			System.out.println("Erro: "+erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return montadora;
	}

	private void preencherParametrosParaInsert(PreparedStatement pstmt, Montadora montadora) throws SQLException  {
	    pstmt.setString(1, montadora.getNome());
		pstmt.setString(2, montadora.getPaisFundacao());
		pstmt.setString(3, montadora.getNomePresidente());
		if(montadora.getDataFundacao() != null) {
			pstmt.setDate(4, Date.valueOf(montadora.getDataFundacao()));
		}
	}
	
	private Montadora converterParaObjeto(ResultSet resultado) throws SQLException {
		Montadora montadora = new Montadora();
		montadora.setId(resultado.getInt("id"));
		montadora.setNome(resultado.getString("nome"));
		montadora.setPaisFundacao(resultado.getString("paisFundacao"));
		montadora.setNomePresidente(resultado.getString("nomePresidente"));
		montadora.setDataFundacao(resultado.getDate("dataFundacao").toLocalDate());
		return montadora;
	}

}
