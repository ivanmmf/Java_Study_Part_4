package com.example.java_study_part_4.Service;
import com.example.java_study_part_4.Model.Login;
import com.example.java_study_part_4.Model.User;

import java.io.FileNotFoundException;
import java.util.List;

public interface LoginService {
    void save(String path) throws FileNotFoundException;
}
