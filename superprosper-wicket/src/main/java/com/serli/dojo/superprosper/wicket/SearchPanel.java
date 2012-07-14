package com.serli.dojo.superprosper.wicket;

import java.util.Arrays;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.serli.dojo.superprosper.service.ServiceClientele;
import com.serli.dojo.superprosper.service.ServiceCommercial;

public class SearchPanel extends Panel {

	public SearchPanel(String id, String searchTextValue, ServiceClientele.Filtre filtre) {
		super(id);
		final TextField<String> searchText = new TextField<String> ("searchText", new Model<String>(searchTextValue));
		final DropDownChoice<ServiceClientele.Filtre> choixClient = new DropDownChoice<ServiceClientele.Filtre>("choixClient", new Model(filtre), Arrays.asList(ServiceClientele.Filtre.values()));
		
		//add(searchText);
		Form<String> formProsper = new Form<String>("formProsper") {
			@Override
			protected void onSubmit() {				
				ResultPage p = new ResultPage(searchText.getModelObject(),choixClient.getModelObject());				
				setResponsePage(p);
			}
		};
		formProsper.add(searchText).add(choixClient);
		add(formProsper);
	}
}
