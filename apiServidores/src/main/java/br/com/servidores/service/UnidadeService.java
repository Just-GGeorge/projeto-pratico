package br.com.servidores.service;

import br.com.servidores.model.Unidade;
import br.com.servidores.repository.UnidadeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class UnidadeService {

    private final UnidadeRepository repository;

    public UnidadeService(UnidadeRepository repository) {
        this.repository = repository;
    }

    public Page<Unidade> listarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Unidade buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unidade com ID " + id + " não encontrada."));
    }

    public Unidade salvar(Unidade unidade) {
        try {
            return repository.save(unidade);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Erro ao salvar unidade: verifique duplicidade ou dados inválidos.");
        }
    }

    public Unidade atualizar(Long id, Unidade unidadeAtualizada) {
        Unidade existente = buscarPorId(id);
        existente.setUnidNome(unidadeAtualizada.getUnidNome());
        existente.setUnidSigla(unidadeAtualizada.getUnidSigla());
        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
