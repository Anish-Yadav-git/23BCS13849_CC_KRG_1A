// class Solution {
//     public int[] findOrder(int n, int[][] prerequisites) {
//         ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
//         boolean[] vis = new boolean[n];
//         for(int  i=0; i<n; i++){
//             adj.add(new ArrayList<>());
//         }
//         for(int[] edge: prerequisites){
//             adj.get(edge[1]).add(edge[0]);
//         }
//         int[] ans = new int[n];
//         Stack<Integer> s = new Stack<>();

//         for(int i=0; i<n; i++){
//             if(!vis[i]){
//                 dfs(adj, vis, s, i);
//             }
//         }

//         int i = 0;
//         while(!s.isEmpty()){
//             ans[i++] = s.pop();
//         }
//         return ans;
//     }

//     private void dfs(ArrayList<ArrayList<Integer>> adj, boolean[] vis, Stack<Integer> s, int node){
//         vis[node] = true;

//         for(int it : adj.get(node)){
//             if(!vis[it]){
//                 dfs(adj, vis, s, it);
//             }
//         }
//         s.push(node);
//     }
// }

import java.util.*;

class Courses{
    public int[] findOrder(int n, int[][] prerequisites) {

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }

        int[] indegree = new int[n];

        for(int[] edge : prerequisites){
            adj.get(edge[1]).add(edge[0]);
            indegree[edge[0]]++;
        }

        Queue<Integer> q = new LinkedList<>();

        for(int i = 0; i < n; i++){
            if(indegree[i] == 0){
                q.add(i);
            }
        }

        int[] ans = new int[n];
        int index = 0;

        while(!q.isEmpty()){
            int node = q.poll();
            ans[index++] = node;

            for(int it : adj.get(node)){
                indegree[it]--;

                if(indegree[it] == 0){
                    q.add(it);
                }
            }
        }

        if(index != n) return new int[]{};

        return ans;
    }
}