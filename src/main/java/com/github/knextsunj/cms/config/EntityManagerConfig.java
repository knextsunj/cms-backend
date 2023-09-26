package com.github.knextsunj.cms.config;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//@Singleton
public class EntityManagerConfig {

    @PersistenceContext(unitName = "cmsPU")
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
