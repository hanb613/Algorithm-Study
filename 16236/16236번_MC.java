package com.mincheolsong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

// bfs큐에 넣을 때 이동할 수 있는 좌표이면 큐에 넣기
// 현재 아기상어의 크기, 위치를 저장하고 있는 변수 필요
// 현재까지 먹은 물고기의 갯수를 저장하는 변수 필요 ( 크기가 커질 때 마다 갱신 )


public class BOJ16236_아기상어 {
	
	static int[] dr = {-1,0,0,1};
	static int[] dc = {0,-1,1,0};
	static int N;
	static int[][] map;
	static int shark_size=2;
	static int shark_row,shark_col;
	static int eaten_fish=0;
	static int time=-1;
	static boolean[][] visited;
	static int answer=0;
	
	
	static void bfs() {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {shark_row,shark_col});
		visited[shark_row][shark_col]=true;
		
		while(!queue.isEmpty()) {
			
			int size = queue.size();
			time++; // 1초씩 증가하며 bfs
			
			
			int flag=0;
			int minR = Integer.MAX_VALUE;
			int minC = Integer.MAX_VALUE;
			
			while(size-- > 0) { // 한 턴씩 bfs수행(1초마다 이동하며 판단) == 가장 가까운 물고기부터 searching
				int[] point = queue.poll();
				int cr = point[0];
				int cc = point[1];
				
				if(map[cr][cc]!=0 && map[cr][cc]!=9 && map[cr][cc]<shark_size) { // 물고기를 먹을 수 있으면 좌표값 비교한 후 갱신, map[cr][cc]!=9를 해주지 않으면 무한루프..
					if(cr<minR) {
						minR = cr;
						minC = cc;
					}else if(cr==minR) {
						if(cc<minC) {
							minC = cc;
						}
					}
					flag=1; // 먹었다는 표시를 나타내기 위한 flag
					continue;
				}
				
				if(flag==0) { // 물고기를 먹은 적이 없으면, 계속해서 주변을 탐색 ( 먹었으면, 주변을 탐색할 필요 없음 )
					for(int d=0;d<4;d++) {
						int nr = cr+dr[d];
						int nc = cc+dc[d];
						if(nr<0 || nr>=N) continue;
						if(nc<0 || nc>=N) continue;
						if(visited[nr][nc]) continue; // 방문 했던 곳은 다시 갈 필요가 없음
						if(map[nr][nc]>shark_size) continue; // 자신 보다 큰 물고기의 위치는 지나갈 수 없음
						queue.add(new int[] {nr,nc});
						visited[nr][nc]=true;
					}
				}
				
			}
			
			if(flag==1) { // 물고기를 먹을 수 있었으면
				
				eaten_fish++; 
				answer=time; // 가장 마지막에 먹은 시간 (answer) 갱신
				
				// 아기상어의 위치 이동
				map[shark_row][shark_col]=0; 
				map[minR][minC]=9; 
				//print();
				//System.out.println("time : " + time);
				shark_row=minR;
				shark_col=minC;
				//System.out.println("먹은 물고기 갯수 : " + eaten_fish);
				//System.out.println("상어 크기 : " + shark_size);
				
				// 크기가 커질 만큼 먹었으면
				if(eaten_fish==shark_size) { 
					shark_size++;
					eaten_fish=0;
					//System.out.println("사이즈 up!");
				}
				
				
				//System.out.println("========");
				// 큐, 방문배열 초기화 ( 물고기를 먹은 위치부터 다시 가까운 거리부터 탐색해야 하기 때문에)
				queue.clear();
				queue.add(new int[] {shark_row,shark_col});
				visited = new boolean[N][N];
				visited[shark_row][shark_col]=true;
				time--; // time을 그냥두면 먹을 때 마다 1초가 skip되어서 -1 시켜줌
			}
			
			
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				int tmp = Integer.parseInt(st.nextToken());
				if(tmp==9) {
					shark_row=i;
					shark_col=j;
				}
				map[i][j]=tmp;
			}
		}
		
		bfs();
		System.out.println(answer);
		
		
	}
	
}
