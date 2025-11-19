import java.util.Scanner;

public class View {
    private Scanner scanner = new Scanner(System.in);
    private Game game;

    public void startGame() {
        System.out.print("Enter number of rows: ");
        int rows = scanner.nextInt();

        System.out.print("Enter number of columns: ");
        int cols = scanner.nextInt();

        game = new Game(rows, cols);

        game.displayGrid();
        game.nextMove();

        System.out.println("Use W/A/S/D to move. Reach 'G' to win");
        System.out.print("Run DFS pathfinder? (y/n): ");
        char ans = Character.toUpperCase(scanner.next().charAt(0));
        if (ans == 'Y') {
            game.runDFS();
            return;
        }

        while (!game.isGameOver()) {
            System.out.print("Your move (W/A/S/D): ");
            char move = scanner.next().toUpperCase().charAt(0);

            game.movePlayer(move);
            game.displayGrid();
            game.nextMove();
        }

        System.out.println("You reached the goal");
        System.out.println("Total moves: " + game.getMoveCount());
        System.out.println("Total cost: " + game.getCost());
    }
}
