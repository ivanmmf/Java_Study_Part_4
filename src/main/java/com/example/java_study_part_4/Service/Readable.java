package com.example.java_study_part_4.Service;
import com.example.java_study_part_4.Model.Login;
import java.io.FileNotFoundException;
import java.util.List;

public interface Readable {

    public List<Login> readLogins(String path) throws FileNotFoundException;
}
