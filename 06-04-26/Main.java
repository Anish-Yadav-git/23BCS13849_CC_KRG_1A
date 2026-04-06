package 06-04-26;

import java.io.*;
import java.util.*;

class Main {

    static class Edge {
        int to, rev, cap;
        Edge(int to, int rev, int cap) {
            this.to = to;
            this.rev = rev;
            this.cap = cap;
        }
    }

    static int N;
    static List<Edge>[] graph;
    static int[] level, ptr;

    static void addEdge(int u, int v) {
        graph[u].add(new Edge(v, graph[v].size(), 1));
        graph[v].add(new Edge(u, graph[u].size() - 1, 0));
    }

    static boolean bfs(int s, int t) {
        Arrays.fill(level, -1);
        Queue<Integer> q = new ArrayDeque<>();
        q.add(s);
        level[s] = 0;

        while (!q.isEmpty()) {
            int u = q.poll();
            for (Edge e : graph[u]) {
                if (e.cap > 0 && level[e.to] == -1) {
                    level[e.to] = level[u] + 1;
                    q.add(e.to);
                }
            }
        }
        return level[t] != -1;
    }

    static int dfs(int u, int t, int flow) {
        if (u == t) return flow;

        for (; ptr[u] < graph[u].size(); ptr[u]++) {
            Edge e = graph[u].get(ptr[u]);
            if (e.cap > 0 && level[e.to] == level[u] + 1) {
                int pushed = dfs(e.to, t, Math.min(flow, e.cap));
                if (pushed > 0) {
                    e.cap -= pushed;
                    graph[e.to].get(e.rev).cap += pushed;
                    return pushed;
                }
            }
        }
        return 0;
    }

    static int maxFlow(int s, int t) {
        int flow = 0;
        while (bfs(s, t)) {
            Arrays.fill(ptr, 0);
            int pushed;
            while ((pushed = dfs(s, t, Integer.MAX_VALUE)) > 0) {
                flow += pushed;
            }
        }
        return flow;
    }

    // Extract paths using edges where flow was used (reverse edge cap = 1)
    static List<List<Integer>> extractPaths(int s, int t) {
        List<List<Integer>> paths = new ArrayList<>();

        while (true) {
            List<Integer> path = new ArrayList<>();
            boolean[] visited = new boolean[N];
            if (!dfsPath(s, t, path, visited)) break;
            paths.add(path);
        }
        return paths;
    }

    static boolean dfsPath(int u, int t, List<Integer> path, boolean[] visited) {
        path.add(u);
        if (u == t) return true;

        visited[u] = true;

        for (Edge e : graph[u]) {
            // flow used edge: reverse edge has cap = 1
            if (!visited[e.to] && graph[e.to].get(e.rev).cap > 0) {
                graph[e.to].get(e.rev).cap--; // remove used flow
                if (dfsPath(e.to, t, path, visited)) return true;
                graph[e.to].get(e.rev).cap++; // backtrack
            }
        }

        path.remove(path.size() - 1);
        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        N = n + 1;
        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            addEdge(a, b);
        }

        level = new int[N];
        ptr = new int[N];

        int flow = maxFlow(1, n);

        System.out.println(flow);

        List<List<Integer>> paths = extractPaths(1, n);

        for (List<Integer> path : paths) {
            System.out.println(path.size());
            for (int x : path) System.out.print(x + " ");
            System.out.println();
        }
    }
}