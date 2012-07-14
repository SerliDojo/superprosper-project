package com.serli.dojo.superprosper.service;

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

import com.serli.dojo.superprosper.domain.Client;
import com.serli.dojo.superprosper.service.ServiceClientele;
import com.serli.dojo.superprosper.service.defaut.ServiceClienteleDefaut;

@RunWith(MockitoJUnitRunner.class)
public class ServiceClienteleDefautTest {

	@Mock
	private EntityManager entityManager;

	private ServiceClienteleDefaut clientService;

	@Before
	public void setUp() {
		clientService = new ServiceClienteleDefaut();
		clientService.setEntityManager(entityManager);
	}

	@Test
	public void testRechercherClientsAvecRecherche() {
		List<Client> clientsFictifs = creerClients(20);
		TypedQuery<Client> query = mockNamedQuery("Rechercher", clientsFictifs);

		List<Client> clientsLus = clientService.rechercherClients("MOTCLE");

		verify(query).setParameter("recherche", "%MOTCLE%");
		assertListEquals(clientsFictifs, clientsLus);
	}

	@Test
	public void testRechercherClientsAvecRechercheEtFiltreClientsSouscris() {
		List<Client> clientsFictifs = creerClients(10);
		TypedQuery<Client> query = mockNamedQuery("RechercherClientsSouscrits", clientsFictifs);

		List<Client> clientsLus = clientService.rechercherClients(ServiceClientele.Filtre.CLIENTS_SOUSCRITS, "MOTCLE");

		verify(query).setParameter("recherche", "%MOTCLE%");
		assertListEquals(clientsFictifs, clientsLus);
	}

	@Test
	public void testRechercherClientsAvecRechercheEtFiltreClientsProspectes() {
		List<Client> clientsFictifs = creerClients(10);
		TypedQuery<Client> query = mockNamedQuery("RechercherClientsProspectes", clientsFictifs);

		List<Client> clientsLus = clientService.rechercherClients(ServiceClientele.Filtre.CLIENTS_PROSPECTES, "MOTCLE");

		verify(query).setParameter("recherche", "%MOTCLE%");
		assertListEquals(clientsFictifs, clientsLus);
	}

	@Test
	public void testRechercherClientsAvecRechercheEtFiltreClientsNonProspectes() {
		List<Client> clientsFictifs = creerClients(10);
		TypedQuery<Client> query = mockNamedQuery("RechercherClientsNonProspectes", clientsFictifs);

		List<Client> clientsLus = clientService.rechercherClients(ServiceClientele.Filtre.CLIENTS_NON_PROSPECTES,
				"MOTCLE");

		verify(query).setParameter("recherche", "%MOTCLE%");
		assertListEquals(clientsFictifs, clientsLus);
	}

	@Test
	public void testRechercherClientsAvecRechercheEtPagination() {
		List<Client> clientsFictifs = creerClients(20);
		TypedQuery<Client> query = mockNamedQuery("Rechercher", clientsFictifs);

		List<Client> clientsLus = clientService.rechercherClients("MOTCLE", 3, 2);

		verify(query).setParameter("recherche", "%MOTCLE%");
		verify(query).setFirstResult(6);
		verify(query).setMaxResults(2);
		assertListEquals(clientsFictifs, clientsLus);
	}

	@Test
	public void testRechercherClientsAvecRechercheEtFiltreEtPagination() {
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

		Map<ServiceClientele.Filtre, Map<ServiceClientele.Tri, String>> queryNames = new HashMap<ServiceClientele.Filtre, Map<ServiceClientele.Tri, String>>();
		queryNames.put(ServiceClientele.Filtre.TOUS_CLIENTS, queryNamesTousClients);
		queryNames.put(ServiceClientele.Filtre.CLIENTS_SOUSCRITS, queryNamesClientsSouscrits);
		queryNames.put(ServiceClientele.Filtre.CLIENTS_PROSPECTES, queryNamesClientsProspectes);
		queryNames.put(ServiceClientele.Filtre.CLIENTS_NON_PROSPECTES, queryNamesClientsNonProspectes);

		for (ServiceClientele.Filtre filtre : ServiceClientele.Filtre.values()) {
			for (ServiceClientele.Tri tri : ServiceClientele.Tri.values()) {
				List<Client> clientsFictifs = creerClients(20);
				reset(entityManager);
				TypedQuery<Client> query = mockNamedQuery(queryNames.get(filtre).get(tri), clientsFictifs);

				List<Client> clientsLus = clientService.rechercherClients(filtre, "MOTCLE", 3, 2, tri);

				verify(query).setParameter("recherche", "%MOTCLE%");
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
