package se.iths.guessthenumbergame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    int secretNumber;
    List<String> list = new ArrayList<>();

    public Game() {
        Random random = new Random();
        secretNumber = random.nextInt(1, 100);
    }

    public List<String> getList() {
        return list;
    }

    public String getAnswer(int guess) {
        if (secretNumber > guess) {
            list.add(guess + ": too small");
            return "Too small";
        } else if (secretNumber < guess) {
            list.add(guess + ": too big");
            return "Too big";
        } else {
            list.clear();
            return "You win!";
        }
    }
}
