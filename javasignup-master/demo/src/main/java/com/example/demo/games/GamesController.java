package com.example.demo.games;


import com.example.demo.appUser.AppUser;
import com.example.demo.appUser.AppUserService;
import com.example.demo.appUser.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "api/v1/games")
public class GamesController {
   private final UserRepository userRepository;
   private final AppUserService appUserService;
   private final GamesService gamesService;
    @Autowired
    public GamesController(UserRepository userRepository, AppUserService appUserService, GamesService gamesService) {
        this.userRepository = userRepository;
        this.appUserService = appUserService;
        this.gamesService = gamesService;
    }

    @PostMapping("rock")
    public ResponseEntity<Object> registerNewUser(@RequestBody GamesClass game){
        AppUser app2=appUserService.getUser();
        app2.setScore(gamesService.rps(game.getPlayer1(), game.getPlayer2()));
        appUserService.saveAppUser(app2);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("").buildAndExpand().toUri();
        return ResponseEntity.created(location).build();

    }
    @PostMapping("Number")
    public ResponseEntity<Object> Number( @RequestBody moveGame game){
        AppUser app=appUserService.getUser();
        Double SCORE=gamesService.towerHanoi(game.getNumber());
       app.setNumberscore( SCORE);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("").buildAndExpand().toUri();
        return ResponseEntity.created(location).build();
    }
    @PostMapping("Dice")
    public ResponseEntity<Object> Dice(@RequestBody Dice game){
        AppUser app=appUserService.getUser();
      Integer score=  gamesService.diceScore(game.getList());
        app.setDiceScore(score );

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("").buildAndExpand().toUri();
        return ResponseEntity.created(location).build();

    }
    @PostMapping("watergras")
    public ResponseEntity<Object> warergrass(@RequestBody firewater game){

        double score= gamesService.calculateDamage(game.getYourType(),game.getOpponentType(),game.getAttack(),game.getDefense());


        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("").buildAndExpand().toUri();
        return ResponseEntity.created(location).build();

    }










}
