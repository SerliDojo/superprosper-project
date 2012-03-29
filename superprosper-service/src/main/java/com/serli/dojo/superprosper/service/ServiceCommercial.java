package com.serli.dojo.superprosper.service;

import com.serli.dojo.superprosper.domain.Client;
import com.serli.dojo.superprosper.domain.Contrat;
import com.serli.dojo.superprosper.domain.Prospection;

/**
 * Contrat de service pour la gestion des affaires commerciales.
 * 
 * @author Laurent RUAUD
 */
public interface ServiceCommercial {

	/**
	 * Ajoute une prospection à un client.
	 * 
	 * Cette méthode vérifie quelques règles de gestion avant d'effectuer
	 * l'ajout.
	 * 
	 * @param prospection prospection à ajouter
	 * @param client client auquel ajouter le contrat
	 */
	public void ajouterProspection(Prospection prospection, Client client);

	/**
	 * Ajoute un contrat à un client.
	 * 
	 * Cette méthode vérifie plusieurs règles de gestion avant d'effectuer
	 * l'ajout.
	 * 
	 * @param contrat contrat à ajouter
	 * @param client client auquel ajouter le contrat
	 */
	public void ajouterContrat(Contrat contrat, Client client);

}