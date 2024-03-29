package it.prova.triage.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "paziente")
public class Paziente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cognome")
	private String cognome;
	@Column(name = "codicefiscale")
	private String codiceFiscale;
	@Column(name = "registrazione")
	private LocalDate registrazione;
	@Column(name = "statopaziente")
	private StatoPaziente statoPaziente;
	
	
	public Paziente(String nome, String cognome, String codiceFiscale, LocalDate registrazione, StatoPaziente statoPaziente) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.registrazione = registrazione;
		this.statoPaziente = statoPaziente;
	}
	
	
	
}