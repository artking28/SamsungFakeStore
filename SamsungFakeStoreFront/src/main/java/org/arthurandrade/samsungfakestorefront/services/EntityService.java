package org.arthurandrade.samsungfakestorefront.services;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.arthurandrade.samsungfakestoreapi.domain.interfaces.IAbstractDTO;
import org.arthurandrade.samsungfakestoreapi.domain.noData.GenericResponse;
import org.arthurandrade.samsungfakestoreapi.util.FilteredPageRequest;

import java.util.List;


public abstract class EntityService<T extends IAbstractDTO<?>> extends AbstractService {

    protected abstract void prepareSave(T obj);

    protected boolean isGetByIdPublic() {
        return false;
    }

    protected boolean isListPublic() {
        return false;
    }

    public T getById(Long id) {
        String path = String.format("%s%s/byId/%s", BASE_URL, getServiceUrl(), id);
        GenericResponse response = client.target(path)
                .request(MediaType.APPLICATION_JSON)
                .get(GenericResponse.class);

        return response.getResult() != null ? (T) response.getResult() : null;
    }

    public T updateById(Long id, T entity) {
        String path = String.format("%s%s/byId/%s", BASE_URL, getServiceUrl(), id);
        GenericResponse response = client.target(path)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(entity), GenericResponse.class);

        return response.getResult() != null ? (T) response.getResult() : null;
    }

    public boolean deleteById(Long id) {
        String path = String.format("%s%s/byId/%s", BASE_URL, getServiceUrl(), id);
        Response response = client.target(path)
                .request(MediaType.APPLICATION_JSON)
                .delete();
        return response.getStatus() == 200;
    }

    public List<T> list(FilteredPageRequest<T> fltr) {
        String path = String.format("%s%s/select", BASE_URL, getServiceUrl());
        System.out.println(path);
        GenericResponse response = client.target(path)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(fltr), GenericResponse.class);

        return response.getResult() != null ? (List<T>) response.getResult() : null;
    }

    public T getFirst(FilteredPageRequest<T> fltr) {
        List<T> list = list(fltr);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public T save(T entity) {
        prepareSave(entity);
        String path = String.format("%s%s/save", BASE_URL, getServiceUrl());
        GenericResponse response = client.target(path)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(entity), GenericResponse.class);

        return response.getResult() != null ? (T) response.getResult() : null;
    }
}
