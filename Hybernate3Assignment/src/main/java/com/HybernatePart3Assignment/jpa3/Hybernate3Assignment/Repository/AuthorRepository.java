package com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Repository;

import com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Entities.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Integer> {
}