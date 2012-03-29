package com.serli.dojo.superprosper.service;

import java.util.Map;

/**
 * Contrat de service pour la récupération des textes codifiés.
 * 
 * @author Laurent RUAUD
 */
public interface ServiceCodification {

	/**
	 * Renvoie le texte correspondant à la combinaison catégorie / code.
	 * 
	 * La langue utilisée est alors celle définie par défaut dans la JVM.
	 * 
	 * @param categorie catégorie dans laquelle se classe le texte
	 * @param code code du texte
	 * @return le texte correspondant
	 */
	String lireTexte(String categorie, String code);

	/**
	 * Renvoie le texte correspondant à la combinaison catégorie / code /
	 * langue.
	 * 
	 * @param categorie catégorie dans laquelle se classe le texte
	 * @param code code du texte
	 * @param langue langue souhaitée
	 * @return le texte correspondant
	 */
	String lireTexte(String categorie, String code, String langue);

	/**
	 * Renvoie tous les textes correspondant à une catégorie.
	 * 
	 * La langue utilisée est alors celle définie par défaut dans la JVM.
	 * 
	 * @param categorie catégorie de laquelle tirer les textes
	 * @return une association de codes et de textes correspondant
	 */
	Map<String, String> lireTextes(String categorie);

	/**
	 * Renvoie tous les textes correspondant à une catégorie dans une langue
	 * définie.
	 * 
	 * @param categorie catégorie de laquelle tirer les textes
	 * @param langue langue souhaitée
	 * @return une association de codes et de textes correspondant
	 */
	Map<String, String> lireTextes(String categorie, String langue);

}
