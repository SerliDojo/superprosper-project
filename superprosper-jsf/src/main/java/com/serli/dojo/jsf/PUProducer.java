/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serli.dojo.jsf;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Laurent
 */
public class PUProducer {
    @Produces
    @PersistenceContext(unitName = "superprosper-pu")
    private EntityManager em;
}
