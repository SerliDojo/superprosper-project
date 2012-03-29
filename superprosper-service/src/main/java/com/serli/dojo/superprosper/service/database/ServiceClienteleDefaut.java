package com.serli.dojo.superprosper.service.database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import com.serli.dojo.superprosper.domain.Client;
import com.serli.dojo.superprosper.service.ServiceClientele;

/**
 * Implémentation du service de gestion des clients.
 * 
 * Cette classe utilise l'injection de gestionnaire d'entité telle que proposée
 * par JPA.
 * 
 * @author Laurent RUAUD
 */
public class ServiceClienteleDefaut extends ServiceGenerique implements ServiceClientele {

	private static final Map<ServiceClientele.Filtre, Map<ServiceClientele.Tri, String>> queryNames = new HashMap<ServiceClientele.Filtre, Map<ServiceClientele.Tri, String>>();
	static {
		Map<ServiceClientele.Tri, String> queryNamesTousClients = new HashMap<ServiceClientele.Tri, String>();
		queryNamesTousClients.put(ServiceClientele.Tri.PAR_NUMERO_ASC, "RechercherParNumeroAsc");
		queryNamesTousClients.put(ServiceClientele.Tri.PAR_NUMERO_DESC, "RechercherParNumeroDesc");
		queryNamesTousClients.put(ServiceClientele.Tri.PAR_NOM_PRENOM_ASC, "RechercherParNomPrenomAsc");
		queryNamesTousClients.put(ServiceClientele.Tri.PAR_NOM_PRENOM_DESC, "RechercherParNomPrenomDesc");
		queryNamesTousClients.put(ServiceClientele.Tri.PAR_REGION_ASC, "RechercherParRegionAsc");
		queryNamesTousClients.put(ServiceClientele.Tri.PAR_REGION_DESC, "RechercherParRegionDesc");

		Map<ServiceClientele.Tri, String> queryNamesClientsSouscrits = new HashMap<ServiceClientele.Tri, String>();
		queryNamesClientsSouscrits.put(ServiceClientele.Tri.PAR_NUMERO_ASC, "RechercherClientsSouscritsParNumeroAsc");
		queryNamesClientsSouscrits.put(ServiceClientele.Tri.PAR_NUMERO_DESC, "RechercherClientsSouscritsParNumeroDesc");
		queryNamesClientsSouscrits.put(ServiceClientele.Tri.PAR_NOM_PRENOM_ASC,
				"RechercherClientsSouscritsParNomPrenomAsc");
		queryNamesClientsSouscrits.put(ServiceClientele.Tri.PAR_NOM_PRENOM_DESC,
				"RechercherClientsSouscritsParNomPrenomDesc");
		queryNamesClientsSouscrits.put(ServiceClientele.Tri.PAR_REGION_ASC, "RechercherClientsSouscritsParRegionAsc");
		queryNamesClientsSouscrits.put(ServiceClientele.Tri.PAR_REGION_DESC, "RechercherClientsSouscritsParRegionDesc");

		Map<ServiceClientele.Tri, String> queryNamesClientsProspectes = new HashMap<ServiceClientele.Tri, String>();
		queryNamesClientsProspectes.put(ServiceClientele.Tri.PAR_NUMERO_ASC, "RechercherClientsProspectesParNumeroAsc");
		queryNamesClientsProspectes.put(ServiceClientele.Tri.PAR_NUMERO_DESC,
				"RechercherClientsProspectesParNumeroDesc");
		queryNamesClientsProspectes.put(ServiceClientele.Tri.PAR_NOM_PRENOM_ASC,
				"RechercherClientsProspectesParNomPrenomAsc");
		queryNamesClientsProspectes.put(ServiceClientele.Tri.PAR_NOM_PRENOM_DESC,
				"RechercherClientsProspectesParNomPrenomDesc");
		queryNamesClientsProspectes.put(ServiceClientele.Tri.PAR_REGION_ASC, "RechercherClientsProspectesParRegionAsc");
		queryNamesClientsProspectes.put(ServiceClientele.Tri.PAR_REGION_DESC,
				"RechercherClientsProspectesParRegionDesc");

		Map<ServiceClientele.Tri, String> queryNamesClientsNonProspectes = new HashMap<ServiceClientele.Tri, String>();
		queryNamesClientsNonProspectes.put(ServiceClientele.Tri.PAR_NUMERO_ASC,
				"RechercherClientsNonProspectesParNumeroAsc");
		queryNamesClientsNonProspectes.put(ServiceClientele.Tri.PAR_NUMERO_DESC,
				"RechercherClientsNonProspectesParNumeroDesc");
		queryNamesClientsNonProspectes.put(ServiceClientele.Tri.PAR_NOM_PRENOM_ASC,
				"RechercherClientsNonProspectesParNomPrenomAsc");
		queryNamesClientsNonProspectes.put(ServiceClientele.Tri.PAR_NOM_PRENOM_DESC,
				"RechercherClientsNonProspectesParNomPrenomDesc");
		queryNamesClientsNonProspectes.put(ServiceClientele.Tri.PAR_REGION_ASC,
				"RechercherClientsNonProspectesParRegionAsc");
		queryNamesClientsNonProspectes.put(ServiceClientele.Tri.PAR_REGION_DESC,
				"RechercherClientsNonProspectesParRegionDesc");

		queryNames.put(ServiceClientele.Filtre.TOUS_CLIENTS, queryNamesTousClients);
		queryNames.put(ServiceClientele.Filtre.CLIENTS_SOUSCRITS, queryNamesClientsSouscrits);
		queryNames.put(ServiceClientele.Filtre.CLIENTS_PROSPECTES, queryNamesClientsProspectes);
		queryNames.put(ServiceClientele.Filtre.CLIENTS_NON_PROSPECTES, queryNamesClientsNonProspectes);
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

}
