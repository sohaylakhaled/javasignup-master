package com.example.demo.appUser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table
public class AppUser implements UserDetails {
    @Id
    @SequenceGenerator(
            name = "user_sequence",sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue( strategy = GenerationType.SEQUENCE,
            generator = "user_sequence")
    private Long id;
    private String email;
    private String firstname;
    private String lastname;
    private String password;

    public AppUser(String firstname, String lastname, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
    }

    public Integer getPlay2Score() {
        return play2Score;
    }

    public void setPlay2Score(Integer play2Score) {
        this.play2Score = play2Score;
    }

    private Integer play2Score;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score =score;
    }

    public Integer getDiceScore() {
        return DiceScore;
    }

    public void setDiceScore(Integer diceScore) {
        DiceScore = diceScore;
    }

    private Integer DiceScore;
private Double numberscore;
    private Integer score;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    private Boolean locked=false;
    private Boolean enabled=false;


    public AppUser(String email, String firstname, String lastname, String password, AppUserRole appUserRole) {
        this.email = email;
        this. firstname= firstname;
        this.lastname = lastname;
        this.password = password;
        this.appUserRole = appUserRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority= new SimpleGrantedAuthority(appUserRole.name());


        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
