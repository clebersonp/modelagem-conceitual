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
import br.com.cleberson.modeloconceitual.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> listar() {
		return ResponseEntity.ok(service.listar());
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Produto> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.buscar(id));
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/{id}/categorias")
	public ResponseEntity<List<Categoria>> buscarCategorias(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.buscarCategoriasDeProduto(id));
	}
}