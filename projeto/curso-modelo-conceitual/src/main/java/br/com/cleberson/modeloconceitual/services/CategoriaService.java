package br.com.cleberson.modeloconceitual.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cleberson.modeloconceitual.domain.Categoria;
import br.com.cleberson.modeloconceitual.repositories.CategoriaRepository;
import br.com.cleberson.modeloconceitual.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository catRepo;
	
	public Categoria buscar(Long id) {
		Categoria categoria = catRepo.findOne(id);
		if (categoria == null) {
			throw new ObjectNotFoundException("Categoria não encontrada! Id: " + id 
					+ " Tipo: " + Categoria.class.getName());
		}
		return categoria;
	}
	
	public List<Categoria> listar() {
		return catRepo.findAll();
	}
}