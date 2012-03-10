package com.serli.dojo.superprosper.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Entité représentant un agent de la société.
 * 
 * @author Pascal MERON
 * @author Laurent RUAUD
 */
@Entity
public class Agent implements Serializable {

	/** Numéro de série. */
	private static final long serialVersionUID = -9163969658789433328L;

	/** Matricule de l'agent. */
	@Id
	@Column(length = 8)
	private String matricule;

	/** Profil de l'agent (codifié). */
	@Basic
	@Column(length = 6, nullable = false)
	private String profil;

	/** Nom de l'agent. */
	@Basic
	@Column(length = 100, nullable = false)
	private String nom;

	/** Prénom de l'agent. */
	@Basic
	@Column(length = 100, nullable = false)
	private String prenom;

	/** Courriel de l'agent. */
	@Basic
	@Column(length = 200, nullable = false)
	private String courriel;

	/**
	 * Renvoie le matricule de l'agent.
	 * 
	 * @return le matricule de l'agent
	 */
	public String getMatricule() {
		return matricule;
	}

	/**
	 * Définit le matricule de l'agent.
	 * 
	 * @param matricule le matricule de l'agent
	 */
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	/**
	 * Renvoie le profil de l'agent.
	 * 
	 * @return le profil de l'agent
	 */
	public String getProfil() {
		return profil;
	}

	/**
	 * Définit le profil de l'agent.
	 * 
	 * @param matricule le profil de l'agent
	 */
	public void setProfil(String profil) {
		this.profil = profil;
	}

	/**
	 * Renvoie le nom de l'agent.
	 * 
	 * @return le nom de l'agent
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Définit le nom de l'agent.
	 * 
	 * @param matricule le nom de l'agent
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Renvoie le prénom de l'agent.
	 * 
	 * @return le prénom de l'agent
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Définit le prénom de l'agent.
	 * 
	 * @param matricule le prénom de l'agent
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * Renvoie le courriel de l'agent.
	 * 
	 * @return le courriel de l'agent
	 */
	public String getCourriel() {
		return courriel;
	}

	/**
	 * Définit le courriel de l'agent.
	 * 
	 * @param matricule le courriel de l'agent
	 */
	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("Agent ").append(prenom).append(" ").append(nom).append(" (")
				.append(matricule).append(")").toString();
	}

	@Override
	public int hashCode() {
		return 31 * ((matricule == null) ? 0 : matricule.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!getClass().isAssignableFrom(obj.getClass()))
			return false;
		Agent other = (Agent) obj;
		if (matricule == null) {
			if (other.matricule != null)
				return false;
		} else if (!matricule.equals(other.matricule))
			return false;
		return true;
	}

}
