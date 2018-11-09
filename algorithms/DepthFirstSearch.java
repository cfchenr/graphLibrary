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
    private Iterator<Integer> neighbors;
    
    /**
     * 
     */
    private WriteLogFile log;

    /**
     * [DFS description]
     * @param  graph       [description]
     * @return             [description]
     * @throws IOException [description]
     */
    public DepthFirstSearch (Graph graph) throws IOException {

        log = new WriteLogFile("output/" + graph.getId() + "/log_dfs.txt");

        graph.sortVertexByDegree();

        vertexList = graph.getVertexList();

        init();

        log.close();
    
    }

    private void init () {

        while (haveNonVisitedVertexes()) {

            stack = new Stack<Vertex>();
            
            Vertex vertex = getFirstNonVisitedVertex();
            
            setVisited(vertex);
            
            setOrder(vertex);
            
            addToStack(vertex);

            while (stack.size() != 0) {

                vertex = getFromStack();
                
                neighbors = getAllSucessors(vertex);
            
                while (neighbors.hasNext()) {
            
                    Vertex n = getNextSucessor(neighbors);
            
                    if (!getVisited(n) && !haveNonVisitedPredecessors(n)) {
            
                        setVisited(n);
                        
                        setOrder(n);
                        
                        addToStack(n);
            
                    }
            
                }

            }

        }

    }

    /**
     * [getFirstNonVisitedVertex description]
     * @return [description]
     */
    private Vertex getFirstNonVisitedVertex () {

        for (int i = 0; i < vertexList.size(); i++)

            if (!vertexList.get(i).isVisited())
            
                return vertexList.get(i);
        
        return vertexList.get(0);
    
    }

    /**
     * [haveNonVisitedVertexes description]
     * @return [description]
     */
    private boolean haveNonVisitedVertexes () {

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
     * [setOrder description]
     * @param vertex [description]
     */
    private void setOrder (Vertex vertex) {

        vertex.setOrder(++orderIndex);
        
        log.writef("Set", "in " + orderIndex + " the order of vertex ", Integer.toString(vertex.getId()+1));        
    
    }

    /**
     * [setVisited description]
     * @param vertex [description]
     */
    private void setVisited (Vertex vertex) {

        vertex.setVisited();
    
        log.writef("Set","as visited vertex", Integer.toString(vertex.getId()+1));        
    
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
     * [getFromStack description]
     * @return [description]
     */
    private Vertex getFromStack () {

        Vertex vertex = stack.pop();
    
        log.writef("Remove","from the stack vertex", Integer.toString(vertex.getId()+1));        
        
        return vertex;
    
    }

    /**
     * [getAllSucessors description]
     * @param  vertex [description]
     * @return        [description]
     */
    private Iterator<Integer> getAllSucessors (Vertex vertex) {

        log.write("Get","the sucessors of vertex", Integer.toString(vertex.getId()+1));      
        
        ArrayList<Integer> temp1 = vertex.getSucessor();
        
        if (!(temp1.size() > 0))
        
            log.write("Without sucessors");
        
        else
        
            for (int i = 0; i < temp1.size(); i++)
        
                log.write(Integer.toString(vertexList.get(temp1.get(i)).getId()+1));
        
        log.writeln();
        
        return vertex.getSucessor().listIterator();
    
    }

    /**
     * [getNextSucessor description]
     * @param  neighbors [description]
     * @return           [description]
     */
    private Vertex getNextSucessor (Iterator<Integer> neighbors) {

        Vertex n = vertexList.get(neighbors.next());
    
        log.writef("Analyze", "vertex", Integer.toString(n.getId()+1));      
    
        return n;
    
    }

    /**
     * [haveNonVisitedPredecessors description]
     * @param  vertex [description]
     * @return        [description]
     */
    private boolean haveNonVisitedPredecessors (Vertex vertex) {

        log.write("Get", "the predecessors of vertex", Integer.toString(vertex.getId()+1));      
        
        if (!(vertex.getPredecessor().size() > 0))
        
            log.write("Without predecessors");
        
        else
        
            for (int i = 0; i < vertex.getPredecessor().size(); i++)
        
                log.write(Integer.toString(vertexList.get(vertex.getPredecessor().get(i)).getId()+1));
        
        log.writeln();
        
        for (int i = 0; i < vertex.getPredecessor().size(); i++) {
        
            log.write("Check", "that vertex " + (vertexList.get(vertex.getPredecessor().get(i)).getId()+1) + " is a non visited predecessor of vertex", Integer.toString(vertex.getId()+1));        
        
            if (!vertexList.get(vertex.getPredecessor().get(i)).isVisited()) {
        
                log.writeln("Yes");
        
                return true;
        
            } else        
        
                log.writeln("No");

        }
        
        return false;

    }

    /**
     * [getVisited description]
     * @param  vertex [description]
     * @return        [description]
     */
    private boolean getVisited (Vertex vertex) {

        log.write("Check", "if has already visited vertex", Integer.toString(vertex.getId()+1));        
        
        if (vertex.isVisited()) {
        
            log.writeln("Yes");

            return true;
        
        } else {
        
            log.writeln("No");

            return false;
        
        }

    }

}