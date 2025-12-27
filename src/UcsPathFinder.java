import java.util.PriorityQueue;
import java.util.Queue;
import java.util.LinkedList;

public class DijkstraPathFinder {

    private int moveCount = 0;

    private static class Node implements Comparable<Node> {
        int r, c, dist;

        Node(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }

        public int compareTo(Node other) {
            return Integer.compare(this.dist, other.dist);
        }
    }

    public Queue<String> findPath(char[][] grid, int startR, int startC, int goalR, int goalC) {

        int rows = grid.length;
        int cols = grid[0].length;

        int[][] dist = new int[rows][cols];
        int[][] parentR = new int[rows][cols];
        int[][] parentC = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dist[i][j] = Integer.MAX_VALUE;
                parentR[i][j] = -1;
                parentC[i][j] = -1;
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[startR][startC] = 0;
        pq.add(new Node(startR, startC, 0));

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        boolean found = false;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int r = cur.r;
            int c = cur.c;

            if (r == goalR && c == goalC) {
                found = true;
                break;
            }

            if (cur.dist > dist[r][c]) continue;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr < 0 || nr >= rows || nc < 0 || nc >= cols) continue;
                if (grid[nr][nc] == '#') continue;

                int cost = (grid[nr][nc] == '+') ? 3 : 1;

                if (dist[r][c] + cost < dist[nr][nc]) {
                    dist[nr][nc] = dist[r][c] + cost;
                    parentR[nr][nc] = r;
                    parentC[nr][nc] = c;

                    pq.add(new Node(nr, nc, dist[nr][nc]));
                }
            }
        }

        if (!found) return null;

        Queue<String> path = new LinkedList<>();
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

        Object[] arr = path.toArray();
        Queue<String> correct = new LinkedList<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            correct.add((String) arr[i]);
        }

        moveCount = correct.size() - 1;
        return correct;
    }

    public void printMove() {
        System.out.println("The move count with Dijkstra is: " + moveCount);
    }
}
