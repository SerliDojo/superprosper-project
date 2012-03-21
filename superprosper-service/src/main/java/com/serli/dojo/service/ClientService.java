package com.serli.dojo.service;

import java.util.List;

import com.serli.dojo.superprosper.domain.Client;
import com.serli.dojo.superprosper.domain.Contrat;
import com.serli.dojo.superprosper.domain.Prospection;

/**
 * Contrat de service pour la récupération des informations concernant les
 * clients.
 * 
 * @author Laurent RUAUD
 */
public interface ClientService {

	/** Enumération des tris possibles lors d'une rechercher de clients. */
	public enum Tri {
		/** Tri ascendant par numéro d'identification. */
		PAR_NUMERO_ASC,
		/** Tri descendant par numéro d'identification. */
		PAR_NUMERO_DESC,
		/** Tri ascendant par nom puis prénom. */
		PAR_NOM_PRENOM_ASC,
		/** Tri descendant par nom puis prénom. */
		PAR_NOM_PRENOM_DESC,
		/** Tri ascendant par région. */
		PAR_REGION_ASC,
		/** Tri descendant par région. */
		PAR_REGION_DESC
	}

	/**
	 * Enumération des filtres prédéfinis utilisables lors d'une rechercher de
	 * clients.
	 */
	public enum Filtre {
		/** Aucun filtre appliqué. */
		TOUS_CLIENTS,
		/** Clients n'ayant pas encore été prospectés. */
		CLIENTS_NON_PROSPECTES,
		/** Clients ayant été prospectés mais n'ayant pas souscrit de contrat. */
		CLIENTS_PROSPECTES,
		/** Clients ayant souscris un contrat. */
		CLIENTS_SOUSCRITS
	}

	/**
	 * Recherche des clients par leur nom ou leur prenom.
	 * 
	 * Cette méthode est équivalente à
	 * <code>rechercherClients(Filtre.TOUS_CLIENTS, recherche)</code>.
	 * 
	 * @param recherche chaîne contenant les mots à rechercher
	 * @return une liste de clients
	 * @see ClientService#rechercherClients(Filtre, String)
	 */
	List<Client> rechercherClients(String recherche);

	/**
	 * Recherche des clients par leur nom ou leur prenom.
	 * 
	 * @param recherche chaîne contenant les mots à rechercher
	 * @return une liste de clients
	 */
	List<Client> rechercherClients(Filtre filtre, String recherche);

	/**
	 * Recherche une plage de clients par leur nom ou leur prenom.
	 * 
	 * Cette méthode est équivalente à
	 * <code>rechercherClients(Filtre.TOUS_CLIENTS, recherche, decalage, nombre, Tri.PAR_NUMERO_ASC)</code>
	 * .
	 * 
	 * @param recherche chaîne contenant les mots à rechercher
	 * @param decalage nombre d'éléments à ignorer
	 * @param nombre nombre maximum d'éléments à rechercher
	 * @return une liste de clients
	 */
	List<Client> rechercherClients(String recherche, int decalage, int nombre);

	/**
	 * Recherche une plage de clients par leur nom ou leur prenom.
	 * 
	 * @param filtre filtre à appliquer
	 * @param recherche chaîne contenant les mots à rechercher
	 * @param decalage nombre d'éléments à ignorer
	 * @param nombre nombre maximum d'éléments à rechercher
	 * @param tri tri à utiliser
	 * @return une liste de clients
	 * @see ClientService#rechercherClients(Filtre, String, int, int, Tri)
	 */
	List<Client> rechercherClients(Filtre filtre, String recherche, int decalage, int nombre, Tri tri);

	/**
	 * Renvoie les informations sur le client correspondant au numéro.
	 * 
	 * @param numero numéro du client
	 * @return l'objet représentant le client
	 */
	Client lireClient(Integer numero);

	/**
	 * Modifie les informations de détail d'un client.
	 * 
	 * @param client client à modifier
	 */
	void modifierClient(Client client);

	/**
	 * Ajoute une prospection à un client.
	 * 
	 * Cette méthode vérifie quelques règles de gestion avant d'effectuer
	 * l'ajout.
	 * 
	 * @param prospection prospection à ajouter
	 * @param client client auquel ajouter le contrat
	 */
	void ajouterProspection(Prospection prospection, Client client);

	/**
	 * Ajoute un contrat à un client.
	 * 
	 * Cette méthode vérifie plusieurs règles de gestion avant d'effectuer
	 * l'ajout.
	 * 
	 * @param contrat contrat à ajouter
	 * @param client client auquel ajouter le contrat
	 */
	void ajouterContrat(Contrat contrat, Client client);

}