package br.com.cleberson.modeloconceitual.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cleberson.modeloconceitual.domain.Categoria;
import br.com.cleberson.modeloconceitual.domain.Produto;
import br.com.cleberson.modeloconceitual.repositories.ProdutoRepository;
import br.com.cleberson.modeloconceitual.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	
	public List<Produto> listar() {
		return repo.findAll();
	}
	
	public Produto buscar(Long id) {
		Produto produto = repo.findOne(id);
		
		if (produto == null) {
			throw new ObjectNotFoundException("Produto não encontrado. Id: " + id
					+ ". Tipo: " + ProdutoService.class.getName());
		}
		
		return produto;
	}

	public List<Categoria> buscarCategoriasDeProduto(Long id) {
		Produto produto = repo.findOne(id);
		
		if (produto == null) {
			throw new ObjectNotFoundException("Produto não encontrado. Id: " + id
					+ ". Tipo: " + ProdutoService.class.getName());
		}
		
		List<Categoria> categorias = produto.getCategorias();
		categorias.stream().forEach(c -> c.setProdutos(Collections.emptyList()));
		return categorias;
	}
}