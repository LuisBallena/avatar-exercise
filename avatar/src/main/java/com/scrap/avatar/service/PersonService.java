package com.scrap.avatar.service;

import com.scrap.avatar.api.dto.PersonDTO;
import com.scrap.avatar.api.external.swapi.PlanetApi;
import com.scrap.avatar.entity.PersonEntity;
import com.scrap.avatar.exception.AvatarException;
import com.scrap.avatar.repository.PersonRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * PersonService.
 *
 * @author Luis Alonso Ballena Garcia
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PlanetApi planetApi;

    @Transactional(readOnly = true)
    public List<PersonEntity> getAll() {
        return personRepository.findAll();
    }

    public PersonEntity savePerson(PersonDTO personDTO) {
        validatePlanet(personDTO);
        return personRepository.save(parsePersonDTO(personDTO));
    }

    public PersonEntity updatePerson(Integer id, PersonDTO personDTO) {
        findPerson(id);
        validatePlanet(personDTO);
        PersonEntity personEntity = parsePersonDTO(personDTO);
        personEntity.setId(id);
        return personRepository.save(personEntity);
    }

    public PersonEntity findPerson(Integer id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new AvatarException(HttpStatus.NOT_FOUND, "No existe el recurso"));
    }

    public void removePerson(Integer id) {
        findPerson(id);
        personRepository.deleteById(id);
    }

    private void validatePlanet(PersonDTO personDTO) {
        if (!planetApi.evaluatePlanetName(personDTO.getPlanet())) {
            throw  new AvatarException(HttpStatus.INTERNAL_SERVER_ERROR, "El nombre de planeta ingresado no existe");
        }
    }

    //un parseador simple por temas practicos
    private PersonEntity parsePersonDTO(PersonDTO personDTO) {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setName(personDTO.getName());
        personEntity.setHeight(personDTO.getHeight());
        personEntity.setMass(personDTO.getMass());
        personEntity.setHairColor(personDTO.getHairColor());
        personEntity.setGender(personDTO.getGender());
        personEntity.setPlanet(personDTO.getPlanet());
        return personEntity;
    }

}
