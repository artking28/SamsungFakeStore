package org.arthurandrade.samsungfakestorefront.services;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.arthurandrade.samsungfakestoreapi.domain.interfaces.IAbstractDTO;
import org.arthurandrade.samsungfakestoreapi.util.FilteredPageRequest;

import java.util.List;
import java.util.Map;


public abstract class EntityService<T extends IAbstractDTO<?>> extends AbstractService {

    protected abstract void prepareSave(T obj);

    protected boolean isGetByIdPublic() {
        return false;
    }

    protected boolean isListPublic() {
        return false;
    }

    public T getById(String id) {
        String path = String.format("%s%s/byId/%s", BASE_URL, getServiceUrl(), id);
        return client.target(path)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<T>() {});
    }

    public List<T> list(FilteredPageRequest<T> fltr) {
        String path = String.format("%s%s/select", BASE_URL, getServiceUrl());
        return client.target(path)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(fltr), new GenericType<List<T>>() {});
    }

    public T getFirst(FilteredPageRequest<T> fltr) {
        List<T> list = list(fltr);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public T save(T obj) {
        prepareSave(obj);
        String path = String.format("%s%s/save", BASE_URL, getServiceUrl());
        return client.target(path)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(obj), new GenericType<T>() {});
    }

    public T patch(Object id, Map<String, Object> params) {
        String path = String.format("%s%s/byId/%s", BASE_URL, getServiceUrl(), id);
        return client.target(path)
                .request(MediaType.APPLICATION_JSON)
                .method("PATCH", Entity.json(params), new GenericType<T>() {});
    }

    public boolean deleteById(Object id) {
        String path = String.format("%s%s/byId/%s", BASE_URL, getServiceUrl(), id);
        Response response = client.target(path)
                .request(MediaType.APPLICATION_JSON)
                .delete();
        return response.getStatus() == 200;
    }
}
