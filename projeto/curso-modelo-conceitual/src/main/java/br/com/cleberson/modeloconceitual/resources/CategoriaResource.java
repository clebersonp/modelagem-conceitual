package br.com.cleberson.modeloconceitual.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.cleberson.modeloconceitual.domain.Categoria;
import br.com.cleberson.modeloconceitual.domain.Produto;
import br.com.cleberson.modeloconceitual.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Categoria>> listar() {
		return ResponseEntity.ok(service.listar());
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(service.buscar(id));
	}
	
	@RequestMapping(value = "/{id}/produtos", method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> listarProdutos(@PathVariable Long idCategoria) {
		return ResponseEntity.ok(service.listarProdutos(idCategoria));
	}
}