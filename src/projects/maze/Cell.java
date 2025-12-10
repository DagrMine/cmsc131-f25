/** `Cell` class additions

Extra attributes

- `neighbors` (Coords[]) - coordinates of neighbors
    - needs accessor/mutator

- `explored` (boolean) - traversal flag (defaults to false)
    - needs accessor/mutator

- `status` (CellStatus enum) - cell's role/state
    - Enum values    
        - `S` maze entrance
        - `E` maze exit  
        - `O` open cell
        - `P` part of solution path
    - Needs accessor/mutator

A cell will be constructed with a coordinates and a status. Decide for yourself what are sensible default values (if any) for the other attributes.
 */

package projects.maze;

public class Cell {

    private final Coords coords;
    //TODO final
    private Coords[] neighborsCoords = new Coords[4];
    private boolean explored = false;
    private CellStatus status;

    // Constructors
    /**Constructor for maze walls, no CellStatus.
     */
    public Cell(Coords c) {
        coords = c;
    }
    /**Constructor for cells that are apart of the maze traversable path.
     */
    public Cell(Coords c, CellStatus s) {
        coords = c;
        status = s;
    }
    /*
    public Cell(Coords c, CellStatus s, Coords[] n){
        coords = c;
        status = s;
        neighborsCoords = n;
    }
    */

    // Accessors
    public Coords getCoords() {
        return coords;
    }
    public CellStatus getStatus() {
        return status;
    }
    public boolean isExplored() {
        return explored;
    }
    /**
     * Get coordinates for a specific neighbor.
     * 
     * @param cellCoordinates coordinates of the cell to be returned.
     * @return returns the array index value of a given neighbor coordinate
     */
    public int getNeighborIndex(Coords cellCoordinates) {
        for (int index = 0; index < neighborsCoords.length; index++) {
            if (neighborsCoords[index].equals(cellCoordinates)) {
                return index;
            }
        }
        return -1;
    }
    /**
     * @return all neighbors of the cell as a Coords[] array. Does nothing if the neighbors have not been initialized.
     */
    public Coords[] getAllNeighbors() {
        return neighborsCoords;
    }

    /**
     * Returns the neighbor coordinates of a given index value
     * 
     * @param i index
     * @return neighborsCoords of a given index
     * @throws ArrayIndexOutOfBoundsException getNeighbor i is greater than 4 or
     *                                        less than 0
     */
    public Coords getNeighbor(int i) {
        if (i <= 0 && i >= 3) {
            return neighborsCoords[i];
        } else
            throw new ArrayIndexOutOfBoundsException("getNeighbor tried to find an invalid neighboring cell.");
    }

    // Modifiers
    public void setStatus(CellStatus status) {
        this.status = status;
    }

    public void setExplored(boolean explored) {
        this.explored = explored;
    }
    /**
     * sets the Coords of a neighbor within the neighborCoords[] array at the index value.
     * @param neighborCoords
     * @param index
     * @return false if the index value is OOB
     */
    public boolean setNeighbor(Coords neighborCoords, int index) {
        if (index > 0 && index < 4) {
            this.neighborsCoords[index] = neighborCoords;
            return true;
        }
        return false;
    }

}
