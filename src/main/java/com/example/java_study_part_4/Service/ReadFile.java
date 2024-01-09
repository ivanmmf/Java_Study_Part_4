package com.example.java_study_part_4.Service;

import com.example.java_study_part_4.Model.Login;
import com.example.java_study_part_4.Model.User;
import com.example.java_study_part_4.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReadFile implements Readable {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReadFile.class);

    private final UserRepository userRepository;

    public ReadFile(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Login> readLogins(String path) {
        List<Login> logins = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line = reader.readLine();
            while (line != null) {
                User user;
                Login login;
                String[] s0 = StringUtils.split(line, "|");
                if (s0 == null) {
                    throw new IllegalStateException("Delimiter not found");
                }
                String fio = s0[1].trim();
                String[] s1 = s0[0].trim().split(",");
                if (s1.length != 3) {
                    throw new IllegalStateException("Invalid data");
                }
                String timeStr = s1[0];
                if (timeStr.isBlank()) {
                    LOGGER.info(path + " " + "user" + " " + s1[1] + " " + "without date");
                    line = reader.readLine();
                    continue;
                }
                Timestamp accessDate = Timestamp.valueOf(timeStr);
                String userName = s1[1];
                String appName = s1[2];

                User searchUser = userRepository.findUserByUsername(userName);
                if (searchUser != null) {
                    user = searchUser;
                } else {

                    user = new User();
                    user.setUsername(userName);
                    user.setFio(fio);
                    userRepository.save(user);
                }

                login = new Login();
                login.setUser(user);
                login.setAccessDate(accessDate);
                login.setApplication(appName);
                logins.add(login);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return logins;

    }
}
