package LabMst;

import java.util.*;

public class MIn {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        int[][] ar = new int[n][2];

        for(int i=0; i<n; i++){
            ar[i][0] = sc.nextInt();
            ar[i][1] = sc.nextInt();
        }

        System.out.println(calculate(ar));
    }
    public static int calculate(int[][] ar){
        int low = max(ar);
        int high = sum(ar);
        Arrays.sort(ar, (a, b) -> Integer.compare(b[1], a[1]));
        while(low <= high){
            int mid = low + (high - low) / 2;
            if(check(mid, ar)){
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return low;
    }
    public static boolean check(int val, int[][] ar){
        for(int i=0; i<ar.length; i++){
            if(ar[i][0] <= val && val >= ar[i][1]){
                val -= ar[i][0];
            }else{
                return false;
            }
        }

        return true;
    }

    public static int sum(int[][] ar){
        int sum = 0;
        for(int i=0; i<ar.length; i++){
            sum += ar[i][1];
        }
        return sum;
    }
    public static int max(int[][] ar){
        int max = Integer.MIN_VALUE;
        for(int i=0; i<ar.length; i++){
            max = ar[i][1] > max ? ar[i][1] : max;
        }
        return max;
    }
}
