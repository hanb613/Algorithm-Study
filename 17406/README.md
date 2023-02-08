# 1. Point들의 순열 구성

```java
static void solve(int n) { // 가능한 Point들의 순열을 selected 리스트에 넣음 
		if(n==list.size()) {
			for(Point s : selected) { // 선택된 Point 순열을 이용하여 rotate 수행
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
```
`n==list.size()`가 되면 완성된 순열을 이용하여 회전을 수행한 뒤(`rotate_top(s)`) 배열의 값을 계산하고 (`calc()`) 최솟값(`answer`)을 갱신 

# 2. `rotate_top(s)` 
```java
static void rotate_top(Point p) { // rotate_main을 호출하는 함수
		int r = p.getR();
		int c = p.getC();
		int s = p.getS();
		for(int i=0;i<s;i++) {
			rotate_main(r-s-1+i,c-s-1+i,2*s-2*i);
		}
  }
```
`rotate_main()` 함수에 회전을 위해 필요한 row(`r-s-1+i`), col(`c-s-1+i`), block_size(`2*s-2*i`) 를 넘겨 줌   
i=0일 때 가장 바깥쪽 회전 수행  
i=1일 때 두 번째 부분 회전 수행  

- row, col, block_size
![image](https://user-images.githubusercontent.com/80660585/217405368-b1e9747a-384f-4e3a-902b-9327109eb4e5.png)  
row,col : 회전을 수행할 부분의 왼쪽 위 좌표  
block_size : 배열 회전을 위해 자를 크기단위  

# 3. `rotate_main(int r, int c, int lne)`
```java
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
		
```
 넘겨받은 인자를 바탕으로 실제 회전을 수행한다
 
 # 4. `calc()`
 회전 완료된 배열의 값을 계산하는 함수
