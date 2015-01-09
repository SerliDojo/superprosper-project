package models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.db.jpa.GenericModel;

/**
 * Entité représentant une prospection affectuée auprès d'un client.
 * 
 * @author Pascal MERON
 * @author Laurent RUAUD
 */
@Entity
public class Prospection extends GenericModel {

	/** Numéro de série. */
	private static final long serialVersionUID = -2119906144700797135L;

	/** Date à laquelle le client a été contacté. */
	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CONTACT", nullable = false)
	private Date contact;

	/** Client prospecté. */
	@Id
	@JoinColumn(name = "CLIENT", referencedColumnName = "NUMERO", nullable = false)
	@ManyToOne
	private Client client;

	/** Agent ayant prospecté le client. */
	@JoinColumn(name = "AGENT", referencedColumnName = "MATRICULE", nullable = false)
	@ManyToOne
	private Agent agent;

	/**
	 * Renvoie la valeur de {@linkplain #contact contact}.
	 * 
	 * @return la valeur de contact
	 */
	public Date getContact() {
		return contact;
	}

	/**
	 * Définit la valeur de {@linkplain #contact contact}.
	 * 
	 * @param contact la valeur de contact à définir
	 */
	public void setContact(Date contact) {
		this.contact = contact;
	}

	/**
	 * Renvoie la valeur de {@linkplain #client client}.
	 * 
	 * @return la valeur de client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * Définit la valeur de {@linkplain #client client}.
	 * 
	 * @param client la valeur de client à définir
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * Renvoie la valeur de {@linkplain #agent agent}.
	 * 
	 * @return la valeur de agent
	 */
	public Agent getAgent() {
		return agent;
	}

	/**
	 * Définit la valeur de {@linkplain #agent agent}.
	 * 
	 * @param agent la valeur de agent à définir
	 */
	public void setAgent(Agent agent) {
		this.agent = agent;
	}

}
