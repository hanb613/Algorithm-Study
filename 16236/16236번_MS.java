import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

	/* 공간 정보 */
	static int N;
	static int[][] map;
	
	/* 아기 상어 정보 */
	static int sharkX;
	static int sharkY;
	static int sharkSize = 2;
	static int numOfEat = 0;

	/* 물고기 정보 */
	static int numOfFish = 0;
	static int[] numOfEachFish = { 0, 0, 0, 0, 0, 0, 0 };

	/* 정답 변수 */
	static int ans = 0;
	
	public static void main(String[] args) throws IOException {

		// 입출력용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 공간 상태 크기 및 배열 선언
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		// 공간 상태 입력
		int tmp;
		for (int i = 0; i < N; i++) {

			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {

				tmp = Integer.parseInt(st.nextToken());
				
				// 공간 상태 배열에 값 넣기
				map[i][j] = tmp;

				// 아기 상어일 경우, 좌푯값 저장
				if (tmp == 9) {
					sharkY = i;
					sharkX = j;
					continue;
				}

				// 물고기일 경우, 크기마다 몇 마리 있는지 저장
				numOfEachFish[tmp] += 1;
			}
		}

		// BFS 돌리기
		for (;;) {
			if (bfs(sharkY, sharkX)) {
				break;
			}
		}

		// 답 출력
		System.out.println(ans);
	}

	// '상/좌/우/하'로 사방 탐색하기
	static int[] dr = { -1, 0, 0, 1 };
	static int[] dc = { 0, -1, 1, 0 };

	/* BFS 정보 */
	static boolean[][] isVisited;
	static PriorityQueue<int[]> canEat;

	/*
	 * 먹을 수 있는 물고기의 거리가 같을 경우,
   * 제일 상단, 제일 왼쪽의 물고기를 먼저 선택
	 */
	static Comparator<int[]> comparator = (o1, o2) -> {

		int flag = 0;

		if (o1[2] == o2[2]) { // 거리가 같을 경우

			if (o1[0] == o2[0]) { // 물고기의 행 위치가 동일할 경우
				flag = Integer.compare(o1[1], o2[1]); // 물고기의 열 위치 
			} else {
				flag = Integer.compare(o1[0], o2[0]); // 물고기의 행 위치 비교
			}

		} else {
			flag = Integer.compare(o1[2], o2[2]);
		}

		return flag;
	};
	

	/**
	 * BFS로 아기 상어가 먹을 수 있는 가장 가까운 물고기 찾기
	 * 
	 * @param r 현재 상어 위치 row
	 * @param c	현재 상어 위치 column
	 * @return 더 이상 아기 상어가 움직일 필요가 없다면 true, 아니면 false
	 */
	private static boolean bfs(int r, int c) {

		/* 
		 * 첫 번째 시간 단축 방법
		 * - 현재 공간에 더 이상 먹을 수 있는 물고기가 없는지 판단하기 
		 */
		numOfFish = 0;	// 현재 아기 상어가 먹을 수 있는 먹이 개수
		int size = Math.min(sharkSize, 7);	// 아기 상어의 크기가 7보다 커질 수 있다.
		for (int i = 1; i < size; i++) {
			numOfFish += numOfEachFish[i];
		}

		// 더 이상 먹을 수 있는 물고기가 없을 경우
		if (numOfFish == 0) {
			return true;
		}

		// BFS를 위한 변수들 선언
		Queue<int[]> queue = new ArrayDeque<>();
		isVisited = new boolean[N][N];
		canEat = new PriorityQueue<>(comparator);

		// 시작 좌표 큐에 넣기
		queue.offer(new int[] { r, c, 0 });
		isVisited[r][c] = true;

		// 사방 탐색을 위한 변수들 선언
		boolean flag = false;
		int[] tmp;	// 현재 공간 상태 
		int nr;		// next row
		int nc; 	// next column
		int cr; 	// current row
		int cc; 	// current column
		int cDist;	// current distance
		
		// BFS 돌리기
		while (!queue.isEmpty()) {

			// 현재 공간 데이터 저장
			tmp = queue.poll();
			cr = tmp[0];
			cc = tmp[1];
			cDist = tmp[2];

			// 사방 탐색
			for (int d = 0; d < 4; d++) {

				nr = cr + dr[d];
				// row 값이 공간을 벗어난 경우
				if (nr < 0 || nr >= N) {
					continue;
				}

				// column 값이 공간을 벗어난 경우
				nc = cc + dc[d];
				if (nc < 0 || nc >= N) {
					continue;
				}

				// 이전에 방문했는지 확인
				if (isVisited[nr][nc]) {
					continue;
				}

				/* 다음 방문할 공간에 물고기가 없을 경우 */
				if (map[nr][nc] == 0) {
					isVisited[nr][nc] = true;
					queue.offer(new int[] { nr, nc, cDist + 1 });
					continue;
				}

				/* 다음 방문할 공간에 물고기가 있을 경우 */
				if (map[nr][nc] > sharkSize) { // 다음 방문할 공간의 물고기 크기가 아기 상어보다 큰 경우
					continue;
				} else if (map[nr][nc] == sharkSize) { // 다음 방문할 공간의 물고기 크기가 아기 상어와 같을 경우
					isVisited[nr][nc] = true;
					queue.offer(new int[] { nr, nc, cDist + 1 });
				} else { // 다음 방문할 공간의 물고기 크기가 아기 상어보다 작을 경우
					isVisited[nr][nc] = true;
					canEat.offer(new int[] { nr, nc, cDist + 1 });

					/* 
					 * 두 번째 시간 단축 방법
					 * - 아기 상어가 먹을 수 있는 가장 가까운 물고기를 찾은 경우
					 */
					if (!canEat.isEmpty() && canEat.peek()[2] < cDist + 1) {
						flag = true;
						break;
					} // 두 번째 시간 단축 end-if
					
				} // 아기 상어 크기 비교 end-if
			} // end for-d

			// 두 번째 시간 단축 flag
			if (flag)
				break;
			
		} // end BFS

		/* 
		 * 세 번째 시간 단축 방법
		 * - 아기 상어가 사방으로 아기 상어 크기보다 큰 물고기로 둘러싸인 경우
		 */
		if (canEat.isEmpty())
			return true;

		/* 먹을 수 있는 물고기가 있을 경우 */
		
		// 먹을 수 있는 가장 가까운 물고기 뽑기
		tmp = canEat.poll();
		int row = tmp[0];		// 이 물고기 row
		int column = tmp[1];	// 이 물고기 column
		int totalDist = tmp[2];	// 이 물고기와의 거리

		// 먹을 수 있는 물고기 개수 하나 줄이기
		numOfEachFish[map[row][column]] -= 1;
		
		// 현재 아기 상어 위치의 공간 상태를 0으로 변경
		map[sharkY][sharkX] = 0;
		
		// 먹을 수 있는 물고기 위치로 아기 상어 이동
		sharkY = row;
		sharkX = column;
		map[sharkY][sharkX] = 9;
		
		// 아기 상어의 몸집이 커지는지 확인
		numOfEat += 1;
		if (numOfEat == sharkSize) {
			numOfEat = 0;
			sharkSize += 1;
		}

		// 출력 거리 변수에 더함
		ans += totalDist;
		
		return false;
	}
}
