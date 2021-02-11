package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
	
	private static final String MSG_ESTADO_EM_USO = "estado de código %d não pode ser removida pois está em uso";

	

	@Autowired
	EstadoRepository estadoReposirotry;
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	public Estado Salvar(Estado estado) {
		return estadoReposirotry.save(estado);
	}
	
	public void exlcuir(Long estadoId){
		try {
			estadoReposirotry.deleteById(estadoId);
			
		}catch(EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradoException(estadoId);
			
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_ESTADO_EM_USO, estadoId));
		}
	}
	
	public Estado buscarOuFalhar(long estadoId) {
		return estadoReposirotry.findById(estadoId)
				.orElseThrow(()-> new EstadoNaoEncontradoException(estadoId));
	}
}
