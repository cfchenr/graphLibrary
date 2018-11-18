import graph.*;
import algorithms.*;
import java.util.*;
import java.io.*;

public class programTest {

    public static Graph [] graph;

    public static void main (String[] args) throws IOException {

        graph = new Graph [args.length];

        for (int i = 0; i < args.length; i++)

            graph[i] = new Graph("matrix/" + args[i] + ".txt");

        ColorizeVersion1 [] v1 = new ColorizeVersion1 [args.length];

        ColorizeVersion2 [] v2 = new ColorizeVersion2 [args.length];

        ColorizeVersion3 [] v3 = new ColorizeVersion3 [args.length];

        DepthFirstSearch [] dfs = new DepthFirstSearch [args.length];

        for (int i = 0; i < args.length; i++) {

            v1[i] = new ColorizeVersion1(graph[i]);
            colorizeVersion1(v1[i]);

            v2[i] = new ColorizeVersion2(graph[i]);
            colorizeVersion2(v2[i]);

            v3[i] = new ColorizeVersion3(graph[i]);
            colorizeVersion3(v3[i]);
            
            dfs[i] = new DepthFirstSearch(graph[i]);
            depth_first_search(dfs[i]);

        }
        
    }

    public static void colorizeVersion1 (ColorizeVersion1 v1) {

        v1.setColorVertex(0);
        
        for (int j = 1; j < v1.getVertexList().size(); j++) {
        
            v1.saveNeighborColors(j);
        
            v1.setColorVertex(j);
        
        }

        v1.finish();

        //System.out.println(checkColors(v1));

    }

    public static void colorizeVersion2 (ColorizeVersion2 v2) {

        v2.setColorVertex(-1);
        
        for (int j = v2.getVertexList().size()-2; j >= 0; j--) {
        
            v2.saveNeighborColors(j);
        
            v2.setColorVertex(j);
        
        }

        v2.finish();

        //System.out.println(checkColors(v2));

    }

    public static void colorizeVersion3 (ColorizeVersion3 v3) {

        colorizeVersion2(v3);

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

    // public static boolean checkColors (ColorizeVersion1 v3) {

    //     ArrayList<Vertex> vertexList = v3.getVertexList();

    //     for (int i = 0; i < vertexList.size(); i++) {

    //         int color = vertexList.get(i).getColor();

    //         for (int j = 0; j < vertexList.size(); j++) {

    //             if (i != j && vertexList.get(i).isNeighbor(vertexList.get(j).getId())) {

    //                 System.out.print((vertexList.get(i).getId()+1) + " is neighbor of " + (vertexList.get(j).getId()+1));

    //                 if (color == vertexList.get(j).getColor()) {

    //                     System.out.println(": same colors");
                
    //                     return false;

    //                 } else

    //                     System.out.println(": different colors");

    //             }

    //         }

    //     }

    //     return true;

    // }

}