package com.serli.dojo.superprosper.domain;

import java.io.Serializable;
import java.util.Date;

public class ProspectId implements Serializable {
	
	private static final long serialVersionUID = 8586701762888380452L;
	
	private Date contact;
	private Client client;

	public ProspectId() {
		super();
	}
	
	public ProspectId(Date contact, Client client) {
		this();
		this.client = client;
		this.contact = contact;
	}
	
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Date getContact() {
		return contact;
	}
	public void setContact(Date contact) {
		this.contact = contact;
	}
	@Override
	public String toString() {
		return "ProspectId [client=" + client + ", contact=" + contact
				+ "]";
	}
}
