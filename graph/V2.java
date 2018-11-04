package graph;
import java.util.*; 
import java.io.*;

public class V2 extends V1 {
    
    /**
     * [V2 description]
     * @param  graph                 [description]
     * @return                       [description]
     * @throws IOException           [description]
     */
    public V2 (Graph graph) throws IOException {

        super();

        log = new WriteLogFile(graph.getName() + "/log_V2.txt");

        pw = new PrintWriter(new File(graph.getName() + "/id_color_V2.csv"));
        
        init(graph);

    }

    @Override
    protected void init (Graph graph) {

        vertexList = graph.getVertexList();
        
        setColorVertex(vertexList.get(vertexList.size()-1));
        
        for (int j = vertexList.size()-2; j >= 0; j--) {
        
            saveNeighborColors(j);
        
            setColorVertex(vertexList.get(j));
        
        }
        
        sb.append("id,Color\n");
        
        for (int p = 0; p < graph.getVertexNumber(); p++)
        
            sb.append((vertexList.get(p).getName()+1) + "," + (vertexList.get(p).getColor()) + "\n");
        
        pw.write(sb.toString());
        
        pw.close();
        
        log.close();


    }

    /**
     * [saveNeighborColors description]
     * @param j [description]
     */
    @Override
    protected void saveNeighborColors (int j) {
    
        colors = new LinkedList<Integer>();
    
        log.writef("Clear", "queue of colors");        

        for (int i = vertexList.size()-1; i > j; i--) {

            log.write("Check", "if vertex " + Integer.toString(vertexList.get(j).getName()+1) + " is neighbor of vertex", Integer.toString(vertexList.get(i).getName()+1));        
            
            if (vertexList.get(j).isNeighbor(vertexList.get(i).getName())) {

                log.writeln("Yes");

                log.writef("Add", Integer.toString(vertexList.get(i).getColor()) + " to queue of colors");        

                colors.add(vertexList.get(i).getColor());

            } else

                log.writeln("No");

        }
    
    }

}