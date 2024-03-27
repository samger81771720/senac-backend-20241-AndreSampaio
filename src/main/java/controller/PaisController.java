package controller;

import exception.ControleVacinasException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.Pais;
import model.entity.Pessoa;
import model.service.PaisService;

@Path("/pais")
public class PaisController {

 private PaisService service = new PaisService();
	
	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Pais consultarPorId(@PathParam("id")int id) throws ControleVacinasException{
		return service.consultarPorId(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Pais salvar(Pais novoPais) throws ControleVacinasException {
		return service.salvar(novoPais);
	}
	
}

