package br.com.servidores.resource;

import br.com.servidores.model.FotoPessoa;
import br.com.servidores.service.FotoPessoaService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fotos")
public class FotoPessoaResource {

    private final FotoPessoaService fotoPessoaService;

    public FotoPessoaResource(FotoPessoaService fotoPessoaService) {
        this.fotoPessoaService = fotoPessoaService;
    }

    @PostMapping(value = "/upload/{pessoaId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoPessoa uploadFoto(
            @PathVariable Long pessoaId,
            @RequestParam("file") MultipartFile file
    ) throws Exception {
        return fotoPessoaService.uploadFoto(pessoaId, file);
    }

    @GetMapping("/url/{fotoId}")
    public String gerarLink(@PathVariable Long fotoId) throws Exception {
        return fotoPessoaService.gerarLinkFoto(fotoId);
    }
}
