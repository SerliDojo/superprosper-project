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
import com.serli.dojo.superprosper.domain.ClientBuilder;
import com.serli.dojo.superprosper.domain.Contrat;
import com.serli.dojo.superprosper.domain.ContratBuilder;
import com.serli.dojo.superprosper.domain.Prospection;
import com.serli.dojo.superprosper.domain.ProspectionBuilder;
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
		GregorianCalendar calendar = new GregorianCalendar();

		Prospection prospection = ProspectionBuilder.aProspection()
				.withContact(calendar.getTime()).build();
		// Prospection prospection = new Prospection();
		// prospection.setContact(calendar.getTime());

		Client client = ClientBuilder.aClient().withNom("Base")
				.withProspections(prospection).build();

		// Client client = creerClient("base");
		// client.setProspections(Arrays.asList(prospection));
		// client.setContrats(new ArrayList<Contrat>());

		// Contrat contrat = new Contrat();
		// contrat.setSignature(calendar.getTime());

		Contrat contrat = ContratBuilder.aContrat()
				.withSignature(calendar.getTime()).build();

		clientService.ajouterContrat(contrat, client);

		assertTrue(client.getContrats().contains(contrat));
		verify(entityManager).persist(contrat);
	}

	@Test
	public void testAjouterContratSansProspection() {
		GregorianCalendar calendar = new GregorianCalendar();

		Prospection prospection = ProspectionBuilder.aProspection()
				.withContact(calendar.getTime()).build();
		// Prospection prospection = new Prospection();
		// prospection.setContact(calendar.getTime());

		Client client = ClientBuilder.aClient().withNom("Base")
				.withProspections(prospection).build();
		// Client client = creerClient("base");
		// client.setProspections(Arrays.asList(prospection));
		// client.setContrats(new ArrayList<Contrat>());

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Contrat contrat = ContratBuilder.aContrat()
				.withSignature(calendar.getTime()).build();
		// Contrat contrat = new Contrat();
		// contrat.setSignature(calendar.getTime());

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
		GregorianCalendar calendar = new GregorianCalendar();

		Prospection prospectionExistance = ProspectionBuilder.aProspection()
				.withContact(calendar.getTime()).build();
		// Prospection prospectionExistance = new Prospection();
		// prospectionExistance.setContact(calendar.getTime());

		Client client = ClientBuilder.aClient().withNom("Base")
				.withProspections(prospectionExistance).build();
		// Client client = creerClient("base");
		// client.setProspections(new ArrayList<Prospection>());
		// client.getProspections().add(prospectionExistance);

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Prospection prospection = ProspectionBuilder.aProspection()
				.withContact(calendar.getTime()).build();
		// Prospection prospection = new Prospection();
		// prospection.setContact(calendar.getTime());

		clientService.ajouterProspection(prospection, client);

		assertTrue(client.getProspections().contains(prospection));
		verify(entityManager).persist(prospection);
	}

	@Test
	public void testAjouterProspectionEnDouble() {
		GregorianCalendar calendar = new GregorianCalendar();

		Prospection prospectionExistance = ProspectionBuilder.aProspection()
				.withContact(calendar.getTime()).build();
		// Prospection prospectionExistance = new Prospection();
		// prospectionExistance.setContact(calendar.getTime());

		Client client = ClientBuilder.aClient().withNom("Base")
				.withProspections(prospectionExistance).build();
		// Client client = creerClient("base");
		// client.setProspections(new ArrayList<Prospection>());
		// client.getProspections().add(prospectionExistance);

		Prospection prospection = ProspectionBuilder.aProspection()
				.withContact(calendar.getTime()).build();
//		Prospection prospection = new Prospection();
//		prospection.setContact(calendar.getTime());

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
