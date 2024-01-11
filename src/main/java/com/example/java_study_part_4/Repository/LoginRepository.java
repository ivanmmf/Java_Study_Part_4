package com.example.java_study_part_4.Repository;
import com.example.java_study_part_4.Model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {
}

