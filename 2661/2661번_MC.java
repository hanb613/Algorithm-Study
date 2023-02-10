package com.mincheolsong;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    static int N;
    
    static boolean check(String st) {
    	
    	for(int i=1;i<=st.length()/2;i++) {
    		if(st.substring(st.length()-i).equals(st.substring(st.length()-2*i, st.length()-i))) {
    			return false;
    		}
    	}
    	
    	return true;
    }
    
    static void solve(int cnt, String st) {
        if(cnt==N) {
        	System.out.println(st);
        	System.exit(0);
        }

        
        for(int i=1;i<=3;i++) {
        	
        	if(check(st+i)) {
        		solve(cnt+1,st+i);
        	}
        }
        
    }

	public static void main(String[] args) {
	    
	    Scanner sc = new Scanner(System.in);
	    
	    N = sc.nextInt();
	    solve(0,"");
		
	    
	    sc.close();

    }

}