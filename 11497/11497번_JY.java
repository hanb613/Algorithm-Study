import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken()); // 테스트 케이스
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 통나무 개수
			// 통나무 높이
			int[] arr = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr); // 오름차순
			int max = 0; // 최소 난이도
			int first = arr[N-1] - arr[N-2];
			int second = arr[N-1] - arr[N-3];
			max = first > second ? first : second;
			for(int i = (N-2); i >= 2; i--) {
				max = max > arr[i] - arr[i-2] ? max : arr[i] - arr[i-2];
			}
			max = max > arr[1] - arr[0] ? max : arr[1] - arr[0];
			System.out.println(max);
		}
	}
}
