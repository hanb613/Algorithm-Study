package com.mincheolsong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	

    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = null;
    	
    	int T = Integer.parseInt(br.readLine());
    	int[] arr;
    	int[] brr;
    	for(int i=0;i<T;i++) {
    		int N = Integer.parseInt(br.readLine());
    		arr = new int[N];
    		brr = new int[N]; 
    		st = new StringTokenizer(br.readLine());
    		
    		for(int j=0;j<N;j++) {
    			arr[j]=Integer.parseInt(st.nextToken());
    		}
    		
    		Arrays.sort(arr);
    		
    		if(arr[0]==arr[N-1]) {
    			System.out.println(0);
    		}
    		else {
	    		int arr_idx=0;
	    		for(int j=0;j<N/2;j++) {
	    			brr[j] = arr[arr_idx++]; // brr배열의 남은 부분 제일 앞, 제일 뒤 반복해서 채우기
	    			brr[N-1-j] = arr[arr_idx++];
	    		}
	    		
	    		if(N%2==1) { // 홀수개 이면 중간에 값을 넣어줘야 함
	    			brr[N/2]=arr[arr_idx];
	    		}
	    		
	    		int max = Math.abs(brr[0]-brr[N-1]); // 제일 앞과 제일 뒤의 차이값으로 초기화
	    		
	    		for(int j=0;j<N-1;j++) {
	    			int calced = Math.abs(brr[j+1]-brr[j]);
	    			max = calced > max ? calced : max;
	    		}
	    		
	    		System.out.println(max);
    		}
    	}
    	
    }

}

