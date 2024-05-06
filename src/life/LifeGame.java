package life;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Implementation of the Conway's Game of Life
 */
public class LifeGame {
    /** Alive cell with this many neighbours will stay alive */
    public static final int NEIGHBOURS_LIVE = 2;
    /** Alive cell with this many neighbours will stay alive, dead cell will become live */
    public static final int NEIGHBOURS_BORN = 3;
    /** Width of the game grid */
    private final int width;
    /** Height of the game grid */
    private final int height;
    /** Current state of the game */
    private boolean[][] cells;
    /** Previous state of the game */
    private boolean[][] prevCells;

    /**
     * Create game of life for the given width and height
     * @param width  width of the game grid
     * @param height height of the game grid
     */
    public LifeGame(int width, int height) {
        // we will add one row and column to the game grid, which will never be populated with live cells to simplify calculations of the cells on the edges
        this.width = width + 2;
        this.height = height + 2;
        // initialise arrays
        cells = new boolean[this.height][];
        prevCells = new boolean[this.height][];
        for (int i = 0; i < this.height; i++) {
            cells[i] = new boolean[this.width];
            prevCells[i] = new boolean[this.width];
        }
    }

    /**
     * Set initial value for the specified row and column of the game grid
     * @param row       row, first row is number 1
     * @param column    column, first column is number 1
     * @param isAlive   true means cell is alive, false means dead
     */
    public void setValue(int row, int column, boolean isAlive){
        if((row < 1) || (row > height - 2)) throw new IllegalArgumentException("Row must be between 1 and " + (height - 2));
        if((column < 1) || (column > width - 2)) throw new IllegalArgumentException("Column must be between 1 and " + (width - 2));
        cells[row+1][column+1] = isAlive;
    }

    /**
     * Calculate the next generation of the game
     */
    public void next(){
        boolean[][] currentCells = cells;

        // will reuse this array, to avoid memory allocation on every generation
        boolean[][] newCells = prevCells;

        for (int column = 1; column < width - 2; column++) {
            for (int row = 1; row < height - 2; row++){
                int neighbours = 0;
                if (currentCells[row+1][column-1]) neighbours++;
                if (currentCells[row+1][column  ]) neighbours++;
                if (currentCells[row+1][column+1]) neighbours++;
                if (currentCells[row-1][column-1]) neighbours++;
                if (currentCells[row-1][column  ]) neighbours++;
                if (currentCells[row-1][column+1]) neighbours++;
                if (currentCells[row  ][column-1]) neighbours++;
                if (currentCells[row  ][column+1]) neighbours++;
                if(neighbours == NEIGHBOURS_BORN) {
                    // new cell is born, or existing one stay alive
                    newCells[row][column] = true;
                }
                else if(neighbours == NEIGHBOURS_LIVE) {
                    // existing cell stay alive
                    newCells[row][column] = currentCells[row][column];
                }
                else {
                    // cell die
                    newCells[row][column] = false;
                }
            }
        }

        prevCells = currentCells;
        cells = newCells;
    }

    /**
     * This method returns the coordinates of all alive cells.
     * @return all alive cells
     */
    public ArrayList<Point> getAliveCells() {
        ArrayList<Point> alive = new ArrayList<>();
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                if(cells[row][col]){
                    alive.add(new Point(row-1, col-1));
                }
            }
        }
        return alive;
    }
}
