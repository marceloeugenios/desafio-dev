package br.com.bycoders.parser.repository;

import br.com.bycoders.parser.dto.ExtratoDto;
import br.com.bycoders.parser.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    @Query("SELECT new br.com.bycoders.parser.dto.ExtratoDto(tx.lojaNome, " +

            "(SELECT sum(t.valor) FROM Transacao t JOIN t.transacaoTipo tp " +
            "WHERE t.lojaNome = tx.lojaNome AND tp.transacaoNatureza = 'ENTRADA') AS entrada, " +

            "(SELECT sum(t.valor) FROM Transacao t JOIN t.transacaoTipo tp " +
            "WHERE t.lojaNome = tx.lojaNome AND tp.transacaoNatureza = 'SAIDA') AS saida) " +

            "FROM Transacao tx " +
            "GROUP BY tx.lojaNome " +
            "ORDER BY tx.lojaNome")
    List<ExtratoDto> extratoAgrupadoPorLoja();

}
