package EXP5;

import java.util.*;

public class Code {

    static final long P = 31;
    static final long MOD = 1000000007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        HashSet<Long> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            String s = sc.next();
            set.add(hash(s));
        }

        while (m-- > 0) {
            String q = sc.next();
            boolean found = false;
            char[] arr = q.toCharArray();

            for (int i = 0; i < arr.length && !found; i++) {
                char original = arr[i];
                for (char c = 'a'; c <= 'c'; c++) {
                    if (c == original) continue;
                    arr[i] = c;
                    if (set.contains(hash(new String(arr)))) {
                        found = true;
                        break;
                    }
                }
                arr[i] = original;
            }
            System.out.println(found ? "YES" : "NO");
        }
        sc.close();
    }

    static long hash(String s) {
        long h = 0;
        long power = 1;

        for (char c : s.toCharArray()) {
            h = (h + (c - 'a' + 1) * power) % MOD;
            power = (power * P) % MOD;
        }
        return h;
    }
}