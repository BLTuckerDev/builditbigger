package dev.bltucker.jokes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class JokeProvider {

    //jokes taken from http://stackoverflow.com/questions/234075/what-is-your-best-programmer-joke
    private static final List<String> jokes =
            new ArrayList<>(Arrays.asList(
                    "There are 10 kinds of people in the world: Those that know binary & those that don't",
                    "Two bytes meet. The first byte asks, “Are you ill?”\n" +
                            "\n" +
                            "The second byte replies, “No, just feeling a bit off.”",
                            "Q: how many programmers does it take to change a light bulb?\n" +
                            "\n" +
                            "A: none, that's a hardware problem",
                    "Keyboard not found ... press F1 to continue",
                            "Q: \"Whats the object-oriented way to become wealthy?\"\n" +
                            "\n" +
                            "A: Inheritance",
                            "Old C programmers don't die, they're just cast into void.",
                    "A SQL query goes into a bar, walks up to two tables and asks, \"Can I join you?\"",
                    "Q: Why don't jokes work in octal?\n" +
                            "\n" +
                            "A: Because 7 10 11.",
                    "If Java is the answer, it must have been a really verbose question.",
                    "When your hammer is C++, everything begins to look like a thumb."
                    ));


    private static final Random random = new Random(System.currentTimeMillis());

    public static String getJoke(){
        return jokes.get(random.nextInt(jokes.size()));
    }

}
