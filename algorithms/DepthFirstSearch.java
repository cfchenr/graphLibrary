package algorithms;
import graph.*;
import log.*;
import java.util.*; 
import java.io.*;

public class DepthFirstSearch {
    
    /**
     * 
     */
    private ArrayList<Vertex> vertexList;
    
    /**
     * 
     */
    private int orderIndex = 0;
    
    /**
     * 
     */
    private Stack<Vertex> stack;
    
    /**
     * 
     */
    private WriteLogFile log;

    /**
     * 
     */
    protected PrintWriter pw;

    /**
     * [StringBuilder description]
     * @return [description]
     */
    protected StringBuilder sb = new StringBuilder();

    /**
     * [DFS description]
     * @param  graph       [description]
     * @return             [description]
     * @throws IOException [description]
     */
    public DepthFirstSearch (Graph graph) throws IOException {

        log = new WriteLogFile("output/" + graph.getId() + "/log_dfs.txt");

        //graph.sortVertexByDegree();

        vertexList = graph.getVertexList();

        stack = new Stack<Vertex>();

        pw = new PrintWriter(new File("output/" + graph.getId() + "/id_order.csv"));

    }

    /**
     * [getFirstNonVisitedVertex description]
     * @return [description]
     */
    public Vertex getFirstNonVisitedVertex () {

        for (int i = 0; i < vertexList.size(); i++)

            if (!vertexList.get(i).isVisited())
            
                return vertexList.get(i);
        
        return vertexList.get(0);
    
    }

    /**
     * [haveNonVisitedVertexes description]
     * @return [description]
     */
    public boolean haveNonVisitedVertexes () {

        log.write("Check", "if the graph contains a non visited vertex", "");        
        
        for (int i = 0; i < vertexList.size(); i++)

            if (!vertexList.get(i).isVisited()) {
        
                log.writeln("Yes. Vertex " + (vertexList.get(i).getId()+1));
            
                return true;
        
            }
        
        log.writeln("No");

        return false;

    }

    /**
     * [setVisited description]
     * @param vertex [description]
     */
    public void setVisited (Vertex vertex) {

        vertex.setVisited();
    
        log.writef("Set","as visited vertex", Integer.toString(vertex.getId()+1));        
  
        setOrder(vertex);

    }

    /**
     * [getVisited description]
     * @param  vertex [description]
     * @return        [description]
     */
    public boolean getVisited (Vertex vertex) {

        log.write("Check", "if has already visited vertex", Integer.toString(vertex.getId()+1));        
        
        if (vertex.isVisited()) {
        
            log.writeln("Yes");

            return true;
        
        } else {
        
            log.writeln("No");

            return false;
        
        }

    }

    /**
     * [setOrder description]
     * @param vertex [description]
     */
    private void setOrder (Vertex vertex) {

        vertex.setOrder(++orderIndex);

        System.out.println((vertex.getId()+1) + " [" + orderIndex + "]");
        
        log.writef("Set", "in " + orderIndex + " the order of vertex ", Integer.toString(vertex.getId()+1));        
    
        addToStack(vertex);

    }

    /**
     * [addToStack description]
     * @param vertex [description]
     */
    private void addToStack (Vertex vertex) {

        stack.add(vertex);
    
        log.writef("Add","to the stack vertex", Integer.toString(vertex.getId()+1));        
    
    }

    /**
     * @return
     */    
    public Stack<Vertex> getStack () {

        return stack;

    }


    /**
     * [getTopStack description]
     * @return [description]
     */
    public Vertex getTopStack () {

        Vertex vertex = stack.pop();
    
        log.writef("Remove","from the stack vertex", Integer.toString(vertex.getId()+1));        
        
        return vertex;
    
    }

    /**
     * [getAllSucessors description]
     * @param  vertex [description]
     * @return        [description]
     */
    public Iterator<Integer> getAllSucessors (Vertex vertex) {

        log.write("Get","the sucessors of vertex", Integer.toString(vertex.getId()+1));      
        
        ArrayList<Integer> temp1 = vertex.getSucessorList();
        
        if (!(temp1.size() > 0))
        
            log.write("Without sucessors");
        
        else
        
            for (int i = 0; i < temp1.size(); i++)
        
                log.write(Integer.toString(vertexList.get(temp1.get(i)).getId()+1));
        
        log.writeln();
        
        return vertex.getSucessorList().listIterator();
    
    }

    /**
     * [getNextSucessor description]
     * @param  neighbors [description]
     * @return           [description]
     */
    public Vertex getNextSucessor (Iterator<Integer> neighbors) {

        Vertex n = vertexList.get(neighbors.next());
    
        log.writef("Analyze", "vertex", Integer.toString(n.getId()+1));      
    
        return n;
    
    }

    /**
     * [haveNonVisitedPredecessors description]
     * @param  vertex [description]
     * @return        [description]
     */
    public boolean haveNonVisitedPredecessors (Vertex vertex) {

        log.write("Get", "the predecessors of vertex", Integer.toString(vertex.getId()+1));      
        
        if (!(vertex.getPredecessorList().size() > 0))
        
            log.write("Without predecessors");
        
        else
        
            for (int i = 0; i < vertex.getPredecessorList().size(); i++)
        
                log.write(Integer.toString(vertexList.get(vertex.getPredecessorList().get(i)).getId()+1));
        
        log.writeln();
        
        for (int i = 0; i < vertex.getPredecessorList().size(); i++) {
        
            log.write("Check", "that vertex " + (vertexList.get(vertex.getPredecessorList().get(i)).getId()+1) + " is a non visited predecessor of vertex", Integer.toString(vertex.getId()+1));        
        
            if (!vertexList.get(vertex.getPredecessorList().get(i)).isVisited()) {
        
                log.writeln("Yes");
        
                return true;
        
            } else        
        
                log.writeln("No");

        }
        
        return false;

    }

    public void finish () {

        sb.append("id,Order\n");
        
        for (int p = 0; p < vertexList.size(); p++)
        
            sb.append((vertexList.get(p).getId()+1) + "," + (vertexList.get(p).getOrder()) + "\n");
        
        pw.write(sb.toString());
        
        pw.close();

        log.close();

    }

}