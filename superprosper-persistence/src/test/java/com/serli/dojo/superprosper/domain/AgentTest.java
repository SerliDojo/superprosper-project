package com.serli.dojo.superprosper.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class AgentTest extends PersistenceTest {

	@Test
	public void testLire() throws Exception {
		insertDataSet("validData");

		Agent agent = entityManager.find(Agent.class, "007");

		assertNotNull(agent);
		assertEquals("AG00  ", agent.getProfil());
		assertEquals("BOND", agent.getNom());
		assertEquals("JAMES", agent.getPrenom());
		assertEquals("JAMES.BOND@MI6.UK", agent.getCourriel());
	}

	@Test
	public void testPersister() {
		Agent agent = new Agent();
		agent.setMatricule("MATRICUL");
		agent.setProfil("PROFIL");
		agent.setNom("NOM");
		agent.setPrenom("PRENOM");
		agent.setCourriel("COURRIEL");

		entityManager.persist(agent);
	}
}
