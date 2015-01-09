package models;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;

import models.Client.Filtre;
import play.db.jpa.GenericModel;

/**
 * Entité représentant un client de la société.
 * 
 * @author Pascal MERON
 * @author Laurent RUAUD
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Rechercher", query = Client.JPQL_SELECTION + Client.JPQL_RECHERCHE),
		@NamedQuery(name = "RechercherClientsSouscrits", query = Client.JPQL_SELECTION + Client.JPQL_RECHERCHE + Client.JPQL_CRITERE_CLIENTS_SOUSCRITS),
		@NamedQuery(name = "RechercherClientsProspectes", query = Client.JPQL_SELECTION + Client.JPQL_RECHERCHE + Client.JPQL_CRITERE_CLIENTS_PROSPECTES),
		@NamedQuery(name = "RechercherClientsNonProspectes", query = Client.JPQL_SELECTION + Client.JPQL_RECHERCHE + Client.JPQL_CRITERE_CLIENTS_NON_PROSPECTES),
		@NamedQuery(name = "RechercherParNumeroAsc", query = Client.JPQL_SELECTION + Client.JPQL_RECHERCHE + Client.JPQL_TRI_PAR_NUMERO_ASC),
		@NamedQuery(name = "RechercherParNumeroDesc", query = Client.JPQL_SELECTION + Client.JPQL_RECHERCHE + Client.JPQL_TRI_PAR_NUMERO_DESC),
		@NamedQuery(name = "RechercherParNomPrenomAsc", query = Client.JPQL_SELECTION + Client.JPQL_RECHERCHE + Client.JPQL_TRI_PAR_NOM_PRENOM_ASC),
		@NamedQuery(name = "RechercherParNomPrenomDesc", query = Client.JPQL_SELECTION + Client.JPQL_RECHERCHE + Client.JPQL_TRI_PAR_NOM_PRENOM_DESC),
		@NamedQuery(name = "RechercherParRegionAsc", query = Client.JPQL_SELECTION + Client.JPQL_RECHERCHE + Client.JPQL_TRI_PAR_REGION_ASC),
		@NamedQuery(name = "RechercherParRegionDesc", query = Client.JPQL_SELECTION + Client.JPQL_RECHERCHE + Client.JPQL_TRI_PAR_REGION_DESC),
		@NamedQuery(name = "RechercherClientsSouscritsParNumeroAsc", query = Client.JPQL_SELECTION + Client.JPQL_RECHERCHE + Client.JPQL_CRITERE_CLIENTS_SOUSCRITS + Client.JPQL_TRI_PAR_NUMERO_ASC),
		@NamedQuery(name = "RechercherClientsSouscritsParNumeroDesc", query = Client.JPQL_SELECTION + Client.JPQL_RECHERCHE + Client.JPQL_CRITERE_CLIENTS_SOUSCRITS + Client.JPQL_TRI_PAR_NUMERO_DESC),
		@NamedQuery(name = "RechercherClientsSouscritsParNomPrenomAsc", query = Client.JPQL_SELECTION + Client.JPQL_RECHERCHE + Client.JPQL_CRITERE_CLIENTS_SOUSCRITS + Client.JPQL_TRI_PAR_NOM_PRENOM_ASC),
		@NamedQuery(name = "RechercherClientsSouscritsParNomPrenomDesc", query = Client.JPQL_SELECTION + Client.JPQL_RECHERCHE + Client.JPQL_CRITERE_CLIENTS_SOUSCRITS + Client.JPQL_TRI_PAR_NOM_PRENOM_DESC),
		@NamedQuery(name = "RechercherClientsSouscritsParRegionAsc", query = Client.JPQL_SELECTION + Client.JPQL_RECHERCHE + Client.JPQL_CRITERE_CLIENTS_SOUSCRITS + Client.JPQL_TRI_PAR_REGION_ASC),
		@NamedQuery(name = "RechercherClientsSouscritsParRegionDesc", query = Client.JPQL_SELECTION + Client.JPQL_RECHERCHE + Client.JPQL_CRITERE_CLIENTS_SOUSCRITS + Client.JPQL_TRI_PAR_REGION_DESC),
		@NamedQuery(name = "RechercherClientsProspectesParNumeroAsc", query = Client.JPQL_SELECTION + Client.JPQL_RECHERCHE + Client.JPQL_CRITERE_CLIENTS_PROSPECTES + Client.JPQL_TRI_PAR_NUMERO_ASC),
		@NamedQuery(name = "RechercherClientsProspectesParNumeroDesc", query = Client.JPQL_SELECTION + Client.JPQL_RECHERCHE + Client.JPQL_CRITERE_CLIENTS_PROSPECTES + Client.JPQL_TRI_PAR_NUMERO_DESC),
		@NamedQuery(name = "RechercherClientsProspectesParNomPrenomAsc", query = Client.JPQL_SELECTION + Client.JPQL_RECHERCHE + Client.JPQL_CRITERE_CLIENTS_PROSPECTES + Client.JPQL_TRI_PAR_NOM_PRENOM_ASC),
		@NamedQuery(name = "RechercherClientsProspectesParNomPrenomDesc", query = Client.JPQL_SELECTION + Client.JPQL_RECHERCHE + Client.JPQL_CRITERE_CLIENTS_PROSPECTES + Client.JPQL_TRI_PAR_NOM_PRENOM_DESC),
		@NamedQuery(name = "RechercherClientsProspectesParRegionAsc", query = Client.JPQL_SELECTION + Client.JPQL_RECHERCHE + Client.JPQL_CRITERE_CLIENTS_PROSPECTES + Client.JPQL_TRI_PAR_REGION_ASC),
		@NamedQuery(name = "RechercherClientsProspectesParRegionDesc", query = Client.JPQL_SELECTION + Client.JPQL_RECHERCHE + Client.JPQL_CRITERE_CLIENTS_PROSPECTES + Client.JPQL_TRI_PAR_REGION_DESC),
		@NamedQuery(name = "RechercherClientsNonProspectesParNumeroAsc", query = Client.JPQL_SELECTION + Client.JPQL_RECHERCHE + Client.JPQL_CRITERE_CLIENTS_NON_PROSPECTES + Client.JPQL_TRI_PAR_NUMERO_ASC),
		@NamedQuery(name = "RechercherClientsNonProspectesParNumeroDesc", query = Client.JPQL_SELECTION + Client.JPQL_RECHERCHE + Client.JPQL_CRITERE_CLIENTS_NON_PROSPECTES + Client.JPQL_TRI_PAR_NUMERO_DESC),
		@NamedQuery(name = "RechercherClientsNonProspectesParNomPrenomAsc", query = Client.JPQL_SELECTION + Client.JPQL_RECHERCHE + Client.JPQL_CRITERE_CLIENTS_NON_PROSPECTES + Client.JPQL_TRI_PAR_NOM_PRENOM_ASC),
		@NamedQuery(name = "RechercherClientsNonProspectesParNomPrenomDesc", query = Client.JPQL_SELECTION + Client.JPQL_RECHERCHE + Client.JPQL_CRITERE_CLIENTS_NON_PROSPECTES + Client.JPQL_TRI_PAR_NOM_PRENOM_DESC),
		@NamedQuery(name = "RechercherClientsNonProspectesParRegionAsc", query = Client.JPQL_SELECTION + Client.JPQL_RECHERCHE + Client.JPQL_CRITERE_CLIENTS_NON_PROSPECTES + Client.JPQL_TRI_PAR_REGION_ASC),
		@NamedQuery(name = "RechercherClientsNonProspectesParRegionDesc", query = Client.JPQL_SELECTION + Client.JPQL_RECHERCHE + Client.JPQL_CRITERE_CLIENTS_NON_PROSPECTES + Client.JPQL_TRI_PAR_REGION_DESC) })
public class Client extends GenericModel {

	static final String JPQL_SELECTION = "select c from Client c";
	static final String JPQL_RECHERCHE = " where c.nom like :recherche or c.prenom like :recherche";
	static final String JPQL_CRITERE_CLIENTS_SOUSCRITS = " and c.contrats is not empty";
	static final String JPQL_CRITERE_CLIENTS_PROSPECTES = " and c.contrats is empty and c.prospections is not empty";
	static final String JPQL_CRITERE_CLIENTS_NON_PROSPECTES = " and c.contrats is empty and c.prospections is empty";
	static final String JPQL_TRI_PAR_NUMERO_ASC = " order by numero asc";
	static final String JPQL_TRI_PAR_NUMERO_DESC = " order by numero desc";
	static final String JPQL_TRI_PAR_NOM_PRENOM_ASC = " order by nom asc, prenom asc";
	static final String JPQL_TRI_PAR_NOM_PRENOM_DESC = " order by nom desc, prenom desc";
	static final String JPQL_TRI_PAR_REGION_ASC = " order by region asc";
	static final String JPQL_TRI_PAR_REGION_DESC = " order by region desc";

	/** Numéro de série. */
	private static final long serialVersionUID = 239754410746100939L;

	/** Identifiant du client. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer numero;

	/** Nom du client. */
	@Basic
	@Column(length = 100, nullable = false)
	private String nom;

	/** Prénom du client. */
	@Basic
	@Column(length = 100, nullable = false)
	private String prenom;

	/** Numéro de téléphone du client. */
	@Basic
	@Column(length = 20, nullable = false)
	private String telephone;

	/** Region de résidence du client (codifiée). */
	@Basic
	@Column(length = 6, nullable = false)
	private String region;

	/** Date de naissance du client. */
	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date naissance;

	/** Adresse de résidence du client. */
	@Basic
	@Column(length = 300, nullable = true)
	private String adresse;

	/** Nombre de personnes composant le foyer du client. */
	@Basic
	@Column(nullable = true)
	private Integer foyer;

	/** Contrats souscrits par ce client. */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
	private List<Contrat> contrats;

	/** Prospections réalisées sur ce client. */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
	private List<Prospection> prospections;

	/**
	 * Renvoie la valeur de {@linkplain #numero numero}.
	 * 
	 * @return la valeur de numero
	 */
	public Integer getNumero() {
		return numero;
	}

	/**
	 * Définit la valeur de {@linkplain #numero numero}.
	 * 
	 * @param numero la valeur de numero à définir
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	/**
	 * Renvoie la valeur de {@linkplain #nom nom}.
	 * 
	 * @return la valeur de nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Définit la valeur de {@linkplain #nom nom}.
	 * 
	 * @param nom la valeur de nom à définir
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Renvoie la valeur de {@linkplain #prenom prenom}.
	 * 
	 * @return la valeur de prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Définit la valeur de {@linkplain #prenom prenom}.
	 * 
	 * @param prenom la valeur de prenom à définir
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * Renvoie la valeur de {@linkplain #telephone telephone}.
	 * 
	 * @return la valeur de telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * Définit la valeur de {@linkplain #telephone telephone}.
	 * 
	 * @param telephone la valeur de telephone à définir
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * Renvoie la valeur de {@linkplain #region region}.
	 * 
	 * @return la valeur de region
	 */
	public String getRegion() {
		return region;
	}
	
	/**
	 * Renvoie la valeur du libellé de {@linkplain #region region}.
	 * 
	 * @return la valeur de du libellé region
	 */
	public String getRegionLibelle() {
		return Texte.lireTexte("REGIONS", region);
	}

	
	
	/**
	 * Définit la valeur de {@linkplain #region region}.
	 * 
	 * @param region la valeur de region à définir
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * Renvoie la valeur de {@linkplain #naissance naissance}.
	 * 
	 * @return la valeur de naissance
	 */
	public Date getNaissance() {
		return naissance;
	}

	/**
	 * Définit la valeur de {@linkplain #naissance naissance}.
	 * 
	 * @param naissance la valeur de naissance à définir
	 */
	public void setNaissance(Date naissance) {
		this.naissance = naissance;
	}

	/**
	 * Renvoie l'âge du client en années
	 */
	public int getAge() {
		DateTime dateNaissance = new DateTime(naissance);
		Period periode = new Period(DateTime.now(), dateNaissance);
		periode.getYears();
		return DateTime.now().minus(naissance.getTime()).getYear()-1970;
	}
	
	/**
	 * Renvoie la valeur de {@linkplain #adresse adresse}.
	 * 
	 * @return la valeur de adresse
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * Définit la valeur de {@linkplain #adresse adresse}.
	 * 
	 * @param adresse la valeur de adresse à définir
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/**
	 * Renvoie la valeur de {@linkplain #foyer foyer}.
	 * 
	 * @return la valeur de foyer
	 */
	public Integer getFoyer() {
		return foyer;
	}

	/**
	 * Définit la valeur de {@linkplain #foyer foyer}.
	 * 
	 * @param foyer la valeur de foyer à définir
	 */
	public void setFoyer(Integer foyer) {
		this.foyer = foyer;
	}

	/**
	 * Renvoie la valeur de {@linkplain #contrats contrats}.
	 * 
	 * @return la valeur de contrats
	 */
	public List<Contrat> getContrats() {
		return contrats;
	}

	/**
	 * Définit la valeur de {@linkplain #contrats contrats}.
	 * 
	 * @param contrats la valeur de contrats à définir
	 */
	public void setContrats(List<Contrat> contrats) {
		this.contrats = contrats;
	}

	/**
	 * Renvoie la valeur de {@linkplain #prospections prospections}.
	 * 
	 * @return la valeur de prospections
	 */
	public List<Prospection> getProspections() {
		return prospections;
	}

	/**
	 * Définit la valeur de {@linkplain #prospections prospections}.
	 * 
	 * @param prospections la valeur de prospections à définir
	 */
	public void setProspections(List<Prospection> prospections) {
		this.prospections = prospections;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("Client ").append(prenom).append(" ").append(nom).append(" (").append(numero)
				.append(")").toString();
	}

	@Override
	public int hashCode() {
		return 31 * ((numero == null) ? 0 : numero.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!getClass().isAssignableFrom(obj.getClass()))
			return false;
		Client other = (Client) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}
	
	/**
	 * Enumération des filtres prédéfinis utilisables lors d'une rechercher de
	 * clients.
	 */
	public static enum Filtre {
		/** Aucun filtre appliqué. */
		TOUS_CLIENTS,
		/** Clients n'ayant pas encore été prospectés. */
		CLIENTS_NON_PROSPECTES,
		/** Clients ayant été prospectés mais n'ayant pas souscrit de contrat. */
		CLIENTS_PROSPECTES,
		/** Clients ayant souscris un contrat. */
		CLIENTS_SOUSCRITS
	}

	/**
	 * Recherche des clients par leur nom ou leur prenom.
	 * 
	 * Cette méthode est équivalente à
	 * <code>rechercherClients(Filtre.TOUS_CLIENTS, recherche)</code>.
	 * 
	 * @param recherche chaîne contenant les mots à rechercher
	 * @return une liste de clients
	 * @see ServiceClientele#rechercherClients(Filtre, String)
	 */
	public static List<Client> rechercherClients(String recherche) {
		return em().createNamedQuery("Rechercher", Client.class).setParameter("recherche", "%" + recherche + "%").getResultList();
	}

	/**
	 * Recherche des clients par leur nom ou leur prenom.
	 * 
	 * @param recherche chaîne contenant les mots à rechercher
	 * @return une liste de clients
	 */
	public static List<Client> rechercherClients(Filtre filtre, String recherche) {
		String queryName = "Rechercher";

		switch (filtre) {
		case CLIENTS_SOUSCRITS:
			queryName = "RechercherClientsSouscrits";
			break;
		case CLIENTS_PROSPECTES:
			queryName = "RechercherClientsProspectes";
			break;
		case CLIENTS_NON_PROSPECTES:
			queryName = "RechercherClientsNonProspectes";
			break;
		default:
			queryName = "Rechercher";
			break;
		}

		TypedQuery<Client> query = em().createNamedQuery(queryName, Client.class);
		query.setParameter("recherche", "%" + recherche + "%");

		return query.getResultList();	}

}
