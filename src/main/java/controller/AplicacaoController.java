package controller;

import exception.ControleVacinasException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
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
	public Aplicacao salvar(Aplicacao registroDaAplicacaoDaVacina) {
		return service.salvar(registroDaAplicacaoDaVacina);
	}
	
	@GET
	@Path("/{id}")
	public Aplicacao consultarPorId(@PathParam("id")int id) {
		return service.consultarPorId(id);
	}
	
}
