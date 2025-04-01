package br.com.servidores.repository;

import br.com.servidores.model.ServidorTemporario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServidorTemporarioRepository extends JpaRepository<ServidorTemporario, Long> {
}