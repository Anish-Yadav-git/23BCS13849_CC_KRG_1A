package 09-03-26;

import java.util.*;

class Pair {
    int node;
    int dist;

    Pair(int node, int dist) {
        this.node = node;
        this.dist = dist;
    }
}

public class Dijkstra {
    public static void main(String[] args) {

        int V = 5;

        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();

        for(int i = 0; i < V; i++){
            adj.add(new ArrayList<>());
        }

        // Graph edges (node, weight)
        adj.get(0).add(new Pair(1, 2));
        adj.get(0).add(new Pair(4, 1));

        adj.get(1).add(new Pair(2, 3));

        adj.get(2).add(new Pair(3, 6));

        adj.get(4).add(new Pair(2, 2));
        adj.get(4).add(new Pair(5, 4));

        int src = 0;

        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> x.dist - y.dist);

        dist[src] = 0;
        pq.add(new Pair(src, 0));

        while(!pq.isEmpty()) {

            Pair cur = pq.poll();
            int node = cur.node;
            int dis = cur.dist;

            for(Pair it : adj.get(node)) {

                int adjNode = it.node;
                int weight = it.dist;

                if(dis + weight < dist[adjNode]) {
                    dist[adjNode] = dis + weight;
                    pq.add(new Pair(adjNode, dist[adjNode]));
                }
            }
        }

        System.out.println("Shortest distances from source:");

        for(int i = 0; i < V; i++) {
            System.out.println(i + " : " + dist[i]);
        }
    }