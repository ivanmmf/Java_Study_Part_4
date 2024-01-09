package com.example.java_study_part_4.Repository;
import com.example.java_study_part_4.Model.Login;
import com.example.java_study_part_4.Model.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@ActiveProfiles("test")
public class LoginRepositoryTest {
    @Autowired
    LoginRepository loginRepository;
    @Autowired
    EntityManager entityManager;


    @Test
    void saveAll() {
        User user = User.builder()
                .id(1)
                .username("ivan_mmf")
                .fio("Рыбников Иван Павлович")
                .build();
        Login login = Login.builder()
                .id(1)
                .accessDate(Timestamp.valueOf("2023-02-24 22:22:16"))
                .user(user)
                .application("web")
                .build();
        Login login1 = Login.builder()
                .id(2)
                .accessDate(Timestamp.valueOf("2023-02-24 22:22:18"))
                .user(user)
                .application("web")
                .build();
        List<Login> logins = new ArrayList<>();
        logins.add(login);
        logins.add(login1);
        loginRepository.saveAll(logins);
        List<Login> logins1 = loginRepository.findAll();
        assertThat(logins1.size()).isEqualTo(2);

    }



}

