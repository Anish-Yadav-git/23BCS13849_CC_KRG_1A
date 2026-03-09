package 09-03-26;

public class FloydWorshell {
    public static void main(String[] args) {

        int INF = 99999;
        
        int[][] graph = {
            {0, 3, INF, 5},
            {2, 0, INF, 4},
            {INF, 1, 0, INF},
            {INF, INF, 2, 0}
        };

        int V = graph.length;

        int[][] dist = new int[V][V];

        // Copy graph into dist matrix
        for(int i = 0; i < V; i++){
            for(int j = 0; j < V; j++){
                dist[i][j] = graph[i][j];
            }
        }

        // Floyd Warshall Algorithm
        for(int k = 0; k < V; k++){
            for(int i = 0; i < V; i++){
                for(int j = 0; j < V; j++){
                    if(dist[i][k] + dist[k][j] < dist[i][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // Print shortest distance matrix
        System.out.println("Shortest distances between every pair of vertices:");

        for(int i = 0; i < V; i++){
            for(int j = 0; j < V; j++){
                if(dist[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }
}
