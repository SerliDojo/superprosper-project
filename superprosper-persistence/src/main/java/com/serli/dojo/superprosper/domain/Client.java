package com.serli.dojo.superprosper.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entité représentant un client de la société.
 * 
 * @author Pascal MERON
 * @author Laurent RUAUD
 */
@Entity
public class Client implements Serializable {

	/** Numéro de série. */
	private static final long serialVersionUID = 239754410746100939L;

	/** Identifiant du client. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer numero;

	/** Nom du client. */
	@Basic
	@Column(length = 100, nullable = false)
	private String nom;

	/** Prénom du client. */
	@Basic
	@Column(length = 100, nullable = false)
	private String prenom;

	/** Numéro de téléphone du client. */
	@Basic
	@Column(length = 20, nullable = false)
	private String telephone;

	/** Region de résidence du client (codifiée). */
	@Basic
	@Column(length = 6, nullable = false)
	private String region;

	/**
	 * Renvoie la valeur de {@linkplain #numero numero}.
	 * 
	 * @return la valeur de numero
	 */
	public Integer getNumero() {
		return numero;
	}

	/**
	 * Définit la valeur de {@linkplain #numero numero}.
	 * 
	 * @param numero la valeur de numero à définir
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	/**
	 * Renvoie la valeur de {@linkplain #nom nom}.
	 * 
	 * @return la valeur de nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Définit la valeur de {@linkplain #nom nom}.
	 * 
	 * @param nom la valeur de nom à définir
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Renvoie la valeur de {@linkplain #prenom prenom}.
	 * 
	 * @return la valeur de prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Définit la valeur de {@linkplain #prenom prenom}.
	 * 
	 * @param prenom la valeur de prenom à définir
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * Renvoie la valeur de {@linkplain #telephone telephone}.
	 * 
	 * @return la valeur de telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * Définit la valeur de {@linkplain #telephone telephone}.
	 * 
	 * @param telephone la valeur de telephone à définir
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * Renvoie la valeur de {@linkplain #region region}.
	 * 
	 * @return la valeur de region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * Définit la valeur de {@linkplain #region region}.
	 * 
	 * @param region la valeur de region à définir
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("Client ").append(prenom).append(" ").append(nom).append(" (").append(numero)
				.append(")").toString();
	}

	@Override
	public int hashCode() {
		return 31 * ((numero == null) ? 0 : numero.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!getClass().isAssignableFrom(obj.getClass()))
			return false;
		Client other = (Client) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

}
