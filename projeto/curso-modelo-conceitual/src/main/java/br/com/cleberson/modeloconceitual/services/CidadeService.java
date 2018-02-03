package br.com.cleberson.modeloconceitual.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cleberson.modeloconceitual.domain.Cidade;
import br.com.cleberson.modeloconceitual.repositories.CidadeRepository;
import br.com.cleberson.modeloconceitual.services.exceptions.ObjectNotFoundException;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;

	public Cidade buscar(Long id) {
		Cidade cidade = repo.findOne(id);
		
		if (cidade == null) {
			throw new ObjectNotFoundException("Cidade não encontrada Id: " + id
					+ ". Tipo: " + CidadeService.class.getName());
		}
		
		return cidade;
	}
	
	public List<Cidade> listar() {
		return repo.findAll();
	}
}