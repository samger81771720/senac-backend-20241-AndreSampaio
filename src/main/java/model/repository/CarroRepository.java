package model.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Carro;
import model.seletor.CarroSeletor;

public class CarroRepository {
	
	public ArrayList<Carro> consultarComFiltros(CarroSeletor seletor){
		ArrayList<Carro> carros = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		String sql =  " SELECT VEICULOS.MONTADORA.nome, VEICULOS.CARRO.* FROM VEICULOS.CARRO "
							  + " INNER JOIN VEICULOS.MONTADORA ON VEICULOS.MONTADORA.ID = VEICULOS.CARRO.IDMONTADORA ";
		
		if(seletor.temFiltro()) {
			sql = preencherFiltros(seletor,sql);
		}
		
		try {
			resultado = stmt.executeQuery(sql);
			while(resultado.next()) {
				Carro carro = construirDoResultSet(resultado);
				carros.add(carro);
			}
		} catch(SQLException erro){
			System.out.println(
					"Erro durante a execução do método \"consultarComFiltros\" ao consultar a lista de carros com o filtro selecionado."
					);
			System.out.println("Erro: "+erro.getMessage());
		} finally{
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return carros;
	}
	
	private Carro construirDoResultSet(ResultSet resultado) throws SQLException{
		Carro carro = new Carro();
		carro.setId(resultado.getInt("id"));
		carro.setModelo(resultado.getString("modelo"));
		carro.setPlaca(resultado.getString("placa"));
		MontadoraRepository montadoraRepository = new MontadoraRepository();
		carro.setMontadora(montadoraRepository.consultarPorId(resultado.getInt("idMontadora")));
		carro.setAno(resultado.getInt("ano"));
		carro.setValor(resultado.getDouble("valor"));
		return carro;
	}
	
	private String preencherFiltros(CarroSeletor seletor, String sql) {
	    
		final String AND = " and ";
	    sql   += " where ";
	    boolean primeiro = true;

	    if (seletor.getNomeMarca() != null && seletor.getNomeMarca().trim().length() > 0) {
	        sql += " UPPER(VEICULOS.MONTADORA.NOME) LIKE UPPER ('%" + seletor.getNomeMarca() + "%')";
	        primeiro = false;
	    }
	    
	    if (seletor.getModelo() != null && seletor.getModelo().trim().length() > 0) {
	        if (!primeiro) {
	            sql += AND;
	        }
	        sql += " UPPER(VEICULOS.CARRO.MODELO) LIKE UPPER('%" + seletor.getModelo() + "%')";
	        primeiro = false;
	    }
	    
	    if (seletor.getAnoInicial() != null && seletor.getAnoFinal() != null) {
	        if (!primeiro) {
	            sql += AND;
	        }
	        sql += " VEICULOS.CARRO.ANO BETWEEN '" + seletor.getAnoInicial()
	                + "' AND '" + seletor.getAnoFinal() + "'";
	    } else if (seletor.getAnoInicial() != null) {
	        if (!primeiro) {
	            sql += AND;
	        }
	        sql += " VEICULOS.CARRO.ANO >= '" + seletor.getAnoInicial() + "'";
	    } else if (seletor.getAnoFinal() != null) {
	        if (!primeiro) {
	            sql += AND;
	        }
	        sql += " VEICULOS.CARRO.ANO <= '" + seletor.getAnoFinal() + "'";
	    } 
	    
	    return sql;
	}
	
}
