package com.serli.dojo.superprosper.domain;

import java.util.Calendar;
import java.util.GregorianCalendar;

public final class Prospections {

	private Prospections() {
	}

	public static Prospection creerAu6Avril2012() {
		Prospection prospection = new Prospection();
		prospection.setContact(new GregorianCalendar(2012, Calendar.APRIL, 6).getTime());
		return prospection;
	}

	public static Prospection creerAu5Juillet2012() {
		Prospection prospection = new Prospection();
		prospection.setContact(new GregorianCalendar(2012, Calendar.JULY, 5).getTime());
		return prospection;
	}

}
