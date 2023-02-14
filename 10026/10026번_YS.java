import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, resultA, resultB;
    static char[][] arr;
    static boolean[][] visit;
    static int[] dx = {-1, 0, 1, 0}; // 상 하 좌 우
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        
        arr = new char[n][n];
        visit = new boolean[n][n];

        String str;
        for(int i=0; i<n; i++) {
            str=br.readLine();
            for(int j=0; j<n; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        // 일반 사람
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(!visit[i][j]) {
                	BFS(0, i, j, arr[i][j]);
                	resultA++;
                }
            }
        }

        // 적록색약인 사람
        visit = new boolean[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(!visit[i][j]) {
                	BFS(1, i, j, arr[i][j]);
                	resultB++; 
                }
            }
        }

        System.out.println(resultA + " " + resultB);
    }

    private static void BFS(int type, int x, int y, char c) {
        Queue<Pair> q = new LinkedList<Pair>();

        q.add(new Pair(x, y));
        visit[x][y] = true;

        while(!q.isEmpty()) {
            Pair p = q.poll();
            for(int i=0; i<4; i++) {
                int nx = p.x+dx[i];
                int ny = p.y+dy[i];

                if(nx<0 || ny<0 || nx>=n || ny>=n || visit[nx][ny]) continue;
                
                if(type==1) { // 적록색약
                	if(c=='R' && arr[nx][ny]=='G') arr[nx][ny]='R';
                    else if(c=='G' && arr[nx][ny]=='R') arr[nx][ny]='G';
                }
                
                if(arr[nx][ny]==c) {
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