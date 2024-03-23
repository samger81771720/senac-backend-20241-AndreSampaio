package controller;

import java.util.ArrayList;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.Aplicacao;
import model.entity.Pessoa;
import model.service.AplicacaoService;

@Path("/aplicacao")
public class AplicacaoController {

	private AplicacaoService service = new AplicacaoService();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Aplicacao salvar(Aplicacao novoRegistroDaAplicacaoDaVacina) {
		return service.salvar(novoRegistroDaAplicacaoDaVacina);
	}
	
	@GET
	@Path("/{id}")
	public Aplicacao consultarPorId(@PathParam("id")int id) {
		return service.consultarPorId(id);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean alterar(Aplicacao registroDaAplicacaoDaVacinaAlterado) {
		return service.alterar(registroDaAplicacaoDaVacinaAlterado);
	}
	
	@DELETE
	@Path("/{id}")
	public boolean excluir(@PathParam("id") int id) {
		return service.excluir(id);
	}
	
	@GET
	@Path("/consultarTodasAplicacoes")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Aplicacao> consultarTodos(){
		return service.consultarTodos();
	}
	
}