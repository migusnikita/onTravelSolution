package ru.mail.migus_nikita.bot.repository;

import ru.mail.migus_nikita.bot.repository.model.City;

public interface CityRepository extends GenericRepository<Long, City> {

    City getByName(String text);

}
