package otg.sid.web;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import otg.sid.entities.appUser;
import otg.sid.service.accountService;

@RestController
public class userController {
    @Autowired
    private accountService accountService;
    @PostMapping("/register")
    public appUser register(@RequestBody userForm userForm){
        return accountService.saveUser(userForm.getUserName(),userForm.getPassword(),userForm.getConfirmedPassword());
    }
}

@Data
class userForm{
    private String userName;
    private String password;
    private String confirmedPassword;

}
