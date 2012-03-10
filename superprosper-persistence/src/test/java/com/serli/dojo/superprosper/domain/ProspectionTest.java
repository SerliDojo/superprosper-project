package com.serli.dojo.superprosper.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.GregorianCalendar;

import org.junit.Test;

public class ProspectionTest extends PersistenceTest {

	@Test
	public void testLire() throws Exception {
		insertDataSet("validData");

		Prospection prospection = (Prospection) entityManager.createQuery(
				"select p from Prospection p where p.client.numero = 123456").getSingleResult();

		assertNotNull(prospection);
		assertEquals(new GregorianCalendar(2012, 0, 23).getTime(), prospection.getContact());
		assertNotNull(prospection.getAgent());
		assertEquals("007", prospection.getAgent().getMatricule());
		assertEquals("BOND", prospection.getAgent().getNom());
		assertNotNull(prospection.getClient());
		assertEquals(new Integer(123456), prospection.getClient().getNumero());
		assertEquals("COOPER", prospection.getClient().getNom());
	}

	@Test
	public void testPersister() {
		Client client = new Client();
		client.setNom("NOM");
		client.setPrenom("PRENOM");
		client.setTelephone("0987654321");
		client.setRegion("REGION");

		Agent agent = new Agent();
		agent.setMatricule("MATRICUL");
		agent.setProfil("PROFIL");
		agent.setNom("NOM");
		agent.setPrenom("PRENOM");
		agent.setCourriel("COURRIEL");

		Prospection prospection = new Prospection();
		prospection.setContact(new GregorianCalendar(2011, 11, 3).getTime());
		prospection.setAgent(agent);
		prospection.setClient(client);

		entityManager.persist(agent);
		entityManager.persist(client);
		entityManager.persist(prospection);
		entityManager.getTransaction().commit();

		assertNotNull(prospection.getClient().getNumero());
	}
}
