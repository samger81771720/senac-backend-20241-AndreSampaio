package model.service;

import java.util.ArrayList;

import model.entity.Carro;
import model.repository.CarroRepository;
import model.seletor.CarroSeletor;

public class CarroService {
	
	CarroRepository carroRepository = new CarroRepository();
	
	public ArrayList<Carro> consultarComFiltros(CarroSeletor seletor){
		return carroRepository.consultarComFiltros(seletor);
	}
	
}
