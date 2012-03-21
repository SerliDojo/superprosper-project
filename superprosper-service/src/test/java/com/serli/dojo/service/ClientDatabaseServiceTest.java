package com.serli.dojo.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		TypedQuery<Client> query = mock(TypedQuery.class);
		when(entityManager.createNamedQuery("Rechercher", Client.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(clientsFictifs);

		List<Client> clientsLus = clientService.rechercherClients("MOTCLE");

		verify(query).setParameter("recherche", "MOTCLE");
		assertNotNull(clientsLus);
		assertEquals(clientsFictifs.size(), clientsLus.size());
		assertEquals(clientsFictifs.get(0).getNumero(), clientsLus.get(0).getNumero());
	}

	@Test
	public void testRechercherClientsAvecRechercheEtFiltreClientsSouscris() {
		List<Client> clientsFictifs = creerClients(10);
		TypedQuery<Client> query = mock(TypedQuery.class);
		when(entityManager.createNamedQuery("RechercherClientsSouscrits", Client.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(clientsFictifs);

		List<Client> clientsLus = clientService.rechercherClients(ClientService.Filtre.CLIENTS_SOUSCRITS, "MOTCLE");

		verify(query).setParameter("recherche", "MOTCLE");
		assertNotNull(clientsLus);
		assertEquals(clientsFictifs.size(), clientsLus.size());
		assertEquals(clientsFictifs.get(0).getNumero(), clientsLus.get(0).getNumero());
	}

	@Test
	public void testRechercherClientsAvecRechercheEtFiltreClientsProspectes() {
		List<Client> clientsFictifs = creerClients(10);
		TypedQuery<Client> query = mock(TypedQuery.class);
		when(entityManager.createNamedQuery("RechercherClientsProspectes", Client.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(clientsFictifs);

		List<Client> clientsLus = clientService.rechercherClients(ClientService.Filtre.CLIENTS_PROSPECTES, "MOTCLE");

		verify(query).setParameter("recherche", "MOTCLE");
		assertNotNull(clientsLus);
		assertEquals(clientsFictifs.size(), clientsLus.size());
		assertEquals(clientsFictifs.get(0).getNumero(), clientsLus.get(0).getNumero());
	}

	@Test
	public void testRechercherClientsAvecRechercheEtFiltreClientsNonProspectes() {
		List<Client> clientsFictifs = creerClients(10);
		TypedQuery<Client> query = mock(TypedQuery.class);
		when(entityManager.createNamedQuery("RechercherClientsNonProspectes", Client.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(clientsFictifs);

		List<Client> clientsLus = clientService.rechercherClients(ClientService.Filtre.CLIENTS_NON_PROSPECTES, "MOTCLE");

		verify(query).setParameter("recherche", "MOTCLE");
		assertNotNull(clientsLus);
		assertEquals(clientsFictifs.size(), clientsLus.size());
		assertEquals(clientsFictifs.get(0).getNumero(), clientsLus.get(0).getNumero());
	}

	@Test
	public void testRechercherClientsAvecRechercheEtPagination() {
		List<Client> clientsFictifs = creerClients(20);
		TypedQuery<Client> query = mock(TypedQuery.class);
		when(entityManager.createNamedQuery("Rechercher", Client.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(clientsFictifs);

		List<Client> clientsLus = clientService.rechercherClients("MOTCLE", 3, 2);

		verify(query).setParameter("recherche", "MOTCLE");
		verify(query).setFirstResult(6);
		verify(query).setMaxResults(2);
		assertNotNull(clientsLus);
		assertEquals(clientsFictifs.size(), clientsLus.size());
		assertEquals(clientsFictifs.get(0).getNumero(), clientsLus.get(0).getNumero());
	}

	@Test
	public void testRechercherClientsAvecRechercheEtFiltreEtPagination() {
		// TODO Tester les 24 chemins
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
}
