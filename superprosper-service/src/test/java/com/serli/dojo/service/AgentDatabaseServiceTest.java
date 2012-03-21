package com.serli.dojo.service;

import static org.mockito.Mockito.*;

import javax.persistence.EntityManager;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.serli.dojo.service.database.AgentDatabaseService;
import com.serli.dojo.superprosper.domain.Agent;

@RunWith(MockitoJUnitRunner.class)
public class AgentDatabaseServiceTest {

	@Mock
	private EntityManager entityManager;

	private AgentDatabaseService agentService;

	@Before
	public void setUp() {
		agentService = new AgentDatabaseService();
		agentService.setEntityManager(entityManager);
	}

	@Test
	public void testLireAgent() {
		Agent agentFictif = new Agent();
		agentFictif.setMatricule("hsimpson");
		agentFictif.setNom("SIMPSON");
		agentFictif.setPrenom("Homer");
		agentFictif.setProfil("SPRVSR");
		agentFictif.setCourriel("homer.simpson@springfield.com");
		when(entityManager.find(Agent.class, "hsimpson")).thenReturn(agentFictif);

		Agent agentLu = agentService.lireAgent("hsimpson");

		Assert.assertEquals(agentFictif.getNom(), agentLu.getNom());
		Assert.assertEquals(agentFictif.getPrenom(), agentLu.getPrenom());
		Assert.assertEquals(agentFictif.getProfil(), agentLu.getProfil());
		Assert.assertEquals(agentFictif.getCourriel(), agentLu.getCourriel());
	}
}
