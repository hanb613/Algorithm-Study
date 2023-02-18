import java.io.*;
import java.util.*;

public class Main {
    static int result;
    
    static char[][] arr;
    static int[] girls;
    static boolean[] visit;
    static Pair[] pair;
    
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        arr = new char[5][5];
        pair = new Pair[25];
        girls = new int[7];
        
        String str;
        for(int i=0; i<5; i++) {
        	str = br.readLine();
        	for(int j=0; j<5; j++) {
        		arr[i][j] = str.charAt(j);
        	}
        }
        
        for(int i=0; i<25; i++) {
        	pair[i] = new Pair(i%5, i/5); // 2차원 -> 1차원
        }
        
        Solution(0, 0);
        
        System.out.println(result);
    }
    
    private static void Solution(int idx, int cnt) {
    	if(idx==7) { // 25개 중 7개 뽑혔으면 모두 이어져있는지 확인
    		checkBFS();
    		return;
    	}
    	
    	if(cnt==25) return;
    	
    	girls[idx]=cnt;
    	Solution(idx+1, cnt+1);
    	Solution(idx, cnt+1);
    }
    
    private static void checkBFS() {
    	Queue<Integer> q = new LinkedList<Integer>();
    	visit = new boolean[7];
    	
    	int cnt=1, sCnt=0;
    	q.offer(girls[0]); // 첫번쨰 여학생 큐에 넣기
    	visit[0]=true;
    	
    	while(!q.isEmpty()) {
    		int cur = q.poll();
    		if(arr[pair[cur].x][pair[cur].y] == 'S') sCnt++; // 현재 여학생이 다솜파이면 +1
    		
    		for(int i=0; i<4; i++) { // 상하좌우 움직이기
    			int nx = pair[cur].x + dx[i];
    			int ny = pair[cur].y+ dy[i];
    			
    			if(nx<0 || ny<0 || nx>=5 || ny>=5) continue;
    			
    			for(int j=1; j<7; j++) { // 뽑힌 1~7번 여학생 확인
    				if(!visit[j] && pair[girls[j]].x==nx && pair[girls[j]].y==ny) { // 방문하지 않았고, nx/ny와 해당 여학생 좌표랑 같으면?
    					q.offer(girls[j]); // 큐에 넣고 
    					visit[j]=true; // 방문 표시
    					cnt++;
    				}
    			}
    		}
    	}
    	
    	if(cnt==7 && sCnt>=4) result++;
    }
    
    private static class Pair{
    	int x, y;
    	public Pair(int x, int y) {
    		this.x=x; 
    		this.y=y;
    	}
    }
}