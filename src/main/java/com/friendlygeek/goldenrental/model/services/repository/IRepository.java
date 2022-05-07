package com.friendlygeek.goldenrental.model.services.repository;

import java.util.ArrayList;

public interface IRepository<T> {
    ArrayList<T> getAll();

    T getById(int id);

    void Update(T entity);

    void Delete(String id);

}
