package ru.mail.migus_nikita.bot.service.conveter;

import ru.mail.migus_nikita.bot.repository.model.City;
import ru.mail.migus_nikita.bot.service.model.CityDTO;

public interface CityConverter {

    CityDTO toDTO(City city);

}
