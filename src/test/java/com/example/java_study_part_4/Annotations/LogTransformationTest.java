package com.example.java_study_part_4.Annotations;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.example.java_study_part_4.MemoryAppender;
import com.example.java_study_part_4.Repository.UserRepository;
import com.example.java_study_part_4.Service.ReadFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
public class LogTransformationTest {


    private ReadFile readFile;

    @BeforeEach
    void setUp() {
        UserRepository userRepository = mock(UserRepository.class);
        readFile = new ReadFile(userRepository);
    }
    @Test
    void LogtransformationLog()  {
        Logger logger = (Logger) LoggerFactory.getLogger(ReadFile.class);
        MemoryAppender memoryAppender = new MemoryAppender();
        memoryAppender.setContext((LoggerContext) LoggerFactory.getILoggerFactory());
        logger.setLevel(Level.INFO);
        logger.addAppender(memoryAppender);
        memoryAppender.start();

        String path = "./TestFile";
        readFile.readLogins(path);
        assertThat(memoryAppender.search(path,Level.INFO).size()).isEqualTo(1);
    }

}
