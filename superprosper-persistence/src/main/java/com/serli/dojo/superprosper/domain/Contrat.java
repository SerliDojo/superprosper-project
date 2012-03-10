package com.serli.dojo.superprosper.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entité représentant un contrat souscrit par un client.
 * 
 * @author Pascal MERON
 * @author Laurent RUAUD
 */
@Entity
public class Contrat implements Serializable {

	/** Numéro de série. */
	private static final long serialVersionUID = 4924679998802311541L;

	/** Numéro identifiant le contrat. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int numero;

	/** Client ayant souscrit le contrat. */
	@JoinColumn(name = "CLIENT", referencedColumnName = "NUMERO", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Client client;

	/** Produits souscrits dans le contrat (codifiés). */
	@CollectionTable(name = "CONTRAT_PRODUIT", joinColumns = @JoinColumn(name = "CONTRAT"))
	@ElementCollection(fetch = FetchType.EAGER)
	@Column(name = "PRODUIT")
	private List<String> produits;

	/** Date de la signature du contrat. */
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date signature;

	/**
	 * Date de prise d'effet du contrat (si différente de la date de signature).
	 */
	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date effet;

	/**
	 * Renvoie l'identifiant du contrat.
	 * 
	 * @return l'identifiant du contrat
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Définit l'identifiant du contrat.
	 * 
	 * @param numero l'identifiant du contrat
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	 * Renvoie le client.
	 * 
	 * @return le client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * Définit le client.
	 * 
	 * @param client le client
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * Renvoie les produits.
	 * 
	 * @return les produits
	 */
	public List<String> getProduits() {
		return produits;
	}

	/**
	 * Définit les produits.
	 * 
	 * @param produits les produits
	 */
	public void setProduits(List<String> produits) {
		this.produits = produits;
	}

	/**
	 * Renvoie la date de signature.
	 * 
	 * @return la date de signature
	 */
	public Date getSignature() {
		return signature;
	}

	/**
	 * Définit la date de signature.
	 * 
	 * @param signature la date de signature
	 */
	public void setSignature(Date signature) {
		this.signature = signature;
	}

	/**
	 * Renvoie la date d'effet.
	 * 
	 * @return la date d'effet
	 */
	public Date getEffet() {
		return effet;
	}

	/**
	 * Définit la date d'effet.
	 * 
	 * @param effet la date d'effet
	 */
	public void setEffet(Date effet) {
		this.effet = effet;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder().append("Contrat n° ").append(numero);
		if (client != null) {
			builder.append(" pour ").append(client.getPrenom()).append(" ").append(client.getNom());
		}
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numero;
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
		Contrat other = (Contrat) obj;
		if (numero != other.numero)
			return false;
		return true;
	}

}
