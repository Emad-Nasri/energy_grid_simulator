import java.util.ArrayList;

public class DFSPathFinder {

    private boolean[][] visited;
    private ArrayList<String> path = new ArrayList<>();
    private boolean found = false;

    private int moveCount = 0;  // عدد الحركات
    private int cost = 0;       // التكلفة

    public ArrayList<String> findPath(char[][] grid, int startRow, int startCol, int goalRow, int goalCol) {

        visited = new boolean[grid.length][grid[0].length];
        path.clear();
        found = false;
        moveCount = 0;
        cost = 0;

        dfs(grid, startRow, startCol, goalRow, goalCol);

        if (found) {
            moveCount = path.size() - 1;
            cost = moveCount;
            System.out.println("Moves = " + moveCount);
            System.out.println("Cost  = " + cost);
            return path;
        } else {
            System.out.println("No path found.");
            return null;
        }
    }

    private void dfs(char[][] grid, int r, int c, int goalR, int goalC) {

        if (found) return;

        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) return;
        if (grid[r][c] == '#') return;
        if (visited[r][c]) return;

        visited[r][c] = true;
        path.add("(" + r + "," + c + ")");

        if (r == goalR && c == goalC) {
            found = true;
            return;
        }

        dfs(grid, r - 1, c, goalR, goalC);
        dfs(grid, r + 1, c, goalR, goalC);
        dfs(grid, r, c - 1, goalR, goalC);
        dfs(grid, r, c + 1, goalR, goalC);

        if (!found) {
            path.remove(path.size() - 1);
        }
    }

    public int getMoveCount() { return moveCount; }
    public int getCost() { return cost; }
}
