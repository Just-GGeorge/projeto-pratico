package br.com.servidores.resource;

import br.com.servidores.model.PessoaEndereco;
import br.com.servidores.service.PessoaEnderecoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/pessoa-endereco")
public class PessoaEnderecoResource {

    private final PessoaEnderecoService service;

    public PessoaEnderecoResource(PessoaEnderecoService service) {
        this.service = service;
    }

    @GetMapping
    public Page<PessoaEndereco> listarTodos(Pageable pageable) {
        return service.listarTodos(pageable);
    }

    @GetMapping("/{pesId}")
    public ResponseEntity<?> buscarPorPessoa(@PathVariable Long pesId) {
        try {
            return ResponseEntity.ok(service.buscarPorPessoaId(null, pesId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody PessoaEndereco obj) {
        try {
            return ResponseEntity.ok(service.salvar(obj));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{pesId}")
    public ResponseEntity<?> deletarPorPessoa(@PathVariable Long pesId) {
        try {
            service.deletarPorPessoaId(null, pesId);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
