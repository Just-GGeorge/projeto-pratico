package br.com.servidores.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "lotacao")
public class Lotacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lot_id")
    private Long lotId;

    @ManyToOne
    @JoinColumn(name = "pes_id")
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "unid_id")
    private Unidade unidade;

    private LocalDate lotDataLotacao;
    private LocalDate lotDataRemocao;
    private String lotPortaria;
	public Long getLotId() {
		return lotId;
	}
	public void setLotId(Long lotId) {
		this.lotId = lotId;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	public LocalDate getLotDataLotacao() {
		return lotDataLotacao;
	}
	public void setLotDataLotacao(LocalDate lotDataLotacao) {
		this.lotDataLotacao = lotDataLotacao;
	}
	public LocalDate getLotDataRemocao() {
		return lotDataRemocao;
	}
	public void setLotDataRemocao(LocalDate lotDataRemocao) {
		this.lotDataRemocao = lotDataRemocao;
	}
	public String getLotPortaria() {
		return lotPortaria;
	}
	public void setLotPortaria(String lotPortaria) {
		this.lotPortaria = lotPortaria;
	}

    // Getters e setters
    
}