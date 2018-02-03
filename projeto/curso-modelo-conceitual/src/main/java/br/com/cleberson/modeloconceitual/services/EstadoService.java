package br.com.cleberson.modeloconceitual.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cleberson.modeloconceitual.domain.Estado;
import br.com.cleberson.modeloconceitual.repositories.EstadoRepository;
import br.com.cleberson.modeloconceitual.services.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repo;

	public Estado buscar(Long id) {
		Estado estado = repo.findOne(id);
		
		if (estado == null) {
			throw new ObjectNotFoundException("Estado não encontrado Id: " + id
					+ ". Tipo: " + EstadoService.class.getName());
		}
		
		return estado;
	}
	
	public List<Estado> listar() {
		return repo.findAll();
	}
}
