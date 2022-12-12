package com.example.demo.games;



import com.example.demo.appUser.AppUser;
import com.example.demo.appUser.AppUserService;
import com.example.demo.appUser.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.internal.util.MockUtil.createMock;

@ExtendWith(MockitoExtension.class)
class GamesServiceTest {
    @InjectMocks
    private GamesController gamesController;
    @Mock
    private  GamesService gamesService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private TestRestTemplate restTemplate;
    @Mock
    private AppUserService appUserService;

    @Test
    void playerWins() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        GamesClass rockpaper= new GamesClass("rock","paper");
        AppUser appUser = new AppUser("Sohayla","khaled","sohayla");
        when(appUserService.getUser()).thenReturn(appUser);
        gamesService.playerWins("rock","paper");
        ResponseEntity<Object> responseEntity = gamesController.registerNewUser(rockpaper);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("");
    }

    @Test
    void calculateDamage() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        firewater fireWater = new firewater("fire","electric",100,22);
  //      AppUser appUser = new AppUser("Sohayla","khaled","sohayla");
//        when(appUserService.getUser()).thenReturn(appUser);
        gamesService.calculateDamage("fire","electric",100,22);
        ResponseEntity<Object> responseEntity = gamesController.warergrass(fireWater);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("");
    }

    @Test
    void rps() {
    }

    @Test
    void towerHanoi() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        moveGame  towerOfHanoiGame = new moveGame(3.0);
        AppUser appUser = new AppUser("Sohayla","khaled","sohayla");
        when(appUserService.getUser()).thenReturn(appUser);
        gamesService.towerHanoi(3.0);
        ResponseEntity<Object> responseEntity = gamesController.Number(towerOfHanoiGame);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("");

    }

    @Test
    void diceScore() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        List<Integer> list= new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(5);
        list.add(1);
        Dice dice = new Dice(list);
        AppUser appUser = new AppUser("Sohayla","khaled","sohayla");
        when(appUserService.getUser()).thenReturn(appUser);
        gamesService.calculateDamage("fire","electric",100,22);
        ResponseEntity<Object> responseEntity = gamesController.Dice(dice);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("");


    }
}