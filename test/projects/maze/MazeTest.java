package projects.maze;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

public class MazeTest {

    @Test
    public void testConstructorAndAccessors() {
        Maze emptyMaze = new Maze(25);
        // Java wasn't recognizing this method for a minute there for some reason?
        assertEquals(emptyMaze.getMazeSize(), 25);
        // assertNull(emptyMaze.getEnd());
        Maze maze = MazeReader.load("data/maze/sample_maze.txt");
        // Start/end
        Coords startCoords = new Coords(1, 0);
        Coords endCoords = new Coords(5, 7);
        assertTrue(maze.getStart().getCoords().equals(startCoords));
        assertTrue(maze.getEnd().getCoords().equals(endCoords));
        // InsertCell
        Cell cell = new Cell(new Coords(0, 0), CellStatus.S);
        emptyMaze.insertCell(cell);
        emptyMaze.insertCell(cell);
        assertTrue(emptyMaze.getStart().getCoords() == cell.getCoords());
        // Maze Size
        assertEquals(emptyMaze.getMazeSize(), 25);
        // Start/End Advanced
        Cell cell1 = new Cell(new Coords(0, 2), CellStatus.O);
        Cell cell2 = new Cell(new Coords(0, 3), CellStatus.E);
        Cell cell3 = new Cell(new Coords(4, 3), CellStatus.E);
        emptyMaze.insertCell(cell1);
        emptyMaze.insertCell(cell2);
        emptyMaze.insertCell(cell3);
        assertTrue(emptyMaze.getEnd().equals(cell2));
    }

    @Test
    public void testNeighborSetup() {
        Maze maze = MazeReader.load("data/maze/sample_maze.txt");
        maze.discoverAndSetupNeighbors();
        // TODO more tests with phase 2
    }

    @Test
    public void testSerialization() {
        // Deletes the file if it previously existed.
        File testfile = new File("data/maze/Test/serialization_test.txt");
        testfile.delete();

        Maze maze = MazeReader.load("data/maze/sample_maze.txt");
        maze.serialize("data/maze/Test/serialization_test.txt");
        try {
            Scanner scanner = new Scanner(new File("data/maze/Test/serialization_test.txt"));
            int index = 0;
            // I couldnt think of how to have this dynamically update right now so GL if you
            // change the test file.
            String[] lines = new String[6];
            while (scanner.hasNextLine()) {
                lines[index] = scanner.nextLine();
                index++;
            }
            // In the future I may just make a testfile class that does this easier.
            assertTrue(lines[0].equals("X X X X X X X X "));
            assertTrue(lines[1].equals("S O O O O O O X "));
            assertTrue(lines[2].equals("X O X O X X O X "));
            assertTrue(lines[3].equals("X X X O O O X X "));
            assertTrue(lines[4].equals("X O O O X O X X "));
            assertTrue(lines[5].equals("X O X O X O O E "));

        } catch (FileNotFoundException e1) {
            fail();
        } catch (NullPointerException e2) {
            fail();
        }

    }
}
