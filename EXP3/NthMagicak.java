package EXP3;

public class NthMagicak {
    public long gcd(long a, long b) {
        if(b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public int nthMagicalNumber(int n, int a, int b) {
        int MOD = (int) 1e9+7;
        int min = Math.min(a, b);
        long gcd = gcd(a, b);
        long lcm = (a * b) / gcd;

        long start = 1, end = n;
        long ans = 0;
        while(start <= end) {
            long mid = start + (end - start)/2;
            long val = mid * min;
            long count = (val / a) + (val / b) - (val / lcm);

            if(count >= n) {
                ans = val;
                end = mid-1;
            } else {
                start = mid+1;
            }
        }

        long max = Math.max(a, b);
        start = 1; end = n;
        while(start <= end) {
            long mid = start + (end - start)/2;
            long val = mid * max;
            long count = (val / a) + (val / b) - (val / lcm);

            if(count >= n) {
                ans = Math.min(ans, val);
                end = mid-1;
            } else {
                start = mid+1;
            }
        }

        return (int)(ans % MOD);
    }
}
