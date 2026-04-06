package Graph;

import java.util.*;

public class Roads {

    static List<List<Integer>> graph;
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] roads = new int[m][m];
        for(int i =0; i< m; i++){
            for(int j=0; j< m; j++){
                roads[i][j] = sc.nextInt();
            }
        }

        System.out.println(minRoadtoConnect(n, roads));
        
    }

    // static ArrayList<Integer> dfsalgo(Arr  ayList<ArrayList<Integer>> adj) {
    //     int n = adj.size();
        
    //     boolean[] visited = new boolean[n];
    //     ArrayList<Integer> res = new ArrayList<>();
        
    //     Stack<Integer> st = new Stack<>();
    //     st.push(0);
        
    //     while (!st.isEmpty()) {
    //         int node = st.pop();
            
    //         if (visited[node] == true) {
    //             continue;
    //         }
            
    //         visited[node] = true;
    //         res.add(node);
            
    //         int size = adj.get(node).size();
    //         for (int i = size - 1; i >= 0; i--) {
    //             int v = adj.get(node).get(i);
    //             if (!visited[v]) st.push(v);
    //         }
    //     }
        
    //     return res;
    // }

    public static void dfs(int node) {
        visited[node] = true;
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor);
            }
        }
    }


   
     public static int minRoadtoConnect(int n, int roads[][]){
        if (roads.length < n - 1) {
            return -1; 
        }

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        visited = new boolean[n + 1];
        int components = 0;

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                components++;
                dfs(i);
            }
        }

        return components - 1;
    }   
}

