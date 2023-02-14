package com.mincheolsong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int w,h;
	static int[][] map;
	static int answer;
	static int[] dx = {-1,-1,0,1,1,1,0,-1};
	static int[] dy = {0,1,1,1,0,-1,-1,-1};
	
	static void solve(int r,int c) {
		
		for(int d=0;d<8;d++) {
			if(r+dx[d]>=0 && r+dx[d]<h && c+dy[d] >= 0 && c+dy[d]<w) {
				if(map[r+dx[d]][c+dy[d]]==1) {
					map[r+dx[d]][c+dy[d]]=-1; // 확인한 육지(-1)
					solve(r+dx[d],c+dy[d]);
				}
			}
		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			if(w==0 && h==0) {
				break;
			}
			
			map = new int[h][w];
			
			
			for(int i=0;i<h;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<w;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			answer=0;
			for(int i=0;i<h;i++) {
				for(int j=0;j<w;j++) {
					if(map[i][j]==1) {
						answer+=1;
						solve(i,j);
					}
				}
			}
			
			System.out.println(answer);
			
			
			
			
			
		}
		
	}
	
	
}



