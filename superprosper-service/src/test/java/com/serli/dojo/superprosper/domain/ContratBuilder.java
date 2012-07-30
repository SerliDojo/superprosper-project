package com.serli.dojo.superprosper.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public final class ContratBuilder implements TestDataBuilder<Contrat> {

	/** Numéro identifiant le contrat. */
	private Integer numero;

	/** Client ayant souscrit le contrat. */
	private Client client;

	/** Produits souscrits dans le contrat (codifiés). */
	private List<String> produits;

	/** Date de la signature du contrat. */
	private Date signature;

	/**
	 * Date de prise d'effet du contrat (si différente de la date de signature).
	 */
	private Date effet;

	private ContratBuilder() {
		this.produits = new ArrayList<String>();
	}

	public static ContratBuilder aContrat() {
		return new ContratBuilder();
	}
	
	public ContratBuilder withClient(Client client) {
		this.client = client;
		return this;
	}
	
	public ContratBuilder withEffet(Date effet) {
		this.effet = effet;
		return this;
	}
	
	public ContratBuilder withNumero(Integer numero) {
		this.numero = numero;
		return this;
	}
	
	public ContratBuilder withProduits(String... produits) {
		this.produits = new ArrayList<String>();
		this.produits.addAll(Arrays.asList(produits));
		return this;
	}
	
	public ContratBuilder withSignature(Date signature) {
		this.signature = signature;
		return this;
	}
	
	@Override
	public Contrat build() {
		Contrat contrat = new Contrat();
		contrat.setClient(client);
		contrat.setEffet(effet);
		contrat.setNumero(numero);
		contrat.setSignature(signature);
		contrat.setProduits(produits);
		return contrat;
	}

}
