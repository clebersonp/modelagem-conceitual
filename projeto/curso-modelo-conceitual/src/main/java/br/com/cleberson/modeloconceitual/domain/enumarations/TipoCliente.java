package br.com.cleberson.modeloconceitual.domain.enumarations;

import java.util.Arrays;
import java.util.List;

public enum TipoCliente {
	
	PESSOA_FISICA(1L, "Pessoa Física"),
	PESSOA_JURIDICA(2L, "Pessoa Jurídica");
	
	private Long codigo;
	private String descricao;
	
	private TipoCliente(Long codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Long getCodigo() {
		return codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCliente getEnum(Long codigo) {
		TipoCliente tipo = null;
		if (codigo == null) {
			return tipo;
		}
		List<TipoCliente> list = Arrays.asList(TipoCliente.values());
		
		tipo = list.stream().filter(l -> l.getCodigo().equals(codigo)).findFirst() .orElse(null);
		
		if (tipo == null) {
			throw new IllegalArgumentException("Não existe enum para o codigo: " + codigo);
		}
		
		return tipo;
	}
}