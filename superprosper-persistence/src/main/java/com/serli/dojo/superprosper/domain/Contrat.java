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
	private Integer numero;

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
	 * Renvoie la valeur de {@linkplain #client client}.
	 * 
	 * @return la valeur de client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * Définit la valeur de {@linkplain #client client}.
	 * 
	 * @param client la valeur de client à définir
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * Renvoie la valeur de {@linkplain #produits produits}.
	 * 
	 * @return la valeur de produits
	 */
	public List<String> getProduits() {
		return produits;
	}

	/**
	 * Définit la valeur de {@linkplain #produits produits}.
	 * 
	 * @param produits la valeur de produits à définir
	 */
	public void setProduits(List<String> produits) {
		this.produits = produits;
	}

	/**
	 * Renvoie la valeur de {@linkplain #signature signature}.
	 * 
	 * @return la valeur de signature
	 */
	public Date getSignature() {
		return signature;
	}

	/**
	 * Définit la valeur de {@linkplain #signature signature}.
	 * 
	 * @param signature la valeur de signature à définir
	 */
	public void setSignature(Date signature) {
		this.signature = signature;
	}

	/**
	 * Renvoie la valeur de {@linkplain #effet effet}.
	 * 
	 * @return la valeur de effet
	 */
	public Date getEffet() {
		return effet;
	}

	/**
	 * Définit la valeur de {@linkplain #effet effet}.
	 * 
	 * @param effet la valeur de effet à définir
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
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

}
