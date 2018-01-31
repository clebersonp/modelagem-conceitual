package br.com.cleberson.modeloconceitual.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cleberson.modeloconceitual.domain.Categoria;
import br.com.cleberson.modeloconceitual.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Long id) {
		return repo.findOne(id);
	}
	
	public List<Categoria> listar() {
		return repo.findAll();
	}
}