package com.errormanager.repository;

import com.errormanager.model.Errors;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ErrorRepository extends CrudRepository<Errors, String> {
    @Override
    Optional<Errors> findById(String s);

    @Override
    boolean existsById(String s);

}
