package com.mincheolsong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static int N;
	static char[][] painting;
	static boolean[][] chk;
	static int answer;
	
	static void solve(int row, int col, char color) { // 적록색약이 아닌 사람
		
				
		if(chk[row][col]==false && painting[row][col]==color) {
			chk[row][col]=true;
			for(int d=0;d<4;d++) {
				if(row+dx[d]>=0 && row+dx[d]<N && col+dy[d]>=0 && col+dy[d]<N) {
					solve(row+dx[d],col+dy[d],color); // 재귀
				}
			}
		}
		
		return;
	}
	
	static void wsolve(int row, int col, char color) { // 적록색약인 사람
		
		if(chk[row][col]==false) {
			if(color=='R' || color=='G') {
				if(painting[row][col]=='R' || painting[row][col]=='G'){
					chk[row][col]=true;
					for(int d=0;d<4;d++) {
						if(row+dx[d]>=0 && row+dx[d]<N && col+dy[d]>=0 && col+dy[d]<N) {
							wsolve(row+dx[d],col+dy[d],color); // 재귀
						}
					}
				}
			}
			else if(painting[row][col]==color) {
				
				chk[row][col]=true;
				for(int d=0;d<4;d++) {
					if(row+dx[d]>=0 && row+dx[d]<N && col+dy[d]>=0 && col+dy[d]<N) {
						wsolve(row+dx[d],col+dy[d],color);
					}
				}
				
			}
		}
		
		return;
	}
	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		painting = new char[N][N];
		chk = new boolean[N][N];
		
		for(int i=0;i<N;i++) {
			painting[i] =  br.readLine().toCharArray();
		}
		
		answer=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(chk[i][j]==false) { // 새로운 영역이면 갯수를 + 1 하고, dfs를 통해서 영역들을 true로 변환
					solve(i,j,painting[i][j]);
					answer+=1;
				}
			}
		}
		
		System.out.print(answer + " ");
		
		// 적록색약 탐색을 위해 초기화
		chk = new boolean[N][N]; 
		answer=0;
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(chk[i][j]==false) { // 새로운 영역이면 갯수를 + 1 하고, dfs를 통해서 영역들을 true로 변환
					wsolve(i,j,painting[i][j]); 
					answer+=1;
				}
			}
		}
		
		System.out.println(answer);
		
	}
	
	
}



