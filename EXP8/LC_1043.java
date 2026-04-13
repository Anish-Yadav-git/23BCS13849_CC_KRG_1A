class Solution {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;

        int[] dp = new int[n + 1];
        dp[n] = 0; 

        for (int i = n - 1; i >= 0; i--) {
            int maxSum = Integer.MIN_VALUE;
            int max = Integer.MIN_VALUE;
            
            for (int j = i; j < Math.min(n, i + k); j++) {
                max = Math.max(max, arr[j]);              
                int sum = (max * (j - i + 1)) + dp[j + 1]; 
                maxSum = Math.max(sum, maxSum);
            }
            dp[i] = maxSum;
        }

        return dp[0];
    }
}
