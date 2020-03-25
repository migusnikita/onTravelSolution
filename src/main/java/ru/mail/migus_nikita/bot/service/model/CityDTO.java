package ru.mail.migus_nikita.bot.service.model;

import javax.validation.constraints.Pattern;

import static ru.mail.migus_nikita.bot.service.constant.CityValidationConstant.NAME_PATTERN;

public class CityDTO {

    private Long id;
    @Pattern(regexp = NAME_PATTERN)
    private String name;
    private String info;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
