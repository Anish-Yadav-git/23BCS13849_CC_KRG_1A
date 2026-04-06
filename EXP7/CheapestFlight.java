package EXP7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CheapestFlight {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        
        for (int[] f : flights) {
            adj.get(f[0]).add(new int[]{f[1], f[2]}); // {destination, price}
        }

        int[] dist = new int[n];
        Arrays.fill(dist, (int)1e9);

        Queue<int[]> q = new LinkedList<>();
        
        dist[src] = 0;
        q.offer(new int[]{0, src, 0});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            
            int stops = cur[0];
            int node = cur[1];
            int cost = cur[2];

            if (stops > k) continue;

            for (int[] nei : adj.get(node)) {
                int adjNode = nei[0];
                int price = nei[1];

                if (cost + price < dist[adjNode] && stops <= k) {
                    dist[adjNode] = cost + price;
                    q.offer(new int[]{stops + 1, adjNode, cost + price});
                }
            }
        }

        return dist[dst] == (int)1e9 ? -1 : dist[dst];
    }
}
