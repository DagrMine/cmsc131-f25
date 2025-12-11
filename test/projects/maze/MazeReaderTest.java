package projects.maze;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

public class MazeReaderTest {
    @Test
    public void mazeReaderTest() {
        Maze mazeRead = MazeReader.load("data/maze/sample_maze.txt");
        assertEquals(mazeRead.grid.getArrayLength(), 22);
        Coords coords = mazeRead.grid.getCellAtIndex(10 - 1).getCoords();
        Coords coords2 = new Coords(2,6);
        assertEquals(coords.getRow(), coords2.getRow());
        assertEquals(coords.getCol(), coords2.getCol());
    }
}
