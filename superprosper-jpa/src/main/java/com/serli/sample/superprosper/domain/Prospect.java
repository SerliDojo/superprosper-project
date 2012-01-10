package com.serli.sample.superprosper.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@IdClass(ProspectId.class)
public class Prospect implements Serializable {

	private static final long serialVersionUID = -2119906144700797135L;
	
	@Id
	@Temporal(TemporalType.DATE)
	@Column(name="DATE_CONTACT")
	private Date contact;
	
	@Id
	@JoinColumn(name="CLIENT", referencedColumnName="numero")
	@OneToOne
	private Client client;

	@JoinColumn(name="AGENT", referencedColumnName = "matricule", nullable = false)	
	@OneToOne
	private Agent agent;

	public Prospect(Date contact, Client client, Agent agent) {
		super();
		this.contact = contact;
		this.client = client;
		this.agent = agent;
	}

	public Date getContact() {
		return contact;
	}

	public void setContact(Date contact) {
		this.contact = contact;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	
	
}
