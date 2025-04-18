package br.com.servidores.dto;

import java.time.LocalDate;
import java.time.Period;

public class ServidorEfetivoDTO {

    private String nome;
    private Integer idade;
    private String unidade;
    private String linkFoto;

    public ServidorEfetivoDTO(String nome, LocalDate dataNascimento, String unidade, String linkFoto) {
        this.nome = nome;
        this.idade = Period.between(dataNascimento, LocalDate.now()).getYears(); // Calcular idade
        this.unidade = unidade;
        this.linkFoto = linkFoto;
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getLinkFoto() {
		return linkFoto;
	}

	public void setLinkFoto(String linkFoto) {
		this.linkFoto = linkFoto;
	}



}