package ru.mail.migus_nikita.bot.repository;

import java.util.List;

public interface GenericRepository<I, T> {

    void create(T entity);

    void update(T entity);

    void delete(T entity);

    T getById(I id);

    void deleteById(I id);

    List<T> getAllEntities();

}
