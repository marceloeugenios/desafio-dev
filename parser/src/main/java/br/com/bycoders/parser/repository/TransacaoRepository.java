package br.com.bycoders.parser.repository;

import br.com.bycoders.parser.model.Transacao;
import br.com.bycoders.parser.model.TransacaoTipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

}
