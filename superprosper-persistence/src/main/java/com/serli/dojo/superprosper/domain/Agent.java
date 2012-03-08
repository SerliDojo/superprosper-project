package com.serli.dojo.superprosper.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Agent implements Serializable {

	private static final long serialVersionUID = -9163969658789433328L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int matricule;
	
	@Basic
	@Column(length = 6, nullable = true)
	String profil;
	
	@Basic
	@Column(length = 100, nullable = true)
	String nom;
	
	@Basic
	@Column(length = 100, nullable = true)
	String prenom;
	
	@Basic
	@Column(length = 200, nullable = true)
	String courriel;

	public Agent(String profil, String nom, String prenom,
			String courriel) {
		super();
		this.profil = profil;
		this.nom = nom;
		this.prenom = prenom;
		this.courriel = courriel;
	}

	public int getMatricule() {
		return matricule;
	}

	public void setMatricule(int matricule) {
		this.matricule = matricule;
	}

	public String getProfil() {
		return profil;
	}

	public void setProfil(String profil) {
		this.profil = profil;
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

	public String getCourriel() {
		return courriel;
	}

	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}

	@Override
	public String toString() {
		return "Agent [matricule=" + matricule + ", profil=" + profil
				+ ", nom=" + nom + ", prenom=" + prenom + ", courriel="
				+ courriel + "]";
	}
}
