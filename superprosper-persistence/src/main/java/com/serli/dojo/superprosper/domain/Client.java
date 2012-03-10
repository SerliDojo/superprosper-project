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
	 * Renvoie l'identifiant du client.
	 * 
	 * @return l'identifiant du client
	 */
	public Integer getNumero() {
		return numero;
	}

	/**
	 * Définit l'identifiant du client.
	 * 
	 * @param numero l'identifiant du client
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	/**
	 * Renvoie le nom du client.
	 * 
	 * @return le nom du client
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Définit le nom du client.
	 * 
	 * @param nom le nom du client
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Renvoie le prénom du client.
	 * 
	 * @return le prénom du client
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Définit le prénom du client.
	 * 
	 * @param prenom le prénom du client
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * Renvoie le numéro de téléphone du client.
	 * 
	 * @return numéro de téléphone du client
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * Définit le numéro de téléphone du client.
	 * 
	 * @param telephone le numéro de téléphone du client
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * Renvoie la région de résidence du client.
	 * 
	 * @return la région de résidence du client
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * Définit la région de résidence du client.
	 * 
	 * @param region la région de résidence du client
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
