import graph.*;

import java.lang.System.*;
import java.util.*; 
import java.io.*;

public class programTest {

    public static void main (String[] args) throws IOException {

        Graph graph = new Graph("matrix/" + args[0] + ".txt");
        
        V3 v3;

        int numIt = 100;
        for (int it = 0; it < numIt; it++)
            v3 = new V3(graph);

        DFS dfs = new DFS(graph);

        for (int i = 0; i < graph.getVertexList().size(); i++)
            System.out.println((graph.getVertexList().get(i).getName()+1) + " has color " + graph.getVertexList().get(i).getColor());

        for (int i = 0; i < graph.getVertexList().size(); i++)
            System.out.println((graph.getVertexList().get(i).getName()+1) + " was visited in " + graph.getVertexList().get(i).getOrder() + " place");


    }

/*    public static void v1 (ArrayList<Vertex> vertexList) {

        vertexList.get(0).setColor(1);

        for (int k = 1; k < graph.getVertexNumber(); k++) {

            Queue<Integer> colors = new LinkedList<Integer>();

            for (int y = 0; y < k; y++) {

                if (graph.isNeighbor(k, y)) colors.add(vertexList.get(y).getColor());

            }

            int c = 1;

            while (colors.contains(c))
                c++;

            vertexList.get(k).setColor(c);

        }

        for (int p = 0; p < graph.getVertexNumber(); p++) {
         
            //pw_log.println("Vertex " + vertexList.get(p).getName() + " with color: " + vertexList.get(p).getColor());

            if (vertexList.get(p).getColor() >= maxColor_v1) maxColor_v1 = vertexList.get(p).getColor();
        
        }

        // Get the Java runtime
        Runtime runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
        // Calculate the used memory
        long memory = runtime.totalMemory() - runtime.freeMemory();
        //pw_log.println("Used memory: " + memory + " bytes");

        mem_v1 = memory;
        total_mem[0] += mem_v1;
       
    }

    public static void v2 (ArrayList<Vertex> vertexList) throws FileNotFoundException {

        PrintWriter pw = new PrintWriter(new File("matrix_color_v2.csv"));;
        StringBuilder sb = new StringBuilder(); ;

        vertexList.get(0).setColor(1);

        for (int k = graph.getVertexNumber()-1; k >= 0; k--) {

            Queue<Integer> colors = new LinkedList<Integer>();

            for (int y = graph.getVertexNumber()-1; y >= k; y--) {

                if (graph.isNeighbor(k, y)) colors.add(vertexList.get(y).getColor());

            }

            int c = 1;

            while (colors.contains(c))
                c++;

            vertexList.get(k).setColor(c);

        }

        sb.append("id");
        sb.append(",");
        sb.append("Color");
        sb.append("\n");

        for (int p = 0; p < graph.getVertexNumber(); p++) {

            //pw_log.println("Vertex " + vertexList.get(p).getName() + " with color: " + vertexList.get(p).getColor());

            sb.append(vertexList.get(p).getName()+1);
            sb.append(",");
            sb.append(vertexList.get(p).getColor());
            sb.append("\n");

            if (vertexList.get(p).getColor() >= maxColor_v2) maxColor_v2 = vertexList.get(p).getColor();

        }

        pw.write(sb.toString());
        pw.close();

        // Get the Java runtime
        Runtime runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
        // Calculate the used memory
        long memory = runtime.totalMemory() - runtime.freeMemory();
        //pw_log.println("Used memory: " + memory + " bytes");

        mem_v2 = memory;
        total_mem[1] += mem_v2;      

    }*/

    

}