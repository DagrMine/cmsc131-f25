/** Maze class specification

- `Maze(int maxCells)` - pre-allocates array to maximum size

- `getStart()` - finds and returns the cell with Status Start

- `getEnd()` - finds and returns the cell with Status End
    - Consider writing a helper method `getFirstCellWithStatus(Status)` which does linear search

- setupNeighbors() populates the neighbors list of each cell in the grid

 */

package projects.maze;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.experimental.categories.Category;

public class Maze {
    // I'm kind of losing my mind not being able to access grid from the maze so
    // it's public now
    public final Grid grid;

    // Constructors

    public Maze(int maxCells) {
        grid = new Grid(maxCells);
    }

    // Private Methods

    /**
     * Searches for the first cell that has a given status within the maze.
     * 
     * @param findThisStatus
     * @return Returns first Cell found with the requested status.
     *         Returns null on a failure to find a cell with the status.
     */
    private Cell getFirstCellWithStatus(CellStatus findThisStatus) {
        Cell[] cells = grid.getAllCells();
        int max = grid.getArrayLength();
        for (int i = 0; i < max; i++) {
            if (cells[i].getStatus().equals(findThisStatus)) {
                return cells[i];
            }
        }
        return null;
    }

    // Public Methods
    // Accessors / Return Methods

    public Cell getStart() {
        return getFirstCellWithStatus(CellStatus.S);
    }

    public Cell getEnd() {
        return getFirstCellWithStatus(CellStatus.E);
    }

    @Deprecated
    public void insertCell(Cell cell) {
        grid.insertCell(cell);
    }

    @Deprecated
    public int getMazeSize() {
        return grid.getArrayLength();
    }

    public Coords[] getNeighbors(Coords cellCoords) {
        return grid.getCell(cellCoords).getAllNeighbors();
    }

    @Deprecated
    public Cell getCell(Coords coords) {
        return grid.getCell(coords);
    }

    // I had to check the class repo for this because I was under the impression
    // from the name that it was supposed to
    // discover neighbors for each cell in the maze and set them for the grid.
    // Which I have now done anyways soo nevermind.
    /**
     * "Discovers" neighbors and adds them to the cell's data.
     * Exists because I break problems into pieces and for clean code.
     */
    public void discoverAndSetupNeighbors() {
        // Preinitialize variables
        // Many of these are just for cleaner code for my sanity and for readability.
        Cell cell;
        Cell temp;

        // It was easier than validating the same thing four times
        final Coords[] direction = new Coords[4];

        for (int i = 0; i < grid.getArrayLength(); i++) {

            // retrieve cell to setup
            cell = grid.getCellAtIndex(i);

            // update first cell location
            final int baseRow = cell.getCoords().getRow();
            final int baseCol = cell.getCoords().getCol();

            // update possible neighbor cell locations
            // North
            direction[0] = new Coords(baseRow - 1, baseCol);
            // East
            direction[1] = new Coords(baseRow, baseCol + 1);
            // South
            direction[2] = new Coords(baseRow + 1, baseCol);
            // West
            direction[3] = new Coords(baseRow, baseCol - 1);

            // check if the neighboring cell in question exists within the maze
            for (int j = 0; j < 4; j++) {
                temp = grid.getCell(direction[j]);
                // Wanted to do null validation here instead of at the cell level because I
                // don't need it everywhere.
                if (temp != null) {
                    if (temp.getStatus() != (null)) {
                        cell.setNeighbor(direction[j], j);
                    }
                }
            }
        }
    }

    /**
     * Writes the maze being called to a given filename.
     * Provided by Dusel. Assumes grid cell has a getStatus() method.
     * 
     * @param filename - Output filename.
     */
    public void serialize(String filename) {
        Cell[] cells = grid.getAllCells();

        FileWriter writer;
        try {
            writer = new FileWriter(new File(filename));
            // discover dimension of maze's underlying grid
            int maxRow = 0, maxCol = 0;
            int idxCell;
            for (idxCell = 0; idxCell < cells.length; idxCell++) {
                int row = cells[idxCell].getCoords().getRow();
                int col = cells[idxCell].getCoords().getCol();
                if (row > maxRow) {
                    maxRow = row;
                }
                if (col > maxCol) {
                    maxCol = col;
                }
            }

            // write cell statuses, using 'X' for absent (inaccessible) cells
            idxCell = 0;
            for (int row = 0; row <= maxRow; row++) {
                for (int col = 0; col <= maxCol; col++) {
                    Cell maybeCell = grid.getCell(
                            new Coords(row, col));
                    if (maybeCell != null) {
                        writer.write(maybeCell.getStatus().name());
                        idxCell++;
                    } else {
                        writer.write('X');
                    }
                    writer.write(' ');
                }
                writer.write(System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
