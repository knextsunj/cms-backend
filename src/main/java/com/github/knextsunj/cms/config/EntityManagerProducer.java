package com.github.knextsunj.cms.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

@ApplicationScoped
public class EntityManagerProducer {

//    @PersistenceContext(unitName = "cmsPU")
//    private EntityManager entityManager;

    @PersistenceUnit(unitName = "cmsPU")
    @Produces
    @Dependent
    private EntityManagerFactory entityManagerFactory;

    @Produces
    @RequestScoped
    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public void close( @Disposes EntityManager entityManager )
    {
        entityManager.close() ;
    }
}
