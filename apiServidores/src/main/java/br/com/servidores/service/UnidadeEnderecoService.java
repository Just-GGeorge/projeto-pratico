package br.com.servidores.service;

import br.com.servidores.model.UnidadeEndereco;
import br.com.servidores.repository.UnidadeEnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadeEnderecoService {

    private final UnidadeEnderecoRepository repository;

    public UnidadeEnderecoService(UnidadeEnderecoRepository repository) {
        this.repository = repository;
    }

    public List<UnidadeEndereco> listarTodos() {
        return repository.findAll();
    }

    public UnidadeEndereco buscarPorId(Long unidId, Long endId) {
        return repository.findByUnidadeUnidIdAndEnderecoEndId(unidId, endId)
                .orElseThrow(() -> new EntityNotFoundException("Associação unidade/endereço não encontrada."));
    }

    public UnidadeEndereco salvar(UnidadeEndereco obj) {
        try {
            return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Erro ao salvar: verifique duplicidade ou dados inválidos.");
        }
    }

    public void deletar(Long unidId, Long endId) {
        UnidadeEndereco existente = buscarPorId(unidId, endId);
        repository.delete(existente);
    }
}
