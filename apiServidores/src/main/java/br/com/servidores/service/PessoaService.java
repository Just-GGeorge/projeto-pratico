package br.com.servidores.service;

import br.com.servidores.model.Pessoa;
import br.com.servidores.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    private final PessoaRepository repository;

    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    public List<Pessoa> listarTodos() {
        return repository.findAll();
    }

    public Pessoa buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa com ID " + id + " não encontrada."));
    }

    public Pessoa salvar(Pessoa pessoa) {
        try {
            return repository.save(pessoa);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Erro ao salvar pessoa: verifique duplicidade ou dados inválidos.");
        }
    }

    public Pessoa atualizar(Long id, Pessoa pessoaAtualizada) {
        Pessoa existente = buscarPorId(id);
        existente.setPesNome(pessoaAtualizada.getPesNome());
        existente.setPesDataNascimento(pessoaAtualizada.getPesDataNascimento());
        existente.setPesSexo(pessoaAtualizada.getPesSexo());
        existente.setPesMae(pessoaAtualizada.getPesMae());
        existente.setPesPai(pessoaAtualizada.getPesPai());
        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
