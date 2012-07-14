package com.serli.dojo.superprosper.wicket;



import java.util.Arrays;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.serli.dojo.superprosper.service.ServiceClientele;

public class HomePage extends WebPage {
		
	
	public HomePage() {
		add(new SearchPanel("panelSearch", "", null));
	}
	
}
