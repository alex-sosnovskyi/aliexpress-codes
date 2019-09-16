package ua.pl.alex.aliexpress.codes.service.api;

import java.util.List;

/**
 * Created by Admin on 01.04.2017.
 */
public interface Service<K, T> {
    List<T> getAll();
    T getById(K key);
    void save(T entity);
    void delete(K key);
    void update(T entity);
}
