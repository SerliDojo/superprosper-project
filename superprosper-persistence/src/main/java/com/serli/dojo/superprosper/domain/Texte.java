package com.serli.dojo.superprosper.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * Entité représentant les textes codifiés.
 * 
 * @author Pascal MERON
 * @author Laurent RUAUD
 */
@Entity
@NamedQuery(name = "pourCategorie", query = "select t from Texte t where t.categorie = :categorie and t.langue = :langue")
public class Texte implements Serializable {

	/** Numéro de série. */
	private static final long serialVersionUID = -4153413538164835805L;

	/** Catégorie du texte codifié. */
	@Id
	@Column(length = 10, nullable = false)
	private String categorie;

	/** Code du texte codifié. */
	@Id
	@Column(length = 6, nullable = false)
	private String code;

	/** Langue du libellé du texte codifié. */
	@Id
	@Column(length = 2, nullable = false)
	private String langue;

	/** Libellé du texte codifié. */
	@Basic
	@Column(length = 200, nullable = false)
	private String texte;

	/**
	 * Renvoie la valeur de {@linkplain #categorie categorie}.
	 * 
	 * @return la valeur de categorie
	 */
	public String getCategorie() {
		return categorie;
	}

	/**
	 * Définit la valeur de {@linkplain #categorie categorie}.
	 * 
	 * @param categorie la valeur de categorie à définir
	 */
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	/**
	 * Renvoie la valeur de {@linkplain #code code}.
	 * 
	 * @return la valeur de code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Définit la valeur de {@linkplain #code code}.
	 * 
	 * @param code la valeur de code à définir
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Renvoie la valeur de {@linkplain #langue langue}.
	 * 
	 * @return la valeur de langue
	 */
	public String getLangue() {
		return langue;
	}

	/**
	 * Définit la valeur de {@linkplain #langue langue}.
	 * 
	 * @param langue la valeur de langue à définir
	 */
	public void setLangue(String langue) {
		this.langue = langue;
	}

	/**
	 * Renvoie la valeur de {@linkplain #texte texte}.
	 * 
	 * @return la valeur de texte
	 */
	public String getTexte() {
		return texte;
	}

	/**
	 * Définit la valeur de {@linkplain #texte texte}.
	 * 
	 * @param texte la valeur de texte à définir
	 */
	public void setTexte(String texte) {
		this.texte = texte;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("Texte [").append(categorie).append("|").append(code).append("|")
				.append(langue).append(" = ").append(texte).append("]").toString();
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
		Texte other = (Texte) obj;
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
