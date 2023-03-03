package se.iths.guessthenumbergame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    int secretNumber;
    List<String> list = new ArrayList<>();

    public Game() {
        Random random = new Random();
        secretNumber = random.nextInt(1, 10);
    }

    public List<String> getList() {
        return list;
    }

    public String getAnswer(int guess) {

        if (secretNumber > guess) {
            list.add(guess + " too small");
            return "";
        } else if (secretNumber < guess) {
            list.add(guess + " too big");
            return "";
        } else {
            list.clear();
            return "You win! New game";
        }
    }
}
