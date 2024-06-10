package controller;

import java.util.ArrayList;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.Carro;
import model.seletor.CarroSeletor;
import model.service.CarroService;

@Path("/carro")
public class CarroController {
	
	CarroService carroService = new CarroService();
	
	@POST
	@Path("/filtro")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Carro> consultarComFitros(CarroSeletor seletor){
		return carroService.consultarComFiltros(seletor);
	}
	
}
