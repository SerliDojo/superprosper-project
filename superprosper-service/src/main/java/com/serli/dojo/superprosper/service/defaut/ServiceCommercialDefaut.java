package com.serli.dojo.superprosper.service.defaut;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.serli.dojo.superprosper.domain.Client;
import com.serli.dojo.superprosper.domain.Contrat;
import com.serli.dojo.superprosper.domain.Prospection;
import com.serli.dojo.superprosper.regle.ReglesCommerciales;
import com.serli.dojo.superprosper.service.ServiceCommercial;

/**
 * Implémentation du service de gestion des affaires commerciales.
 * 
 * Cette classe utilise l'injection de gestionnaire d'entité telle que proposée
 * par JPA.
 * 
 * @author Laurent RUAUD
 */
public class ServiceCommercialDefaut extends ServiceGenerique implements ServiceCommercial {

	@Override
	public void ajouterProspection(Prospection prospection, Client client) {
		// Aucune prospection ne doit avoir été effectuée à la même date
		ReglesCommerciales.PAS_DEUX_PROSPECTION_A_LA_MEME_DATE.verifier(new ImmutablePair<Prospection, Client>(
				prospection, client));

		prospection.setClient(client);
		client.getProspections().add(prospection);

		getEntityManager().persist(prospection);
		getEntityManager().merge(client);
	}

	@Override
	public void ajouterContrat(Contrat contrat, Client client) {
		// Une prospection doit avoir été effectuée à la date de la signature
		ReglesCommerciales.PROSPECTION_OBLIGATOIRE_AVANT_CONTRAT.verifier(new ImmutablePair<Contrat, Client>(contrat,
				client));

		contrat.setClient(client);
		client.getContrats().add(contrat);

		getEntityManager().persist(contrat);
		getEntityManager().merge(client);
	}

}
