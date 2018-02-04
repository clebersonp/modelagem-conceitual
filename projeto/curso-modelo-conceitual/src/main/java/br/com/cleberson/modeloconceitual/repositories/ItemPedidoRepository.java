package br.com.cleberson.modeloconceitual.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cleberson.modeloconceitual.domain.ItemPedido;
import br.com.cleberson.modeloconceitual.domain.ItemPedidoPk;

@Repository	
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoPk> {
}