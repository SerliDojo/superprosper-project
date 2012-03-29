package com.serli.dojo.superprosper.regle;

/**
 * Classe abstraite définissant le contrat d'une règle métier.
 * 
 * @author Laurent RUAUD
 */
public abstract class ReglesGeneriques {

	/**
	 * Contrat de règle.
	 * 
	 * @author Laurent RUAUD
	 * 
	 * @param <O> type de l'objet à vérifier par la règle
	 */
	public static interface Regle<O> {
		
		/**
		 * Vérifie la conformité de l'objet à cette règle.
		 * 
		 * @param objet l'objet à vérifier
		 */
		void verifier(O objet);
	}
}
