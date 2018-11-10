import graph.*;
import algorithms.*;
import java.util.*;
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
            //colorizeVersion1(v1[i])

            v2[i] = new ColorizeVersion2(graph[i]);
            //colorizeVersion2(v2[i])

            v3[i] = new ColorizeVersion3(graph[i]);
            //colorizeVersion3(v3[i])
            
            dfs[i] = new DepthFirstSearch(graph[i]);
            depth_first_search(dfs[i]);

        }

        System.out.println("Success program!");
        
    }

    public static void depth_first_search (DepthFirstSearch dfs) {

        Vertex vertex;

        Iterator<Integer> neighbors;

        while (dfs.haveNonVisitedVertexes()) {

            vertex = dfs.getFirstNonVisitedVertex();
            
            dfs.setVisited(vertex);

            while (dfs.getStack().size() != 0) {

                vertex = dfs.getTopStack();
                
                neighbors = dfs.getAllSucessors(vertex);
            
                while (neighbors.hasNext()) {
            
                    Vertex n = dfs.getNextSucessor(neighbors);
            
                    if (!dfs.getVisited(n) && !dfs.haveNonVisitedPredecessors(n))
            
                        dfs.setVisited(n);
            
                }

            }

        }

        dfs.finish();

    }

}