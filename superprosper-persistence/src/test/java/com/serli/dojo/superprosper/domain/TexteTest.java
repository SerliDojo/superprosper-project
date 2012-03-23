package com.serli.dojo.superprosper.domain;

import static org.junit.Assert.*;

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
		assertEquals("NO", texte.getCode());
		assertEquals("REGIONS", texte.getCategorie());
		assertEquals("FR", texte.getLangue());
	}

	@Test
	public void testPersister() {
		Texte texte = Zombies.riseTexte();

		entityManager.persist(texte);
	}

	@Test
	public void testToString() {
		Texte texte = Zombies.riseTexte();

		assertTrue(texte.toString().contains("CODE"));
		assertTrue(texte.toString().contains("TEXTE"));
	}

	@Test
	public void testEqualsHashCode() {
		Texte texte1 = Zombies.riseTexte();
		Texte texte2 = Zombies.riseTexte();

		assertEquals(texte1.hashCode(), texte2.hashCode());
		assertEquals(new Texte().hashCode(), new Texte().hashCode());

		assertTrue(texte1.equals(texte1));
		assertTrue(texte1.equals(texte2));
		assertTrue(texte2.equals(texte1));

		assertFalse(texte1.equals(null));
		assertFalse(texte1.equals(texte2.toString()));
		assertFalse(texte1.equals(new Texte()));
		assertFalse(new Texte().equals(texte1));
	}

	@Test
	public void testEqualsWithNull() {
		Texte texte1 = Zombies.riseTexte();

		Texte texte2 = Zombies.riseTexte();
		texte2.setCategorie(null);
		assertFalse(texte1.equals(texte2));

		texte2 = Zombies.riseTexte();
		texte2.setCode(null);
		assertFalse(texte1.equals(texte2));

		texte2 = Zombies.riseTexte();
		texte2.setLangue(null);
		assertFalse(texte1.equals(texte2));
	}

	@Test
	public void testEqualsWithNullCategorie() {
		Texte texte1 = Zombies.riseTexte();
		texte1.setCategorie(null);

		Texte texte2 = Zombies.riseTexte();
		assertFalse(texte1.equals(texte2));

		texte2.setCategorie(null);
		assertTrue(texte1.equals(texte2));

		texte2 = Zombies.riseTexte();
		texte2.setCode(null);
		assertFalse(texte1.equals(texte2));

		texte2 = Zombies.riseTexte();
		texte2.setLangue(null);
		assertFalse(texte1.equals(texte2));
	}

	@Test
	public void testEqualsWithNullCode() {
		Texte texte1 = Zombies.riseTexte();
		texte1.setCode(null);

		Texte texte2 = Zombies.riseTexte();
		assertFalse(texte1.equals(texte2));

		texte2.setCategorie(null);
		assertFalse(texte1.equals(texte2));

		texte2 = Zombies.riseTexte();
		texte2.setCode(null);
		assertTrue(texte1.equals(texte2));

		texte2 = Zombies.riseTexte();
		texte2.setLangue(null);
		assertFalse(texte1.equals(texte2));
	}

	@Test
	public void testEqualsWithNullLangue() {
		Texte texte1 = Zombies.riseTexte();
		texte1.setLangue(null);

		Texte texte2 = Zombies.riseTexte();
		assertFalse(texte1.equals(texte2));

		texte2.setCategorie(null);
		assertFalse(texte1.equals(texte2));

		texte2 = Zombies.riseTexte();
		texte2.setCode(null);
		assertFalse(texte1.equals(texte2));

		texte2 = Zombies.riseTexte();
		texte2.setLangue(null);
		assertTrue(texte1.equals(texte2));
	}
}
