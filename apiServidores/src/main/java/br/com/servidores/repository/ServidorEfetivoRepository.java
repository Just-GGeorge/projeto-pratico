package br.com.servidores.repository;

import br.com.servidores.dto.EnderecoFuncionalDTO;
import br.com.servidores.dto.ServidorEfetivoDTO;
import br.com.servidores.model.ServidorEfetivo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ServidorEfetivoRepository extends JpaRepository<ServidorEfetivo, Long> {
	@Query(value = """
		    SELECT 
		        p.pes_nome AS nome,
		        CAST(EXTRACT(YEAR FROM AGE(CURRENT_DATE, p.pes_data_nascimento)) AS INTEGER) AS idade,
		        u.unid_nome AS unidade,
		        fp.fp_hash AS linkFoto
		    FROM servidor_efetivo se
		    JOIN pessoa p ON p.pes_id = se.pes_id
		    JOIN lotacao l ON l.pes_id = p.pes_id
		    JOIN unidade u ON u.unid_id = l.unid_id
		    LEFT JOIN foto_pessoa fp ON fp.pes_id = p.pes_id
		    WHERE u.unid_id = :unidadeId
		""", nativeQuery = true)
		List<ServidorEfetivoDTO> findServidoresEfetivosPorUnidade(@Param("unidadeId") Long unidadeId);

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
		List<EnderecoFuncionalDTO> buscarEnderecoFuncionalPorNomeServidor(@Param("nome") String nome);
   //
}