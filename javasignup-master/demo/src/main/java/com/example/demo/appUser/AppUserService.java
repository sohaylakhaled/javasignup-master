package com.example.demo.appUser;

import com.example.demo.registration.token.ConfirmationToken;
import com.example.demo.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;


@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
    @Autowired
   private final UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ConfirmationTokenService confirmationTokenService;
   private final static String USER_NOT_FOUND_MSG="user with email %s not found";

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).
                orElseThrow(()->new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,email)));
    }
    public String signUp(AppUser appUser){

      boolean userExsit= userRepository.findByEmail(appUser.getEmail()).isPresent();
      if(userExsit){
          throw new IllegalStateException("email already taken");

      }
       String encodedPassword=  bCryptPasswordEncoder.encode(appUser.getPassword());

      appUser.setPassword(encodedPassword);
      userRepository.save(appUser);
      String token =  UUID.randomUUID().toString();
      ConfirmationToken confirmationToken = new ConfirmationToken(
              token, LocalDateTime.now(),
              LocalDateTime.now().plusMinutes(15),appUser);
      confirmationTokenService.saveCofirmationtoken(confirmationToken);
        return token;

    }
    public int enableAppUser(String email) {
        return userRepository.enableAppUser(email);
    }
    public AppUser getUser(){
        AppUser app=new AppUser();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(Objects.nonNull(auth.getPrincipal()) && auth.getPrincipal() instanceof AppUser) {
             app = (AppUser) auth.getPrincipal();}
        return app;
    }
    public void saveAppUser(AppUser user){
        userRepository.save(user);
    }
}
