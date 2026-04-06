package Graph;

import java.util.ArrayList;
import java.util.Scanner;

public class BellmanFord {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();

        for(int i=0; i<4; i++){
            adj.add(new ArrayList<>());
        }

        adj.get(1).add(new Pair(2,4));
        adj.get(1).add(new Pair(3, 5));
        adj.get(2).add(new Pair(3, -2));
        adj.get(3).add(new Pair(4,4));
        adj.get(2).add(new Pair(4, -2));
    }
}
