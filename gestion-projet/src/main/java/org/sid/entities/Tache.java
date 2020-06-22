package org.sid.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;
@Document
@Data @AllArgsConstructor @NoArgsConstructor
public class Tache {
    @Id
    private String id;
    private String name;
    private String duree;
    private String outils;
    @DBRef
    private Projet projet;
    @DBRef
    private Collection<Realised> realised = new ArrayList<>();

    @Override
    public String toString() {
        return "Tache{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", duree='" + duree + '\'' +
                ", outils='" + outils + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getOutils() {
        return outils;
    }

    public void setOutils(String outils) {
        this.outils = outils;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public Collection<Realised> getRealised() {
        return realised;
    }

    public void setRealised(Collection<Realised> realised) {
        this.realised = realised;
    }
}
