import static java.lang.System.*;
import java.util.*; 
import java.io.*;

public class sma_1 {

    static int i = 0;
    static String [] line;
    static Graph graph;
    static PrintWriter pw_log;
    static Integer [] mem_diff = new Integer[6];
    static ArrayList<Integer> order = new ArrayList<Integer>();
    static long mem_v1, mem_v2, mem_v3;
    static long [] total_mem = new long[3];
    static int maxColor_v1, maxColor_v2, maxColor_v3;
        
    public static void main (String[] args) throws IOException {

        String file = "matrix_adj.txt";
        graph = new Graph(file);
        ArrayList<Vertex> vertexList = graph.getVertexList();

        long startTime, stopTime, elapsedTime;
        pw_log = new PrintWriter(new File("log.txt"));

        System.out.print("         _");
        for (int it = 0; it < 100; it++) 
            System.out.print("_");
        System.out.println("_");
        System.out.print("Loading: [");

        maxColor_v1 = 1;
        maxColor_v2 = 1;
        maxColor_v3 = 1;

        long time_v1, time_v2, time_v3;

        long [] total_time = new long[3];
        
        Integer [] time_diff = new Integer[6];

        for (int it = 0; it < 6; it++) {
            time_diff[it] = 0;
            mem_diff[it] = 0;
        }
        
        
        for (int it = 0; it < 3; it++) {
            total_mem[it] = 0;
            total_time[it] = 0;
        }

        int numIt = 100;

        for (int it = 0; it < numIt; it++) {

            System.out.print("=");
         
            startTime = System.currentTimeMillis();
            v1(vertexList);
            stopTime = System.currentTimeMillis();
            elapsedTime = stopTime - startTime;
            time_v1 = elapsedTime;
            total_time[0] += time_v1;

            startTime = System.currentTimeMillis();
            v2(vertexList);
            stopTime = System.currentTimeMillis();
            elapsedTime = stopTime - startTime;
            time_v2 = elapsedTime;
            total_time[1] += time_v2;

            startTime = System.currentTimeMillis();
            v3(vertexList);
            stopTime = System.currentTimeMillis();
            elapsedTime = stopTime - startTime;
            time_v3 = elapsedTime;
            total_time[2] += time_v3;

            dfs(vertexList);
            if (it == 0) {
                pw_log.println("Order by which visited the vertices:");
                for (int it2 = 0; it2 < order.size(); it2++)
                    if (it2 == order.size() - 1)
                        pw_log.print(order.indexOf(it2)+1);
                    else
                        pw_log.print(order.indexOf(it2)+1 + " > ");
                pw_log.println();
                pw_log.println();
            }

            if (time_v3 > time_v2)
                time_diff[0]++;
            else if (time_v3 == time_v2)
                time_diff[1]++;
            else 
                time_diff[2]++;

            if (time_v3 > time_v1)
                time_diff[3]++;
            else if (time_v3 == time_v1)
                time_diff[4]++;
            else 
                time_diff[5]++;

            if (mem_v3 > mem_v2)
                mem_diff[0]++;
            else if (mem_v3 == mem_v2)
                mem_diff[1]++;
            else 
                mem_diff[2]++;

            if (mem_v3 > mem_v1)
                mem_diff[3]++;
            else if (mem_v3 == mem_v1)
                mem_diff[4]++;
            else 
                mem_diff[5]++;

        }

        pw_log.println("version 3 faster than version 2: " + time_diff[2]);
        pw_log.println("version 3 as fast as version 2: " + time_diff[1]);
        pw_log.println("version 3 less faster than version 2: " + time_diff[0]);
        pw_log.println();
        pw_log.println("version 3 faster than version 1: " + time_diff[5]);
        pw_log.println("version 3 as fast as version 1: " + time_diff[4]);
        pw_log.println("version 3 less faster than version 1: " + time_diff[3]);
        pw_log.println();
        pw_log.println("Mean time version 1: " + total_time[0]/numIt + "ms");
        pw_log.println("Mean time version 2: " + total_time[1]/numIt + "ms");
        pw_log.println("Mean time version 3: " + total_time[2]/numIt + "ms");
        pw_log.println();
        pw_log.println("version 3 used less memory than version 3: " + mem_diff[2]);
        pw_log.println("version 3 used as much memory as version 3: " + mem_diff[1]);
        pw_log.println("version 3 used more memory than version 3: " + mem_diff[0]);
        pw_log.println();
        pw_log.println("version 3 used less memory than version 2: " + mem_diff[5]);
        pw_log.println("version 3 used as much memory as version 2: " + mem_diff[4]);
        pw_log.println("version 3 used more memory than version 2: " + mem_diff[3]);
        pw_log.println();
        pw_log.println("Mean memory version 1: " + total_mem[0]/numIt + "bytes");
        pw_log.println("Mean memory version 2: " + total_mem[1]/numIt + "bytes");
        pw_log.println("Mean memory version 3: " + total_mem[2]/numIt + "bytes");
        pw_log.println();
        pw_log.println("Maximum color used in version 1: " + maxColor_v1);
        pw_log.println("Maximum color used in version 2: " + maxColor_v2);
        pw_log.println("Maximum color used in version 3: " + maxColor_v3);

        System.out.println("]");
        pw_log.close();

    }

    public static void v1 (ArrayList<Vertex> vertexList) {

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

    }

    public static void v3 (ArrayList<Vertex> vertexList) throws FileNotFoundException {

        PrintWriter pw = new PrintWriter(new File("matrix_color_v3.csv"));;
        StringBuilder sb = new StringBuilder(); ;

        HashMap<Integer, Integer> vertexDegree = new HashMap<Integer, Integer>();

        for (int i = 0; i < vertexList.size(); i++)
            vertexDegree.put(i, vertexList.get(i).getDegree());

        ArrayList<Integer> vertexDegreeSort = sortVertexByDegree(vertexDegree);

        vertexList.get(vertexDegreeSort.size()-1).setColor(1);

        for (int j = vertexDegreeSort.size()-2; j >= 0; j--) {

            Queue<Integer> colors = new LinkedList<Integer>();

            for (int i = vertexDegreeSort.size()-1; i > j; i--)
                if (graph.isNeighbor(vertexList.get(vertexDegreeSort.get(j)).getName(),vertexList.get(vertexDegreeSort.get(i)).getName()))
                    colors.add(vertexList.get(vertexDegreeSort.get(i)).getColor());

            int k = 1;

            while(colors.contains(k))
                k++;

            vertexList.get(vertexDegreeSort.get(j)).setColor(k);

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

            if (vertexList.get(p).getColor() >= maxColor_v3) maxColor_v3 = vertexList.get(p).getColor();

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

        mem_v3 = memory;
        total_mem[2] += mem_v3;

    }

    public static ArrayList<Integer> sortVertexByDegree (HashMap<Integer, Integer> vertexDegree) {

        List<Integer> mapKeys = new ArrayList<>(vertexDegree.keySet());
        List<Integer> mapValues = new ArrayList<>(vertexDegree.values());
        Collections.sort(mapValues);
        Collections.sort(mapKeys);

        ArrayList<Integer> sortedMap = new ArrayList<>();

        Iterator<Integer> valueIt = mapValues.iterator();

        while (valueIt.hasNext()) {

            Integer val = valueIt.next();
            Iterator<Integer> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {

                Integer key = keyIt.next();
                Integer comp1 = vertexDegree.get(key);
                Integer comp2 = val;

                if (comp1.equals(comp2)) {
                    keyIt.remove();
                    sortedMap.add(key);
                    break;
                }

            }

        }

        return sortedMap;

    }

    public static void dfs (ArrayList<Vertex> vertexList) {

        ArrayList<Vertex> nonVisitedVertexes = new ArrayList<Vertex>();

        for (int i = 0; i < vertexList.size(); i++)
            nonVisitedVertexes.add(vertexList.get(i));

        while (!nonVisitedVertexes.isEmpty()) {

            Stack<Vertex> vertexesWithSucessors = new Stack<Vertex>();
            Vertex vertex = vertexList.get(nonVisitedVertexes.get(0).getName());

            vertexesWithSucessors.add(vertex);
            vertexList.get(vertex.getName()).setVisited();
            nonVisitedVertexes.remove(vertex);

            while (!vertexesWithSucessors.isEmpty()) {

                Integer element = vertexesWithSucessors.pop().getName();
                ArrayList<Integer> sucessors = vertexList.get(element).getSucessor();

                order.add(element);

                for (int i = 0; i < sucessors.size(); i++) {

                    Vertex n = vertexList.get(sucessors.get(i));

                    if (n != null && !(n.isVisited())) {

                        vertexesWithSucessors.add(n);
                        vertexList.get(n.getName()).setVisited();
                        nonVisitedVertexes.remove(n);

                    }

                }

            }

        }

    }

}