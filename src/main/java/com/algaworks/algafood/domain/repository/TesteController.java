package com.algaworks.algafood.domain.repository;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;


@RestController
@RequestMapping("/teste")
public class TesteController{
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@GetMapping("/cozinhas/por-nome")
	public List<Cozinha> cozinhasPorNome(String nome){
		return cozinhaRepository.findByNomeContaining(nome);
		
	}
	
	@GetMapping("/restaurantes/por-taxa-frete")
	public List<Restaurante> restaurantesPorTaxaFrete(
				BigDecimal taxaInicial, BigDecimal taxaFinal){
			return restauranteRepository.queryByTaxaFreteBetween(taxaInicial, taxaFinal);
		}
	
	@GetMapping("/restaurantes/por-nome")
	public List<Restaurante> restaurantesPorNome(
				String nome, Long cozinhaId){
			return restauranteRepository.consultarPorNome(nome, cozinhaId);
		}
	
	@GetMapping("/restaurantes/por-primeiro-nome")
	public Optional<Restaurante> restaurantesPrimeiroNome(
				String nome){
			return restauranteRepository.findFirstRestauranteByNomeContaining(nome);
		}
	
	@GetMapping("/restaurantes/por-primeiros2-nome")
	public List<Restaurante> restaurantesPrimeirosNome(
				String nome){
			return restauranteRepository.findTop2ByNomeContaining(nome);
		}
		
	@GetMapping("/cozinhas/exists-nome")
	public boolean cozinhaExists(
				String nome){
			return cozinhaRepository.existsByNome(nome);
		}
	
	@GetMapping("/restaurantes/count-por-cozinha")
	public int restaurantePorCozinha(
				long cozinhaId){
			return restauranteRepository.countByCozinhaId(cozinhaId);
		}
	
	@GetMapping("/restaurantes/por-nome-e-frete")
	public List<Restaurante> restaurantesPorNomeFrete(
			String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
			return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
	}
	
	@GetMapping("/restaurantes/com-frete-gratis")
	public List<Restaurante> restauranteComFreteGratis(String nome){
	
		return restauranteRepository.findComFreteGratis(nome);
	}
	
	@GetMapping("/restaurantes/primeiro")
	public Optional<Restaurante> restaurantePrimeiro(){
	
		return restauranteRepository.buscarPrimeiro();
	}
	
	
	
	
	
	
	
	
}