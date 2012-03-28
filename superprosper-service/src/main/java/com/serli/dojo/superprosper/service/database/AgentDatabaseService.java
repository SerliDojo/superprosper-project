package com.serli.dojo.superprosper.service.database;

import com.serli.dojo.superprosper.domain.Agent;
import com.serli.dojo.superprosper.service.AgentService;

/**
 * Implémentation du service de lecture des agents prenant comme source une base
 * de données.
 * 
 * Cette classe utilise l'injection de gestionnaire d'entité telle que proposée
 * par JPA.
 * 
 * @author Laurent RUAUD
 */
public class AgentDatabaseService extends GenericDatabaseService implements AgentService {

	@Override
	public Agent lireAgent(String matricule) {
		Agent agent = getEntityManager().find(Agent.class, matricule);
		return agent;
	}

}
