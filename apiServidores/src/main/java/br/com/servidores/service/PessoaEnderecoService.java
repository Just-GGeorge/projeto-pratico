package br.com.servidores.service;

import br.com.servidores.model.PessoaEndereco;
import br.com.servidores.repository.PessoaEnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class PessoaEnderecoService {

    private final PessoaEnderecoRepository repository;

    public PessoaEnderecoService(PessoaEnderecoRepository repository) {
        this.repository = repository;
    }

    public Page<PessoaEndereco> listarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<PessoaEndereco> buscarPorPessoaId(Pageable pageable, Long pesId) {
    	Page<PessoaEndereco> lista = repository.findAllByPessoaPesId(pageable,pesId);
        if (lista.isEmpty()) {
            throw new EntityNotFoundException("Nenhum endereço encontrado para a pessoa com ID " + pesId);
        }
        return lista;
    }

    public PessoaEndereco salvar(PessoaEndereco obj) {
        try {
            return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Erro ao salvar: verifique duplicidade ou dados inválidos.");
        }
    }

    public void deletarPorPessoaId(Pageable pageable,Long pesId) {
    	Page<PessoaEndereco> lista = buscarPorPessoaId(pageable, pesId);
        repository.deleteAll(lista);
    }
}
