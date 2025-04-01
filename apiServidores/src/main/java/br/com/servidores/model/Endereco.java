package br.com.servidores.model;

import jakarta.persistence.*;

@Entity
@Table(name = "endereco")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "end_id")
    private Long endId;

    private String endTipoLogradouro;
    private String endLogradouro;
    private Integer endNumero;
    private String endBairro;

    @ManyToOne
    @JoinColumn(name = "cid_id")
    private Cidade cidade;

	public Long getEndId() {
		return endId;
	}

	public void setEndId(Long endId) {
		this.endId = endId;
	}

	public String getEndTipoLogradouro() {
		return endTipoLogradouro;
	}

	public void setEndTipoLogradouro(String endTipoLogradouro) {
		this.endTipoLogradouro = endTipoLogradouro;
	}

	public String getEndLogradouro() {
		return endLogradouro;
	}

	public void setEndLogradouro(String endLogradouro) {
		this.endLogradouro = endLogradouro;
	}

	public Integer getEndNumero() {
		return endNumero;
	}

	public void setEndNumero(Integer endNumero) {
		this.endNumero = endNumero;
	}

	public String getEndBairro() {
		return endBairro;
	}

	public void setEndBairro(String endBairro) {
		this.endBairro = endBairro;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

    // Getters e setters
    
}