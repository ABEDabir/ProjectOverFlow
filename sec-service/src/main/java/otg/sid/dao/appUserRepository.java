package otg.sid.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import otg.sid.entities.appUser;

@RepositoryRestResource
public interface appUserRepository extends JpaRepository<appUser, String> {
    public appUser findByUserName(String userName);

}
