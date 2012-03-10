package com.serli.dojo.superprosper.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class TexteTest extends PersistenceTest {

	@Test
	public void testLire() throws Exception {
		insertDataSet("validData");

		Texte texteId = new Texte();
		texteId.setCategorie("REGIONS");
		texteId.setCode("NO");
		texteId.setLangue("FR");

		Texte texte = entityManager.find(Texte.class, texteId);

		assertNotNull(texte);
		assertEquals("Nord-ouest", texte.getTexte());
	}

	@Test
	public void testPersister() {
		Texte texte = new Texte();
		texte.setCategorie("CATEGORY");
		texte.setCode("CODE");
		texte.setLangue("FR");
		texte.setTexte("TEXTE");

		entityManager.persist(texte);
	}
}
