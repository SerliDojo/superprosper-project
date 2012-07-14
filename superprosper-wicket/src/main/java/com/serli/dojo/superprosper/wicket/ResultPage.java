package com.serli.dojo.superprosper.wicket;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.serli.dojo.superprosper.domain.Client;
import com.serli.dojo.superprosper.service.ServiceClientele;
import com.serli.dojo.superprosper.service.defaut.ServiceClienteleDefaut;

public class ResultPage extends WebPage {
	
	public static final String PERSISTENCE_UNIT_NAME = "superprosper-pu";

	private static EntityManagerFactory entityManagerFactory;

	protected EntityManager entityManager;

	public ResultPage(String searchTextValue, ServiceClientele.Filtre filtre){
		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		
		add(new SearchPanel("searchPanel", searchTextValue, filtre));

        final Label customerName = new Label("customerName", new Model());
        customerName.setOutputMarkupId(true);
		add(customerName);
		
		List<IColumn<Client>> columns = new ArrayList<IColumn<Client>>();
        columns.add(new PropertyColumn<Client>(new Model<String>("Numéro"), "numero"));
        columns.add( new PropertyColumn<Client>(new Model<String>("Nom"), "nom"));
        columns.add( new PropertyColumn<Client>(new Model<String>("Prénom"), "prenom"));
        columns.add( new PropertyColumn<Client>(new Model<String>("Téléphone"), "telephone"));
        columns.add( new PropertyColumn<Client>(new Model<String>("Region"), "region"));
        columns.add(new AbstractColumn<Client>(new Model<String>("")) {
            public void populateItem(Item<ICellPopulator<Client>> cellItem, String componentId, final IModel<Client> model) {
                cellItem.add(new AjaxLink<Client>(componentId, model) {
                	@Override
                	public void onClick(AjaxRequestTarget target) {
                		customerName.setDefaultModelObject(model.getObject().getPrenom() + " " + model.getObject().getNom());
                		target.add(customerName);
                	}
                }.add(new AttributeAppender("class", "btn btn-primary btn-mini")));
            }
        });
        
        entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		ServiceClienteleDefaut service = new ServiceClienteleDefaut();
        service.setEntityManager(entityManager);
        List<Client> rechercherClients = service.rechercherClients(filtre, searchTextValue);
        entityManager.getTransaction().commit();
        System.out.println(rechercherClients.size());
        DefaultDataTable<Client> table = new DefaultDataTable<Client>("datatable", columns, new ClientProvider(rechercherClients), 10);
        add(table);
	}
	
}
