package com.serli.dojo.service.database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	private static final Map<ClientService.Filtre, Map<ClientService.Tri, String>> queryNames = new HashMap<ClientService.Filtre, Map<ClientService.Tri, String>>();
	static {
		Map<ClientService.Tri, String> queryNamesTousClients = new HashMap<ClientService.Tri, String>();
		queryNamesTousClients.put(ClientService.Tri.PAR_NUMERO_ASC, "RechercherParNumeroAsc");
		queryNamesTousClients.put(ClientService.Tri.PAR_NUMERO_DESC, "RechercherParNumeroDesc");
		queryNamesTousClients.put(ClientService.Tri.PAR_NOM_PRENOM_ASC, "RechercherParNomPrenomAsc");
		queryNamesTousClients.put(ClientService.Tri.PAR_NOM_PRENOM_DESC, "RechercherParNomPrenomDesc");
		queryNamesTousClients.put(ClientService.Tri.PAR_REGION_ASC, "RechercherParRegionAsc");
		queryNamesTousClients.put(ClientService.Tri.PAR_REGION_DESC, "RechercherParRegionDesc");

		Map<ClientService.Tri, String> queryNamesClientsSouscrits = new HashMap<ClientService.Tri, String>();
		queryNamesClientsSouscrits.put(ClientService.Tri.PAR_NUMERO_ASC, "RechercherClientsSouscritsParNumeroAsc");
		queryNamesClientsSouscrits.put(ClientService.Tri.PAR_NUMERO_DESC, "RechercherClientsSouscritsParNumeroDesc");
		queryNamesClientsSouscrits.put(ClientService.Tri.PAR_NOM_PRENOM_ASC,
				"RechercherClientsSouscritsParNomPrenomAsc");
		queryNamesClientsSouscrits.put(ClientService.Tri.PAR_NOM_PRENOM_DESC,
				"RechercherClientsSouscritsParNomPrenomDesc");
		queryNamesClientsSouscrits.put(ClientService.Tri.PAR_REGION_ASC, "RechercherClientsSouscritsParRegionAsc");
		queryNamesClientsSouscrits.put(ClientService.Tri.PAR_REGION_DESC, "RechercherClientsSouscritsParRegionDesc");

		Map<ClientService.Tri, String> queryNamesClientsProspectes = new HashMap<ClientService.Tri, String>();
		queryNamesClientsProspectes.put(ClientService.Tri.PAR_NUMERO_ASC, "RechercherClientsProspectesParNumeroAsc");
		queryNamesClientsProspectes.put(ClientService.Tri.PAR_NUMERO_DESC, "RechercherClientsProspectesParNumeroDesc");
		queryNamesClientsProspectes.put(ClientService.Tri.PAR_NOM_PRENOM_ASC,
				"RechercherClientsProspectesParNomPrenomAsc");
		queryNamesClientsProspectes.put(ClientService.Tri.PAR_NOM_PRENOM_DESC,
				"RechercherClientsProspectesParNomPrenomDesc");
		queryNamesClientsProspectes.put(ClientService.Tri.PAR_REGION_ASC, "RechercherClientsProspectesParRegionAsc");
		queryNamesClientsProspectes.put(ClientService.Tri.PAR_REGION_DESC, "RechercherClientsProspectesParRegionDesc");

		Map<ClientService.Tri, String> queryNamesClientsNonProspectes = new HashMap<ClientService.Tri, String>();
		queryNamesClientsNonProspectes.put(ClientService.Tri.PAR_NUMERO_ASC,
				"RechercherClientsNonProspectesParNumeroAsc");
		queryNamesClientsNonProspectes.put(ClientService.Tri.PAR_NUMERO_DESC,
				"RechercherClientsNonProspectesParNumeroDesc");
		queryNamesClientsNonProspectes.put(ClientService.Tri.PAR_NOM_PRENOM_ASC,
				"RechercherClientsNonProspectesParNomPrenomAsc");
		queryNamesClientsNonProspectes.put(ClientService.Tri.PAR_NOM_PRENOM_DESC,
				"RechercherClientsNonProspectesParNomPrenomDesc");
		queryNamesClientsNonProspectes.put(ClientService.Tri.PAR_REGION_ASC,
				"RechercherClientsNonProspectesParRegionAsc");
		queryNamesClientsNonProspectes.put(ClientService.Tri.PAR_REGION_DESC,
				"RechercherClientsNonProspectesParRegionDesc");

		queryNames.put(ClientService.Filtre.TOUS_CLIENTS, queryNamesTousClients);
		queryNames.put(ClientService.Filtre.CLIENTS_SOUSCRITS, queryNamesClientsSouscrits);
		queryNames.put(ClientService.Filtre.CLIENTS_PROSPECTES, queryNamesClientsProspectes);
		queryNames.put(ClientService.Filtre.CLIENTS_NON_PROSPECTES, queryNamesClientsNonProspectes);
	}

	@Override
	public List<Client> rechercherClients(String recherche) {
		return rechercherClients(Filtre.TOUS_CLIENTS, recherche);
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
			queryName = "Rechercher";
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
		TypedQuery<Client> query = getEntityManager().createNamedQuery(queryNames.get(filtre).get(tri), Client.class);
		query.setParameter("recherche", recherche);
		query.setFirstResult(decalage * nombre);
		query.setMaxResults(nombre);

		return query.getResultList();
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
