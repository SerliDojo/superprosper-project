package com.serli.dojo.superprosper.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entité représentant une prospection affectuée auprès d'un client.
 * 
 * @author Pascal MERON
 * @author Laurent RUAUD
 */
@Embeddable
public class Prospection implements Serializable {

	/** Numéro de série. */
	private static final long serialVersionUID = -2119906144700797135L;

	/** Date à laquelle le client a été contacté. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CONTACT", nullable = false)
	private Date contact;

	/** Client prospecté. */
	@JoinColumn(name = "CLIENT", referencedColumnName = "NUMERO", nullable = false)
	@ManyToOne
	private Client client;

	/** Agent ayant prospecté le client. */
	@JoinColumn(name = "AGENT", referencedColumnName = "MATRICULE", nullable = false)
	@ManyToOne
	private Agent agent;

	/**
	 * Renvoie la date de contact.
	 * 
	 * @return la date de contact
	 */
	public Date getContact() {
		return contact;
	}

	/**
	 * Définit la date de contact.
	 * 
	 * @param contact la date de contact
	 */
	public void setContact(Date contact) {
		this.contact = contact;
	}

	/**
	 * Renvoie le client.
	 * 
	 * @return le client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * Définit le client.
	 * 
	 * @param client le client
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * Renvoie l'agent.
	 * 
	 * @return l'agent
	 */
	public Agent getAgent() {
		return agent;
	}

	/**
	 * Définit l'agent.
	 * 
	 * @param agent l'agent
	 */
	public void setAgent(Agent agent) {
		this.agent = agent;
	}

}
