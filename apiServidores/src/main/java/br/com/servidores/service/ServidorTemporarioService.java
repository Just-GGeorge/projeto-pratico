package br.com.servidores.service;

import br.com.servidores.model.Pessoa;
import br.com.servidores.model.ServidorEfetivo;
import br.com.servidores.model.ServidorTemporario;
import br.com.servidores.repository.PessoaRepository;
import br.com.servidores.repository.ServidorEfetivoRepository;
import br.com.servidores.repository.ServidorTemporarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServidorTemporarioService {

    private final ServidorEfetivoRepository servidorEfetivoRepository;
    private final ServidorTemporarioRepository repository;
    private final PessoaRepository pessoaRepository;


    public ServidorTemporarioService(ServidorTemporarioRepository repository, ServidorEfetivoRepository servidorEfetivoRepository, PessoaRepository pessoaRepository) {
        this.repository = repository;
        this.servidorEfetivoRepository = servidorEfetivoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public List<ServidorTemporario> findAll() {
        return repository.findAll();
    }

    public ServidorTemporario salvar(ServidorTemporario servidorTemporario) {
        Long pessoaId = servidorTemporario.getPessoa().getPesId();

        if (servidorEfetivoRepository.existsById(pessoaId)) {
            throw new RuntimeException("Já existe um servidor temporário com o ID de pessoa " + pessoaId);
        }

        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new RuntimeException("Pessoa com ID " + pessoaId + " não encontrada."));
        servidorTemporario.setPessoa(pessoa);

        return repository.save(servidorTemporario);
    }
    
    public Optional<ServidorTemporario> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
