package com.serli.dojo.superprosper.wicket;

import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.serli.dojo.superprosper.domain.Client;

public class ClientProvider extends SortableDataProvider<Client> {
	
	private List<Client> clients;

	public ClientProvider(List<Client> clients) {
		this.clients = clients;
	}

	@Override
	public Iterator<Client> iterator(int arg0, int arg1) {
		return clients.subList(arg0, Math.min(arg0 + arg1, clients.size())).iterator();
	}

	@Override
	public IModel<Client> model(Client arg0) {
		return new Model<Client>(arg0);
	}

	@Override
	public int size() {
		return clients.size();
	}

}
