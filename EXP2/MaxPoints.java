package EXP2;

public class MaxPoints {
    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    private int[] reducer(int x, int y, int z, int w) {
        int a = x - y;
        int b = z - w;

        if (a == 0) return new int[]{x, 0};
        if (b == 0) return new int[]{0, z};

        int g = gcd(Math.abs(a), Math.abs(b));
        return new int[]{a / g, b / g};
    }

    public int maxPoints(int[][] points) {
        int n = points.length;
        int res = 1;

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {

                int[] slope = reducer(points[i][0], points[j][0], points[i][1], points[j][1]);
                int x = slope[0], y = slope[1];

                int ctr = 2;

                for (int k = j + 1; k < n; k++) {
                    int[] curr = reducer(points[i][0], points[k][0], points[i][1], points[k][1]);

                    if (curr[0] == x && curr[1] == y) {
                        ctr++;
                    }
                }

                res = Math.max(res, ctr);
            }
        }

        return res;
    }
}
