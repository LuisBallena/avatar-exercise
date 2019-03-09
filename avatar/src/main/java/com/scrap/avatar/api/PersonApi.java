package com.scrap.avatar.api;

import com.scrap.avatar.api.dto.PersonDTO;
import com.scrap.avatar.entity.PersonEntity;
import com.scrap.avatar.service.PersonService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * PersonController.
 *
 * @author Luis Alonso Ballena Garcia
 */
@RestController
@RequestMapping("/persona")
public class PersonApi {

    @Autowired
    private PersonService personService;

    @GetMapping
    public List<PersonEntity> getAll() {
        return personService.getAll();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<PersonEntity> savePerson(@Valid @RequestBody PersonDTO personDTO) {
        PersonEntity personEntity = personService.savePerson(personDTO);
        return new ResponseEntity(personEntity, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public PersonEntity getById(@PathVariable(value = "id") Integer id) {
        return personService.findPerson(id);
    }

    @DeleteMapping(value = "/{id}")
    public void savePerson(@PathVariable(value = "id") Integer id) {
        personService.removePerson(id);
    }

    @PutMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<PersonEntity> updatePerson(@PathVariable(value = "id") Integer id,
                                                     @Valid @RequestBody PersonDTO personDTO) {
        PersonEntity personEntity = personService.updatePerson(id, personDTO);
        return new ResponseEntity(personEntity, HttpStatus.ACCEPTED);
    }


}
