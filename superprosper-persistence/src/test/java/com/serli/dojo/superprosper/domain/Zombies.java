package com.serli.dojo.superprosper.domain;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;

public class Zombies {

	public static Agent riseAgent() {
		Agent agent = new Agent();
		agent.setMatricule("MATRICUL");
		agent.setProfil("PROFIL");
		agent.setNom("NOM");
		agent.setPrenom("PRENOM");
		agent.setCourriel("COURRIEL");
		return agent;
	}

	public static Client riseClient() {
		Client client = new Client();
		client.setNumero(12345);
		client.setNom("NOM");
		client.setPrenom("PRENOM");
		client.setTelephone("0987654321");
		client.setRegion("REGION");
		client.setFoyer(3);
		client.setAdresse("ADRESSE");
		client.setNaissance(new Date());
		return client;
	}

	public static Contrat riseContrat() {
		Contrat contrat = new Contrat();
		contrat.setNumero(67890);
		contrat.setEffet(new GregorianCalendar(2011, 11, 3).getTime());
		contrat.setSignature(new GregorianCalendar(2012, 1, 3).getTime());
		contrat.setProduits(Arrays.asList(new String[] { "AZERTY", "QSDFGH", "WXCVBN" }));
		return contrat;
	}

	public static Texte riseTexte() {
		Texte texte = new Texte();
		texte.setCategorie("CATEGORY");
		texte.setCode("CODE");
		texte.setLangue("FR");
		texte.setTexte("TEXTE");
		return texte;
	}

}
