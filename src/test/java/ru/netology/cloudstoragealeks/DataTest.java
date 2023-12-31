package ru.netology.cloudstoragealeks;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.userdetails.UserDetails;
import ru.netology.cloudstoragealeks.entity.CloudFile;
import ru.netology.cloudstoragealeks.entity.Role;
import ru.netology.cloudstoragealeks.entity.User;
import ru.netology.cloudstoragealeks.config.security.Roles;
import ru.netology.cloudstoragealeks.dto.request.AuthRequest;
import ru.netology.cloudstoragealeks.dto.response.AuthResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataTest {

    public static final String USERNAME_1 = "admin@gmail.com";
    public static final String USERNAME_2 = "user@gmail.com";
    public static final String USER_UNAUTHORIZED = "unauthorized@gmail.com";
    public static final String PASSWORD = "100";
    public static final Long USER_ID = 1L;
    public static final String BEARER_TOKEN = "Bearer Token";
    public static final String FILENAME_1 = "file";
    public static final String FILENAME_2 = "file2";
    public static final String FILENAME_NEW = "file_new";
    public static final String TOKEN_1 = "token1";
    public static final String TOKEN_2 = "token2";
    public static final Set<Role> ROLE_SET = new HashSet<>(List.of(new Role(Roles.ROLE_ADMIN)));
    public static final UserDetails USER_DETAILS = new User(USER_ID, USERNAME_1, PASSWORD, ROLE_SET);
    public static final AuthResponse RESPONSE = new AuthResponse(TOKEN_1);
    public static final AuthRequest REQUEST = new AuthRequest(USERNAME_1, PASSWORD);
    public static final MockMultipartFile MULTIPART_FILE = new MockMultipartFile(
            FILENAME_1,
            FILENAME_1.getBytes()
    );
    public static CloudFile TEST_FILE_1 = null;
    public static CloudFile TEST_FILE_2 = null;

    static {
        try {
            TEST_FILE_1 = CloudFile.builder()
                    .filename(FILENAME_1).date(LocalDateTime.now()).type(MULTIPART_FILE.getContentType()).fileData(MULTIPART_FILE.getBytes()).size(MULTIPART_FILE.getSize()).userId(USER_ID).build();
            TEST_FILE_2 = CloudFile.builder()
                    .filename(FILENAME_2).date(LocalDateTime.now()).type(MULTIPART_FILE.getContentType()).fileData(MULTIPART_FILE.getBytes()).size(MULTIPART_FILE.getSize()).userId(USER_ID).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CloudFile FILE_EDIT_NAME = CloudFile.builder()
            .filename(FILENAME_NEW).date(TEST_FILE_1.getDate()).type(TEST_FILE_1.getType()).fileData(TEST_FILE_1.getFileData()).size(TEST_FILE_1.getSize()).userId(TEST_FILE_1.getUserId()).build();

    public static final List<CloudFile> CLOUD_FILES = List.of(
            TEST_FILE_1,
            TEST_FILE_2
    );
}

