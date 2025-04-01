package br.com.servidores.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pessoa")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pes_id")
    private Long pesId;

    private String pesNome;
    private LocalDate pesDataNascimento;
    private String pesSexo;
    private String pesMae;
    private String pesPai;

	public Long getPesId() {
		return pesId;
	}

	public void setPesId(Long pesId) {
		this.pesId = pesId;
	}

	public String getPesNome() {
		return pesNome;
	}

	public void setPesNome(String pesNome) {
		this.pesNome = pesNome;
	}

	public LocalDate getPesDataNascimento() {
		return pesDataNascimento;
	}

	public void setPesDataNascimento(LocalDate pesDataNascimento) {
		this.pesDataNascimento = pesDataNascimento;
	}

	public String getPesSexo() {
		return pesSexo;
	}

	public void setPesSexo(String pesSexo) {
		this.pesSexo = pesSexo;
	}

	public String getPesMae() {
		return pesMae;
	}

	public void setPesMae(String pesMae) {
		this.pesMae = pesMae;
	}

	public String getPesPai() {
		return pesPai;
	}

	public void setPesPai(String pesPai) {
		this.pesPai = pesPai;
	}

    
}
