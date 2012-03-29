package com.serli.dojo.superprosper.service.defaut;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Implémentation générique d'un service prenant comme source une base de
 * données.
 * 
 * Cette classe utilise l'injection de gestionnaire d'entité telle que proposée
 * par JPA.
 * 
 * @author Laurent RUAUD
 */
public class ServiceGenerique {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Renvoie la valeur de {@linkplain #entityManager entityManager}.
	 * 
	 * @return la valeur de entityManager
	 */
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Définit la valeur de {@linkplain #entityManager entityManager}.
	 * 
	 * @param entityManager la valeur de entityManager à définir
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
