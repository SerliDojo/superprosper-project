package com.serli.dojo.superprosper.service.database;

import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

import com.serli.dojo.superprosper.domain.Client;
import com.serli.dojo.superprosper.domain.Contrat;
import com.serli.dojo.superprosper.domain.Prospection;
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
		List<Prospection> prospectionsPrecedentes = client.getProspections();
		for (Prospection prospectionPrecedente : prospectionsPrecedentes) {
			if (DateUtils.isSameDay(prospectionPrecedente.getContact(), prospection.getContact())) {
				throw new RuntimeException("Une prospection a déjà eu lieu à la même date.");
			}
		}

		prospection.setClient(client);
		client.getProspections().add(prospection);

		getEntityManager().persist(prospection);
		getEntityManager().merge(client);
	}

	@Override
	public void ajouterContrat(Contrat contrat, Client client) {
		// Une prospection doit avoir été effectuée à la date de la signature
		boolean prospecteAuMemeJour = false;
		List<Prospection> prospections = client.getProspections();
		for (Prospection prospection : prospections) {
			prospecteAuMemeJour |= DateUtils.isSameDay(prospection.getContact(), contrat.getSignature());
		}
		if (!prospecteAuMemeJour) {
			throw new RuntimeException("Aucune prospection n'a eu lieu à la date de signature.");
		}

		contrat.setClient(client);
		client.getContrats().add(contrat);

		getEntityManager().persist(contrat);
		getEntityManager().merge(client);
	}

}
