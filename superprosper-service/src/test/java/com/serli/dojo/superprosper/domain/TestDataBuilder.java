package com.serli.dojo.superprosper.domain;

/**
 * Interface définissant la méthode de construction d'objet métier.
 *
 * @param <T> Type d'objet métier à construire.
 */
public interface TestDataBuilder<T> {

	/**
	 * Construit une instance.
	 * @return
	 */
	T build();
}
