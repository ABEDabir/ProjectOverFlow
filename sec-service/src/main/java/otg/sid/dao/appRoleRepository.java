package otg.sid.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import otg.sid.entities.appRole;

@RepositoryRestResource
public interface appRoleRepository extends JpaRepository<appRole,String> {
    public appRole findByRoleName(String roleName);
}
