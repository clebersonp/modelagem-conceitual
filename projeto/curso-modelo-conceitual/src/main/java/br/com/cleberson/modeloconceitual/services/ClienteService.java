package br.com.cleberson.modeloconceitual.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cleberson.modeloconceitual.domain.Cliente;
import br.com.cleberson.modeloconceitual.repositories.ClienteRepository;
import br.com.cleberson.modeloconceitual.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Long id) {
		Cliente cliente = repo.findOne(id);
		
		if (cliente == null) {
			throw new ObjectNotFoundException("Cliente não encontrado. Id: " + id
					+ ". Tipo: " + ClienteService.class.getName());
		}
		
		return cliente;
	}
	
	public List<Cliente> listar() {
		return repo.findAll();
	}
}