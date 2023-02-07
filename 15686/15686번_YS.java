import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m, result=987654321;
	static int[][] arr;
	static boolean[] visit;
	static List<Pair> chicken;
	static List<Pair> home;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		chicken = new ArrayList<Pair>();
		home = new ArrayList<Pair>();
		arr = new int[n][n];
		visit = new boolean[13];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 2) chicken.add(new Pair(i, j));
				else if (arr[i][j]==1) home.add(new Pair(i, j));
			}
		}
		
		Solution(0, 0);
		System.out.println(result);
	}
	
	public static void Solution(int k, int cnt) {
		if(cnt==m) {
			int ret=0;
			for(int i=0; i<home.size(); i++) {
				int dist=987654321; // 집과 치킨집 사이의 거리
				for(int j=0; j<chicken.size(); j++) {
					if(visit[j]) { // 선택한 치킨집
						int nx = Math.abs(chicken.get(j).x - home.get(i).x);
						int ny = Math.abs(chicken.get(j).y - home.get(i).y);
						dist = Math.min(dist, nx+ny); // 이전에 최소 거리였던 치킨 집 vs 현재 치킨 집 
					}
				}
				ret += dist;
			}
			result = Math.min(result, ret);
			return;
		}
		
		for(int i=k; i<chicken.size(); i++) {
			visit[i]=true;
			Solution(i+1, cnt+1);
			visit[i]=false;
		}
	}
	
	public static class Pair{
		int x, y;
		Pair(int x, int y){
			this.x=x;
			this.y=y;
		}
	}
}
