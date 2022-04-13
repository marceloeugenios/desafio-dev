package br.com.bycoders.parser.repository;

import br.com.bycoders.parser.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    @Query("SELECT t from Transacao t " +
            "JOIN FETCH t.transacaoTipo tp " +
            "WHERE t.lojaNome = :lojaNome " +
            "ORDER BY t.id")
    List<Transacao> findByLojaNome(@Param("lojaNome") String lojaName);

}
