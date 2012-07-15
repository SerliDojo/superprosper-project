package com.serli.dojo.superprosper.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public final class Clients {

	private Clients() {
	}

	public static Client creerAlpha() {
		return creer("alpha");
	}

	public static Client creerBeta() {
		return creer("beta");
	}

	public static Client creerAlphaAvecProspectionAu6Avril2012() {
		Client clientAlpha = creerAlpha();
		clientAlpha.getProspections().add(Prospections.creerAu6Avril2012());
		return clientAlpha;
	}

	public static Client creerAlphaAvecDeuxProspections() {
		Client clientAlpha = creerAlpha();
		clientAlpha.getProspections().addAll(
				Arrays.asList(Prospections.creerAu6Avril2012(), Prospections.creerAu5Juillet2012()));
		return clientAlpha;
	}

	public static Client creerBetaAvecProspectionAu5Juillet2012() {
		Client clientBeta = creerBeta();
		clientBeta.getProspections().add(Prospections.creerAu5Juillet2012());
		return clientBeta;
	}

	private static Client creer(String base) {
		Client client = new Client();
		client.setNom("Nom-" + base);
		client.setPrenom("Prenom-" + base);
		client.setTelephone("'Tel-" + base);
		client.setRegion("Reg" + base);
		client.setNaissance(new Date());
		client.setFoyer(base.length());
		client.setAdresse("Adresse-" + base);
		client.setNumero(client.hashCode());
		client.setProspections(new ArrayList<Prospection>());
		client.setContrats(new ArrayList<Contrat>());
		return client;
	}
}
