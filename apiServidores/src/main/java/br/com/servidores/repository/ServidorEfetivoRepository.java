package br.com.servidores.repository;

import br.com.servidores.dto.EnderecoFuncionalDTO;
import br.com.servidores.dto.ServidorEfetivoDTO;
import br.com.servidores.model.ServidorEfetivo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ServidorEfetivoRepository extends JpaRepository<ServidorEfetivo, Long> {
	@Query("""
		    SELECT new br.com.servidores.dto.ServidorEfetivoDTO(
		        p.pesNome,
		        p.pesDataNascimento,
		        u.unidNome,
		        fp.hash
		    )
		    FROM ServidorEfetivo se
		    JOIN Pessoa p ON se.pessoa.pesId = p.pesId
		    JOIN Lotacao l ON p.pesId = l.pessoa.pesId
		    JOIN Unidade u ON l.unidade.unidId = u.unidId
		    LEFT JOIN FotoPessoa fp ON fp.pessoa.pesId = p.pesId
		    WHERE u.unidId = :unidadeId
		""")
		Page<ServidorEfetivoDTO> findServidoresEfetivosPorUnidade(Pageable pageable, @Param("unidadeId") Long unidadeId);

	@Query(value = """
		    SELECT new br.com.servidores.dto.EnderecoFuncionalDTO(
		        p.pesNome,
		        u.unidNome,
		        e.endTipoLogradouro,
		        e.endLogradouro,
		        e.endNumero,
		        e.endBairro,
		        c.cidNome,
		        c.cidUf
		    )
		    FROM ServidorEfetivo se
		    JOIN Pessoa p ON se.pessoa.pesId = p.pesId
		    JOIN Lotacao l ON p.pesId = l.pessoa.pesId
		    JOIN Unidade u ON l.unidade.unidId = u.unidId
		    JOIN UnidadeEndereco ue ON u.unidId = ue.unidade.unidId  
		    JOIN Endereco e ON ue.endereco.endId = e.endId
		    JOIN Cidade c ON e.cidade.cidId = c.cidId
		    WHERE LOWER(p.pesNome) LIKE LOWER(CONCAT('%', :nome, '%'))
		""")
	Page<EnderecoFuncionalDTO> buscarEnderecoFuncionalPorNomeServidor(Pageable pageable, @Param("nome") String nome);
   //
}