import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int w, h, result;
    static int[][] arr;
    static boolean[][] visit;
    static int[] dx = {-1, 0, 1, 0, 1, 1, -1, -1}; // 상 하 좌 우, 대각선
    static int[] dy = {0, -1, 0, 1, 1, -1, 1, -1};

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true) {
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            if(w==0 && h==0) break; // 입력이 0 0이면 종료

            result=0;
            arr = new int[w][h];
            visit = new boolean[w][h];

            for(int i=0; i<w; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<h; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i=0; i<w; i++) {
                for(int j=0; j<h; j++) {
                    if(arr[i][j]==1 && !visit[i][j]) { // 방문하지 않은 섬이면 BFS 돌리고 result++
                    	BFS(i, j);
                    	result++; 
                    }
                }
            }

            System.out.println(result);
        }
    }

    private static void BFS(int x, int y) {
        Queue<Pair> q = new LinkedList<Pair>();

        q.add(new Pair(x, y));
        visit[x][y] = true;

        while(!q.isEmpty()) {
            Pair p = q.poll();
            for(int i=0; i<8; i++) {
                int nx = p.x+dx[i];
                int ny = p.y+dy[i];

                if(nx<0 || ny<0 || nx>=w || ny>=h || visit[nx][ny]) continue; // 범위 넘어가거나, 방문 했던 섬이면 X
                if(arr[nx][ny]==1) { // 섬이면 큐에 넣기
                    visit[nx][ny]=true;
                    q.add(new Pair(nx, ny));
                }
            }
        }
    }

    private static class Pair{
        int x, y;
        public Pair(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
}