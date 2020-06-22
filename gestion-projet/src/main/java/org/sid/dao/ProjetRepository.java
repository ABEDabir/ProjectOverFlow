package org.sid.dao;

import org.sid.entities.Projet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface ProjetRepository extends MongoRepository<Projet,String> {
}
