package com.example.demo;

import com.example.demo.appUser.AppUser;
import com.example.demo.appUser.AppUserRole;
import com.example.demo.appUser.AppUserService;
import com.example.demo.appUser.UserRepository;
import com.example.demo.games.GamesService;
import com.example.demo.registration.EmailValidation;
import com.example.demo.registration.RegistrationRequest;
import com.example.demo.registration.RegistrationService;

import com.example.demo.registration.token.ConfirmationToken;
import com.example.demo.registration.token.ConfirmationTokenService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


public class testService {

    @Mock
    private EmailValidation emailValidation;

    @Mock
    private UserRepository userRepository;
    private AutoCloseable autoCloseable;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    private GamesService gamesService;
    @InjectMocks

    private AppUserService appUserService;
    @Mock
    private ConfirmationTokenService confirmationTokenService;

    @AfterEach
    void testDown() throws Exception {
        autoCloseable.close();
    }

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        this.gamesService = new GamesService(this.userRepository);
    }

    @Test
    void testTower() {
        double value = gamesService.towerHanoi(3.0);
        Assertions.assertEquals(7.0, value);
    }

    @Test
    void rps() {
        double value = gamesService.rps("paper", "rock");
        Assertions.assertEquals(value, 2);
    }

    @Test
    void diceScore() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(5);
        list.add(1);
        double value = gamesService.diceScore(list);
        Assertions.assertEquals(1150, value);
    }

    @Test
    void calculateDamage() {
        Integer value = gamesService.calculateDamage("fire", "electric", 10, 2);
        Assertions.assertEquals(250, value);
    }

    @Test
    void registerTest() {
        AppUser appUser = new AppUser();
        appUser.setFirstname("sohayla");
        appUser.setLastname("khaled");
        appUser.setEmail("sohayla@gmail.com");
        appUser.setPassword("password");
        appUser.setAppUserRole(AppUserRole.USER);
        ConfirmationToken confirmationToken = new ConfirmationToken();
//        when(userRepository.findByEmail("Email")).thenReturn(Optional.of(appUser));
        when(bCryptPasswordEncoder.encode(appUser.getPassword())).thenReturn("hi");
        when(userRepository.save(appUser)).thenReturn(appUser);
//        when(UUID.randomUUID()).thenReturn(uuid);
        doNothing().when(confirmationTokenService).saveCofirmationtoken(confirmationToken);

        String token = appUserService.signUp(appUser);
        String[] parts = token.split("-");
        Assertions.assertTrue(parts.length == 5);


    }

    @Test
    void LoadUserByName() {
        AppUser appUser = new AppUser();
        appUser.setEmail("hi@gmail.com");
        when(userRepository.findByEmail(appUser.getEmail())).thenReturn(Optional.of(appUser));
        AppUser appUser1;
        appUser1 = (AppUser) appUserService.loadUserByUsername(appUser.getEmail());
        Assertions.assertEquals(appUser, appUser1);
    }

}