package br.com.servidores.service;

import br.com.servidores.model.FotoPessoa;
import br.com.servidores.model.Pessoa;
import br.com.servidores.repository.FotoPessoaRepository;
import br.com.servidores.repository.PessoaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class FotoPessoaService {

    private final FotoPessoaRepository fotoPessoaRepository;
    private final PessoaRepository pessoaRepository;
    private final MinioService minioService;
    private static final String BUCKET = "fotos";

    public FotoPessoaService(FotoPessoaRepository fotoPessoaRepository,
                              PessoaRepository pessoaRepository,
                              MinioService minioService) {
        this.fotoPessoaRepository = fotoPessoaRepository;
        this.pessoaRepository = pessoaRepository;
        this.minioService = minioService;
    }

    public FotoPessoa uploadFoto(Long pessoaId, MultipartFile file) throws Exception {
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        String hash = UUID.randomUUID() + "_" + file.getOriginalFilename();

        minioService.uploadFile(BUCKET, hash, file.getInputStream(), file.getSize(), file.getContentType());

        FotoPessoa foto = new FotoPessoa();
        foto.setPessoa(pessoa);
        foto.setHash(hash);
        foto.setBucket(BUCKET);
        foto.setData(LocalDate.now());

        return fotoPessoaRepository.save(foto);
    }

    public String gerarLinkFoto(Long fotoId) throws Exception {
        FotoPessoa foto = fotoPessoaRepository.findById(fotoId)
                .orElseThrow(() -> new RuntimeException("Foto não encontrada"));

        return minioService.getFileUrl(foto.getBucket(), foto.getHash());
    }
}