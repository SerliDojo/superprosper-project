package com.serli.sample.superprosper.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@IdClass(ContratId.class)
public class Contrat implements Serializable {
	
	private static final long serialVersionUID = 4924679998802311541L;

	@Id
	int numero;
	
	@Id
	@Column(length = 100, nullable = false)
	String produit;
	
	@JoinColumn(name="CLIENT", referencedColumnName = "NUMERO", nullable = false)	
	@OneToOne(fetch = FetchType.LAZY)
	private Client client;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_SIGNATURE", nullable = true)
	private Date dateSignature;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_EFFET", nullable = true)
	private Date dateEffet;
	
	public Contrat() {};
	
	public Contrat(String produit, Client client, Date dateSignature,
			Date dateEffet) {
		super();
		this.produit = produit;
		this.client = client;
		this.dateSignature = dateSignature;
		this.dateEffet = dateEffet;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getProduit() {
		return produit;
	}

	public void setProduit(String produit) {
		this.produit = produit;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Date getDateSignature() {
		return dateSignature;
	}

	public void setDateSignature(Date dateSignature) {
		this.dateSignature = dateSignature;
	}

	public Date getDateEffet() {
		return dateEffet;
	}

	public void setDateEffet(Date dateEffet) {
		this.dateEffet = dateEffet;
	}

	@Override
	public String toString() {
		return "Contrat [numero=" + numero + ", produit=" + produit
				+ ", client=" + client + ", dateSignature=" + dateSignature
				+ ", dateEffet=" + dateEffet + "]";
	}
}
