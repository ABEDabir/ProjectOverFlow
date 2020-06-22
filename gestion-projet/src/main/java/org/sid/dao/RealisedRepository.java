package org.sid.dao;

import org.sid.entities.Realised;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RealisedRepository extends MongoRepository<Realised,String> {
}
