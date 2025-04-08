package br.com.servidores.resource;

import br.com.servidores.model.Endereco;
import br.com.servidores.service.EnderecoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoResource {

    private final EnderecoService service;

    public EnderecoResource(EnderecoService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Endereco> listarTodos(Pageable pageable) {
        return service.listarTodos(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Endereco endereco) {
        try {
            return ResponseEntity.ok(service.salvar(endereco));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Endereco endereco) {
        try {
            return ResponseEntity.ok(service.atualizar(id, endereco));
        } catch (EntityNotFoundException | DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            service.deletar(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao excluir endereço.");
        }
    }
}
