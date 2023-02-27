import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n, size, cnt, result;
	static int[][] arr;
	static Data now;
	static List<Data> fish;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		fish = new ArrayList<Data>();
		size=2;
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());	
				if(arr[i][j]==9) {
					now = new Data(i, j, 0);
					arr[i][j]=0;
				}
			}
		}
		
		Solution();
		
		System.out.println(result);
	}
	
	
	private static void Solution() {
		while(true) {
			boolean visit[][] = new boolean[n][n];
			Queue<Data> q = new LinkedList<Data>();
			q.add(new Data(now.x, now.y, now.dist));
			visit[now.x][now.y] = true;

			while(!q.isEmpty()) {
				Data shark = q.remove();

				for(int i=0; i<4; i++) {
					int nx = shark.x + dx[i];
					int ny = shark.y + dy[i];

					if(nx<0 || nx>=n || ny<0 || ny>=n || visit[nx][ny]) continue;
					
					if(arr[nx][ny]>0 && arr[nx][ny]<size) { // 먹을 수 있음 
						q.add(new Data(nx, ny, shark.dist+1));
						fish.add(new Data(nx, ny, shark.dist+1));
						visit[nx][ny] = true;
					} 
					else if(arr[nx][ny] == 0 || arr[nx][ny] == size) { // 먹지는 못하고 지나갈 수 있는 것 
						q.add(new Data(nx, ny, shark.dist+1));
						visit[nx][ny] = true;
					}
				}
			}
			
			if(fish.size() == 0) break; // 먹을 수 있는 물고기가 없으면 break
			else { // 먹을 수 있는 물고기가 있으면 
				Collections.sort(fish);

				result += fish.get(0).dist;
				arr[fish.get(0).x][fish.get(0).y] = 0;
				cnt++;
				
				if(cnt == size) { // 물고기를 먹은 수 == 내 크기 -> 크기 + 1
					size++; cnt = 0;
				}

				now.x = fish.get(0).x;
				now.y = fish.get(0).y;
				fish.clear();
			}
		}
	}
	
	public static class Data implements Comparable<Data>{
		int x, y, dist;
		
		Data(int x, int y, int dist){
			this.x=x;
			this.y=y;
			this.dist=dist;
		}

		@Override
		public int compareTo(Data o) {
			if(this.dist==o.dist) {
				if(this.x==o.x) {
					return this.y-o.y;
				}
				return this.x-o.x;
			}
			return this.dist-o.dist;
		}
	}
}
