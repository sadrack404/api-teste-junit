package br.com.dicasdeumdev.api.controller;

import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.domain.dto.UserDTO;
import br.com.dicasdeumdev.api.services.impl.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserControllerTest {

    public static final Long ID = 1L;
    public static final String NAME = "Valdir";
    public static final String EMAIL = "email@gmail.com";
    public static final String PASSWORD = "123456";

    public static final String EMAIL_JA_CADASTRADO = "Email já cadastrado";
    public static final String USUARIO_COM_O_ID_NAO_ENCONTRADO = "Usuario com o ID nao encontrado!";
    public static final int INDEX = 0;
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";

    private User user;
    private UserDTO userDTO;
    @InjectMocks
    private UserController controller;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserService service;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
    }
}