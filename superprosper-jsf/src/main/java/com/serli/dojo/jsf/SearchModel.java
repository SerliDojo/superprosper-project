/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serli.dojo.jsf;

import com.serli.dojo.superprosper.domain.Client;
import com.serli.dojo.superprosper.service.ServiceClientele;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class SearchModel implements Serializable {
    private String searchValue;
    private List<Client> clients;
    private ServiceClientele.Filtre filtre;
    private Client client;

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Client> getClients() {
        return clients;
    }

    public ServiceClientele.Filtre getFiltre() {
        return filtre;
    }

    public void setFiltre(ServiceClientele.Filtre filtre) {
        this.filtre = filtre;
    }
    
    public ServiceClientele.Filtre[] getCriteria() {
        return ServiceClientele.Filtre.values();
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }
    
    @PostConstruct
    public void start() {
        this.searchValue = "";
        this.clients = Collections.emptyList();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
    
}
