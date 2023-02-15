import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static char[][] picture;
	static boolean[][] RGB;
	static boolean[][] GB;
	static int[] mr = {1, 0, -1, 0};
	static int[] mc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String str;
		// N 입력
		N = Integer.parseInt(st.nextToken());
		// 그림 입력
		picture = new char[N][N];
		for(int i = 0; i < N; i++) {
			str = br.readLine();
			for(int j = 0; j < N; j++) {
				picture[i][j] = str.charAt(j);
			}
		}
		// 구역 탐색
		int rgb = 0;
		int gb = 0;
		RGB = new boolean[N][N];
		GB = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!RGB[i][j]) {
					find_rgb(i, j);
					rgb++;
				}
				if(!GB[i][j]) {
					find_gb(i, j);
					gb++;
				}
			}
		}
		System.out.printf("%d %d\n", rgb, gb);
		
		
	}

	private static void find_rgb(int i, int j) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[]{i, j});
		RGB[i][j] = true;
		int nx, ny;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for(int m = 0; m < 4; m++) {
				nx = cur[0] + mr[m];
				ny = cur[1] + mc[m];
				if(0 <= nx && nx < N && 0 <= ny && ny < N ) {
					if(picture[cur[0]][cur[1]] == picture[nx][ny] && !RGB[nx][ny]) {
						RGB[nx][ny] = true;
						q.offer(new int[]{nx, ny});
					}
				}
			}
		}
	}
	
	public static void find_gb(int i, int j) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[]{i, j});
		GB[i][j] = true;
		int nx, ny;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for(int m = 0; m < 4; m++) {
				nx = cur[0] + mr[m];
				ny = cur[1] + mc[m];
				if(0 <= nx && nx < N && 0 <= ny && ny < N && !GB[nx][ny]) {
					if((picture[cur[0]][cur[1]] == picture[nx][ny]) || (picture[cur[0]][cur[1]] == 'G' && picture[nx][ny] == 'R') || (picture[cur[0]][cur[1]] == 'R' && picture[nx][ny] == 'G')) {
						GB[nx][ny] = true;
						q.offer(new int[]{nx, ny});
					}
				}
			}
		}
	}

}
