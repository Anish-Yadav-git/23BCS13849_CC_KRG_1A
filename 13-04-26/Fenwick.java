package 13-04-26;

public class Fenwick {
    int n = 6;
    int[] bits = new int[n + 1]; 

    public int sum(int i) {
        int ans = 0;
        for (; i > 0; i -= (i & -i)) {
            ans += bits[i];
        }
        return ans;
    }

    public void update(int i, int x) {
        for (; i <= n; i += (i & -i)) {
            bits[i] += x;
        }
    }

    public int rangeSum(int l, int r) {
        return sum(r) - sum(l - 1);
    }

    public static void main(String[] args) {
        Fenwick ft = new Fenwick();

        ft.update(1, 1);
        ft.update(2, 3);
        ft.update(3, 5);
        ft.update(4, 7);
        ft.update(5, 9);
        ft.update(6, 11);

        // Query examples
        System.out.println("Sum 1 to 3: " + ft.sum(3));        // 1+3+5 = 9
        System.out.println("Sum 2 to 5: " + ft.rangeSum(2, 5)); // 3+5+7+9 = 24

        ft.update(3, 2);

        System.out.println("After update:");
        System.out.println("Sum 1 to 3: " + ft.sum(3)); // 1+3+7 = 11
    }
}
