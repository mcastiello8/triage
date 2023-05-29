package it.prova.triage;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

import it.prova.triage.dto.PazienteDTO;
import it.prova.triage.model.Paziente;
import it.prova.triage.model.Ruolo;
import it.prova.triage.model.StatoPaziente;
import it.prova.triage.model.Utente;
import it.prova.triage.service.paziente.PazienteService;
import it.prova.triage.service.ruolo.RuoloService;
import it.prova.triage.service.utente.UtenteService;

@SpringBootApplication
@CrossOrigin
public class TriageApplication implements CommandLineRunner {
	@Autowired
	private RuoloService ruoloServiceInstance;
	@Autowired
	private UtenteService utenteServiceInstance;
	@Autowired
	private PazienteService pazienteServiceInstance;
	
	public static void main(String[] args) {
		SpringApplication.run(TriageApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("admin", Ruolo.ROLE_ADMIN) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("admin", Ruolo.ROLE_ADMIN));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("operator", Ruolo.ROLE_SUB_OPERATOR) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("operator", Ruolo.ROLE_SUB_OPERATOR));
		}
		if (utenteServiceInstance.findByUsername("admin") == null) {
			Utente admin = new Utente("admin", "admin", "admin", "admin", LocalDate.now());
			admin.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("admin", Ruolo.ROLE_ADMIN));
			utenteServiceInstance.inserisciNuovo(admin);
// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(admin.getId());
		}
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("admin", Ruolo.ROLE_ADMIN) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("admin", Ruolo.ROLE_ADMIN));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("operator", Ruolo.ROLE_SUB_OPERATOR) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("operator", Ruolo.ROLE_SUB_OPERATOR));
		}
		if (utenteServiceInstance.findByUsername("operator") == null) {
			Utente utente = new Utente("operator", "operator", "operator", "operator", LocalDate.now());
			utente.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("operator", Ruolo.ROLE_SUB_OPERATOR));
			utenteServiceInstance.inserisciNuovo(utente);
// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(utente.getId());
		}
		
		Paziente paziente = new Paziente("Mario", "Rossi", "MARRSS123", LocalDate.now(), StatoPaziente.IN_ATTESA_VISITA);
		PazienteDTO pazienteDTO = PazienteDTO.buildPazienteDTOFromModel(paziente);
		pazienteServiceInstance.inserisciPaziente(pazienteDTO);
		
	}
}
