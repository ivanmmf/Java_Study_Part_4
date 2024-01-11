package com.example.java_study_part_4.Service;

import com.example.java_study_part_4.Model.Login;

import java.io.FileNotFoundException;
import java.util.List;

public interface Updatable {
    List<Login> update(String path) throws FileNotFoundException;
}
