package projects.maze;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

public class MazeTest {
    // Done
    @Test
    public void testConstructorAndAccessors() {
        Maze emptyMaze = new Maze(25);
        // Java wasn't recognizing this method for a minute there for some reason?
        assertEquals(emptyMaze.grid.getArrayLength(), 25);
        // assertNull(emptyMaze.getEnd());
        Maze maze = MazeReader.load("data/maze/sample_maze.txt");
        // Start/end
        Coords startCoords = new Coords(1, 0);
        Coords endCoords = new Coords(5, 7);
        assertTrue(maze.getStart().getCoords().equals(startCoords));
        assertTrue(maze.getEnd().getCoords().equals(endCoords));
        // InsertCell
        Cell cell = new Cell(new Coords(0, 0), CellStatus.S);
        emptyMaze.grid.insertCell(cell);
        emptyMaze.grid.insertCell(cell);
        assertTrue(emptyMaze.getStart().getCoords() == cell.getCoords());
        // Maze Size
        assertEquals(emptyMaze.grid.getArrayLength(), 25);
        // Start/End Advanced
        Cell cell1 = new Cell(new Coords(0, 2), CellStatus.O);
        Cell cell2 = new Cell(new Coords(0, 3), CellStatus.E);
        Cell cell3 = new Cell(new Coords(4, 3), CellStatus.E);
        emptyMaze.grid.insertCell(cell1);
        emptyMaze.grid.insertCell(cell2);
        emptyMaze.grid.insertCell(cell3);
        assertTrue(emptyMaze.getEnd().equals(cell2));
    }

    @Test
    public void testNeighborSetup() {
        Maze maze = MazeReader.load("data/maze/Test/neighbors_test_maze.txt");
        Coords[] neighbors = maze.getNeighbors(new Coords(2, 1));
        int[] test = new int[2];
        test[0] = maze.getStart().getCoords().getCol();
        test[1] = maze.getStart().getCoords().getRow();
        // System.out.println(test[0]+","+test[1]);
        assertTrue(maze.getStart().getCoords().equals(new Coords(2, 0)));
        //Coords test2 = maze.grid.getCell(new Coords(1, 1)).getCoords();
        // System.out.println(test2.getCol()+","+test2.getRow());
        // North
        assertTrue(neighbors[0].equals(new Coords(1, 1)));
        // East
        assertTrue(neighbors[1].equals(new Coords(2, 2)));
        // South
        assertTrue(neighbors[2].equals(new Coords(3, 1)));
        // West
        assertTrue(neighbors[3].equals(new Coords(2, 0)));

        Coords[] neighbors2 = maze.getNeighbors(new Coords(2, 3));
        assertTrue(neighbors2[0] == null);
        assertTrue(neighbors2[1].equals(new Coords(2, 4)));
        assertTrue(neighbors2[2] == null);
        assertTrue(neighbors2[3].equals(new Coords(2, 2)));
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
            // interesting quirk of the mazereader is that it just ignores the last line if
            // it's all X's

            scanner.close();
        } catch (FileNotFoundException e1) {
            fail("FileNotFoundException thrown. The relative path for the file is probably wrong.");
        } catch (NullPointerException e2) {
            fail("NullPointerException. Self explanatory.");
            
        } catch (Exception e3) {
            fail("How did you throw this?");
        }
    }

    @Test
    public void testSolver() {
        Maze maze1 = new Maze(25);
        assertThrows(NullPointerException.class, () -> {
            maze1.solveMaze();
        });
        File testfile = new File("data/maze/Test/solver_test.txt");
        testfile.delete();
        Maze maze = MazeReader.load("data/maze/sample_maze.txt");
        maze.solveMaze();
        maze.serialize("data/maze/Test/solver_test.txt");
        // Copied from the serialization test
        try {
            Scanner scanner = new Scanner(new File("data/maze/Test/solver_test.txt"));
            int index = 0;
            String[] lines = new String[6];
            while (scanner.hasNextLine()) {
                lines[index] = scanner.nextLine();
                index++;
            }

            assertTrue(lines[0].equals("X X X X X X X X "));
            assertTrue(lines[1].equals("S P P P O O O X "));
            assertTrue(lines[2].equals("X O X P X X O X "));
            assertTrue(lines[3].equals("X X X P P P X X "));
            assertTrue(lines[4].equals("X O O O X P X X "));
            assertTrue(lines[5].equals("X O X O X P P E "));

            scanner.close();
        } catch (FileNotFoundException e1) {
            fail("FileNotFoundException thrown. The relative path for the file is probably wrong.");
        }
    }
}
