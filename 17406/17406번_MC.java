package bf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class Point{
	int r,c,s;
	
	Point(int r, int c, int s){
		this.r = r;
		this.c = c;
		this.s = s;
	}
	
	public int getR() {
		return r;
	}
	
	public int getC() {
		return c;
	}
	
	public int getS() {
		return s;
	}

	@Override
	public String toString() {
		return "Point [r=" + r + ", c=" + c + ", s=" + s + "]";
	}
}

public class Baekjoon17406 {
	static int N;
	static int M;
	static int K;
	static int[][] arr;
	static List<Point> list;
	static boolean[] chk;
	static List<Point> selected = new ArrayList<>();
	static int answer = Integer.MAX_VALUE;
	static int[][] origin_arr;
	
	static int calc() {
		int result=Integer.MAX_VALUE;
		for(int i=0;i<N;i++) {
			int tmp = Arrays.stream(arr[i]).sum();
			result = result > tmp ? tmp : result;
		}
		return result;
	}
	
	
	static void rotate_main(int r, int c, int len) {
		LinkedList<Integer> rotate_list = new LinkedList<>();
		
		for(int p=c;p<c+len;p++) {
			rotate_list.add(arr[r][p]);
		}
		for(int p=r;p<r+len;p++) {
			rotate_list.add(arr[p][c+len]);
		}
		for(int p=c+len;p>c;p--) {
			rotate_list.add(arr[r+len][p]);
		}
		for(int p=r+len;p>r;p--) {
			rotate_list.add(arr[p][c]);
		}
		
		// 회전을 LinkedList에 구현
		rotate_list.addFirst(rotate_list.getLast()); 
		
		rotate_list.removeLast();
	
		// LinkedList에 적용된 회전을 arr배열에 적용
		int sb_idx=0;
		for(int p=c;p<c+len;p++) {
			arr[r][p] = rotate_list.get(sb_idx++);
		}
		for(int p=r;p<r+len;p++) {
			arr[p][c+len] = rotate_list.get(sb_idx++);
		}
		for(int p=c+len;p>c;p--) {
			arr[r+len][p] = rotate_list.get(sb_idx++);
		}
		for(int p=r+len;p>r;p--) {
			arr[p][c] = rotate_list.get(sb_idx++);
		}
		
	}
	
	static void rotate_top(Point p) { // rotate_main을 호출하는 함수
		int r = p.getR();
		int c = p.getC();
		int s = p.getS();
		for(int i=0;i<s;i++) {
			rotate_main(r-s-1+i,c-s-1+i,2*s-2*i);
		}
	}
	
	static void solve(int n) { // 가능한 Point들의 순열을 selected 리스트에 넣음 
		if(n==list.size()) {
			for(Point s : selected) { // 선택된 회전 조합들을 순회하며 rotate 수행
				rotate_top(s); // 회전 시작
			}
			int tmp = calc(); // rotate완료된 배열에서의 값을 계산
			
			answer = (answer>tmp)?tmp:answer; // 최솟값 갱신
			
			for(int i=0;i<N;i++) // 배열 원상복구
				arr[i] = origin_arr[i].clone(); 
			
			return;
		}
		
		for(int i=0;i<list.size();i++) { // 재귀함수로 Point들의 순열을 구현
			if(chk[i]==false) {
				chk[i]=true;
				selected.add(list.get(i));
				solve(n+1);
				chk[i]=false;
				selected.remove(selected.size()-1);
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		origin_arr = new int[N][M]; // arr의 원본값 저장을 위한 배열
		list = new ArrayList<>();
		chk = new boolean[K];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0;i<N;i++)
			origin_arr[i] = arr[i].clone();
		
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			list.add(new Point(r,c,s));
		}
		
		
		solve(0);
		
		System.out.println(answer);
		
		
				
	}

}
