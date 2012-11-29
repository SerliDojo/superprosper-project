package com.serli.dojo.superprosper.wicket;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;

import com.serli.dojo.superprosper.service.ServiceClientele.Filtre;

public class HomePage extends WebPage {

	public HomePage() {
		Pair<String, Filtre> pair = new ImmutablePair<String, Filtre>("", null);
		add(new SearchPanel("panelSearch", Model.of(pair)));
	}

}
