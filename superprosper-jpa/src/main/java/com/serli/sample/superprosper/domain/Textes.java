package com.serli.sample.superprosper.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(TexteId.class)
public class Textes implements Serializable {

	private static final long serialVersionUID = -4153413538164835805L;

	@Id
	@Column(length = 10, nullable = false)
	String categorie;

	@Id
	@Column(length = 6, nullable = false)
	String code;
	
	@Id
	@Column(length = 2, nullable = false)
	String langue;
	
	@Basic
	@Column(length = 200, nullable = false)
	String texte;
	
	public Textes() {
		super();
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLangue() {
		return langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}
	
	@Override
	public String toString() {
		return "Textes [categorie=" + categorie + ", code=" + code
				+ ", langue=" + langue + ", texte=" + texte + "]";
	}
}
