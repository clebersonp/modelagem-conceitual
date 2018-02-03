package br.com.cleberson.modeloconceitual.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.cleberson.modeloconceitual.domain.Cidade;
import br.com.cleberson.modeloconceitual.services.CidadeService;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeResource {

	@Autowired
	private CidadeService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cidade> buscar(@PathVariable(value = "id") Long id) {
		return ResponseEntity.ok(service.buscar(id));
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Cidade>> listar() {
		return ResponseEntity.ok(service.listar());
	}
}