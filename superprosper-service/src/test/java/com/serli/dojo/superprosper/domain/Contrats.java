package com.serli.dojo.superprosper.domain;

import java.util.Calendar;
import java.util.GregorianCalendar;

public final class Contrats {

	private Contrats() {
	}

	public static Contrat creerSigneAu5Juillet2012() {
		Contrat contrat = new Contrat();
		contrat.setSignature(new GregorianCalendar(2012, Calendar.JULY, 5).getTime());
		return contrat;
	}

}
