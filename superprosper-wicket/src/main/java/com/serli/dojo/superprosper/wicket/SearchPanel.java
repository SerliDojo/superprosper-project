package com.serli.dojo.superprosper.wicket;

import java.util.Arrays;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import com.serli.dojo.superprosper.service.ServiceClientele;

public class SearchPanel extends Panel {

	public SearchPanel(String id, IModel<Pair<String, ServiceClientele.Filtre>> model) {
		super(id, model);
		final TextField<String> searchText = new TextField<String>("searchText", new PropertyModel<String>(model,
				"left"));
		final DropDownChoice<ServiceClientele.Filtre> choixClient = new DropDownChoice<ServiceClientele.Filtre>(
				"choixClient", new PropertyModel<ServiceClientele.Filtre>(model, "right"),
				Arrays.asList(ServiceClientele.Filtre.values()));

		// add(searchText);
		Form<String> formProsper = new Form<String>("formProsper") {
			@Override
			protected void onSubmit() {
				ResultPage p = new ResultPage(searchText.getModelObject(), choixClient.getModelObject());
				setResponsePage(p);
			}
		};
		formProsper.add(searchText).add(choixClient);
		add(formProsper);
	}
}
