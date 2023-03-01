package com.errormanager.repository;

import com.errormanager.model.Errors;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ErrorRepository extends CrudRepository<Errors, String> {

}
