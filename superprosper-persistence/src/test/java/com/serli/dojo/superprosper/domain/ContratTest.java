package com.serli.dojo.superprosper.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
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
		entityManager.getTransaction().begin();

		Client client = new Client();
		client.setNom("NOM");
		client.setPrenom("PRENOM");
		client.setTelephone("0987654321");
		client.setRegion("REGION");

		Contrat contrat = new Contrat();
		contrat.setEffet(new GregorianCalendar(2011, 11, 3).getTime());
		contrat.setSignature(new GregorianCalendar(2012, 1, 3).getTime());
		contrat.setClient(client);
		contrat.setProduits(Arrays.asList(new String[] { "AZERTY", "QSDFGH", "WXCVBN" }));

		entityManager.persist(client);
		entityManager.persist(contrat);
		entityManager.getTransaction().commit();

		assertNotNull(contrat.getClient().getNumero());
	}
}
