package algorithms;
import graph.*;
import log.*;
import java.util.*; 
import java.io.*;

public class ColorizeVersion2 extends ColorizeVersion1 {

    /**
     * [V2 description]
     * @param  graph                 [description]
     * @return                       [description]
     * @throws IOException           [description]
     */
    public ColorizeVersion2 (Graph graph) throws IOException {

        super(graph, "V2");

    }

    public ColorizeVersion2 (Graph graph, String version) throws IOException {

        super(graph, version);

    }

    /**
     * [saveNeighborColors description]
     * @param j [description]
     */
    public void saveNeighborColors (int j) {
    
        colors = new LinkedList<Integer>();
    
        log.writef("Clear", "queue of colors");        

        for (int i = vertexList.size()-1; i > j; i--) {

            log.write("Check", "if vertex " + Integer.toString(vertexList.get(j).getId()+1) + " is neighbor of vertex", Integer.toString(vertexList.get(i).getId()+1));        
            
            if (vertexList.get(j).isNeighbor(vertexList.get(i).getId())) {

                log.writeln("Yes");

                log.writef("Add", Integer.toString(vertexList.get(i).getColor()) + " to queue of colors");        

                colors.add(vertexList.get(i).getColor());

            } else

                log.writeln("No");

        }
    
    }
    
}
