package it.prova.triage.dto;



import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import it.prova.triage.model.Paziente;
import it.prova.triage.model.StatoPaziente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PazienteDTO {


	private Long id;
	
	@NotBlank(message = "{nome.notblank}")
	private String nome;
	
	@NotBlank(message = "{nome.notblank}")
	private String cognome;
	
	@NotBlank(message = "{nome.notblank}")
	private String codiceFiscale;
	
	@NotBlank(message = "{registrazione.notblank}")
	private LocalDate registrazione;
	
	private StatoPaziente statoPaziente;
	
	public Paziente buildPazienteModel() {
		Paziente result = Paziente.builder().id(this.id).nome(this.nome).cognome(this.cognome).codiceFiscale(codiceFiscale).registrazione(registrazione).statoPaziente(this.statoPaziente).build();
		return result;
	}
	
	public static PazienteDTO buildPazienteDTOFromModel(Paziente pazienteModel) {
		PazienteDTO result = PazienteDTO.builder().id(pazienteModel.getId()).nome(pazienteModel.getNome()).cognome(pazienteModel.getCognome()).codiceFiscale(pazienteModel.getCodiceFiscale()).registrazione(pazienteModel.getRegistrazione()).statoPaziente(pazienteModel.getStatoPaziente()).build();
		return result;
	}
	
	public static List<PazienteDTO> createPazienteDTOListFromModelList(
			List<Paziente> modelListInput) {
		return modelListInput.stream().map(inputEntity -> {
			return PazienteDTO.buildPazienteDTOFromModel(inputEntity);
		}).collect(Collectors.toList());
	}
	
}