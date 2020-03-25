package ru.mail.migus_nikita.bot.service.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static ru.mail.migus_nikita.bot.service.constant.CityValidationConstant.NAME_PATTERN;

public class NewCityDTO {

    @NotNull
    @NotEmpty
    @Pattern(regexp = NAME_PATTERN)
    private String name;
    @NotNull
    @NotEmpty
    private String info;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
