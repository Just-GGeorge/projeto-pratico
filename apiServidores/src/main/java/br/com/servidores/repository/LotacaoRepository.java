package br.com.servidores.repository;

import br.com.servidores.model.Lotacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotacaoRepository extends JpaRepository<Lotacao, Long> {
}
