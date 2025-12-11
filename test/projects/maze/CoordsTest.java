package projects.maze;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CoordsTest {
    //Done
    @Test
    public void testCoords(){
        Coords coords = new Coords(0, 0);
        assertTrue(coords.getCol() == 0);
        assertEquals(coords.getRow(), 0);
        Coords coords1 = new Coords(0, 0);
        Coords coords2 = new Coords(1, 0);
        assertTrue(coords.equals(coords1));
        assertFalse(coords.equals(coords2));
    }
}
