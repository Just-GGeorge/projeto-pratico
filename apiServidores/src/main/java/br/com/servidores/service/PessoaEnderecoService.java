package br.com.servidores.service;

import br.com.servidores.model.PessoaEndereco;
import br.com.servidores.repository.PessoaEnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaEnderecoService {

    private final PessoaEnderecoRepository repository;

    public PessoaEnderecoService(PessoaEnderecoRepository repository) {
        this.repository = repository;
    }

    public List<PessoaEndereco> listarTodos() {
        return repository.findAll();
    }

    public List<PessoaEndereco> buscarPorPessoaId(Long pesId) {
        List<PessoaEndereco> lista = repository.findAllByPessoaPesId(pesId);
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

    public void deletarPorPessoaId(Long pesId) {
        List<PessoaEndereco> lista = buscarPorPessoaId(pesId);
        repository.deleteAll(lista);
    }
}
