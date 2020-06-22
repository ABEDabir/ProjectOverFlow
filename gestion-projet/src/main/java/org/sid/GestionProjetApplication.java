package org.sid;

import org.sid.dao.ProjetRepository;
import org.sid.dao.RealisedRepository;
import org.sid.dao.TacheRepository;
import org.sid.entities.Projet;
import org.sid.entities.Realised;
import org.sid.entities.Tache;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

@SpringBootApplication
public class GestionProjetApplication {

    public static void main(String[] args) {

        SpringApplication.run(GestionProjetApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProjetRepository projetRepository, TacheRepository tacheRepository, RealisedRepository realisedRepository){
        return args -> {
            projetRepository.deleteAll();
            for (String c : Arrays.asList("1 Android desc1", "2 J2ee desc2", "3 Agile desc3")) {
                projetRepository.save(new Projet(c.split(" ")[0], c.split(" ")[1], c.split(" ")[2], new ArrayList<>()));
            }

            tacheRepository.deleteAll();
            Projet p1 = projetRepository.findById("1").get();

            Stream.of("taches taches taches tahces", "T2", "T3").forEach(name->{
                Tache t=tacheRepository.save(new Tache(null,name,name,name,p1,new ArrayList<>()));
                p1.getTaches().add(t);
                projetRepository.save(p1);
            });

            Projet p2 = projetRepository.findById("2").get();

            Stream.of("T4", "T5").forEach(name->{
                Tache t = tacheRepository.save(new Tache(name,name,name,name,p2,new ArrayList<>()));
                p2.getTaches().add(t);
                projetRepository.save(p2);

            });


            realisedRepository.deleteAll();
            Tache tt = tacheRepository.findById("T4").get();

            Stream.of("R1","R2").forEach(name->{
                Realised r = realisedRepository.save(new Realised(null,name,name,tt));
                tt.getRealised().add(r);
                tacheRepository.save(tt);
            });


        };

    }
}
