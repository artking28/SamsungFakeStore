package org.arthurandrade.samsungfakestoreapi.domain.interfaces;

public interface IAbstractEntity<T extends IAbstractEntity<?, ?>, DTO extends IAbstractDTO<T>> extends IEntity {

    DTO toDTO();

}
