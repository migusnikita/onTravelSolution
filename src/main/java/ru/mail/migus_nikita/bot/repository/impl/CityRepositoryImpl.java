package ru.mail.migus_nikita.bot.repository.impl;

import ru.mail.migus_nikita.bot.repository.CityRepository;
import ru.mail.migus_nikita.bot.repository.model.City;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

@Repository
public class CityRepositoryImpl extends GenericRepositoryImpl<Long, City> implements CityRepository {

    @Override
    public City getByName(String text) {
        String query = "FROM " + entityClass.getName() +
                " WHERE name =: text";
        Query q = entityManager.createQuery(query)
                .setParameter("text", text);
        try {
            return (City) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
