package EXP6;

public class Alien {
    
}public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    String[] s = new String[n];

    for (int i = 0; i < n; i++) {
        s[i] = sc.next();
    }

    int[][] a = new int[26][26];
    int[] visited = new int[26];
    char[] result = new char[27];
    Arrays.fill(result, '.');

    int k = 0;

    for (int i = 0; i < n - 1; i++) {
        int l1 = s[i].length();
        int l2 = s[i + 1].length();

        int j;
        for (j = 0; j < l1 && j < l2; j++) {
            if (s[i].charAt(j) == s[i + 1].charAt(j)) continue;

            int u = s[i].charAt(j) - 'a';
            int v = s[i + 1].charAt(j) - 'a';

            if (a[u][v] == 0) {
                a[u][v] = -1;
                a[v][u] = 1;
            } else if (a[u][v] == 1) {
                System.out.println("Impossible");
                return;
            }
            break;
        }

        if (j == l1 || j == l2) {
            if (l1 > l2) {
                System.out.println("Impossible");
                return;
            }
        }
    }

    while (true) {
        boolean found = false;

        for (int i = 0; i < 26; i++) {
            if (visited[i] == 1) continue;

            int j;
            for (j = 0; j < 26; j++) {
                if (a[i][j] > 0) break;
            }

            if (j < 26) continue;

            for (j = 0; j < 26; j++) {
                if (a[i][j] < 0) {
                    a[j][i] = 0;
                }
            }

            result[k++] = (char) (i + 'a');
            visited[i] = 1;
            found = true;
            break;
        }

        if (!found) {
            System.out.println("Impossible");
            return;
        }

        if (k == 26) break;
    }

    System.out.println(new String(result, 0, 26));
}
