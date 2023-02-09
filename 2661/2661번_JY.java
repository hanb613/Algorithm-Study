import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2661 {
	
	static int N;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		perm("");
	}

	private static void perm(String str) {
		if(str.length() == N) {
			System.out.println(str);
			System.exit(0); // 강제 종료, 오름차순으로 재귀했기 때문에 가장 작은수부터 출력
		}
		for(int i = 1; i <= 3; i++) { // 1 ~ 3
			if(backtracking(str+i)) // 입력했을 때 정상적이라면 재귀호출
				perm(str+i); 
		}
	}

	private static boolean backtracking(String str) {
		int len = str.length() / 2;
		for(int i = 1; i <= len; i++) {
			String front = str.substring(str.length() - i * 2, str.length() - i); // ??
			String back = str.substring(str.length() - i, str.length()); // ??
			if(front.equals(back)) return false;
		}
		return true;
	}
}
