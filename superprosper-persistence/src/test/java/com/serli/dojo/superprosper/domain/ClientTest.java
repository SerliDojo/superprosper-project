package com.serli.dojo.superprosper.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ClientTest extends PersistenceTest {

	@Test
	public void testLire() throws Exception {
		insertDataSet("validData");

		Client client = entityManager.find(Client.class, 123456);

		assertNotNull(client);
		assertEquals("COOPER", client.getNom());
		assertEquals("SHELDON", client.getPrenom());
		assertEquals("0123456789", client.getTelephone());
		assertEquals("IDF   ", client.getRegion());
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
