package com.serli.dojo.superprosper.domain;

import java.io.Serializable;

/**
 * Classe d'identité représentant la clé primaire les textes codifiés.
 * 
 * @author Pascal MERON
 * @author Laurent RUAUD
 */
public class TexteId implements Serializable {

	/** Numéro de série. */
	private static final long serialVersionUID = 4108612999877230208L;

	/** Catégorie du texte codifié. */
	private String categorie;

	/** Code du texte codifié. */
	private String code;

	/** Langue du libellé du texte codifié. */
	private String langue;

	/**
	 * Renvoie la categorie du texte codifié.
	 * 
	 * @return la categorie du texte codifié
	 */
	public String getCategorie() {
		return categorie;
	}

	/**
	 * Définit la categorie du texte codifié.
	 * 
	 * @param categorie la categorie du texte codifié
	 */
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	/**
	 * Renvoie le code du texte codifié.
	 * 
	 * @return le code du texte codifié
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Définit le code du texte codifié.
	 * 
	 * @param code le code du texte codifié
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Renvoie la langue du texte codifié.
	 * 
	 * @return la langue du texte codifié
	 */
	public String getLangue() {
		return langue;
	}

	/**
	 * Définit la langue du texte codifié.
	 * 
	 * @param langue la langue du texte codifié
	 */
	public void setLangue(String langue) {
		this.langue = langue;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("TexteId [").append(categorie).append("|").append(code).append("|")
				.append(langue).append("]").toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categorie == null) ? 0 : categorie.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((langue == null) ? 0 : langue.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TexteId other = (TexteId) obj;
		if (categorie == null) {
			if (other.categorie != null)
				return false;
		} else if (!categorie.equals(other.categorie))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (langue == null) {
			if (other.langue != null)
				return false;
		} else if (!langue.equals(other.langue))
			return false;
		return true;
	}
}