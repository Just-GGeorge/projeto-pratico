package br.com.servidores.repository;

import br.com.servidores.model.UnidadeEndereco;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadeEnderecoRepository extends JpaRepository<UnidadeEndereco, Long> {
	
    Optional<UnidadeEndereco> findByUnidadeUnidIdAndEnderecoEndId(Long unidId, Long endId);

}
