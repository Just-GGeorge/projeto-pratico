package br.com.servidores.service;

import br.com.servidores.dto.EnderecoFuncionalDTO;
import br.com.servidores.dto.ServidorEfetivoDTO;
import br.com.servidores.model.Pessoa;
import br.com.servidores.model.ServidorEfetivo;
import br.com.servidores.repository.PessoaRepository;
import br.com.servidores.repository.ServidorEfetivoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServidorEfetivoService {


    private final ServidorEfetivoRepository servidorEfetivoRepository;
    private final MinioService minioService;
    private final PessoaRepository pessoaRepository;


    public List<EnderecoFuncionalDTO> buscarEnderecoFuncionalPorNome(String nome) {
        return servidorEfetivoRepository.buscarEnderecoFuncionalPorNomeServidor(nome);
    }
    public ServidorEfetivoService(ServidorEfetivoRepository servidorEfetivoRepository, MinioService minioService, PessoaRepository pessoaRepository) {
        this.servidorEfetivoRepository = servidorEfetivoRepository;
        this.minioService = minioService;
        this.pessoaRepository = pessoaRepository;
    }

    public List<ServidorEfetivoDTO> buscarPorUnidade(Long unidId) {
        List<ServidorEfetivoDTO> lista = servidorEfetivoRepository.findServidoresEfetivosPorUnidade(unidId);

        // Atualiza os links da foto
        lista.forEach(dto -> {
            try {
                if (dto.getLinkFoto() != null) {
                    String url = minioService.getFileUrl("fotos", dto.getLinkFoto());
                    dto.setLinkFoto(url);
                }
            } catch (Exception e) {
                dto.setLinkFoto(null);
            }
        });

        return lista;
    }
    public List<ServidorEfetivo> listarTodos() {
        return servidorEfetivoRepository.findAll();
    }

    public Optional<ServidorEfetivo> buscarPorId(Long id) {
        return servidorEfetivoRepository.findById(id);
    }

    public ServidorEfetivo salvar(ServidorEfetivo servidorEfetivo) {
        Long pessoaId = servidorEfetivo.getPessoa().getPesId();

        // Verifica se a pessoa já está cadastrada como servidor efetivo
        if (servidorEfetivoRepository.existsById(pessoaId)) {
            throw new RuntimeException("Já existe um servidor efetivo com o ID de pessoa " + pessoaId);
        }

        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new RuntimeException("Pessoa com ID " + pessoaId + " não encontrada."));
        servidorEfetivo.setPessoa(pessoa);

        return servidorEfetivoRepository.save(servidorEfetivo);
    }


    public void deletar(Long id) {
        servidorEfetivoRepository.deleteById(id);
    }

    public ServidorEfetivo atualizar(Long id, ServidorEfetivo servidorAtualizado) {
        Optional<ServidorEfetivo> existente = servidorEfetivoRepository.findById(id);
        if (existente.isPresent()) {
            ServidorEfetivo servidor = existente.get();
            servidor.setSeMatricula(servidorAtualizado.getSeMatricula());
            return servidorEfetivoRepository.save(servidor);
        } else {
            throw new RuntimeException("Servidor efetivo com ID " + id + " não encontrado.");
        }
    }

}
