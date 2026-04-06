package Graph;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Topo {
    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        Stack<Integer> s = new Stack<>();

        boolean[] vis = new boolean[6];

        for(int  i=0; i<6; i++){
            adj.add(new ArrayList<>());
        }

        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(3).add(5);
        adj.get(4).add(0);
        adj.get(4).add(5);

        int[] ans = new int[6];
    
        bfs(adj, ans, 0);
        
        // int i = 0;

        // while(!s.isEmpty()){
        //     ans[i++] = s.pop();
        // }

        // System.out.println(Arrays.toString(ans));

    }

    private static void dfs(ArrayList<ArrayList<Integer>> adj, boolean[] vis, Stack<Integer> s, int node){
        vis[node] = true;

        for(int it : adj.get(node)){
            if(!vis[it]){
                dfs(adj, vis, s, it);
            }
        }

        s.push(node);
    }

    private static void bfs(ArrayList<ArrayList<Integer>> adj, int[] ans, int src){
        
        int[] indeg = new int[6];

        for(int i = 0; i < 6; i++){
            for(int it : adj.get(i)){
                indeg[it]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();

        for(int i = 0; i < 6; i++){
            if(indeg[i] == 0){
                q.add(i);
            }
        }
        int i=0;
        while(!q.isEmpty()){
            int node= q.poll();
            ans[i++] = node;

            for(int it: adj.get(node)){
                indeg[it] --;
                if(indeg[it] == 0){
                    q.add(it);
                }
            }
        }
        System.out.println(Arrays.toString(ans));
    }
}
