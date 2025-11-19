public class PathTracker {
    private boolean[][] visited;

    public PathTracker(int rows, int cols) {
        visited = new boolean[rows][cols];
    }

    public void markVisited(int row, int col) {
        visited[row][col] = true;
    }

    public boolean isVisited(int row, int col) {
        return visited[row][col];
    }
}
