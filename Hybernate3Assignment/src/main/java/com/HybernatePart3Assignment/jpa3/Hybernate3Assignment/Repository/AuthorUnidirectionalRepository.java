package com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Repository;

import com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Entities.AuthorUnidirectional;
import org.springframework.data.repository.CrudRepository;

public interface AuthorUnidirectionalRepository extends CrudRepository<AuthorUnidirectional, Integer> {
}