package br.com.servidores.resource;

import br.com.servidores.model.Unidade;
import br.com.servidores.service.UnidadeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/unidades")
public class UnidadeResource {

    private final UnidadeService unidadeService;

    public UnidadeResource(UnidadeService unidadeService) {
        this.unidadeService = unidadeService;
    }

    @GetMapping
    public Page<Unidade> listar(Pageable pageable) {
        return unidadeService.listarTodos(pageable);
    }

    @GetMapping("/{id}")
    public Unidade buscarPorId(@PathVariable Long id) {
        return unidadeService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Unidade unidade) {
        try {
            return ResponseEntity.ok(unidadeService.salvar(unidade));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Unidade unidade) {
        try {
            return ResponseEntity.ok(unidadeService.atualizar(id, unidade));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        unidadeService.deletar(id);
    }
}

