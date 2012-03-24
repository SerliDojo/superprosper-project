package com.serli.dojo.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.serli.dojo.service.database.ClientDatabaseService;
import com.serli.dojo.superprosper.domain.Client;

@RunWith(MockitoJUnitRunner.class)
public class ClientDatabaseServiceTest {

	@Mock
	private EntityManager entityManager;

	private ClientDatabaseService clientService;

	@Before
	public void setUp() {
		clientService = new ClientDatabaseService();
		clientService.setEntityManager(entityManager);
	}

	@Test
	public void testRechercherClientsAvecRecherche() {
		List<Client> clientsFictifs = creerClients(20);
		TypedQuery<Client> query = mockNamedQuery("Rechercher", clientsFictifs);

		List<Client> clientsLus = clientService.rechercherClients("MOTCLE");

		verify(query).setParameter("recherche", "MOTCLE");
		assertListEquals(clientsFictifs, clientsLus);
	}

	@Test
	public void testRechercherClientsAvecRechercheEtFiltreClientsSouscris() {
		List<Client> clientsFictifs = creerClients(10);
		TypedQuery<Client> query = mockNamedQuery("RechercherClientsSouscrits", clientsFictifs);

		List<Client> clientsLus = clientService.rechercherClients(ClientService.Filtre.CLIENTS_SOUSCRITS, "MOTCLE");

		verify(query).setParameter("recherche", "MOTCLE");
		assertListEquals(clientsFictifs, clientsLus);
	}

	@Test
	public void testRechercherClientsAvecRechercheEtFiltreClientsProspectes() {
		List<Client> clientsFictifs = creerClients(10);
		TypedQuery<Client> query = mockNamedQuery("RechercherClientsProspectes", clientsFictifs);

		List<Client> clientsLus = clientService.rechercherClients(ClientService.Filtre.CLIENTS_PROSPECTES, "MOTCLE");

		verify(query).setParameter("recherche", "MOTCLE");
		assertListEquals(clientsFictifs, clientsLus);
	}

	@Test
	public void testRechercherClientsAvecRechercheEtFiltreClientsNonProspectes() {
		List<Client> clientsFictifs = creerClients(10);
		TypedQuery<Client> query = mockNamedQuery("RechercherClientsNonProspectes", clientsFictifs);

		List<Client> clientsLus = clientService
				.rechercherClients(ClientService.Filtre.CLIENTS_NON_PROSPECTES, "MOTCLE");

		verify(query).setParameter("recherche", "MOTCLE");
		assertListEquals(clientsFictifs, clientsLus);
	}

	@Test
	public void testRechercherClientsAvecRechercheEtPagination() {
		List<Client> clientsFictifs = creerClients(20);
		TypedQuery<Client> query = mockNamedQuery("Rechercher", clientsFictifs);

		List<Client> clientsLus = clientService.rechercherClients("MOTCLE", 3, 2);

		verify(query).setParameter("recherche", "MOTCLE");
		verify(query).setFirstResult(6);
		verify(query).setMaxResults(2);
		assertListEquals(clientsFictifs, clientsLus);
	}

	@Test
	public void testRechercherClientsAvecRechercheEtFiltreEtPagination() {
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
		queryNamesClientsSouscrits.put(ClientService.Tri.PAR_NOM_PRENOM_ASC, "RechercherClientsSouscritsParNomPrenomAsc");
		queryNamesClientsSouscrits.put(ClientService.Tri.PAR_NOM_PRENOM_DESC, "RechercherClientsSouscritsParNomPrenomDesc");
		queryNamesClientsSouscrits.put(ClientService.Tri.PAR_REGION_ASC, "RechercherClientsSouscritsParRegionAsc");
		queryNamesClientsSouscrits.put(ClientService.Tri.PAR_REGION_DESC, "RechercherClientsSouscritsParRegionDesc");

		Map<ClientService.Tri, String> queryNamesClientsProspectes = new HashMap<ClientService.Tri, String>();
		queryNamesClientsProspectes.put(ClientService.Tri.PAR_NUMERO_ASC, "RechercherClientsProspectesParNumeroAsc");
		queryNamesClientsProspectes.put(ClientService.Tri.PAR_NUMERO_DESC, "RechercherClientsProspectesParNumeroDesc");
		queryNamesClientsProspectes.put(ClientService.Tri.PAR_NOM_PRENOM_ASC, "RechercherClientsProspectesParNomPrenomAsc");
		queryNamesClientsProspectes.put(ClientService.Tri.PAR_NOM_PRENOM_DESC, "RechercherClientsProspectesParNomPrenomDesc");
		queryNamesClientsProspectes.put(ClientService.Tri.PAR_REGION_ASC, "RechercherClientsProspectesParRegionAsc");
		queryNamesClientsProspectes.put(ClientService.Tri.PAR_REGION_DESC, "RechercherClientsProspectesParRegionDesc");

		Map<ClientService.Tri, String> queryNamesClientsNonProspectes = new HashMap<ClientService.Tri, String>();
		queryNamesClientsNonProspectes.put(ClientService.Tri.PAR_NUMERO_ASC, "RechercherClientsNonProspectesParNumeroAsc");
		queryNamesClientsNonProspectes.put(ClientService.Tri.PAR_NUMERO_DESC, "RechercherClientsNonProspectesParNumeroDesc");
		queryNamesClientsNonProspectes.put(ClientService.Tri.PAR_NOM_PRENOM_ASC, "RechercherClientsNonProspectesParNomPrenomAsc");
		queryNamesClientsNonProspectes.put(ClientService.Tri.PAR_NOM_PRENOM_DESC, "RechercherClientsNonProspectesParNomPrenomDesc");
		queryNamesClientsNonProspectes.put(ClientService.Tri.PAR_REGION_ASC, "RechercherClientsNonProspectesParRegionAsc");
		queryNamesClientsNonProspectes.put(ClientService.Tri.PAR_REGION_DESC, "RechercherClientsNonProspectesParRegionDesc");

		Map<ClientService.Filtre, Map<ClientService.Tri, String>> queryNames = new HashMap<ClientService.Filtre, Map<ClientService.Tri, String>>();
		queryNames.put(ClientService.Filtre.TOUS_CLIENTS, queryNamesTousClients);
		queryNames.put(ClientService.Filtre.CLIENTS_SOUSCRITS, queryNamesClientsSouscrits);
		queryNames.put(ClientService.Filtre.CLIENTS_PROSPECTES, queryNamesClientsProspectes);
		queryNames.put(ClientService.Filtre.CLIENTS_NON_PROSPECTES, queryNamesClientsNonProspectes);

		for (ClientService.Filtre filtre : ClientService.Filtre.values()) {
			for (ClientService.Tri tri : ClientService.Tri.values()) {
				List<Client> clientsFictifs = creerClients(20);
				reset(entityManager);
				TypedQuery<Client> query = mockNamedQuery(queryNames.get(filtre).get(tri), clientsFictifs);

				List<Client> clientsLus = clientService.rechercherClients(filtre, "MOTCLE", 3, 2, tri);

				verify(query).setParameter("recherche", "MOTCLE");
				verify(query).setFirstResult(6);
				verify(query).setMaxResults(2);
				assertListEquals(clientsFictifs, clientsLus);
			}
		}
	}

	@Test
	public void testLireClient() {
		Client clientFictif = creerClient("base");
		when(entityManager.find(Client.class, 123)).thenReturn(clientFictif);

		Client clientLu = clientService.lireClient(123);

		assertNotNull(clientLu);
		assertEquals(clientFictif.getNom(), clientLu.getNom());
		assertEquals(clientFictif.getPrenom(), clientLu.getPrenom());
	}

	@Test
	public void testModifierClient() {
		Client clientFictif = creerClient("base");

		clientService.modifierClient(clientFictif);

		verify(entityManager).merge(clientFictif);
	}

	private Client creerClient(String base) {
		Client client = new Client();
		client.setNom("Nom-" + base);
		client.setPrenom("Prenom-" + base);
		client.setTelephone("'Tel-" + base);
		client.setRegion("Reg" + base);
		client.setNaissance(new Date());
		client.setFoyer(base.length());
		client.setAdresse("Adresse-" + base);
		client.setNumero(client.hashCode());

		return client;
	}

	private List<Client> creerClients(int nombre) {
		List<Client> clients = new ArrayList<Client>(nombre);
		for (int i = 0; i < nombre; i++) {
			clients.add(creerClient(Integer.toString(i)));
		}

		return clients;
	}

	private TypedQuery<Client> mockNamedQuery(String queryName, List<Client> clients) {
		TypedQuery<Client> query = mock(TypedQuery.class);
		when(entityManager.createNamedQuery(queryName, Client.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(clients);
		return query;
	}

	private void assertListEquals(List<Client> clientsFictifs, List<Client> clientsLus) {
		assertNotNull(clientsLus);
		assertEquals(clientsFictifs.size(), clientsLus.size());
		assertEquals(clientsFictifs.get(0).getNumero(), clientsLus.get(0).getNumero());
	}
}
