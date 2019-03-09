package com.scrap.avatar.repository;

import com.scrap.avatar.entity.PersonEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * PersonRepository.
 *
 * @author Luis Alonso Ballena Garcia
 */
@Repository
public interface PersonRepository extends CrudRepository<PersonEntity,Integer> {

    List<PersonEntity> findAll();


}
