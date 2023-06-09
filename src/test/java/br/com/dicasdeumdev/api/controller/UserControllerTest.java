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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

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
        startUser();
    }

    @Test
    void whenFindByIdThenReturnSucess() {
        //Quando o  service findById receber qauqluer Long retorne um usario
        when(service.findById(anyLong())).thenReturn(user);
        //Quando o modelmapper fizer o mapeamento de any para any retorne um usuarioDTO
        when(modelMapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = controller.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());

        //Garanta que o response seja um ResponseEntity
        assertEquals(ResponseEntity.class, response.getClass());
        //Garanta que o Body seja um user body
        assertEquals(UserDTO.class, Objects.requireNonNull(response.getBody()).getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(PASSWORD, response.getBody().getPassword());
        assertEquals(EMAIL, response.getBody().getEmail());
    }

    @Test
    void whenFindAllThenReturnAtListDTO() {
        //Quando encontrar um usuario retorne uma lista de usuarios
        //Podemos usar o Collections
        when(service.findAll()).thenReturn(List.of(user));
        when(modelMapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<List<UserDTO>> response = controller.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        //assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(UserDTO.class, Objects.requireNonNull(response.getBody().get(INDEX)).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(NAME, response.getBody().get(INDEX).getName());
        assertEquals(PASSWORD, response.getBody().get(INDEX).getPassword());
        assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
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