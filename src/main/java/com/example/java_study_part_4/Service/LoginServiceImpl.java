package com.example.java_study_part_4.Service;

import com.example.java_study_part_4.Model.Login;
import com.example.java_study_part_4.Repository.LoginRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {
    private final LoginRepository repository;
    private final UpdateFile updateFile;

    public LoginServiceImpl(LoginRepository repository, UpdateFile updateFile) {
        this.repository = repository;
        this.updateFile = updateFile;
    }

    @Override
    @Transactional
    public void save(String path) {
        List<Login> logins = updateFile.update(path);
        repository.saveAll(logins);
    }
}
