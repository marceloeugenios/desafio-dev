package br.com.desafio.parser.repository;

import br.com.desafio.parser.model.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, Integer> {

}
