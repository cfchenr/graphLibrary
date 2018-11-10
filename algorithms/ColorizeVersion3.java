package algorithms;
import graph.*;
import log.*;
import java.io.*;

public class ColorizeVersion3 extends ColorizeVersion2 {
    
    /**
     * [V3 description]
     * @param  graph                 [description]
     * @return                       [description]
     * @throws IOException           [description]
     */
    public ColorizeVersion3 (Graph graph) throws IOException {

        super();

        this.graph = graph;

        vertexList = graph.getVertexList();

        log = new WriteLogFile("output/" + graph.getId() + "/log_V3.txt");

        pw = new PrintWriter(new File("output/" + graph.getId() + "/id_color_V3.csv"));

    }

    public void sortVertexByDegree () {

        graph.sortVertexByDegree();

        vertexList = graph.getVertexList();

    }

}