package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

	public static void accueil() {
		render();
	}

	public static void search(String filtre, String recherche) {
		List<Client> clients = Client.rechercherClients(Client.Filtre.valueOf(filtre), recherche);
		Client clientdetails = Client.findById(20);
		
		render(clients, recherche, clientdetails);
	}

}