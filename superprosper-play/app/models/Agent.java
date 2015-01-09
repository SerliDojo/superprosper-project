package models;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.jpa.GenericModel;
import play.db.jpa.Model;

/**
 * Entité représentant un agent de la société.
 * 
 * @author Pascal MERON
 * @author Laurent RUAUD
 */
@Entity
public class Agent extends GenericModel {

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
	 * Renvoie la valeur de {@linkplain #matricule matricule}.
	 * 
	 * @return la valeur de matricule
	 */
	public String getMatricule() {
		return matricule;
	}

	/**
	 * Définit la valeur de {@linkplain #matricule matricule}.
	 * 
	 * @param matricule la valeur de matricule à définir
	 */
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	/**
	 * Renvoie la valeur de {@linkplain #profil profil}.
	 * 
	 * @return la valeur de profil
	 */
	public String getProfil() {
		return profil;
	}

	/**
	 * Définit la valeur de {@linkplain #profil profil}.
	 * 
	 * @param profil la valeur de profil à définir
	 */
	public void setProfil(String profil) {
		this.profil = profil;
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
	 * Renvoie la valeur de {@linkplain #courriel courriel}.
	 * 
	 * @return la valeur de courriel
	 */
	public String getCourriel() {
		return courriel;
	}

	/**
	 * Définit la valeur de {@linkplain #courriel courriel}.
	 * 
	 * @param courriel la valeur de courriel à définir
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
