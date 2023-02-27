import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16236 {
    
    static int N; // 공간의 크기
    static int[][] map; // 공간
    static int[] babyShark; // 아기 상어의 위치
    
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우
    static int[] dy = {0, 0, -1, 1};
    static List<int[]> list = new LinkedList<>();
    static int ans;
    static int temp;
    static Queue<int[]> q = new ArrayDeque<>();
    static int[][] visited;
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 공간의 크기 입력
        map = new int[N][N]; // 공간 입력
        babyShark = new int[4]; // [0] 행, [1] 열, [2] 아기 상어의 레벨, [3] 현재까지 먹은 물고기 수
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9) { // 아기 상어의 위치 저장
                    babyShark[0] = i;
                    babyShark[1] = j;
                    babyShark[2] = 2;
                    babyShark[3] = 0;
                    map[i][j] = 0; // 아기 상어의 위치를 삭제
                }
            }
        }
        // 초기화
        ans = 0; 
        temp = 0;
        visited = new int[N][N]; // 생성
        // 물고기를 탐색하는 Queue
        while(bfs());
        System.out.println(ans);
        
    }

    private static boolean bfs() {
        q.clear(); // Queue 초기화
        for(int i = 0; i < N; i++) {
            Arrays.fill(visited[i], 0); // 방문 체크 초기화
        }
        list.clear(); // 유망성 물고기 초기화
        q.add(new int[] {babyShark[0], babyShark[1]}); // 아기 상어가 있는 x, y축
        visited[babyShark[0]][babyShark[1]] = 1;
        
        // 임시 변수
        int[] cur, eat;
        int nx, ny;
        
        while(!q.isEmpty()) {
            cur = q.poll(); // 현재 위치
            
            // 먹을 수 있는 물고기가 있다면
            if(map[cur[0]][cur[1]] > 0 && map[cur[0]][cur[1]] < babyShark[2]) { // 아기 상어의 레벨보다 작으면 먹을 수 있다
                // x, y, 아기상어와의 거리, 물고기 레벨
                list.add(new int[]{cur[0], cur[1], (visited[cur[0]][cur[1]] - 1), map[cur[0]][cur[1]]});
            }

            // 없다면
            for(int d = 0; d < 4; d++) {
                nx = cur[0] + dx[d];
                ny = cur[1] + dy[d];
                // 범위 체크
                if(nx < 0 || nx >= N) continue;
                if(ny < 0 || ny >= N) continue;
                // 시나리오 체크 : 아기 상어의 레벨보다 크거나 같은 물고기는 지나갈 수 없다
                if(map[nx][ny] > babyShark[2]) continue;
                // 방문 체크
                if(visited[nx][ny] != 0) continue;
                // 방문 표시
                visited[nx][ny] = visited[cur[0]][cur[1]] + 1;
                q.add(new int[]{nx, ny});
            }
        }
        
        // 먹을 수 있는 물고기 list가 완성된다
        if(list.size() == 0) { // 먹을 수 있는 물고기가 없다
            return false;
        }
        else { // 먹을 수 있는 물고기가 있다
            if(list.size() != 1) { // 여러 마리 있는 경우 정렬
                // 조건에 따라 거리가 가장 가까운, 가장 위에 있는, 가장 왼쪽에 있는 물고기 순으로 정렬한다
                Collections.sort(list, new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        if(o1[2] == o2[2]) { // 거리가 같다면
                            if(o1[0] == o2[0]) { // 행 위치가 같다면
                                return o1[1] - o2[1]; // 열 오름차순
                            }
                            else { // 행 위치가 같지않다면
                                return o1[0] - o2[0]; // 행 오름차순
                            }
                        }
                        else { // 거리가 같지 않다면
                            return o1[2] - o2[2]; // 거리 오름차순
                        }
                    }
                });
            }
            eat = list.get(0);
            ans += eat[2]; // 움직인 횟수
            // 크기가 같은 수의 물고기를 먹을 때마다 1 증가한다
            if(map[eat[0]][eat[1]] <= babyShark[2]) babyShark[3] += 1; 
            // 크기만큼 물고기를 먹었다면 아기상어 성장!
            if(babyShark[2] == babyShark[3]) {
            	babyShark[2] += 1; 
            	babyShark[3] = 0;
            }
            map[eat[0]][eat[1]] = 0; // 먹은 물고기를 지운다
            // 아기 상어 이동
            babyShark[0] = eat[0];
            babyShark[1] = eat[1];
            return true;
        }
        
        
    }

}
