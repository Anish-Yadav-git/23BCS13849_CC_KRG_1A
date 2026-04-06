package 06-04-26;

public class MatrixChain {
    static int matrixMultiplication(int N, int arr[]) {
        int[][] dp = new int[N][N];

        for (int len = 2; len < N; len++) {
            for (int i = 1; i < N - len + 1; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j]  + arr[i - 1] * arr[k] * arr[j];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        return dp[1][N - 1];
    }
    public static void main(String[] args) {
        
    }
}
