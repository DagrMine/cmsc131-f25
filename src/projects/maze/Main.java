package projects.maze;

public class Main {

    static void phase1() {
        Maze maze = MazeReader.load( "data/maze/sample_maze.txt" );
        System.out.println("Phase 1 Maze successfully loaded!");
        maze.serialize("data/maze/sample_maze_out.txt");
    }
    static void phase2() {
        Maze maze = MazeReader.load( "data/maze/hard_maze.txt" );
        System.out.println("Phase 2 Hard Maze successfully loaded!");
        if (maze.solveMaze()) {
            System.out.println("Phase 2 Maze successfully solved!");
            maze.serialize("data/maze/hard_maze_out.txt");
        } else {
            System.out.println("No solution found!");
        }

        maze = MazeReader.load( "data/maze/hard_maze_nosol.txt" );
        System.out.println("Phase 2 Hard Maze (nosol) successfully loaded!");
        if (maze.solveMaze()) {
            System.out.println("Phase 2 Maze successfully solved!");
            maze.serialize("data/maze/hard_maze_nosol_out.txt");
        } else {
            System.out.println("No solution found!");
        }
    }
    public static void main(String[] args) {
        phase1();
        phase2();
    }

}
