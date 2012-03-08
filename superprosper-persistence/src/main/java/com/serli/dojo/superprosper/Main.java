package com.serli.dojo.superprosper;

import java.util.Date;

import javax.persistence.EntityManager;

import com.serli.dojo.superprosper.domain.Agent;
import com.serli.dojo.superprosper.domain.Client;
import com.serli.dojo.superprosper.domain.Contrat;
import com.serli.dojo.superprosper.domain.Prospect;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("--- DEBUT ---");
		Agent a = new Agent("CONSEI", "DOM5", "THOM", "quake@wares.org");
		Client c = new Client("Pascal5", "MERON", "0505050505", "PT-CH");
		Prospect p = new Prospect(new Date(), c, a);
		Contrat t = new Contrat("Tél illimité2", c, new Date(), new Date());
		// TODO Auto-generated method stub
		EntityManager em =  PersistenceUtil.getEntityManager();
		
		em.getTransaction().begin();
		em.persist(a);
		em.persist(c);
		em.persist(p);
		em.persist(t);
		em.getTransaction().commit();

		
		
		System.out.println("--- FIN ---");
	}

}
