package algorithms;
import graph.*;
import log.*;
import java.util.*; 
import java.io.*;

public class ColorizeVersion1 {

    /**
     * 
     */
    protected ArrayList<Vertex> vertexList;
    
    /**
     * 
     */
    protected Queue<Integer> colors;
    
    /**
     * 
     */
    protected WriteLogFile log;
    
    /**
     * 
     */
    protected PrintWriter pw;
    
    /**
     *
     */
    protected StringBuilder sb = new StringBuilder();

    public ColorizeVersion1 (Graph graph, String version) throws IOException {

        colors = new LinkedList<Integer>();

        graph.setDefaultColorVertexes();

        vertexList = graph.getVertexList();

        log = new WriteLogFile("output/" + graph.getId() + "/log_" + version + ".txt");

        pw = new PrintWriter(new File("output/" + graph.getId() + "/id_color_" + version + ".csv"));

    }

    /**
     * [V1 description]
     * @param  graph                 [description]
     * @return                       [description]
     * @throws IOException           [description]
     */
    public ColorizeVersion1 (Graph graph) throws IOException {

        this(graph, "V1");
        
    }

    /**
     * 
     * @param id
     */
    public void setColorVertex (int id) {

        Vertex vertex;

        if (id == -1)   
            vertex = vertexList.get(vertexList.size()-1);

        else
            vertex = vertexList.get(id);

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
    
        colors = new LinkedList<Integer>();
    
        log.writef("Clear", "queue of colors");   
        
        for (int i = 0; i < j; i++) {

            log.write("Check", "if vertex " + Integer.toString(vertexList.get(j).getId()+1) + " is neighbor of vertex", Integer.toString(vertexList.get(i).getId()+1));        
            
            if (vertexList.get(j).isNeighbor(vertexList.get(i).getId())) {

                log.writeln("Yes");

                log.writef("Add", Integer.toString(vertexList.get(i).getColor()) + " to queue of colors");        

                colors.add(vertexList.get(i).getColor());

            } else

                log.writeln("No");

        }

    }

    /**
     * [getFirstColorAvailable description]
     * @return [description]
     */
    protected int getFirstColorAvailable () {
    
        int k = 1;

        while(colors.contains(k)) {

            log.write(Integer.toString(k));

            k++;

        }
    
        return k;
    
    }

    public void finish () {

        sb.append("id,Color\n");
        
        for (int p = 0; p < vertexList.size(); p++)
        
            sb.append((vertexList.get(p).getId()+1) + "," + (vertexList.get(p).getColor()) + "\n");
        
        pw.write(sb.toString());
        
        pw.close();
        
        log.close();

    }

    /**
     * 
     */
    public ArrayList<Vertex> getVertexList () {

        return vertexList;

    }

}
