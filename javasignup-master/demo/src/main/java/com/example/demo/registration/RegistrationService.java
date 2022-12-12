package com.example.demo.registration;

import com.example.demo.appUser.AppUser;
import com.example.demo.appUser.AppUserRole;
import com.example.demo.appUser.AppUserService;
import com.example.demo.registration.token.ConfirmationToken;
import com.example.demo.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {
    @Autowired
    ConfirmationTokenService confirmationTokenService;
    @Autowired
    private EmailValidation emailValidation;
    @Autowired

    private AppUserService appUserService;
    public String register(RegistrationRequest request) {
      boolean isValidemail= emailValidation.test(request.getEmail());
      if(!isValidemail){
          throw new IllegalStateException("email not valid");
      }

     String token=   appUserService.signUp(
                new AppUser(
                        request.getEmail(),
                        request.getFirstName(),
                        request.getLastName(),
                        request.getPassword(),
                        AppUserRole.USER

                )
        );
      return token;
    }
    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiredAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(
                confirmationToken.getAppUser().getEmail());
        return "confirmed";
    }
}
