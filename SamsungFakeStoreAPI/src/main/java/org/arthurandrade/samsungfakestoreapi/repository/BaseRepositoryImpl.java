package org.arthurandrade.samsungfakestoreapi.repository;

import jakarta.persistence.EntityManager;
import org.arthurandrade.samsungfakestoreapi.domain.interfaces.IAbstractEntity;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class BaseRepositoryImpl<T extends IAbstractEntity<?,?>, ID extends Long> extends SimpleJpaRepository<T, ID> implements IBaseRepository<T, ID> {

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }
}
