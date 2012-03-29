package com.serli.dojo.superprosper.service.defaut;

import com.serli.dojo.superprosper.domain.Agent;
import com.serli.dojo.superprosper.service.ServiceOrganisation;

/**
 * Implémentation du service d'identification des utilisateurs.
 * 
 * Cette classe utilise l'injection de gestionnaire d'entité telle que proposée
 * par JPA.
 * 
 * @author Laurent RUAUD
 */
public class ServiceOrganisationDefaut extends ServiceGenerique implements ServiceOrganisation {

	@Override
	public Agent lireAgent(String matricule) {
		Agent agent = getEntityManager().find(Agent.class, matricule);
		return agent;
	}

}
