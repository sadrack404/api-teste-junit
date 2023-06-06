package br.com.dicasdeumdev.api.services.impl;

import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.domain.dto.UserDTO;
import br.com.dicasdeumdev.api.repositories.UserRepository;
import br.com.dicasdeumdev.api.services.exceptions.DataIntregityViolationException;
import br.com.dicasdeumdev.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

    public static final Long ID = 1L;
    public static final String NAME = "Valdir";
    public static final String EMAIL = "email@gmail.com";
    public static final String PASSWORD = "123456";

    public static final String EMAIL_JA_CADASTRADO = "Email já cadastrado";
    public static final String USUARIO_COM_O_ID_NAO_ENCONTRADO = "Usuario com o ID nao encontrado!";
    public static final int INDEX = 0;
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
    @InjectMocks
    private UserServiceImpl service;
    @Mock
    private UserRepository repository;
    @Mock
    private ModelMapper modelMapper;
    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        /*Método Mockito ficou estático*/
        when(repository.findById(anyLong())).thenReturn(optionalUser);

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
        when(repository.findById(anyLong())).thenThrow(new ObjectNotFoundException(USUARIO_COM_O_ID_NAO_ENCONTRADO));

        try {
            service.findById(ID);
        } catch (Exception ex) {
            //Asegure que essa classe é da mesma clase que ...
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            //Asegure que a frase será "Usuario com o ID nao encontrado!"
            assertEquals(USUARIO_COM_O_ID_NAO_ENCONTRADO, ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnListOfUsersDTO() {
        when(repository.findAll()).thenReturn(List.of(user));

        List<User> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(user);

        User response = service.create(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());//VERIFICA SE O OBJETO É IGUAL AO ID
        assertEquals(NAME, response.getName());//VERIFICA SE O OBJETO É IGUAL AO NAME
        assertEquals(EMAIL, response.getEmail());//VERIFICA SE O OBJETO É IGUAL AO EMAIL
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenCreateThenReturnAnDataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser.get());

        try {
            optionalUser.get().setId(2L);
            service.create(userDTO);
        } catch (Exception ex) {
            assertEquals(DataIntregityViolationException.class, ex.getClass());
            assertEquals(EMAIL_JA_CADASTRADO, ex.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(user);

        User response = service.update(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());//VERIFICA SE O OBJETO É IGUAL AO ID
        assertEquals(NAME, response.getName());//VERIFICA SE O OBJETO É IGUAL AO NAME
        assertEquals(EMAIL, response.getEmail());//VERIFICA SE O OBJETO É IGUAL AO EMAIL
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenUpdateThenReturnAnDataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser.get());

        try {
            optionalUser.get().setId(2L);
            service.create(userDTO);
        } catch (Exception ex) {
            assertEquals(DataIntregityViolationException.class, ex.getClass());
            assertEquals(EMAIL_JA_CADASTRADO, ex.getMessage());
        }
    }

    @Test
    void deleteWithSucess() {
        when(repository.findById(anyLong())).thenReturn(optionalUser);
        doNothing().when(repository).deleteById(anyLong());
        service.delete(ID);
        verify(repository, times(1)).deleteById(anyLong());
    }

    @Test
    void deleteWithObjectNotFoundExeption() {

        when(repository.findById(anyLong()))
                .thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));

        try {
            service.delete(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        }
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}