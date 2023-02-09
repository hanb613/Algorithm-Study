import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static int n;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        Solution(1, "1"); // 첫번째 - 1 넣어두기
    }
    
    private static void Solution(int k, String str) {
    	if(k==n) {
    		System.out.println(str);
    		System.exit(0);
    	}
    	
    	else {
        	for(int i=1; i<=3; i++) {
        		if(!isSameStr(str+i)) { // 수열 체크
        			Solution(k+1, str+i);    
        		}  		
        	}
    	}
    }
    
    private static boolean isSameStr(String str) {
    	int size = str.length();
    	
    	for(int i=1; i<=size/2; i++) {
        	String tmp = str.substring(size-2*i, size-i); // 문자열 앞쪽
        	String tmp2 = str.substring(size-i); // 뒤쪽

        	if(tmp.equals(tmp2)) return true; // 동일한거 있으면 X
    	}
    	return false;
    }
}