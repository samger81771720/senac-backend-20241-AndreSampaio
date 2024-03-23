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

public class AplicacaoRepository implements BaseRepository<Aplicacao>{

	@Override
	public Aplicacao salvar(Aplicacao registroDaAplicacaoDaVacina) {
		String query = "insert into VACINACAO.APLICACAO_VACINA (id_Pessoa, id_Vacina, dataAplicacao, avaliacaoDaReacao) values(?,?,?,?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			preencherParametrosParaInsertOuUpdate(pstmt, registroDaAplicacaoDaVacina);
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();
			if(resultado.next()) {
				registroDaAplicacaoDaVacina.setIdAplicacao(resultado.getInt(1));
			}
		} catch(SQLException erro) {
			System.out.println("Erro na tentativa de salvar um novo registro de aplicação de vacina no banco de dados da pessoa de id "
					+registroDaAplicacaoDaVacina.getIdAplicacao());
			System.out.println("Erro: "+erro);
		} finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return registroDaAplicacaoDaVacina;
	}
	
	private void preencherParametrosParaInsertOuUpdate(PreparedStatement pstmt, Aplicacao registroDaAplicacaoDaVacina) throws SQLException {
		pstmt.setInt(1, registroDaAplicacaoDaVacina.getIdPessoa());
		pstmt.setInt(2, registroDaAplicacaoDaVacina.getVacinaAplicada().getIdVacina());
		if(registroDaAplicacaoDaVacina.getDataAplicacao() != null) {
			pstmt.setDate(3, Date.valueOf(registroDaAplicacaoDaVacina.getDataAplicacao()));
			pstmt.setInt(4, registroDaAplicacaoDaVacina.getAvaliacaoReacao());
		}
	}
	
	@Override
	public boolean excluir(int id) {
		// TODO Stub de método gerado automaticamente
		return false;
	}

	@Override
	public boolean alterar(Aplicacao entidade) {
		// TODO Stub de método gerado automaticamente
		return false;
	}

	public Aplicacao consultarPorId(int id) {
		Connection coon = Banco.getConnection();
		Statement stmt = Banco.getStatement(coon);
		ResultSet resultado = null;
		Aplicacao aplicacaoDaPessoa = new Aplicacao();
		String query = "select * from VACINACAO.APLICACAO_VACINA where id_Aplicacao = " + id;
		try {
			resultado = stmt.executeQuery(query);
			if(resultado.next()) {
				PessoaRepository pessoaRepository = new PessoaRepository();
				VacinaRepository vacinaRepository = new VacinaRepository();
				aplicacaoDaPessoa.setIdAplicacao(resultado.getInt("id_Aplicacao"));
				Pessoa pessoaQueRecebeuAplicacao = pessoaRepository.consultarPorId(resultado.getInt("id_Pessoa"));
				aplicacaoDaPessoa.setIdPessoa(pessoaQueRecebeuAplicacao.getIdPessoa());
				Vacina vacinaAplicada = vacinaRepository.consultarPorId(resultado.getInt("id_Vacina"));
				aplicacaoDaPessoa.setVacinaAplicada(vacinaAplicada);
				aplicacaoDaPessoa.setDataAplicacao(resultado.getDate("dataAplicacao").toLocalDate());
				aplicacaoDaPessoa.setAvaliacaoReacao(resultado.getInt("avaliacaoDaReacao"));
			}
		} catch (Exception erro) {
			System.out.println("Erro ao tentar consultar a aplicação de id: "+id);
			System.out.println("Erro: " + erro );
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(coon);
		}
		return aplicacaoDaPessoa;
	}

	@Override
	public ArrayList<Aplicacao> consultarTodos() {
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
