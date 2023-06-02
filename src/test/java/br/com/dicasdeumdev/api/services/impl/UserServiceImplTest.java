package br.com.dicasdeumdev.api.services.impl;

import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.domain.dto.UserDTO;
import br.com.dicasdeumdev.api.repositories.UserRepository;
import br.com.dicasdeumdev.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final long ID = 1L;
    public static final String NAME = "Valdir";
    public static final String EMAIL = "email@gmail.com";
    public static final String PASSWORD = "123456";
    @InjectMocks
    private UserServiceImpl service;
    @Mock
    private UserRepository repository;
    @Mock
    private ModelMapper modelMapper;
    private User user;
    private UserDTO userDTO;
    private Optional<User> optinalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        /*Método Mockito ficou estático*/
        when(repository.findById(anyLong())).thenReturn(optinalUser);

        User response = service.findById(ID);

        assertNotNull(response);//SEMPRE VERIFICA SE O OBJETO É NULL
        /*Método Assertions ficou estático*/
        assertEquals(User.class, response.getClass());//VERIFICA SE O OBJETO É UM OBJETO DE USER
        assertEquals(ID, response.getId());//VERIFICA SE O OBJETO É IGUAL AO ID
        assertEquals(NAME, response.getName());//VERIFICA SE O OBJETO É IGUAL AO NAME
        assertEquals(EMAIL, response.getEmail());//VERIFICA SE O OBJETO É IGUAL AO EMAIL
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        //Quando enontrar um ID qualquer, me retorne a excessão ObjectNotFoundException
        when(repository.findById(anyLong())).thenThrow(new ObjectNotFoundException("Usuario com o ID nao encontrado!"));

        try {
            service.findById(ID);
        } catch (Exception ex) {
            //Asegure que essa classe é da mesma clase que ...
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            //Asegure que a frase será "Usuario com o ID nao encontrado!"
            assertEquals("Usuario com o ID nao encontrado!", ex.getMessage());
        }
    }

    @Test
    void whenFindAllUsersReturnAll() {
    }

    @Test
    void create() {
    }

    @Test
    void findByEmail() {
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
        optinalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }

}