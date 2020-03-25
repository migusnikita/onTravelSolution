package ru.mail.migus_nikita.bot.service.conveter.impl;

import ru.mail.migus_nikita.bot.repository.model.City;
import ru.mail.migus_nikita.bot.service.conveter.CityConverter;
import ru.mail.migus_nikita.bot.service.model.CityDTO;
import org.springframework.stereotype.Component;

@Component
public class CityConverterImpl implements CityConverter {

    @Override
    public CityDTO toDTO(City city) {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(city.getId());
        cityDTO.setName(city.getName());
        cityDTO.setInfo(city.getInfo());
        return cityDTO;
    }

}
