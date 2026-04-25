package FInalESTPractical;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Pair{
    int first;
    int second;
    public Pair(int first, int second){
        this.first = first;
        this.second = second;
    }
}
public class NetworkDelayTIme {
    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<Pair>> adj = new ArrayList<>();

        for(int i=0; i<=n; i++){
            adj.add(new ArrayList<>());
        }

        for(int i=0; i<times.length; i++){
            adj.get(times[i][0]).add(new Pair(times[i][1], times[i][2]));
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.first - b.first);
        pq.add(new Pair(0, k));

        int[] time = new int[n+1];
        Arrays.fill(time, (int)1e9);
        time[k] = 0;

        while(!pq.isEmpty()){
            Pair node = pq.poll();
            int u = node.second;

            for(Pair it : adj.get(u)){
                int v = it.first;
                int wt = it.second;
                if(time[u] + wt < time[v]){
                    time[v] = time[u] + wt;
                    pq.add(new Pair(time[v], v));
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for(int i = 1; i<=n ; i++){
            if(time[i] == (int)1e9) return -1; 
            max = time[i] > max ? time[i] : max;
        }

        return max;
    }
}
