package com.serli.dojo.superprosper.domain;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Test;

public class ContratTest extends PersistenceTest {

	@Test
	public void testLire() throws Exception {
		insertDataSet("validData");

		Contrat contrat = (Contrat) entityManager.find(Contrat.class, 3456);

		assertNotNull(contrat);
		assertEquals(new GregorianCalendar(2011, 10, 23).getTime(), contrat.getSignature());
		assertEquals(new GregorianCalendar(2012, 0, 1).getTime(), contrat.getEffet());
		assertNotNull(contrat.getProduits());
		assertEquals(2, contrat.getProduits().size());
		assertNotNull(contrat.getClient());
		assertEquals(new Integer(123456), contrat.getClient().getNumero());
		assertEquals("COOPER", contrat.getClient().getNom());
	}

	@Test
	public void testPersister() {
		Client client = Zombies.riseClient();
		client.setNumero(null);

		Contrat contrat = Zombies.riseContrat();
		contrat.setNumero(null);
		contrat.setClient(client);

		entityManager.persist(client);
		entityManager.persist(contrat);
		entityManager.getTransaction().commit();

		assertNotNull(contrat.getNumero());
		assertNotNull(contrat.getClient().getNumero());
	}

	@Test
	public void testToString() {
		Client client = Zombies.riseClient();
		Contrat contrat = Zombies.riseContrat();
		contrat.setClient(client);

		assertTrue(contrat.toString().contains("67890"));
		assertTrue(contrat.toString().contains("NOM"));
	}

	@Test
	public void testEqualsHashCode() {
		Contrat contrat1 = Zombies.riseContrat();
		Contrat contrat2 = Zombies.riseContrat();

		assertEquals(contrat1.hashCode(), contrat2.hashCode());
		assertEquals(new Contrat().hashCode(), new Contrat().hashCode());

		assertTrue(contrat1.equals(contrat1));
		assertTrue(contrat1.equals(contrat2));
		assertTrue(contrat2.equals(contrat1));

		assertFalse(contrat1.equals(null));
		assertFalse(contrat1.equals(contrat2.toString()));
		assertFalse(contrat1.equals(new Contrat()));
		assertFalse(new Contrat().equals(contrat1));
	}
}
