
import java.util.*;

class Edge implements Comparable<Edge> {
    int u, v, weight;

    public Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}


class DSU {
    int[] parent, size;

    public DSU(int n) {
        parent = new int[n];
        size = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); 
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return;

        if (size[rootX] < size[rootY]) {
            parent[rootX] = rootY;
            size[rootY] += size[rootX];
        } else {
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
        }
    }
}

public class KruskalMST {

    public static void main(String[] args) {
        int V = 4;

        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 6));
        edges.add(new Edge(0, 3, 5));
        edges.add(new Edge(1, 3, 15));
        edges.add(new Edge(2, 3, 4));

        int mstWeight = kruskal(V, edges);
        System.out.println("MST Weight: " + mstWeight);
    }

    public static int kruskal(int V, List<Edge> edges) {
        Collections.sort(edges); 

        DSU dsu = new DSU(V);
        int mstWeight = 0;
        int edgesUsed = 0;

        for (Edge e : edges) {
            int u = e.u;
            int v = e.v;

            if (dsu.find(u) != dsu.find(v)) {
                dsu.union(u, v);
                mstWeight += e.weight;
                edgesUsed++;

                if (edgesUsed == V - 1) break;
            }
        }

        return mstWeight;
    }
}