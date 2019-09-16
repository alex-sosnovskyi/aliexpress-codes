package ua.pl.alex.aliexpress.codes.dao;

import ua.pl.alex.aliexpress.codes.Entity.Entity;


import java.util.List;

/**
 * Created by Admin on 01.04.2017.
 */
public interface Dao<K, T extends Entity<K>> {
    List<T> getAll();
    T getById(K key);
    void save(T entity);
    void delete(K key);
    void update(T entity);
}
