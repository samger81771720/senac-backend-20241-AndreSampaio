package controller;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.vemnox1.Jogador;
import repository.JogadorRepository;


@Path("/jogador")
public class JogadorController {
	// Violando o modelo MVC(apenas para teste)
	private JogadorRepository jogadorRepository = new JogadorRepository();
	
	@POST
	@Path("/salvar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public Jogador salvar(Jogador novoJogador) {
		return jogadorRepository.salvar(novoJogador);
	}

}
