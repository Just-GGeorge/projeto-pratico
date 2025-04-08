package br.com.servidores.resource;

import br.com.servidores.model.Pessoa;
import br.com.servidores.service.PessoaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    private final PessoaService service;

    public PessoaResource(PessoaService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Pessoa> listarTodos(Pageable pageable) {
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
    public ResponseEntity<?> salvar(@RequestBody Pessoa pessoa) {
        try {
            return ResponseEntity.ok(service.salvar(pessoa));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        try {
            return ResponseEntity.ok(service.atualizar(id, pessoa));
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
            return ResponseEntity.badRequest().body("Erro ao excluir pessoa.");
        }
    }
}
