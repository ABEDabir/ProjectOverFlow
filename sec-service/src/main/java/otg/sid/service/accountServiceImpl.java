package otg.sid.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otg.sid.dao.appRoleRepository;
import otg.sid.dao.appUserRepository;
import otg.sid.entities.appRole;
import otg.sid.entities.appUser;

@Service
@Transactional
public class accountServiceImpl implements accountService {

    private appUserRepository appUserRepository;
    private appRoleRepository appRoleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public accountServiceImpl(otg.sid.dao.appUserRepository appUserRepository,
                              otg.sid.dao.appRoleRepository appRoleRepository, BCryptPasswordEncoder bCryptPasswordEncoder)
                               {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
                               }

    @Override
    public appUser saveUser(String userName, String password, String confirmedPassword) {
        appUser user = appUserRepository.findByUserName(userName);
        if(user!=null) throw new RuntimeException("User already exists");
        if(!password.equals(confirmedPassword)) throw new RuntimeException("You must enter matching passwords");
        appUser appUser = new appUser();
        appUser.setUserName(userName);
        appUser.setActivated(true);
        appUser.setPassword(bCryptPasswordEncoder.encode(password));
        appUserRepository.save(appUser);
        addRoleToUser(userName,"USER");
        return appUser;
    }

    @Override
    public appRole save(appRole role) {
        return appRoleRepository.save(role);
    }

    @Override
    public appUser loadUSerByUsername(String userName) {

        return appUserRepository.findByUserName(userName);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        appUser appUser = appUserRepository.findByUserName(userName);
        appRole appRole = appRoleRepository.findByRoleName(roleName);
        appUser.getRoles().add(appRole);
    }
}
