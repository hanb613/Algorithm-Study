import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k, result=Integer.MAX_VALUE;
    static int[][] arr;
    static int[] numArr;
    static boolean[] visit;
    static Pair[] oper;
    static int x, y, r;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        k=Integer.parseInt(st.nextToken());
        
        oper = new Pair[k];
        visit = new boolean[k];
        numArr = new int[k];
	    arr = new int[n][m];
	    
	    for(int i=0; i<n; i++) {
	        st = new StringTokenizer(br.readLine());
	        for(int j=0; j<m; j++) {
	            arr[i][j] = Integer.parseInt(st.nextToken());
	        }
	    }
	    
	    for(int i=0; i<k; i++) {
	        st = new StringTokenizer(br.readLine());
	        x = Integer.parseInt(st.nextToken());
	        y = Integer.parseInt(st.nextToken());
	        r = Integer.parseInt(st.nextToken()); 
	        oper[i] = new Pair(x,y,r);
	    }
	    
	    Solution(0);
	    
	    System.out.println(result);
	}
    
    public static void copyArr(int[][] A, int[][] B) {
    	for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				A[i][j] = B[i][j];
			}
		}
    }

    public static void Solution(int idx) {
    	if(idx == k) {
        	int[][] newArr = new int[n][m];
        	copyArr(newArr, arr); // 원래 배열 복사     		

        	for(int t=0; t<k; t++) {
        		int tc = numArr[t];
        		
        		int nx=oper[tc].x-oper[tc].s-1;
        	    int ny=oper[tc].y-oper[tc].s-1;
        	    int nx2=oper[tc].x+oper[tc].s-1;
        	    int ny2=oper[tc].y+oper[tc].s-1;
        		Rotation(nx,ny,nx2,ny2, newArr);
        	}
        	
        	// 각 행에 있는 모든 수의 합 중 최솟값 찾기
        	for(int i=0; i<n; i++) {
    			int ret=0;
    			for(int j=0; j<m; j++) {
    				ret+=newArr[i][j];
    			}
    			result = Math.min(ret, result);
			}
        	
            return;
    	}
    	
    	// 순열로 순서 구하기
    	for(int i=0; i<k; i++) {
    		if(!visit[i]) {
    			visit[i]=true;
                numArr[idx] = i;
                Solution(idx+1);
                visit[i]=false;
    		}
    	}
    	
    }
    
	public static void Rotation(int x1, int y1, int x2, int y2, int[][] tmp) {
	    
		if(x1==x2 && y1==y2) return; // size 1 정사각형
		
		int[] ver = new int[3];
        ver[0] = tmp[x1][y2];
        ver[1] = tmp[x2][y2];
        ver[2] = tmp[x2][y1];
	    
        for(int i = y2; i > y1; i--) {
            tmp[x1][i] = tmp[x1][i - 1];
        }

        for(int i = x2; i > x1; i--) {
            if(i == x1 + 1) tmp[i][y2] = ver[0];
            else tmp[i][y2] = tmp[i - 1][y2];
        }

        for(int i = y1; i < y2; i++) {
            if(i == y2 - 1) tmp[x2][i] = ver[1];
            else tmp[x2][i] = tmp[x2][i + 1];
        }

        for(int i = x1; i < x2; i++) {
            if(i == x2 - 1) tmp[i][y1] = ver[2];
            else tmp[i][y1] = tmp[i + 1][y1];
        }    
        
        Rotation(x1 + 1, y1 + 1, x2 - 1, y2 - 1, tmp);
	}
	
	public static class Pair{
		int x, y, s;
		Pair(int x, int y, int s){
			this.x=x;
			this.y=y;
			this.s=s;
		}
		
	}
}