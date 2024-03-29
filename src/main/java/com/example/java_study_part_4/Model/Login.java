package com.example.java_study_part_4.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "LOGINS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Login implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public User getUser() {
        return user;
    }

    public String getApplication() {
        return application;
    }

    @Column(name = "ACCESS_DATE")
    private Timestamp accessDate;
    @JsonBackReference(value = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @Cascade({org.hibernate.annotations.CascadeType.MERGE})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "APPLICATION")
    private String application;

    public void setAccessDate(Timestamp accessDate) {
        this.accessDate = accessDate;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", accessDate=" + accessDate +
                ", user=" + user +
                ", application='" + application + '\'' +
                '}';
    }
}
