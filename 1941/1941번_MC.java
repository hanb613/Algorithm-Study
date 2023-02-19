package com.mincheolsong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static char[][] students = new char[5][5];
	static int[] selected = new int[7];
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static int[] row = new int[25];
	static int[] col = new int[25];
	static Deque<Integer> queue = new ArrayDeque<>();
	static boolean[] chk;
	static int answer=0;
	
	static void check() {
		
		queue.add(selected[0]);
		chk[selected[0]]=true;
		int sCnt = 0;
		int cnt=1;
		
		while(!queue.isEmpty()) {
			int current = queue.pollFirst();
			
			if(students[row[current]][col[current]]=='S') {
				sCnt++;
			}
				
			for(int d=0;d<4;d++) {
				for(int i=1;i<7;i++) { // 선택된 좌표들을 돌아가며, 현재 좌표와 인접해있는지 검사
					if(!chk[selected[i]] && row[current]+dx[d]==row[selected[i]] && col[current]+dy[d]==col[selected[i]]) { 
						chk[selected[i]]=true;
						cnt++;
						queue.add(selected[i]);
					}
				}
			}
		}
		
		if(cnt == 7) { // 7개가 모두 인접해있고
            if(sCnt >=4) { // 이다솜파 이면
                answer++;
            }
        }
		
	}
	
	static void combi(int n,int start) {
		if(n==7) {
			// 선택된 좌표들이 연결되어 있고, 'S'가 최소 4개 이상 있는지 확인
			chk = new boolean[25];
			
			check();
			
			return;
		}
		
		
		for(int i=start;i<25;i++) {
			selected[n]=i;
			combi(n+1,i+1);
		}
	}
	
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for(int i=0;i<5;i++) {
        	students[i] = br.readLine().toCharArray();
        }
        for(int i=0;i<25;i++) {
        	row[i] = i/5;
        	col[i] = i%5;
        }
        
        combi(0,0);
        
        System.out.println(answer);

    }

}
