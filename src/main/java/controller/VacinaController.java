package controller;

import java.util.ArrayList;
import java.util.List;

import exception.ControleVacinasException;
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
import model.entity.Vacina;
import model.service.VacinaService;

@Path("/vacina")
public class VacinaController {
	
	private VacinaService service = new VacinaService();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Vacina Salvar(Vacina novaVacina) throws ControleVacinasException{
		return service.salvar(novaVacina);
	}
	
	@GET
	@Path("/{id}")
	public Vacina consultarPorId(@PathParam("id")int id){
		return service.consultarPorId(id);
	}
	
	@GET
	@Path("/consultarVacinas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Vacina> consultarTodos(){
		 return service.consultarTodos();
	}
	
	@DELETE
	@Path("/{id}")
	public boolean excluir(@PathParam("id") int id){
		 return service.excluir(id);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean alterar(Vacina vacinaParaAlterar) {
		return service.alterar(vacinaParaAlterar);
	}
	
	/*@GET
	@Path("/consultarAplicacoesDaPessoa/{idPessoa}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Aplicacao> consultarTodasAplicacoesDaPessoa(@PathParam("idPessoa")int id){
		return service.consultarTodasAplicacoesDaPessoa(id);
	}*/

}
