package projects.maze;

import java.util.Arrays;

public class Grid {

    private final Cell[] cells;
    private int cellCount;

    public Grid(int maxCells) {
        cells = new Cell[maxCells];
        cellCount = 0;
    }

    public boolean insertCell(Cell cell) {
        if (cellCount < cells.length) {
            cells[cellCount] = cell;
            cellCount++;
            return true;
        }
        return false;
    }

    /**
     * @param vh coordinates to be searched for
     * @return Cell with the given coordinates
     */
    public Cell getCell(Coords vh) {
        for (int idx = 0; idx < cellCount; idx++) {
            if (cells[idx].getCoords().equals(vh)) {
                return cells[idx];
            }
        }
        return null;
    }

    /**
     * 
     * @param i index
     * @return Cell at the given index value from within the grid datastructure.
     */
    public Cell getCellAtIndex(int i) {
        return cells[i];
    }

    public int getCellCount() {
        return cellCount;
    }
    
    public Cell[] getAllCells() {
        Cell[] allCells = new Cell[cellCount];
        for (int idx = 0; idx < cellCount; idx++) {
            allCells[idx] = cells[idx];
        }
        return allCells;
    }
    
    public int getArrayLength() {
        return cells.length;
    }

}
