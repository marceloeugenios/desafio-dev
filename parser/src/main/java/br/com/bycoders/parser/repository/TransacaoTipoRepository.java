package br.com.bycoders.parser.repository;

import br.com.bycoders.parser.model.TransacaoTipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoTipoRepository extends JpaRepository<TransacaoTipo, Integer> {

}
