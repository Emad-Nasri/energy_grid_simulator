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

        System.out.println("Choose solving method: dfs / bfs / no");
        String choice = scanner.next().toLowerCase();

        while (!choice.equals("dfs") && !choice.equals("bfs") && !choice.equals("no")) {
            System.out.println("Invalid choice! Enter dfs or bfs or no");
            choice = scanner.next().toLowerCase();
        }

        if (choice.equals("dfs")) {
            game.runDFS();
        }
        else if (choice.equals("bfs")) {
            game.runBFS();
        }

        if (choice.equals("dfs") || choice.equals("bfs")) {
            return; // lock game for reading only
        }

        System.out.println("Use W/A/S/D to move. Reach 'G' to win");

        while (!game.isGameOver()) {
            game.nextMove();
            System.out.print("Your move (W/A/S/D): ");
            char move = scanner.next().toUpperCase().charAt(0);

            game.movePlayer(move);
            game.displayGrid();
        }

        System.out.println("You reached the goal");
        System.out.println("Total moves: " + game.getMoveCount());
        System.out.println("Total cost: " + game.getCost());
    }
}
