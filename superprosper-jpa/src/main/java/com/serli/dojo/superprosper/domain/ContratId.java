package com.serli.dojo.superprosper.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

public class ContratId implements Serializable {

	private static final long serialVersionUID = 8381794426729315394L;
	
	private int numero;
	
	private String produit;
	
	public ContratId() {};
	
	public ContratId(int numero, String produit) {
		super();
		this.numero = numero;
		this.produit = produit;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public String getProduit() {
		return produit;
	}
	
	public void setProduit(String produit) {
		this.produit = produit;
	}
	
	@Override
	public String toString() {
		return "ContratId [numero=" + numero + ", produit=" + produit + "]";
	}	
}
