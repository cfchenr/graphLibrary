package graph;
import java.util.*; 
import java.io.*;

public class V3 extends V1 {
    
    /**
     * [V3 description]
     * @param  graph                 [description]
     * @return                       [description]
     * @throws IOException           [description]
     */
    public V3 (Graph graph) throws IOException {

        super();

        log = new WriteLogFile(graph.getName() + "/log_V3.txt");

        pw = new PrintWriter(new File(graph.getName() + "/id_color_V3.csv"));
        
        init(graph);

    }

    @Override
    protected void init (Graph graph) {

        graph.sortVertexByDegree();

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

}