package br.com.cleberson.modeloconceitual.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.cleberson.modeloconceitual.domain.Pedido;
import br.com.cleberson.modeloconceitual.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> buscar(@PathVariable(value = "id") Long id) {
		return ResponseEntity.ok(service.buscar(id));
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Pedido>> listar() {
		return ResponseEntity.ok(service.listar());
	}
}