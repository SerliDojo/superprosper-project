package com.serli.dojo.superprosper.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entité représentant un client de la société.
 * 
 * @author Pascal MERON
 * @author Laurent RUAUD
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Rechercher", query = "select c from Client c where c.nom like :recherche or c.prenom like :recherche"),
		@NamedQuery(name = "RechercherClientsSouscrits", query = "select c from Client c where c.contrats is not empty and c.nom like :recherche or c.prenom like :recherche"),
		@NamedQuery(name = "RechercherClientsProspectes", query = "select c from Client c where c.contrats is empty and c.prospections is not empty and c.nom like :recherche or c.prenom like :recherche"),
		@NamedQuery(name = "RechercherClientsNonProspectes", query = "select c from Client c where c.contrats is empty and c.prospections is empty and c.nom like :recherche or c.prenom like :recherche") })
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

	/** Date de naissance du client. */
	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date naissance;

	/** Adresse de résidence du client. */
	@Basic
	@Column(length = 300, nullable = true)
	private String adresse;

	/** Nombre de personnes composant le foyer du client. */
	@Basic
	@Column(nullable = true)
	private Integer foyer;

	/** Contrats souscrits par ce client. */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
	private List<Contrat> contrats;

	/** Prospections réalisées sur ce client. */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
	private List<Prospection> prospections;

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

	/**
	 * Renvoie la valeur de {@linkplain #naissance naissance}.
	 * 
	 * @return la valeur de naissance
	 */
	public Date getNaissance() {
		return naissance;
	}

	/**
	 * Définit la valeur de {@linkplain #naissance naissance}.
	 * 
	 * @param naissance la valeur de naissance à définir
	 */
	public void setNaissance(Date naissance) {
		this.naissance = naissance;
	}

	/**
	 * Renvoie la valeur de {@linkplain #adresse adresse}.
	 * 
	 * @return la valeur de adresse
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * Définit la valeur de {@linkplain #adresse adresse}.
	 * 
	 * @param adresse la valeur de adresse à définir
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/**
	 * Renvoie la valeur de {@linkplain #foyer foyer}.
	 * 
	 * @return la valeur de foyer
	 */
	public Integer getFoyer() {
		return foyer;
	}

	/**
	 * Définit la valeur de {@linkplain #foyer foyer}.
	 * 
	 * @param foyer la valeur de foyer à définir
	 */
	public void setFoyer(Integer foyer) {
		this.foyer = foyer;
	}

	/**
	 * Renvoie la valeur de {@linkplain #contrats contrats}.
	 * 
	 * @return la valeur de contrats
	 */
	public List<Contrat> getContrats() {
		return contrats;
	}

	/**
	 * Définit la valeur de {@linkplain #contrats contrats}.
	 * 
	 * @param contrats la valeur de contrats à définir
	 */
	public void setContrats(List<Contrat> contrats) {
		this.contrats = contrats;
	}

	/**
	 * Renvoie la valeur de {@linkplain #prospections prospections}.
	 * 
	 * @return la valeur de prospections
	 */
	public List<Prospection> getProspections() {
		return prospections;
	}

	/**
	 * Définit la valeur de {@linkplain #prospections prospections}.
	 * 
	 * @param prospections la valeur de prospections à définir
	 */
	public void setProspections(List<Prospection> prospections) {
		this.prospections = prospections;
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
