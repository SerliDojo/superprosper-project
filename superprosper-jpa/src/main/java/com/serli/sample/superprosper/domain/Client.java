package com.serli.sample.superprosper.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client implements Serializable {

	private static final long serialVersionUID = 239754410746100939L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int numero;
	
	@Basic
	@Column(length = 100, nullable = false)
	String nom;

	@Basic
	@Column(length = 100, nullable = false)
	String prenom;
	
	@Basic
	@Column(length = 20, nullable = false)
	String telephone;
	
	@Basic
	@Column(length = 6, nullable = false)
	String region;

	public Client(String nom, String prenom, String telephone) {
		this(nom, prenom, telephone, null);
	}
	
	public Client(String nom, String prenom, String telephone,
			String region) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.region = region;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Override
	public String toString() {
		return "Client [numero=" + numero + ", nom=" + nom + ", prenom="
				+ prenom + ", telephone=" + telephone + ", region=" + region
				+ "]";
	}
}
