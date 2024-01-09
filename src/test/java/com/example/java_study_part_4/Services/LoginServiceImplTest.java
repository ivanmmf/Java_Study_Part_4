package com.example.java_study_part_4.Services;

import com.example.java_study_part_4.Model.Login;
import com.example.java_study_part_4.Model.User;
import com.example.java_study_part_4.Repository.LoginRepository;
import com.example.java_study_part_4.Service.LoginServiceImpl;
import com.example.java_study_part_4.Service.UpdateFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class LoginServiceImplTest {
    private LoginRepository loginRepository;

    private UpdateFile updateFile;

    private LoginServiceImpl service;

    @BeforeEach
    void setUp() {
        loginRepository = mock(LoginRepository.class);
        updateFile = mock(UpdateFile.class);
        service = new LoginServiceImpl(loginRepository,updateFile);

    }

    @Test
    void save()  {
        User user = User.builder()
                .id(1)
                .username("ivan_mmf")
                .fio("Рыбников Иван Павлович")
                .build();
        Login login = Login.builder()
                .id(1)
                .accessDate(Timestamp.valueOf("2023-02-24 22:22:16"))
                .user(user)
                .application("web")
                .build();

        given(updateFile.update("123")).willReturn(List.of(login));
        List<Login> logins = updateFile.update("123");
        when(loginRepository.saveAll(logins)).thenReturn(List.of(login));
        service.save("123");
        verify(loginRepository).saveAll(logins);
    }

}