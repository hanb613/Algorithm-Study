import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int n, w, l;
	static int time, weight;
	static int[] arr;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());

		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int idx=0, cnt=0;
		List<Truck> bridge = new ArrayList<Truck>();
		
		while(cnt!=n) { // n개의 트럭이 건널 떄까지
			int size = bridge.size();
			for(int i=0;i<size; i++) { // 다리 위에 있는 트럭 한칸씩 앞으로 옮기기
				bridge.get(i).length--;
			}
			
			if(size!=0 && bridge.get(0).length<0) { // 맨 앞 트럭이 다리를 건넜으면 -> 무게 뺴주고, 리스트에서 삭제
				weight-=bridge.get(0).weight;
				bridge.remove(0); cnt++;
			}
			
			while(idx<n && weight+arr[idx]<=l) { // 현재무게 + 다음 트럭 무게 <= 최대 하중
				weight += arr[idx]; // 새로 들어온 트럭 무게 plus
				bridge.add(new Truck (arr[idx++], w-1)); 
			}
			time++;
		}
		
		System.out.println(time);
	}
	
	public static class Truck{
		int weight, length;
		Truck(int weight, int length){
			this.weight=weight;
			this.length=length;
		}
	}
}