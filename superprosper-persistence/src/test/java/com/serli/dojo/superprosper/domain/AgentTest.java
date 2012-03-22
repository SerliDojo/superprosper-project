package com.serli.dojo.superprosper.domain;

import static org.junit.Assert.*;

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
		Agent agent = Zombies.riseAgent();

		entityManager.persist(agent);
	}

	@Test
	public void testToString() {
		Agent agent = Zombies.riseAgent();

		assertTrue(agent.toString().contains("NOM"));
	}

	@Test
	public void testEqualsHashCode() {
		Agent agent1 = Zombies.riseAgent();
		Agent agent2 = Zombies.riseAgent();

		assertEquals(agent1.hashCode(), agent2.hashCode());
		assertEquals(new Agent().hashCode(), new Agent().hashCode());

		assertTrue(agent1.equals(agent1));
		assertTrue(agent1.equals(agent2));
		assertTrue(agent2.equals(agent1));

		assertFalse(agent1.equals(null));
		assertFalse(agent1.equals(agent2.toString()));
		assertFalse(agent1.equals(new Agent()));
		assertFalse(new Agent().equals(agent1));
	}
}
