package org.arthurandrade.samsungfakestoreapi.domain.interfaces;

public interface IAbstractDTO<T extends IAbstractEntity<?,?>> extends IEntity {

    T toEntity();
}
