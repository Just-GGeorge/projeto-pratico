package br.com.servidores.service;

import br.com.servidores.model.Lotacao;
import br.com.servidores.repository.LotacaoRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LotacaoService {

    private final LotacaoRepository lotacaoRepository;

    public LotacaoService(LotacaoRepository lotacaoRepository) {
        this.lotacaoRepository = lotacaoRepository;
    }

    public List<Lotacao> listarTodos() {
        return lotacaoRepository.findAll();
    }

    public Optional<Lotacao> buscarPorId(Long id) {
        return lotacaoRepository.findById(id);
    }

    public Lotacao salvar(Lotacao lotacao) {
        try {
            return lotacaoRepository.save(lotacao);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistsException("Já existe uma lotação com os dados informados ou com chave duplicada.");
        }
    }

    public Lotacao atualizar(Long id, Lotacao atualizado) {
        return lotacaoRepository.findById(id)
            .map(l -> {
                l.setPessoa(atualizado.getPessoa());
                l.setUnidade(atualizado.getUnidade());
                l.setLotDataLotacao(atualizado.getLotDataLotacao());
                l.setLotDataRemocao(atualizado.getLotDataRemocao());
                l.setLotPortaria(atualizado.getLotPortaria());
                return salvar(l);
            }).orElseThrow(() -> new RuntimeException("Lotação com ID " + id + " não encontrada."));
    }

    public void deletar(Long id) {
        lotacaoRepository.deleteById(id);
    }
}
