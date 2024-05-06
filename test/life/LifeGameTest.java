package life;

import org.junit.Assert;
import org.junit.Test;

import java.awt.Point;

/**
 * Tests for game of life
 */
public class LifeGameTest {

    @Test
    public void testGlider() {
        LifeGame game = new LifeGame(200, 200);
        game.setValue(2, 3, true);
        game.setValue(3, 1, true);
        game.setValue(3, 3, true);
        game.setValue(4, 2, true);
        game.setValue(4, 3, true);

        // generation 1
        game.next();
        Point[] expected = new Point[]{
                new Point(2, 2),
                new Point(3, 3),
                new Point(3, 4),
                new Point(4, 2),
                new Point(4, 3),
        };
        Assert.assertArrayEquals("Wrong output", expected, game.getAliveCells().toArray());

        // generation 2
        game.next();
        expected = new Point[]{
                new Point(2, 3),
                new Point(3, 4),
                new Point(4, 2),
                new Point(4, 3),
                new Point(4, 4),
        };
        Assert.assertArrayEquals("Wrong output", expected, game.getAliveCells().toArray());

        // generation 3
        game.next();
        expected = new Point[]{
                new Point(3, 2),
                new Point(3, 4),
                new Point(4, 3),
                new Point(4, 4),
                new Point(5, 3),
        };
        Assert.assertArrayEquals("Wrong output", expected, game.getAliveCells().toArray());

        // generation 4
        game.next();
        expected = new Point[]{
                new Point(3, 4),
                new Point(4, 2),
                new Point(4, 4),
                new Point(5, 3),
                new Point(5, 4),
        };
        Assert.assertArrayEquals("Wrong output", expected, game.getAliveCells().toArray());
    }

    @Test
    public void testSingleCell() {
        LifeGame game = new LifeGame(200, 200);
        game.setValue(1, 1, true);
        game.next();
        Point[] expected = new Point[0];
        Assert.assertArrayEquals("Wrong output", expected, game.getAliveCells().toArray());
    }

    @Test
    public void testBeehive() {
        LifeGame game = new LifeGame(200, 200);
        game.setValue(5, 5, true);
        game.setValue(6, 5, true);
        game.setValue(7, 5, true);
        game.setValue(5, 6, true);
        game.setValue(6, 6, true);
        game.setValue(7, 6, true);
        game.next();
        Point[] expected = new Point[]{
                new Point(5,5),
                new Point(5,6),
                new Point(6,4),
                new Point(6,7),
                new Point(7,5),
                new Point(7,6),
        };
        Assert.assertArrayEquals("Wrong output", expected, game.getAliveCells().toArray());
        game.next();
        Assert.assertArrayEquals("Wrong output", expected, game.getAliveCells().toArray());
    }
}
