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

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		int[] input = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<N;i++) {
			input[i]=Integer.parseInt(st.nextToken());
		}
		
		
		if(reverse_np(input)) {
			for(int i=0;i<N;i++) {
				System.out.print(input[i] + " ");
			}
		}else {
			System.out.println(-1);
		}
		
		
	}

	private static boolean reverse_np(int[] input) {
		
		int n = input.length;
		
		// step1. 뒤쪽부터 꼭대기(v자로 꺾이는 부분)를 찾는다. (꼭대기 바로 앞이 교환할 자리)
		int i = n-1;
		while(i>0 && input[i-1] <= input[i]) {
			i--;
		}
		
		if(i==0) return false; // 가장 처음에 오는 순열이므로 더이상 찾는 순열이 없으니까 false 리턴
		
		// step2. 꼭대기 바로 앞(i-1) 자리에 교환할 값을 뒤족부터 찾는다.
		int j = n-1;
		while(input[i-1]<=input[j]) { // j는 무조건 i까지는 오게 됨 ( 무조건 답이 있음 ) -> 다른 조건이 필요 x
			j--;
		}
		
		// step3. 꼭대기 바로 앞(i-1)자리와 그 자리 값보다 큰 수 중 가장 작은 값을 교환
		
		swap(input,i-1,j);
		
		// step3 . 꼭대기 부터 맨 뒤까지 내림차순..?으로 정렬
		int k=n-1;
		while(i<k) {
			swap(input,i++,k--);
		}
		
		
		return true;
	}
	
	private static void swap(int[] input, int i, int j) {
		int temp = input[i];
		input[i] = input[j];
		input[j] = temp;
		
	}
}
