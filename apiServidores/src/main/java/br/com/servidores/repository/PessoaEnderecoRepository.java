package br.com.servidores.repository;

import br.com.servidores.model.PessoaEndereco;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaEnderecoRepository extends JpaRepository<PessoaEndereco, Long> {

	 List<PessoaEndereco> findAllByPessoaPesId(Long pesId);
}