package algorithms;
import graph.*;
import log.*;
import java.util.*;
import java.io.*;

public class ColorizeVersion3 extends ColorizeVersion2 {

    /**
     * 
     */
    Hashtable<Integer, Integer> colors;
    
    /**
     * 
     */
    protected ArrayList<Vertex> vertexByDegree;

    /**
     * [V3 description]
     * @param  graph                 [description]
     * @return                       [description]
     * @throws IOException           [description]
     */
    public ColorizeVersion3 (Graph graph) throws IOException {

        super(graph, "V3");

        colors = new Hashtable<Integer, Integer>();

        vertexByDegree = graph.getVertexByDegree();

    }

    /**
     * 
     * @param id
     */
    public void setColorVertex (int id) {

        Vertex vertex;

        if (id == -1)   
            vertex = vertexList.get(vertexByDegree.get(vertexByDegree.size()-1).getId());

        else
            vertex = vertexList.get(vertexByDegree.get(id).getId());

        log.write("Get", "first color that is available for vertex", Integer.toString(vertex.getId()+1));        

        int color = getFirstColorAvailable();

        log.writeln("Available[" + Integer.toString(color) + "]");

        log.writef("Set", "in " + color + " the color of vertex ", Integer.toString(vertex.getId()+1), "");        
    
        vertex.setColor(color);
    
    }

    /**
     * [saveNeighborColors description]
     * @param j [description]
     */
    public void saveNeighborColors (int j) {

        log.writef("Clear", "table of colors");        
    
        colors = new Hashtable<Integer, Integer>();    

        for (int i = 0; i < vertexList.get(vertexByDegree.get(j).getId()).getNeighborList().size(); i++) {

                log.writef("Add", vertexList.get(vertexList.get(vertexByDegree.get(j).getId()).getNeighborList().get(i)).getColor() + " to table of colors", "", " from vertex " + Integer.toString(vertexList.get(vertexList.get(vertexByDegree.get(j).getId()).getNeighborList().get(i)).getId()+1));        
            
                colors.put(vertexList.get(vertexList.get(vertexByDegree.get(j).getId()).getNeighborList().get(i)).getColor(), 1);
        
        }
    
    }

    /**
     * [getFirstColorAvailable description]
     * @return [description]
     */
    protected int getFirstColorAvailable () {
    
        int k = 1;

        while(colors.containsKey(k)) {

            log.write(Integer.toString(k));

            k++;

        }

        return k;
    
    }

}