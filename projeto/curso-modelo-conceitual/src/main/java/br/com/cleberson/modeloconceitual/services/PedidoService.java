package br.com.cleberson.modeloconceitual.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cleberson.modeloconceitual.domain.Pedido;
import br.com.cleberson.modeloconceitual.repositories.PedidoRepository;
import br.com.cleberson.modeloconceitual.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Long id) {
		Pedido pedido = repo.findOne(id);
		
		if (pedido == null) {
			throw new ObjectNotFoundException("Pedido não encontrado. Id: " + id
					+ " . Tipo: " + PedidoService.class.getName());
		}
		
		return pedido;
	}
	
	public List<Pedido> listar() {
		return repo.findAll();
	}
}