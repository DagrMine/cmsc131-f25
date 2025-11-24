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
    private boolean explored = false;
    private CellStatus status;

    //Initializer
    public Cell(Coords c) {
        coords = c;
    }

    //Accessors
    public Coords getCoords() {
        return coords;
    }

    public CellStatus getStatus() {
        return status;
    }

    public boolean isExplored() {
        return explored;
    }

    //Modifiers
    public void setStatus(CellStatus status) {
        this.status = status;
    }
    public void setExplored(boolean explored) {
        this.explored = explored;
    }

}
