package com.example.java_study_part_4.Services;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.example.java_study_part_4.MemoryAppender;
import com.example.java_study_part_4.Model.Login;
import com.example.java_study_part_4.Repository.UserRepository;
import com.example.java_study_part_4.Service.ReadFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;



public class ReadFileTest {
    private UserRepository userRepository;



    private ReadFile readFile;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        readFile = new ReadFile(userRepository);
    }
    @Test
    void readLoginsCreateLogin()  {
        String path = "./TestFile";
        List<Login> logins = readFile.readLogins(path);
        assertThat(logins.get(0).getUser().getFio()).isEqualTo("Rybnikov Ivan Pavlovich");
    }

    @Test
    void readLoginsEmptyAccessDate()  {
        String path = "./TestFile";
        List<Login> logins = readFile.readLogins(path);
        assertThat(logins.size()).isEqualTo(3);
    }

    @Test
    void readLoginsEmptyAccessDateLog()  {
        Logger logger = (Logger) LoggerFactory.getLogger(ReadFile.class);
        MemoryAppender memoryAppender = new MemoryAppender();
        memoryAppender.setContext((LoggerContext) LoggerFactory.getILoggerFactory());
        logger.setLevel(Level.INFO);
        logger.addAppender(memoryAppender);
        memoryAppender.start();

        String path = "./TestFile";
        readFile.readLogins(path);
        assertThat(memoryAppender.search(path, Level.INFO).size()).isEqualTo(1);
    }


}
