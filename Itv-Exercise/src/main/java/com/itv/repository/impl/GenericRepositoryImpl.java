package com.itv.repository.impl;

import com.itv.repository.GenericRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by sakibchoudhury on 30/12/17.
 */
@Repository
@Transactional
public abstract class GenericRepositoryImpl implements GenericRepository {

    @PersistenceContext
    protected EntityManager entityManager;


    @Override
    public <T> T insert(T t) {
        entityManager.persist(t);
        return t;
    }

    @Override
    public <T> T update(T t) {
        return entityManager.merge(t);
    }

    @Override
    public <T> List<T> batchInsert(List<T> entityList) {
        for(Object object : entityList) {
            entityManager.persist(object);
        }

        return entityList;
    }


}
