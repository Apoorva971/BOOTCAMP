package com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Repository;

import com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Entities.AuthorBidirectional;
import org.springframework.data.repository.CrudRepository;

public interface AuthorBidirectionalRepository extends CrudRepository<AuthorBidirectional, Integer> {
}