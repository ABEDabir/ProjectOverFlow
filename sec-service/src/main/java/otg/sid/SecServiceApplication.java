package otg.sid;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import otg.sid.entities.appRole;
import otg.sid.service.accountService;

import java.util.stream.Stream;

@SpringBootApplication
public class SecServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(SecServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(accountService accountService){
        return  args -> {
            accountService.save(new appRole(null,"USER"));
            accountService.save(new appRole(null,"ADMIN"));
            Stream.of("user1", "user2", "user3","admin").forEach(un->{
                accountService.saveUser(un,"1234","1234");
            });
            accountService.addRoleToUser("admin","ADMIN");
        };

    }

    @Bean
    BCryptPasswordEncoder getBCPE(){
        return new BCryptPasswordEncoder();
    }

}
