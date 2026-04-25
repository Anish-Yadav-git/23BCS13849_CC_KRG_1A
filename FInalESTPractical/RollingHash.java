package FInalESTPractical;


public class RollingHash {

    static final int p = 31;
    static final int mod = 1000000007;

    public static long computeHash(String s) {
        long hash = 0;
        long power = 1;

        for (int i = 0; i < s.length(); i++) {
            int val = s.charAt(i) - 'a' + 1;
            hash = (hash + val * power) % mod;
            power = (power * p) % mod;
        }

        return hash;
    }

    public static void main(String[] args) {
        String s = "geeksforgeeks";
        System.out.println("Hash value: " + computeHash(s));
    }
}

