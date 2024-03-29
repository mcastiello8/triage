package it.prova.triage.dto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


import it.prova.triage.model.Ruolo;
import it.prova.triage.model.StatoUtente;
import it.prova.triage.model.Utente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UtenteDTO {

	private Long id;

	private String username;

	private String password;

	private String confermaPassword;

	private String nome;

	private String cognome;


	private LocalDate dataRegistrazione;

	private StatoUtente stato;

	private Long[] ruoliIds;

	public Utente buildUtenteModel(boolean includeIdRoles) {
		Utente result = new Utente(this.id, this.username, this.password, this.nome, this.cognome,
				this.dataRegistrazione, this.stato);
		if (includeIdRoles && ruoliIds != null)
			result.setRuoli(Arrays.asList(ruoliIds).stream().map(id -> new Ruolo(id)).collect(Collectors.toSet()));

		return result;
	}

	// niente password...
	public static UtenteDTO buildUtenteDTOFromModel(Utente utenteModel) {

		UtenteDTO result = UtenteDTO.builder().id(utenteModel.getId()).username(utenteModel.getUsername())
				.nome(utenteModel.getNome()).cognome(utenteModel.getCognome()).stato(utenteModel.getStato()).build();

		if (!utenteModel.getRuoli().isEmpty())
			result.ruoliIds = utenteModel.getRuoli().stream().map(r -> r.getId()).collect(Collectors.toList())
					.toArray(new Long[] {});

		return result;
	}
	
	
	public static List<UtenteDTO> createUtenteDTOListFromModelList(List<Utente> modelListInput) {
	    return modelListInput.stream().map(utenteEntity -> {
	        return UtenteDTO.buildUtenteDTOFromModel(utenteEntity);
	    }).collect(Collectors.toList());
	}
	
	


}