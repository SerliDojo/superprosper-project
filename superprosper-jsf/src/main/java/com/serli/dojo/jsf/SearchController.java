package com.serli.dojo.jsf;

import com.serli.dojo.superprosper.domain.Client;
import com.serli.dojo.superprosper.service.ServiceClientele;
import java.util.Collections;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Stateless
@Named
public class SearchController {

    @Inject
    SearchModel model;
    
    @Inject
    ServiceClientele serviceClientele;

    public String search() {
        String value = model.getSearchValue();
        model.setClients(
                serviceClientele
                .rechercherClients(model.getFiltre(), value));
        return "search.xhtml";
    }
    
    public String select(Client client) {
        model.setClient(client);
        return "search.xhtml";
    }
    

}
