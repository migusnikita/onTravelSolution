package ru.mail.migus_nikita.bot.web;

import ru.mail.migus_nikita.bot.service.CityService;
import ru.mail.migus_nikita.bot.service.model.CityDTO;
import ru.mail.migus_nikita.bot.service.model.NewCityDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("api/")
public class ApiController {

    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
    private final CityService cityService;

    @Autowired
    public ApiController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping(value = "v1/cities")
    public List<CityDTO> getCities() {
        return cityService.getCities();
    }

    @GetMapping(value = "v1/cities/{id}")
    public CityDTO getCity(@PathVariable("id") Long id) {
        return cityService.getById(id);
    }

    @PostMapping(value = "v1/cities", consumes = APPLICATION_JSON_VALUE)
    public CityDTO addCity(@Valid @RequestBody NewCityDTO newCityDTO) {
        return cityService.add(newCityDTO);
    }

    @PutMapping(value = "v1/cities/{id}", consumes = APPLICATION_JSON_VALUE)
    public CityDTO updateCity(
            @PathVariable("id") Long id,
            @Valid @RequestBody CityDTO cityDTO
    ) {
        cityDTO.setId(id);
        return cityService.update(cityDTO);
    }

    @DeleteMapping(value = "v1/cities/{id}")
    public ResponseEntity deleteCity(@PathVariable("id") Long id) {
        cityService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @SuppressWarnings(value = "unchecked")
    public ResponseEntity processValidationError(MethodArgumentNotValidException e) {
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        List<String> errorsList = errors.stream()
                .map(error -> String.format("Field: %s - %s.", error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ResponseEntity(errorsList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @SuppressWarnings(value = "unchecked")
    public ResponseEntity defaultError(Exception e) {
        logger.error(e.getMessage(), e);
        return new ResponseEntity("Something went wrong!", HttpStatus.BAD_REQUEST);
    }

}
