package com.example.java_study_part_4.Repository;
import com.example.java_study_part_4.Model.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    EntityManager entityManager;


    @Test
    void findByUsername() {
        User user = User.builder()
                .username("ivan_mmf")
                .fio("Рыбников Иван Павлович")
                .build();


        entityManager.persist(user);

       User user1 =  userRepository.findUserByUsername("ivan_mmf");

       assertThat(user1.getUsername()).isEqualTo("ivan_mmf");

    }

    @Test
    void save() {
        User user = User.builder()
                .id(1)
                .username("ivan_mmf")
                .fio("Рыбников Иван Павлович")
                .build();


        userRepository.save(user);

        User user1 =  userRepository.findUserByUsername("ivan_mmf");

        assertThat(user1.getId()).isEqualTo(1);

    }



}
