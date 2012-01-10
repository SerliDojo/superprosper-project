package com.serli.sample.superprosper.domain;

import java.io.Serializable;

public class TexteId implements Serializable {

	private static final long serialVersionUID = 4108612999877230208L;
	
	private String categorie = null;
	private String code = null;
	private String langue = null;
	
	public TexteId(String categorie, String code, String langue) {
		super();
		this.categorie = categorie;
		this.code = code;
		this.langue = langue;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((categorie == null) ? 0 : categorie.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((langue == null) ? 0 : langue.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TexteId other = (TexteId) obj;
		if (categorie == null) {
			if (other.categorie != null)
				return false;
		} else if (!categorie.equals(other.categorie))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (langue == null) {
			if (other.langue != null)
				return false;
		} else if (!langue.equals(other.langue))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TexteId [categorie=" + categorie + ", code=" + code
				+ ", langue=" + langue + "]";
	}
}
