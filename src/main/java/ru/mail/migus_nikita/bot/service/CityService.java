package ru.mail.migus_nikita.bot.service;

import ru.mail.migus_nikita.bot.service.model.CityDTO;
import ru.mail.migus_nikita.bot.service.model.NewCityDTO;

import java.util.List;

public interface CityService {

    String getInfo(String text);

    List<CityDTO> getCities();

    CityDTO getById(Long id);

    CityDTO add(NewCityDTO newCityDTO);

    CityDTO update(CityDTO cityDTO);

    void delete(Long id);

}
