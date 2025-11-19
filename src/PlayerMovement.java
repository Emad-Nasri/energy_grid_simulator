import java.util.Random;

public class PlayerMovement {
    private Random random = new Random();

    public void movePlayer(Game game, char direction) {

        int newRow = game.getPlayerRow();
        int newCol = game.getPlayerCol();

        switch (direction) {
            case 'W': newRow--; break;
            case 'S': newRow++; break;
            case 'A': newCol--; break;
            case 'D': newCol++; break;
            default:
                System.out.println("Wrong value!");
                return;
        }

        char[][] grid = game.getGrid();

        // Boundary check
        if (newRow < 0 || newRow >= grid.length || newCol < 0 || newCol >= grid[0].length) {
            System.out.println("You hit the boundary!");
            return;
        }

        // Wall check
        if (grid[newRow][newCol] == '#') {
            System.out.println("You hit a wall!");
            return;
        }

        // Count moves
        game.incrementMoveCount();
        game.addCost(1);

        // Bonus cell '+'
        if (grid[newRow][newCol] == '+') {
            int extra = 3 + random.nextInt(4); // 3–6
            game.addCost(extra);
            System.out.println("Extra cost: " + extra);
        }

        // Reached goal?
        if (newRow == game.getGoalRow() && newCol == game.getGoalCol()) {
            game.setGameOver(true);
        }

        // Mark path
        if (!(game.getPlayerRow() == game.getStartRow() && game.getPlayerCol() == game.getStartCol())) {
            game.getPathTracker().markVisited(game.getPlayerRow(), game.getPlayerCol());
        }

        // Update position
        game.setPlayerRow(newRow);
        game.setPlayerCol(newCol);
    }
}
