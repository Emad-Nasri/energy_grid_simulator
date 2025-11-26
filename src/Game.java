import java.util.ArrayList;
import java.util.Random;

public class Game {
    private char[][] grid;
    private int playerRow, playerCol;
    private int startRow, startCol;
    private int goalRow, goalCol;
    private int cost = 0;
    private int moveCount = 0;
    private boolean gameOver = false;

    private Random random = new Random();
    private PathTracker pathTracker;
    private PlayerMovement playerMovement;
    DFSPathFinder dfsFinder;

    public char[][] getGrid() { return grid; }
    public int getPlayerRow() { return playerRow; }
    public int getPlayerCol() { return playerCol; }
    public int getStartRow() { return startRow; }
    public int getStartCol() { return startCol; }
    public int getGoalRow() { return goalRow; }
    public int getGoalCol() { return goalCol; }
    public PathTracker getPathTracker() { return pathTracker; }
    public boolean isGameOver() { return gameOver; }

    public void setPlayerRow(int r) { playerRow = r; }
    public void setPlayerCol(int c) { playerCol = c; }
    public void addCost(int c) { cost += c; }
    public void incrementMoveCount() { moveCount++; }
    public void setGameOver(boolean b) { gameOver = b; }

    public Game(int rows, int cols) {
        grid = new char[rows][cols];
        pathTracker = new PathTracker(rows, cols);
        playerMovement = new PlayerMovement();
        dfsFinder = new DFSPathFinder();
        generateGrid();
    }

    private void generateGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int rand = random.nextInt(10);

                if (rand < 2) grid[i][j] = '#';
                else if (rand == 2) grid[i][j] = '+';
                else grid[i][j] = '.';
            }
        }


        startRow = random.nextInt(grid.length);
        startCol = random.nextInt(grid[0].length);
        playerRow = startRow;
        playerCol = startCol;
        grid[startRow][startCol] = 'S';


        do {
            goalRow = random.nextInt(grid.length);
            goalCol = random.nextInt(grid[0].length);
        } while (goalRow == startRow && goalCol == startCol);

        grid[goalRow][goalCol] = 'G';
    }

    public void displayGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                if (i == playerRow && j == playerCol) {
                    System.out.print("P ");
                }
                else if (pathTracker.isVisited(i, j) && grid[i][j] == '.') {
                    System.out.print("* ");
                }
                else {
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.println();
        }

        System.out.println("The cost: " + cost + " and your moves: " + moveCount);
    }

    public void nextMove() {
        StringBuilder possible = new StringBuilder("You can move: ");
        boolean hasMove = false;


        if (playerRow - 1 >= 0 && grid[playerRow - 1][playerCol] != '#') {
            possible.append("W ");
            hasMove = true;
        }


        if (playerRow + 1 < grid.length && grid[playerRow + 1][playerCol] != '#') {
            possible.append("S ");
            hasMove = true;
        }


        if (playerCol - 1 >= 0 && grid[playerRow][playerCol - 1] != '#') {
            possible.append("A ");
            hasMove = true;
        }


        if (playerCol + 1 < grid[0].length && grid[playerRow][playerCol + 1] != '#') {
            possible.append("D ");
            hasMove = true;
        }

        if (!hasMove) {
            System.out.println("Wrong move");
        } else {
            System.out.println(possible.toString());
        }
    }

    public void movePlayer(char direction) {
        playerMovement.movePlayer(this, direction);
    }
    public void runDFS() {
        var result = dfsFinder.findPath(grid, startRow, startCol, goalRow, goalCol);

        if (result == null) {
            System.out.println("No path found with DFS");
        } else {
            System.out.println("DFS Path from S to G:");
            for (String step : result) {
                System.out.print(step + " then ");
            }
            System.out.println("END");
        }
    }

    public void runBFS() {
        BFSPathFinder bfs = new BFSPathFinder();
        ArrayList<String> result = bfs.findPath(grid, startRow, startCol, goalRow, goalCol);

        if (result == null) {
            System.out.println("No path found using BFS.");
            return;
        }

        System.out.println("This is a path by BFS :");
        for (String step : result) {
            if(playerRow!=goalRow&&playerCol!=goalCol)
                System.out.print(step+" then ");
        }
        System.out.println("End");
        System.out.println("moves count : "+bfs.getMoveCount());
        System.out.println("final costs : "+bfs.getCost());

        gameOver = true;
    }


    public int getCost() { return cost; }
    public int getMoveCount() { return moveCount; }
}
