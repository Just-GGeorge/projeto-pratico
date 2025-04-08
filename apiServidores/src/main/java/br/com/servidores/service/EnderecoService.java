package br.com.servidores.service;

import br.com.servidores.model.Endereco;
import br.com.servidores.repository.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class EnderecoService {

    private final EnderecoRepository repository;

    public EnderecoService(EnderecoRepository repository) {
        this.repository = repository;
    }

    public Page<Endereco> listarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Endereco buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Endereço com ID " + id + " não encontrado."));
    }

    public Endereco salvar(Endereco endereco) {
        try {
            return repository.save(endereco);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Erro ao salvar endereço: verifique duplicidade ou dados inválidos.");
        }
    }

    public Endereco atualizar(Long id, Endereco enderecoAtualizado) {
        Endereco existente = buscarPorId(id);
        existente.setEndTipoLogradouro(enderecoAtualizado.getEndTipoLogradouro());
        existente.setEndLogradouro(enderecoAtualizado.getEndLogradouro());
        existente.setEndNumero(enderecoAtualizado.getEndNumero());
        existente.setEndBairro(enderecoAtualizado.getEndBairro());
        existente.setCidade(enderecoAtualizado.getCidade());
        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
