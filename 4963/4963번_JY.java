import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int W, H;
	static int[][] map;
	// 가로, 세로 또는 대각선으로 연결되어 있는 땅은 걸어갈 수 있는 땅
	static int[] mr = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] mc = {-1, 0, 1, 1, 1, 0, -1, -1};
	static boolean[][] list;
	static int res;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		while(true) {
			// W, H 입력
			st =  new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			if(W == 0 && H == 0) break; // 0 0일 경우에 종료
			// 섬과 바다 지도 입력
			map = new int[H][W];
			for(int i = 0; i < H; i++) {
				st =  new StringTokenizer(br.readLine());
				for(int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken()); // 땅1, 바다0
				}
			}
			// BFS 탐색
			res = 0;
			list = new boolean[H][W]; // false 초기화
			for(int i = 0; i < H; i++) {
				for(int j = 0; j < W; j++) {
					if(map[i][j] == 1 && !list[i][j]) { // map에서 땅이고 방문한 적이 없는 곳
						explore(i, j);
						res++;
					}
				}
			}
			sb.append(res).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void explore(int i, int j) {
		Queue<Data> q = new ArrayDeque<>();
		q.offer(new Data(i, j)); // 좌표 값을 큐에 삽입
		int nx, ny;
		while(!q.isEmpty()) {
			Data d = q.poll();
			
			if(map[d.x][d.y] == 0) { // 바다인 경우
				continue;
			}
			
			for(int m = 0; m < 8; m++) {
				nx = d.x + mr[m];
				ny = d.y + mc[m];
				if(0 <= nx && nx < H && 0 <= ny && ny < W) {
					if(!list[nx][ny]) { //방문한 적이 없다면
						list[nx][ny] = true;
						q.offer(new Data(nx, ny));
					}
				}
			}
		}
	}

	public static class Data{
		int x;
		int y;
		// 인자 생성자
		public Data(int x, int y) {
			super();
			this.x = x;
			this.y = y;
    }
	}

}
