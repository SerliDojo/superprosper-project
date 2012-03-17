package com.serli.dojo.service.database;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.serli.dojo.service.AgentService;
import com.serli.dojo.superprosper.domain.Agent;

/**
 * Implémentation du service de lecture des agents prenant comme source
 * une base de données.
 * 
 * Cette classe utilise l'injection de gestionnaire d'entité telle que proposée
 * par JPA.
 * 
 * @author Laurent RUAUD
 */
public class AgentDatabaseService implements AgentService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Agent lireAgent(String matricule){
		Agent agent = entityManager.find(Agent.class, matricule);
		return agent;
	}

}
