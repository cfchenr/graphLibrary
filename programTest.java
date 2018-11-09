import graph.*;
import algorithms.*;
import java.io.*;

public class programTest {

    public static void main (String[] args) throws IOException {

        Graph [] graph = new Graph [args.length];

        for (int i = 0; i < args.length; i++)

            graph[i] = new Graph("matrix/" + args[i] + ".txt");

        ColorizeVersion1 [] v1 = new ColorizeVersion1 [args.length];

        ColorizeVersion2 [] v2 = new ColorizeVersion2 [args.length];

        ColorizeVersion3 [] v3 = new ColorizeVersion3 [args.length];

        DepthFirstSearch [] dfs = new DepthFirstSearch [args.length];

        for (int i = 0; i < args.length; i++) {

            v1[i] = new ColorizeVersion1(graph[i]);

            v2[i] = new ColorizeVersion2(graph[i]);

            v3[i] = new ColorizeVersion3(graph[i]);
            
            dfs[i] = new DepthFirstSearch(graph[i]);

        }

        System.out.println("Success program!");
        
    }

}