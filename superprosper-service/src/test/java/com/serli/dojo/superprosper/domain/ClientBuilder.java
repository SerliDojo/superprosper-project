package com.serli.dojo.superprosper.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public final class ClientBuilder implements TestDataBuilder<Client> {

	/** Identifiant du client. */
	private Integer numero;

	/** Nom du client. */
	private String nom;

	/** Prénom du client. */
	private String prenom;

	/** Numéro de téléphone du client. */
	private String telephone;

	/** Region de résidence du client (codifiée). */
	private String region;

	/** Date de naissance du client. */
	private Date naissance;

	/** Adresse de résidence du client. */
	private String adresse;

	/** Nombre de personnes composant le foyer du client. */
	private Integer foyer;

	/** Contrats souscrits par ce client. */
	private List<Contrat> contrats;

	/** Prospections réalisées sur ce client. */
	private List<Prospection> prospections;


	private ClientBuilder() {
		this.contrats = new ArrayList<Contrat>();
		this.prospections = new ArrayList<Prospection>();
	}

	public static ClientBuilder aClient() {
		return new ClientBuilder();
	}
	
	public ClientBuilder withAdresse(String adresse) {
		this.adresse = adresse;
		return this;
	}
	
	public ClientBuilder withFoyer(Integer foyer) {
		this.foyer = foyer;
		return this;
	}
	
	public ClientBuilder withNom(String nom) {
		this.nom = nom;
		return this;
	}
	
	public ClientBuilder withPrenom(String prenom) {
		this.prenom = prenom;
		return this;
	}
	
	public ClientBuilder withNumero(Integer numero) {
		this.numero = numero;
		return this;
	}
	
	public ClientBuilder withNaissance(Date naissance) {
		this.naissance = naissance;
		return this;
	}
	
	public ClientBuilder withRegion(String region) {
		this.region = region;
		return this;
	}
	
	public ClientBuilder withTelephone(String telephone) {
		this.telephone = telephone;
		return this;
	}
	
	public ClientBuilder withContrats(Contrat... contrats) {
		this.contrats = new ArrayList<Contrat>(); 
		this.contrats.addAll(Arrays.asList(contrats));
		return this;
	}
	
	public ClientBuilder withContrats(List<Contrat> contrats) {
		this.contrats = contrats; 
		return this;
	}
	
	public ClientBuilder withProspections(Prospection... prospections) {
		this.prospections = new ArrayList<Prospection>(); 
		this.prospections.addAll(Arrays.asList(prospections));
		return this;
	}
	
	public ClientBuilder withProspections(List<Prospection> prospections) {
		this.prospections = prospections;
		return this;
	}
	
	@Override
	public Client build() {
		Client client = new Client();
		client.setAdresse(adresse);
		client.setFoyer(foyer);
		client.setNumero(numero);
		client.setNaissance(naissance);
		client.setNom(nom);
		client.setPrenom(prenom);
		client.setRegion(region);
		client.setTelephone(telephone);
		client.setContrats(contrats);
		client.setProspections(prospections);
		return client;
	}

}
