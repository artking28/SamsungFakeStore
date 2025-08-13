package org.arthurandrade.samsungfakestoreapi.controller;

import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.arthurandrade.samsungfakestoreapi.util.FilteredPageRequest;
import org.springframework.lang.Nullable;
import org.arthurandrade.samsungfakestoreapi.domain.exceptions.BusinessRuleException;
import org.arthurandrade.samsungfakestoreapi.domain.interfaces.IAbstractDTO;
import org.arthurandrade.samsungfakestoreapi.domain.interfaces.IAbstractEntity;
import org.arthurandrade.samsungfakestoreapi.domain.noData.GenericResponse;
import org.arthurandrade.samsungfakestoreapi.service.IService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import static org.arthurandrade.samsungfakestoreapi.domain.enums.ResponseCodeEnum.TYPE_ERROR;


public abstract class AbstractController<T extends IAbstractEntity<T, DTO>, DTO extends IAbstractDTO<T>> {

    protected abstract IService<T, DTO> getService();

    protected CircularFifoQueue<String> lastestsPersists = new CircularFifoQueue<>(500);

    @GetMapping("byId/{id}")
    public ResponseEntity<GenericResponse> getById(@PathVariable Long id) {
        var dto = getService().getById(id);
        return ResponseEntity.ok(new GenericResponse(dto));
    }

    @DeleteMapping("byId/{id}")
    protected ResponseEntity<GenericResponse> deleteByid(@PathVariable Long id) throws BusinessRuleException {
        var dto = getService().getById(id);
        getService().deleteById(id);

        // Return the removed entity
        return ResponseEntity.ok(new GenericResponse(dto));
    }

    @PostMapping("select")
    public ResponseEntity<GenericResponse> list(@Nullable @RequestBody FilteredPageRequest<DTO> request) throws BusinessRuleException {
        var result = getService().find(request);
        return ResponseEntity.ok(new GenericResponse(result));
    }

    @PostMapping("save")
    public ResponseEntity<GenericResponse> save(@RequestBody DTO dto) throws BusinessRuleException {
        GenericResponse genericResponse = new GenericResponse();
        var isNew = dto.getId() == null;
        if (isNew && StringUtils.hasText(dto.getUuidCheck()) && lastestsPersists.contains(dto.getUuidCheck())) {
            throw new BusinessRuleException("error.entity.alreadyPersisted", TYPE_ERROR);
        }

        dto = getService().save(dto);
        if (isNew && StringUtils.hasText(dto.getUuidCheck())) {
            lastestsPersists.add(dto.getUuidCheck());
        }

        return ResponseEntity.ok().body(genericResponse);
    }
}
