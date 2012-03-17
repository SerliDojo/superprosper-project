package com.serli.dojo.service;

import com.serli.dojo.superprosper.domain.Agent;

/**
 * Contrat de service pour la récupération des informations concernant les agents.
 * 
 * @author Laurent RUAUD
 */
public interface AgentService {

	/**
	 * Renvoie les informations sur l'agent correspondant au matricule.
	 * 
	 * @param matricule matricule de l'agent
	 * @return l'objet représentant l'agent
	 */
	Agent lireAgent(String matricule);

}