package br.com.servidores.repository;

import br.com.servidores.model.PessoaEndereco;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaEnderecoRepository extends JpaRepository<PessoaEndereco, Long> {

	Page<PessoaEndereco> findAllByPessoaPesId(Pageable pageable,Long pesId);
}