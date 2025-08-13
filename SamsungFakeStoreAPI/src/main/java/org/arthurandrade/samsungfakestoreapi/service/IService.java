package org.arthurandrade.samsungfakestoreapi.service;

import jakarta.persistence.EntityNotFoundException;
import org.arthurandrade.samsungfakestoreapi.domain.exceptions.BusinessRuleException;
import org.arthurandrade.samsungfakestoreapi.domain.interfaces.IAbstractDTO;
import org.arthurandrade.samsungfakestoreapi.domain.interfaces.IAbstractEntity;
import org.arthurandrade.samsungfakestoreapi.util.FilteredPageRequest;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IService<T extends IAbstractEntity<T, DTO>, DTO extends IAbstractDTO<T>> {

    JpaRepository<T, Long> getRepository();

    DTO getById(Long id) throws EntityNotFoundException;

    void deleteById(Long id) throws EntityNotFoundException;

    DTO save(DTO dto) throws BusinessRuleException;

    List<DTO> find(DTO dto) throws ObjectNotFoundException;

    List<DTO> find(FilteredPageRequest<DTO> request) throws ObjectNotFoundException;
}
