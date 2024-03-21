package model.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Aplicacao;
import model.entity.Vacina;

public class AplicacoesDaVacinaRepository implements BaseRepository<AplicacoesDaVacinaRepository>{

	@Override
	public AplicacoesDaVacinaRepository salvar(AplicacoesDaVacinaRepository novaEntidade) {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public boolean excluir(int id) {
		// TODO Stub de método gerado automaticamente
		return false;
	}

	@Override
	public boolean alterar(AplicacoesDaVacinaRepository entidade) {
		// TODO Stub de método gerado automaticamente
		return false;
	}

	@Override
	public AplicacoesDaVacinaRepository consultarPorId(int id) {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public ArrayList<AplicacoesDaVacinaRepository> consultarTodos() {
		// TODO Stub de método gerado automaticamente
		return null;
	}
	
	public ArrayList<Aplicacao> consultarTodasAplicacoesDaPessoa(int id) {
		ArrayList<Aplicacao> aplicacoesDaPessoa = new ArrayList<Aplicacao>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		String query = "select * from VACINACAO.APLICACAO_VACINA "
				+ "where VACINACAO.APLICACAO_VACINA.id_Pessoa  ="+id;
		try{
			resultado = stmt.executeQuery(query);
			while(resultado.next()){
				Aplicacao aplicacao = new Aplicacao();
				VacinaRepository vacinaRepository = new VacinaRepository();
				aplicacao.setIdAplicacao(resultado.getInt("id_Aplicacao"));
				aplicacao.setIdPessoa(resultado.getInt("id_Pessoa"));
				Vacina vacinaAplicada = vacinaRepository.consultarPorId(resultado.getInt("id_Vacina"));
				aplicacao.setVacinaAplicada(vacinaAplicada);
				if(resultado.getDate("dataAplicacao")!=null) {
					aplicacao.setDataAplicacao(resultado.getDate("dataAplicacao").toLocalDate());
				}
				aplicacao.setAvaliacaoReacao(resultado.getInt("avaliacaoDaReacao"));
				aplicacoesDaPessoa.add(aplicacao);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao executar consultar todas as aplicações da pessoa.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return aplicacoesDaPessoa;
	}

}
