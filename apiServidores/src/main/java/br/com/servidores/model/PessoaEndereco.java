package br.com.servidores.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pessoa_endereco")
public class PessoaEndereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long peId;

	@ManyToOne
	@JoinColumn(name = "pes_id")
	private Pessoa pessoa;

	@ManyToOne
	@JoinColumn(name = "end_id")
	private Endereco endereco;

	// Getters e Setters
	public Long getPeId() {
		return peId;
	}

	public void setPeId(Long peId) {
		this.peId = peId;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
