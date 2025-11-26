import java.util.*;

public class BFSPathFinder {

    public ArrayList<String> findPath(char[][] grid, int startR, int startC, int goalR, int goalC) {

        int rows = grid.length;
        int cols = grid[0].length;

        boolean[][] visited = new boolean[rows][cols];
        int[][] parentR = new int[rows][cols];
        int[][] parentC = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            Arrays.fill(parentR[i], -1);
            Arrays.fill(parentC[i], -1);
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startR, startC});
        visited[startR][startC] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        boolean found = false;

        while (!queue.isEmpty()) {
            int[] cur = queue.remove();
            int r = cur[0];
            int c = cur[1];

            if (r == goalR && c == goalC) {
                found = true;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr < 0 || nr >= rows || nc < 0 || nc >= cols) continue;
                if (grid[nr][nc] == '#') continue;
                if (visited[nr][nc]) continue;

                visited[nr][nc] = true;

                parentR[nr][nc] = r;
                parentC[nr][nc] = c;

                queue.add(new int[]{nr, nc});
            }
        }

        if (!found) return null;

        ArrayList<String> path = new ArrayList<>();

        int r = goalR;
        int c = goalC;

        while (!(r == startR && c == startC)) {
            path.add("(" + r + "," + c + ")");
            int pr = parentR[r][c];
            int pc = parentC[r][c];
            r = pr;
            c = pc;
        }

        path.add("(" + startR + "," + startC + ")");
        Collections.reverse(path);

        return path;
    }
}
