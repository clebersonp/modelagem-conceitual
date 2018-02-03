package br.com.cleberson.modeloconceitual.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cleberson.modeloconceitual.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}