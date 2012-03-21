package com.serli.dojo.service.database;

import java.util.List;

import javax.persistence.TypedQuery;

import com.serli.dojo.service.ClientService;
import com.serli.dojo.superprosper.domain.Client;
import com.serli.dojo.superprosper.domain.Contrat;
import com.serli.dojo.superprosper.domain.Prospection;

/**
 * Implémentation du service de gestion des clients prenant comme source une
 * base de données.
 * 
 * Cette classe utilise l'injection de gestionnaire d'entité telle que proposée
 * par JPA.
 * 
 * @author Laurent RUAUD
 */
public class ClientDatabaseService extends GenericDatabaseService implements ClientService {

	@Override
	public List<Client> rechercherClients(String recherche) {
		TypedQuery<Client> query = getEntityManager().createNamedQuery("Rechercher", Client.class);
		query.setParameter("recherche", recherche);

		return query.getResultList();
	}

	@Override
	public List<Client> rechercherClients(Filtre filtre, String recherche) {
		String queryName = "Rechercher";

		switch (filtre) {
		case CLIENTS_SOUSCRITS:
			queryName = "RechercherClientsSouscrits";
			break;
		case CLIENTS_PROSPECTES:
			queryName = "RechercherClientsProspectes";
			break;
		case CLIENTS_NON_PROSPECTES:
			queryName = "RechercherClientsNonProspectes";
			break;
		default:
			break;
		}

		TypedQuery<Client> query = getEntityManager().createNamedQuery(queryName, Client.class);
		query.setParameter("recherche", recherche);

		return query.getResultList();
	}

	@Override
	public List<Client> rechercherClients(String recherche, int decalage, int nombre) {
		TypedQuery<Client> query = getEntityManager().createNamedQuery("Rechercher", Client.class);
		query.setParameter("recherche", recherche);
		query.setFirstResult(decalage * nombre);
		query.setMaxResults(nombre);

		return query.getResultList();
	}

	@Override
	public List<Client> rechercherClients(Filtre filtre, String recherche, int decalage, int nombre, Tri tri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client lireClient(Integer numero) {
		Client client = getEntityManager().find(Client.class, numero);
		return client;
	}

	@Override
	public void modifierClient(Client client) {
		getEntityManager().merge(client);
	}

	@Override
	public void ajouterProspection(Prospection prospection, Client client) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ajouterContrat(Contrat contrat, Client client) {
		// TODO Auto-generated method stub

	}

}
