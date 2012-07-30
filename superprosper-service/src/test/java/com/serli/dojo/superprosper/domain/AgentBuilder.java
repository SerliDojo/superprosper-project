package com.serli.dojo.superprosper.domain;


public final class AgentBuilder implements TestDataBuilder<Agent> {

	/** Matricule de l'agent. */
	private String matricule;

	/** Profil de l'agent (codifié). */
	private String profil;

	/** Nom de l'agent. */
	private String nom;

	/** Prénom de l'agent. */
	private String prenom;

	/** Courriel de l'agent. */
	private String courriel;

	private AgentBuilder() {
	}
	
	public static AgentBuilder anAgent() {
		return new AgentBuilder();
	}

	public AgentBuilder withMatricule(String matricule) {
		this.matricule = matricule;
		return this;
	}
	
	public AgentBuilder withNom(String nom) {
		this.nom = nom;
		return this;
	}
	
	public AgentBuilder withPrenom(String prenom) {
		this.prenom = prenom;
		return this;
	}
	
	public AgentBuilder withProfil(String profil) {
		this.profil = profil;
		return this;
	}
	
	public AgentBuilder withCourriel(String courriel) {
		this.courriel = courriel;
		return this;
	}
	
	@Override
	public Agent build() {
		Agent agent = new Agent();
		agent.setCourriel(courriel);
		agent.setMatricule(matricule);
		agent.setNom(nom);
		agent.setPrenom(prenom);
		agent.setProfil(profil);
		return agent;
	}
	
}
