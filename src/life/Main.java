package life;

import java.awt.*;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This program reads an initial state for the game of life from the std input and outputs first 100 generations into the std output.
 * Both input and output specify the locations of the alive cells as in the following example:
 * {@code [[5,5],[6,5],[7,5],[5,6],[6,6],[7,6]]}
 */
public class Main {
    /** Width of the game grid */
    public static final int GAME_WIDTH = 200;

    /** Height of the game grid */
    public static final int GAME_HEIGHT = 200;

    /** How many generations to output */
    public static final int GENERATIONS = 100;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please specify initial locations of the alive cells, for example:");
            System.out.println("[[5,5],[6,5],[7,5],[5,6],[6,6],[7,6]]");
            System.exit(0);
        } else {
            executeGame(args[0], System.out, GENERATIONS);
        }
    }

    /**
     * For a given input, output the specified number of game of life generations
     *
     * @param input       where to read the initial state of the game from
     * @param out         where to write the output to
     * @param generations how many generations to output
     */
    private static void executeGame(String input, PrintStream out, int generations) {
        LifeGame game = new LifeGame(GAME_WIDTH, GAME_HEIGHT);

        // remove first [ and last ]
        int first = input.indexOf('[');
        int last = input.lastIndexOf(']');
        String aliveCells = input.substring(first + 1, last);

        // now we have comma separated list of coordinates of alive cells, parse them using regex
        Pattern pattern = Pattern.compile("\\[(\\s*)(\\d+)(\\s*),(\\s*)(\\d+)(\\s*)]");
        Matcher matcher = pattern.matcher(aliveCells);
        while (matcher.find()) {
            int row = Integer.parseInt(matcher.group(2));
            int col = Integer.parseInt(matcher.group(5));
            game.setValue(row, col, true);
        }

        while (generations > 0) {
            game.next();
            print(out, game.getAliveCells());
            generations--;
        }
    }

    /**
     * Print cell coordinates to the specified stream
     *
     * @param out   stream for output
     * @param cells list of coordinates to print
     */
    private static void print(PrintStream out, ArrayList<Point> cells) {
        boolean printComma = false;
        out.print("[");
        for (Point cell : cells) {
            if (printComma) out.print(",");
            out.print("[");
            out.print(cell.x);
            out.print(",");
            out.print(cell.y);
            out.print("]");
            printComma = true;
        }
        out.println("]");
    }
}
