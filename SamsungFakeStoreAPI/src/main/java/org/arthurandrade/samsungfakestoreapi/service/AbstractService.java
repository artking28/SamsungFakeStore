package org.arthurandrade.samsungfakestoreapi.service;


import jakarta.persistence.EntityNotFoundException;
import org.arthurandrade.samsungfakestoreapi.domain.exceptions.BusinessRuleException;
import org.arthurandrade.samsungfakestoreapi.util.FilteredPageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.arthurandrade.samsungfakestoreapi.domain.interfaces.IAbstractDTO;
import org.arthurandrade.samsungfakestoreapi.domain.interfaces.IAbstractEntity;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;

import java.util.List;

@SuppressWarnings("RedundantThrows")
public abstract class AbstractService<T extends IAbstractEntity<T, DTO>, DTO extends IAbstractDTO<T>> implements IService<T, DTO> {

	public DTO getById(Long id) throws EntityNotFoundException {
		return getRepository().findById(id).map(IAbstractEntity::toDTO).orElseThrow(EntityNotFoundException::new);
	}

	public List<DTO> find(DTO dto) throws ObjectNotFoundException {
		if(dto == null) {
			return this.getRepository().findAll().stream().map(IAbstractEntity::toDTO).toList();
		}

		Example<T> example = Example.of(dto.toEntity());
		List<T> ret = this.getRepository().findAll(example);
		return ret.stream().map(IAbstractEntity::toDTO).toList();
	}

	public List<DTO> find(FilteredPageRequest<DTO> request) throws ObjectNotFoundException {
		if(request == null) {
			var ret = this.getRepository().findAll();
            return ret.stream().map(IAbstractEntity::toDTO).toList();
		}

		var pageable = request.toSpringPageRequest();
		DTO filter = request.getContent();
		if(filter == null) {
            var ret = this.getRepository().findAll(pageable);
			return ret.stream().map(IAbstractEntity::toDTO).toList();
		}

		Example<T> example = Example.of(filter.toEntity());
		Page<T> page = this.getRepository().findAll(example, pageable);
		return page.getContent().stream().map(IAbstractEntity::toDTO).toList();
	}

	protected Long count() {
		return getRepository().count();
	}

	@Transactional(rollbackFor = Exception.class)
	public DTO save(DTO dto) throws BusinessRuleException {
		T entity = dto.toEntity();
		commonValidations(entity);
		if(entity.getId() == null) {
			beforeCreate(entity);
		} else {
			beforeSave(entity);
		}
		return getRepository().save(entity).toDTO();
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Long id) throws EntityNotFoundException {
		getRepository().findById(id).orElseThrow(EntityNotFoundException::new);
		getRepository().deleteById(id);
	}

	abstract void beforeCreate(T entity) throws BusinessRuleException;

	abstract void beforeSave(T entity) throws BusinessRuleException;

	protected abstract void commonValidations(T entity) throws BusinessRuleException;

}
