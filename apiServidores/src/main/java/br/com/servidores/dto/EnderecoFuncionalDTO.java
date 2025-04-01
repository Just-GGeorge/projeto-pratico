package br.com.servidores.dto;

public class EnderecoFuncionalDTO {

	private String nomeServidor;
	private String unidade;
	private String tipoLogradouro;
	private String logradouro;
	private Integer numero;
	private String bairro;
	private String cidade;
	private String uf;

	public EnderecoFuncionalDTO(String nomeServidor, String unidade, String tipoLogradouro, String logradouro,
			Integer numero, String bairro, String cidade, String uf) {
		this.nomeServidor = nomeServidor;
		this.unidade = unidade;
		this.tipoLogradouro = tipoLogradouro;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
	}

	public String getNomeServidor() {
		return nomeServidor;
	}

	public String getUnidade() {
		return unidade;
	}

	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public String getUf() {
		return uf;
	}
}
