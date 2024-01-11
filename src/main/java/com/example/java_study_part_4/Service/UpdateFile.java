package com.example.java_study_part_4.Service;

import com.example.java_study_part_4.Enums.Application;
import com.example.java_study_part_4.Model.Login;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UpdateFile implements Updatable {

    private final ReadFile readFile;

    public String toFirstCharUpperAll(String string) {
        StringBuilder sb = new StringBuilder(string);
        for (int i = 0; i < sb.length(); i++) {
            if (i == 0 || sb.charAt(i - 1) == ' ') {//first letter to uppercase by default
                sb.setCharAt(i, Character.toUpperCase(sb.charAt(i)));
            }
        }
        return sb.toString();
    }

    public Boolean checkEnum(String matchValue) {
        boolean checkEnum = false;
        for (Application application : Application.values()) {
            if (application.name().equals(matchValue)) {
                checkEnum = true;
                break;
            }
        }
        return checkEnum;
    }

    public UpdateFile(ReadFile readFile) {
        this.readFile = readFile;
    }

    @Override
    public List<Login> update(String path) {
        List<Login> trueLogin = new ArrayList<>();
        List<Login> logins = readFile.readLogins(path);
        for (Login login : logins) {

            String fio = toFirstCharUpperAll(login.getUser().getFio());
            login.getUser().setFio(fio);

            if (!checkEnum(login.getApplication())) {
                login.setApplication("other");
            }
            trueLogin.add(login);
        }
        return trueLogin;
    }
}
