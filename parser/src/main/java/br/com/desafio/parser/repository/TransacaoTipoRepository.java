package br.com.desafio.parser.repository;

import br.com.desafio.parser.model.TransacaoTipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoTipoRepository extends JpaRepository<TransacaoTipo, Integer> {

}
