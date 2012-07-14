package com.serli.dojo.superprosper.service;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.serli.dojo.superprosper.domain.Texte;
import com.serli.dojo.superprosper.service.defaut.ServiceCodificationDefaut;

@RunWith(MockitoJUnitRunner.class)
public class ServiceCodificationDefautTest {

	@Mock
	private EntityManager entityManager;

	private ServiceCodificationDefaut texteService;

	@Before
	public void setUp() {
		texteService = new ServiceCodificationDefaut();
		texteService.setEntityManager(entityManager);
	}

	@Test
	public void testLireTexteSansLocale() {
		Texte texteFictif = creerTexte("base");
		when(entityManager.find(any(Class.class), any())).thenReturn(texteFictif);

		String texteLu = texteService.lireTexte("Ca", "Co");

		Assert.assertEquals(texteFictif.getTexte(), texteLu);
	}

	@Test
	public void testLireTexteAvecLocale() {
		Texte texteFictif = creerTexte("base");
		when(entityManager.find(any(Class.class), any())).thenReturn(texteFictif);

		String texteLu = texteService.lireTexte("Ca", "Co", "La");

		Assert.assertEquals(texteFictif.getTexte(), texteLu);
	}

	@Test
	public void testLireTextesSansLocale() {
		List<Texte> textesFictifs = creerTextes(4);
		TypedQuery<Texte> query = mock(TypedQuery.class);
		when(entityManager.createNamedQuery("pourCategorie", Texte.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(textesFictifs);

		Map<String, String> textesLus = texteService.lireTextes("Ca");

		Assert.assertEquals(4, textesLus.size());
		Assert.assertEquals(textesFictifs.get(0).getTexte(), textesLus.get(textesFictifs.get(0).getCode()));
	}

	@Test
	public void testLireTextesAvecLocale() {
		List<Texte> textesFictifs = creerTextes(4);
		TypedQuery<Texte> query = mock(TypedQuery.class);
		when(entityManager.createNamedQuery("pourCategorie", Texte.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(textesFictifs);

		Map<String, String> textesLus = texteService.lireTextes("Ca", "La");

		Assert.assertEquals(4, textesLus.size());
		Assert.assertEquals(textesFictifs.get(0).getTexte(), textesLus.get(textesFictifs.get(0).getCode()));
	}

	private Texte creerTexte(String base) {
		Texte texte = new Texte();
		texte.setCategorie("Cat-" + base);
		texte.setCode("Cod-" + base);
		texte.setTexte("Tex-" + base);
		texte.setLangue("Lan-" + base);

		return texte;
	}

	private List<Texte> creerTextes(int nombre) {
		List<Texte> textes = new ArrayList<Texte>(nombre);
		for (int i = 0; i < nombre; i++) {
			textes.add(creerTexte(Integer.toString(i)));
		}

		return textes;
	}

}
