package br.com.servidores.service;

import br.com.servidores.model.Cidade;
import br.com.servidores.repository.CidadeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class CidadeService {

    private final CidadeRepository repository;

    public CidadeService(CidadeRepository repository) {
        this.repository = repository;
    }

    public Page<Cidade> listarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Cidade buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cidade com ID " + id + " não encontrada."));
    }

    public Cidade salvar(Cidade cidade) {
        try {
            return repository.save(cidade);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Erro ao salvar cidade: possivelmente dados duplicados.");
        }
    }

    public Cidade atualizar(Long id, Cidade cidadeAtualizada) {
        Cidade existente = buscarPorId(id);
        existente.setCidNome(cidadeAtualizada.getCidNome());
        existente.setCidUf(cidadeAtualizada.getCidUf());
        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
