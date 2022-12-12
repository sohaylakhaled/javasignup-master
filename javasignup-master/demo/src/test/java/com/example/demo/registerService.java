package com.example.demo;

import com.example.demo.appUser.AppUser;
import com.example.demo.appUser.AppUserRole;
import com.example.demo.appUser.AppUserService;
import com.example.demo.registration.EmailValidation;
import com.example.demo.registration.RegistrationRequest;
import com.example.demo.registration.RegistrationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class registerService {
    @InjectMocks
    RegistrationService registrationService;
    @Mock
    private EmailValidation emailValidation;
    @Mock
    AppUserService appUserService;

    @Test
    void register(){
        AppUser appUser=new AppUser();
        appUser.setFirstname("marwan");
        appUser.setLastname("sayed");
        appUser.setEmail("marwan@gmail.com");
        appUser.setPassword("password");
        appUser.setAppUserRole(AppUserRole.USER);

        //  when
        when(emailValidation.test(appUser.getEmail())).thenReturn(true);
        when(appUserService.signUp(appUser)).thenReturn("token");

        String real=registrationService.register(
                new RegistrationRequest("marwan","sayed","marwan@gmail.com","password"));

        assertEquals("token",real);
    }
}
