package br.com.servidores.resource;

import br.com.servidores.model.ServidorTemporario;
import br.com.servidores.service.ServidorTemporarioService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/servidores-temporarios")
public class ServidorTemporarioResource {

    private final ServidorTemporarioService service;

    public ServidorTemporarioResource(ServidorTemporarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<ServidorTemporario> findAll() {
        return service.findAll();
    }

    @PostMapping
    public ServidorTemporario save(@RequestBody ServidorTemporario servidor) {
        return service.salvar(servidor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ServidorTemporario> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
