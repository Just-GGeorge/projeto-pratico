package br.com.servidores.resource;

import br.com.servidores.model.Lotacao;
import br.com.servidores.service.LotacaoService;
import jakarta.persistence.EntityExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lotacoes")
public class LotacaoResource {

    private final LotacaoService lotacaoService;

    public LotacaoResource(LotacaoService lotacaoService) {
        this.lotacaoService = lotacaoService;
    }

    @GetMapping
    public List<Lotacao> listarTodos() {
        return lotacaoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lotacao> buscarPorId(@PathVariable Long id) {
        return lotacaoService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Lotacao lotacao) {
        try {
            return ResponseEntity.ok(lotacaoService.salvar(lotacao));
        } catch (EntityExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Lotacao lotacao) {
        try {
            return ResponseEntity.ok(lotacaoService.atualizar(id, lotacao));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        lotacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
