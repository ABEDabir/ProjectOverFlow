package org.sid.dao;

import org.sid.entities.Tache;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TacheRepository extends MongoRepository<Tache,String> {
}
