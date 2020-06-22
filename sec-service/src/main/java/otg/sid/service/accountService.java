package otg.sid.service;

import otg.sid.entities.appRole;
import otg.sid.entities.appUser;

public interface accountService {
    public appUser saveUser(String userName, String password, String confirmedPassword);
    public appRole save(appRole role);
    public appUser loadUSerByUsername(String userName);
    public void addRoleToUser(String username, String role);


}
