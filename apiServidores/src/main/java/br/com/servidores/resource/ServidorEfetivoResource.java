package br.com.servidores.resource;

import br.com.servidores.dto.EnderecoFuncionalDTO;
import br.com.servidores.dto.ServidorEfetivoDTO;
import br.com.servidores.model.ServidorEfetivo;
import br.com.servidores.service.ServidorEfetivoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servidores-efetivos")
public class ServidorEfetivoResource {

    private final ServidorEfetivoService servidorEfetivoService;

    public ServidorEfetivoResource(ServidorEfetivoService servidorEfetivoService) {
        this.servidorEfetivoService = servidorEfetivoService;
    }

    @GetMapping("/unidade/{unidId}")
    public List<ServidorEfetivoDTO> buscarPorUnidade(@PathVariable Long unidId) {
        return servidorEfetivoService.buscarPorUnidade(unidId);
    }
    
    @GetMapping("/endereco-funcional")
    public List<EnderecoFuncionalDTO> buscarEnderecoFuncional(@RequestParam("nome") String nome) {
        return servidorEfetivoService.buscarEnderecoFuncionalPorNome(nome);
    }

    @GetMapping
    public List<ServidorEfetivo> listarTodos() {
        return servidorEfetivoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServidorEfetivo> buscarPorId(@PathVariable Long id) {
        return servidorEfetivoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody ServidorEfetivo servidor) {
        try {
            ServidorEfetivo salvo = servidorEfetivoService.salvar(servidor);
            return ResponseEntity.ok(salvo);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServidorEfetivo> atualizar(@PathVariable Long id, @RequestBody ServidorEfetivo servidor) {
        try {
            return ResponseEntity.ok(servidorEfetivoService.atualizar(id, servidor));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        servidorEfetivoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
