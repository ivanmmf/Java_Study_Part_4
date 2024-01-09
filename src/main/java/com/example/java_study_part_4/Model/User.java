package com.example.java_study_part_4.Model;

import jakarta.persistence.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "USERS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "USERNAME",unique = true)
    private String username;

    @Column(name = "FIO")
    private String fio;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getFio() {
        return fio;
    }
}