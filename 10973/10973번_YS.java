import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * 1. 기존 수열의 맨 오른쪽부터 하나씩 {왼쪽 원소} > {오른쪽 원소} 인지 검사
 * 2. 조건에 맞으면 그 왼쪽 원소를 중심으로 
             오른쪽에 존재하는 원소들 중에서 '기준보다 작은 값 중 가장 큰 값' 을 찾는다.
 * 3. 그렇게 찾은 값을 기준의 값과 교환한다.
 * 4. 다시 기준을 중심으로 오른쪽 원소들을 내림차순
 * */

public class Main {
    static int n;
    static Integer[] arr;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new Integer[n];
        
        StringTokenizer	st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
        }
        
        if(!Solution()) System.out.println(-1);
        else {
        	for(int i : arr) {
        		System.out.print(i + " ");
        	}
        }
    }
    
    private static boolean Solution() {
    	int idx = n-1; // 맨 오른쪽부터 검사
    	
    	// 왼쪽 원소 > 오른쪽 원소를 찾기 위한 단계
    	while(idx>0 && arr[idx-1] <= arr[idx]){ 
    		idx--;
        }
    	
    	// 첫번쨰 순열이면 X
    	if(idx<=0) return false;
    	
    	// 원쪽 원소를 기준으로 오른쪽에 존재한는 원소 중 기준보다 작으면서, 가장 큰 값
    	int j = arr.length-1;
        while(arr[idx-1] <= arr[j]){
            j--;
        }
        
        // 찾은 값을 기준의 값과 교환
        swap(idx-1, j);
        
        // 기준을 중심으로 오른쪽 원소들을 내림차순
        Arrays.sort(arr, idx, arr.length, Collections.reverseOrder());

        return true;
    }
    
    static Integer[] swap(int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        return arr;
    }
}