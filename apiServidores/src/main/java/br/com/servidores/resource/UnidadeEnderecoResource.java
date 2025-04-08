package br.com.servidores.resource;

import br.com.servidores.model.UnidadeEndereco;
import br.com.servidores.service.UnidadeEnderecoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/unidade-endereco")
public class UnidadeEnderecoResource {

    private final UnidadeEnderecoService service;

    public UnidadeEnderecoResource(UnidadeEnderecoService service) {
        this.service = service;
    }

    @GetMapping
    public Page<UnidadeEndereco> listarTodos(Pageable pageable) {
        return service.listarTodos(pageable);
    }

    @GetMapping("/{unidId}/{endId}")
    public ResponseEntity<?> buscar(@PathVariable Long unidId, @PathVariable Long endId) {
        try {
            return ResponseEntity.ok(service.buscarPorId(unidId, endId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody UnidadeEndereco obj) {
        try {
            return ResponseEntity.ok(service.salvar(obj));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{unidId}/{endId}")
    public ResponseEntity<?> deletar(@PathVariable Long unidId, @PathVariable Long endId) {
        try {
            service.deletar(unidId, endId);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
