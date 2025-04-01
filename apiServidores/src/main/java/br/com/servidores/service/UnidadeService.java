package br.com.servidores.service;

import br.com.servidores.model.Unidade;
import br.com.servidores.repository.UnidadeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadeService {

    private final UnidadeRepository repository;

    public UnidadeService(UnidadeRepository repository) {
        this.repository = repository;
    }

    public List<Unidade> listarTodos() {
        return repository.findAll();
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
