package com.example.java_study_part_4.Services;

import com.example.java_study_part_4.Model.Login;
import com.example.java_study_part_4.Model.User;
import com.example.java_study_part_4.Service.ReadFile;
import com.example.java_study_part_4.Service.UpdateFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Timestamp;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class UpdateFileTest {
    private ReadFile readFile;
    private UpdateFile updateFile;

    @BeforeEach
    void setUp() {
        readFile = Mockito.mock(ReadFile.class);
        updateFile = new UpdateFile(readFile);
    }

    @Test
    void updateChangeFIO() {
        User user = User.builder()
                .id(1)
                .username("ivan_mmf")
                .fio("рыбников иван павлович")
                .build();
        Login login = Login.builder()
                .id(1)
                .accessDate(Timestamp.valueOf("2023-02-24 22:22:16"))
                .user(user)
                .application("web")
                .build();
        given(readFile.readLogins("123")).willReturn(List.of(login));
        List<Login> logins = updateFile.update("123");
        assertThat(logins.get(0).getUser().getFio()).isEqualTo("Рыбников Иван Павлович");

    }

    @Test
    void updateCorrectFIO() {
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
        given(readFile.readLogins("123")).willReturn(List.of(login));
        List<Login> logins = updateFile.update("123");
        assertThat(logins.get(0).getUser().getFio()).isEqualTo("Рыбников Иван Павлович");

    }

    @Test
    void updateChangeApplication() {
        User user = User.builder()
                .id(1)
                .username("ivan_mmf")
                .fio("Рыбников Иван Павлович")
                .build();
        Login login = Login.builder()
                .id(1)
                .accessDate(Timestamp.valueOf("2023-02-24 22:22:16"))
                .user(user)
                .application("ffdfd")
                .build();
        given(readFile.readLogins("123")).willReturn(List.of(login));
        List<Login> logins = updateFile.update("123");
        assertThat(logins.get(0).getApplication()).isEqualTo("other");
    }

    @Test
    void updateCorrectApplication() {
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
        given(readFile.readLogins("123")).willReturn(List.of(login));
        List<Login> logins = updateFile.update("123");
        assertThat(logins.get(0).getApplication()).isEqualTo("web");
    }
}
