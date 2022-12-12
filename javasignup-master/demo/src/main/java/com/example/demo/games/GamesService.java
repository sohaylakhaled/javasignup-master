package com.example.demo.games;

import com.example.demo.appUser.AppUser;
import com.example.demo.appUser.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GamesService {


    private final UserRepository userRepository;

    @Autowired
    public GamesService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    boolean playerWins(String playerMove, String playerMove2) {

        if (playerMove.equals("rock")) {
            return playerMove2.equals("scissors");
        } else if (playerMove.equals("paper")) {
            return playerMove2.equals("rock");
        } else {
            return playerMove2.equals("paper");
        }
    }

//    public Integer score() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String email= auth.getPrincipal().toString();
//        Optional<AppUser> appUser= userRepository.findByEmail(email);
//        appUser.get().setScore(rps());
//        return appUser.get().getScore();
//    }
public static int calculateDamage(String yourType, String opponentType, int attack, int defense){
    int damage;
    double effective = 0;
    if(yourType.equals("fire")&&opponentType.equals("grass")){
        effective=2;
    } else if (yourType.equals("grass")&&opponentType.equals("fire")) {
        effective=0.5;

    } else if (yourType.equals( "fire")&&opponentType.equals("water")||yourType.equals("water")&&opponentType.equals("grass")||
            yourType.equals("water")&&opponentType.equals("electric")) {
        effective=0.5;
    }
    else if (opponentType.equals( "fire")&&yourType.equals("water")||opponentType.equals("water")&&yourType.equals("grass")||
            opponentType.equals("water")&&yourType.equals("electric")) {
        effective=2;
    }
    else if (yourType.equals("fire")&&opponentType.equals("electric")||yourType.equals("grass")&&opponentType.equals("electric")) {
        effective=1;
    }
    else if ((opponentType.equals("fire")&&yourType.equals("electric")||opponentType.equals("grass")&&yourType.equals("electric"))){
        effective=1;
    }

    damage = (int) (50 * (attack / defense) * effective);

    return damage;
}
    public Integer rps(String player1, String player2) {
        Integer score;
        if (player1.equals(player2)) {
            score = 1;
        } else if (playerWins(player1, player2)) {
            score = 2;
        } else {
            score = 0;
        }

        return score;
    }

    public Double towerHanoi(Double discs) {
        return (Math.pow(2, discs) - 1);
    }


    public Integer diceScore(List<Integer> list) {
        int countOne = 0, countTwo = 0, countThree = 0, countFour = 0, countFive = 0, countSix = 0, countextra = 0;
        Integer total = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 1) {
                countOne += 1;
                countextra += 1;
            } else if (list.get(i) == 2) {
                countTwo += 1;

            } else if (list.get(i) == 3) {
                countThree += 1;

            } else if (list.get(i) == 4) {
                countFour += 1;
            } else if (list.get(i) == 5) {
                countFive += 1;
            } else if (list.get(i) == 6) {
                countSix += 1;
            }


        }

        if (countOne >= 3) {
            if (countOne == 3)
                total += 1000;
            else {
                total += 1000;
                int t = countOne - 3;
                for (int i = 0; i < t; i++) {
                    total += 100;
                }
            }
        }
        if (countOne == 1) {
            total += 100;
        }

        if (countTwo >= 3) {
            total += 200;

        }
        if (countThree >= 3) {
            total += 300;


        }

        if (countFour >= 3) {
            total += 400;

        }
        if (countFive == 3) {
            if (countFive == 3)
                total += 500;
            else {
                total += 500;
                int t = countFive - 3;
                for (int i = 0; i < t; i++) {
                    total += 50;
                }
            }

        }
        if (countFive == 1) {
            total += 50;

        }
        if (countSix >= 3) {
            total += 600;

        }
        return total;
    }
}