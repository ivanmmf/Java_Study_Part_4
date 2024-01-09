package com.example.java_study_part_4.Repository;

import com.example.java_study_part_4.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);
    User save(User user);

}

