package projects.maze;
/**Enum that determines a cell's status
 * @param S start
 * @param E end
 * @param O open cell
 * @param P path part
 */
public enum CellStatus {
    /**Maze Entrance */
    S,
    /**Maze Exit */
    E,
    /**Open Cell */
    O,
    /**Solution Path Part */
    P
}
