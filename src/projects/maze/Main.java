package projects.maze;

public class Main {

    static void phase1() {
        Maze maze = MazeReader.load( "data/maze/sample_maze.txt" );
        System.out.println("Phase 1 Maze successfully loaded!");
        maze.serialize("data/maze/sample_maze_out.txt");
    }
    static void phase2() {
        Maze maze = MazeReader.load( "data/maze/sample_maze3.txt" );
        System.out.println("Phase 2 Maze successfully loaded!");
        maze.solveMaze();
        System.out.println("Phase 2 Maze successfully solved!");
        maze.serialize("data/maze/sample_maze3_out.txt");
    }
    public static void main(String[] args) {
        phase1();
        phase2();
    }

}
