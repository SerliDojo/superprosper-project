package com.serli.dojo.superprosper.domain;

import java.util.Date;

public final class ProspectionBuilder implements TestDataBuilder<Prospection> {

	private Date contact;

	/** Client prospecté. */
	private Client client;

	/** Agent ayant prospecté le client. */
	private Agent agent;

	private ProspectionBuilder() {
	}
	
	public static ProspectionBuilder aProspection() {
		return new ProspectionBuilder();
	}
	
	public ProspectionBuilder withContact(Date contact) {
		this.contact = contact;
		return this;
	}
	
	public ProspectionBuilder withClient(Client client) {
		this.client = client;
		return this;
	}
	
	public ProspectionBuilder withAgent(Agent agent) {
		this.agent = agent;
		return this;
	}
	
	@Override
	public Prospection build() {
		Prospection prospection = new Prospection();
		prospection.setAgent(agent);
		prospection.setClient(client);
		prospection.setContact(contact);
		return prospection;
	}

}
