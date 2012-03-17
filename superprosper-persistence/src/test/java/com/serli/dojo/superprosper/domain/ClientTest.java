package com.serli.dojo.superprosper.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.GregorianCalendar;

import org.junit.Test;

public class ClientTest extends PersistenceTest {

	@Test
	public void testLire() throws Exception {
		insertDataSet("validData");

		Client client1 = entityManager.find(Client.class, 123456);

		assertNotNull(client1);
		assertEquals("COOPER", client1.getNom());
		assertEquals("SHELDON", client1.getPrenom());
		assertEquals("0123456789", client1.getTelephone());
		assertEquals("IDF   ", client1.getRegion());

		Client client2 = entityManager.find(Client.class, 234567);

		assertNotNull(client2);
		assertEquals("LEAKEY HOFSTADTER", client2.getNom());
		assertEquals("LEONARD", client2.getPrenom());
		assertEquals("0598765689", client2.getTelephone());
		assertEquals("SO    ", client2.getRegion());
		assertEquals(new GregorianCalendar(1978, 2, 31).getTime(), client2.getNaissance());
		assertEquals("42 Rue du Cinglé, 24260 Le Bugue", client2.getAdresse());
		assertEquals(new Integer(2), client2.getFoyer());
	}

	@Test
	public void testPersister() {
		Client client = new Client();
		client.setNom("NOM");
		client.setPrenom("PRENOM");
		client.setTelephone("0987654321");
		client.setRegion("REGION");

		entityManager.persist(client);
		entityManager.getTransaction().commit();

		assertNotNull(client.getNumero());
	}
}
