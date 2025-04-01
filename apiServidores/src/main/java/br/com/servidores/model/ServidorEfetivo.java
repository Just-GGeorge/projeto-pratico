package br.com.servidores.model;

import jakarta.persistence.*;

@Entity
@Table(name = "servidor_efetivo")
public class ServidorEfetivo {

    @Id
    private Long pesId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "pes_id")
    private Pessoa pessoa;

    @Column(name = "se_matricula", unique = true)
    private String seMatricula;

    public Long getPesId() {
        return pesId;
    }

    public void setPesId(Long pesId) {
        this.pesId = pesId;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getSeMatricula() {
        return seMatricula;
    }

    public void setSeMatricula(String seMatricula) {
        this.seMatricula = seMatricula;
    }
}
