package com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Repository;


import com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Entities.AuthorManyToMany;
import org.springframework.data.repository.CrudRepository;

public interface AuthorManyToManyRepository extends CrudRepository<AuthorManyToMany, Integer> {

    }

