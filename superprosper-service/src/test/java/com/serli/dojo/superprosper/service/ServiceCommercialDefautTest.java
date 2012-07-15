package com.serli.dojo.superprosper.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.serli.dojo.superprosper.domain.Client;
import com.serli.dojo.superprosper.domain.Clients;
import com.serli.dojo.superprosper.domain.Contrat;
import com.serli.dojo.superprosper.domain.Contrats;
import com.serli.dojo.superprosper.domain.Prospection;
import com.serli.dojo.superprosper.domain.Prospections;
import com.serli.dojo.superprosper.service.defaut.ServiceCommercialDefaut;

@RunWith(MockitoJUnitRunner.class)
public class ServiceCommercialDefautTest {

	@Mock
	private EntityManager entityManager;

	private ServiceCommercialDefaut clientService;

	@Before
	public void setUp() {
		clientService = new ServiceCommercialDefaut();
		clientService.setEntityManager(entityManager);
	}

	@Test
	public void testAjouterContrat() {
		Client client = Clients.creerBetaAvecProspectionAu5Juillet2012();
		Contrat contrat = Contrats.creerSigneAu5Juillet2012();

		clientService.ajouterContrat(contrat, client);

		assertTrue(client.getContrats().contains(contrat));
		verify(entityManager).persist(contrat);
	}

	@Test
	public void testAjouterContratSansProspection() {
		Client client = Clients.creerAlphaAvecProspectionAu6Avril2012();
		Contrat contrat = Contrats.creerSigneAu5Juillet2012();

		try {
			clientService.ajouterContrat(contrat, client);
			fail("Une exception aurait du être levée si aucune prospection n'a eu lieu à la date de signature.");
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}

		assertFalse(client.getContrats().contains(contrat));
		verify(entityManager, never()).persist(contrat);
	}

	@Test
	public void testAjouterProspection() {
		Client client = Clients.creerAlphaAvecProspectionAu6Avril2012();
		Prospection prospection = Prospections.creerAu5Juillet2012();

		clientService.ajouterProspection(prospection, client);

		assertTrue(client.getProspections().contains(prospection));
		verify(entityManager).persist(prospection);
	}

	@Test
	public void testAjouterProspectionEnDouble() {
		Prospection prospection = Prospections.creerAu6Avril2012();
		Client client = Clients.creerAlphaAvecProspectionAu6Avril2012();

		try {
			clientService.ajouterProspection(prospection, client);
			fail("Une exception aurait du être levée si une prospection a déjà eu lieu à la même date.");
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}

		assertFalse(client.getProspections().contains(prospection));
		verify(entityManager, never()).persist(prospection);
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
}
