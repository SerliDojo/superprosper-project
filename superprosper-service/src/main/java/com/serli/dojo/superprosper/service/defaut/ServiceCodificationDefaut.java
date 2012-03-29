package com.serli.dojo.superprosper.service.defaut;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.TypedQuery;

import com.serli.dojo.superprosper.domain.Texte;
import com.serli.dojo.superprosper.service.ServiceCodification;

/**
 * Implémentation du service de lecture des textes codifiés.
 * 
 * Cette classe utilise l'injection de gestionnaire d'entité telle que proposée
 * par JPA.
 * 
 * @author Laurent RUAUD
 */
public class ServiceCodificationDefaut extends ServiceGenerique implements ServiceCodification {

	@Override
	public String lireTexte(String categorie, String code) {
		return lireTexte(categorie, code, Locale.getDefault().getLanguage());
	}

	@Override
	public String lireTexte(String categorie, String code, String langue) {
		Texte texteId = new Texte();
		texteId.setCategorie(categorie);
		texteId.setCode(code);
		texteId.setLangue(langue.toUpperCase());

		Texte texte = getEntityManager().find(Texte.class, texteId);
		return texte.getTexte();
	}

	@Override
	public Map<String, String> lireTextes(String categorie) {
		return lireTextes(categorie, Locale.getDefault().getLanguage());
	}

	@Override
	public Map<String, String> lireTextes(String categorie, String langue) {
		Map<String, String> textes = Collections.emptyMap();

		TypedQuery<Texte> query = getEntityManager().createNamedQuery("pourCategorie", Texte.class);
		query.setParameter("categorie", categorie);
		query.setParameter("langue", langue);

		List<Texte> results = query.getResultList();
		if (results != null && results.size() > 0) {
			textes = new HashMap<String, String>(results.size());
			for (Texte texte : results) {
				textes.put(texte.getCode(), texte.getTexte());
			}
		}

		return textes;
	}

}
