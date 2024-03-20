package model.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Pais;
import model.entity.Pessoa;

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
		return null;
	}

	@Override
	public Pais salvar(Pais novaEntidade) {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public boolean excluir(int id) {
		// TODO Stub de método gerado automaticamente
		return false;
	}

	@Override
	public boolean alterar(Pais entidade) {
		// TODO Stub de método gerado automaticamente
		return false;
	}

	@Override
	public ArrayList<Pais> consultarTodos() {
		// TODO Stub de método gerado automaticamente
		return null;
	}
	
}
