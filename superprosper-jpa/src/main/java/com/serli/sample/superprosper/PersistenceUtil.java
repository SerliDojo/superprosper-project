package com.serli.sample.superprosper;


import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class PersistenceUtil {
	
	private static EntityManager entityManager;
	
	public void closeEntityManager() {
		if (entityManager != null)  {
			entityManager.close();
		}
		
		entityManager = null;
	}
	
	/**
	 * @return 
	 */
	public static EntityManager getEntityManager() {
		if (entityManager == null) {
			entityManager = Persistence.createEntityManagerFactory("superprosper-persistence").createEntityManager();
		}
		
		return entityManager;
	}
}