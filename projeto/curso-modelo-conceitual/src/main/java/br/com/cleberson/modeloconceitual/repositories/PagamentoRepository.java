package br.com.cleberson.modeloconceitual.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cleberson.modeloconceitual.domain.Pagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}