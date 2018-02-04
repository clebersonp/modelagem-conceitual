package br.com.cleberson.modeloconceitual.domain.enumarations;

import java.util.Arrays;
import java.util.List;

public enum EstadoPagamento {

	PENDENTE(1L, "Pendente"),
	QUITADO(2L, "Quitado"),
	CANCELADO(3L, "Cancelado");
	
	private Long codigo;
	private String descricao;
	
	private EstadoPagamento(Long codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Long getCodigo() {
		return codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	
	public static EstadoPagamento getEnum(Long codigo) {
		EstadoPagamento pagamento = null;
		List<EstadoPagamento> list = Arrays.asList(EstadoPagamento.values());
		pagamento = list.stream().filter(e -> e.getCodigo().equals(codigo)).findFirst().orElse(null);
		if (pagamento == null) {
			throw new IllegalArgumentException("Não existe EstadoPagamento com o valor: " + codigo);
		}
		return pagamento;
	}
}