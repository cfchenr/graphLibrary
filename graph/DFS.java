package graph;
import java.util.*; 
import java.io.*;
import java.util.Date;
import java.sql.Timestamp;

public class DFS {
    
    /**
     * 
     */
    private ArrayList<Vertex> vertexList;
    
    /**
     * 
     */
    private Integer orderIndex = 0;
    
    /**
     * 
     */
    private Stack<Integer> stack;
    
    /**
     * 
     */
    private PrintWriter log_dfs;

    /**
     * [DFS description]
     * @param  graph       [description]
     * @return             [description]
     * @throws IOException [description]
     */
    public DFS (Graph graph) throws IOException {

        log_dfs = new PrintWriter(new File(graph.getName() + "/log_dfs.txt"));

        vertexList = graph.getVertexList();

        log_dfs.printf("%-30s%-20s%-60s%-10s%s\n","[timestamp]", "action","details","vertex"," observations");    

        int orderVertexList = 0;  

        while (haveNonVisitedVertexes()) {

            stack = new Stack<Integer>();
            
            Integer vertex = getFirstNonVisitedVertex();
            
            setVisited(vertexList.get(vertex));
            
            setOrder(vertexList.get(vertex));
            
            addToStack(vertexList.get(vertex));

            while (stack.size() != 0) {

                vertex = getFromStack();
                
                Iterator<Integer> neighbors = getAllSucessors(vertexList.get(vertex));
            
                while (neighbors.hasNext()) {
            
                    Integer n = getNextSucessor(neighbors);
            
                    if (!getVisited(vertexList.get(n)) && !haveNonVisitedPredecessors(vertexList.get(n))) {
            
                        setVisited(vertexList.get(n));
                        
                        setOrder(vertexList.get(n));
                        
                        addToStack(vertexList.get(n));
            
                    }
            
                }

            }

        }

        log_dfs.close();
    
    }

    /**
     * [getFirstNonVisitedVertex description]
     * @return [description]
     */
    private Integer getFirstNonVisitedVertex () {

        for (int i = 0; i < vertexList.size(); i++)

            if (!vertexList.get(i).isVisited())
            
                return vertexList.get(i).getName();
        
        return vertexList.get(0).getName();
    
    }

    /**
     * [haveNonVisitedVertexes description]
     * @return [description]
     */
    private boolean haveNonVisitedVertexes () {

        log_dfs.printf("%-30s%-20s%-60s%-10s","["+new Timestamp(new Date().getTime())+"]", "Check", "if the graph contains a non visited vertex","-");        
        
        for (int i = 0; i < vertexList.size(); i++)

            if (!vertexList.get(i).isVisited()) {
        
                log_dfs.println(" Yes. Vertex " + (vertexList.get(i).getName()+1));
            
                return true;
        
            }
        
        log_dfs.println(" No");

        return false;

    }

    /**
     * [setOrder description]
     * @param vertex [description]
     */
    private void setOrder (Vertex vertex) {

        vertex.setOrder(++orderIndex);
        
        log_dfs.printf("%-30s%-20s%-60s%-10s\n","["+new Timestamp(new Date().getTime())+"]", "Set", "in " + orderIndex + " the order of vertex ", (vertex.getName()+1));        
    
    }

    /**
     * [setVisited description]
     * @param vertex [description]
     */
    private void setVisited (Vertex vertex) {

        vertex.setVisited();
    
        log_dfs.printf("%-30s%-20s%-60s%-10s\n","["+new Timestamp(new Date().getTime())+"]", "Set","as visited vertex",(vertex.getName()+1));        
    
    }

    /**
     * [addToStack description]
     * @param vertex [description]
     */
    private void addToStack (Vertex vertex) {

        stack.add(vertex.getName());
    
        log_dfs.printf("%-30s%-20s%-60s%-10s\n","["+new Timestamp(new Date().getTime())+"]", "Add","to the stack vertex",(vertex.getName()+1));        
    
    }

    /**
     * [getFromStack description]
     * @return [description]
     */
    private Integer getFromStack () {

        Integer vertex = stack.pop();
    
        log_dfs.printf("%-30s%-20s%-60s%-10s\n","["+new Timestamp(new Date().getTime())+"]", "Remove","from the stack vertex",(vertexList.get(vertex).getName()+1));        
        
        return vertex;
    
    }

    /**
     * [getAllSucessors description]
     * @param  vertex [description]
     * @return        [description]
     */
    private Iterator<Integer> getAllSucessors (Vertex vertex) {

        log_dfs.printf("%-30s%-20s%-60s%-10s","["+new Timestamp(new Date().getTime())+"]", "Get","the sucessors of vertex",(vertex.getName()+1));      
        
        ArrayList<Integer> temp1 = vertex.getSucessor();
        
        if (!(temp1.size() > 0))
        
            log_dfs.print(" Without sucessors");
        else
        
            for (int i = 0; i < temp1.size(); i++)
        
                log_dfs.print(" " + (vertexList.get(temp1.get(i)).getName()+1) + " ");
        
        log_dfs.println();
        
        return vertex.getSucessor().listIterator();
    
    }

    /**
     * [getNextSucessor description]
     * @param  neighbors [description]
     * @return           [description]
     */
    private Integer getNextSucessor (Iterator<Integer> neighbors) {

        Integer n = neighbors.next();
    
        log_dfs.printf("%-30s%-20s%-60s%-10s\n","["+new Timestamp(new Date().getTime())+"]", "Analyze", "vertex",(vertexList.get(n).getName()+1));      
    
        return n;
    
    }

    /**
     * [haveNonVisitedPredecessors description]
     * @param  vertex [description]
     * @return        [description]
     */
    private boolean haveNonVisitedPredecessors (Vertex vertex) {

        log_dfs.printf("%-30s%-20s%-60s%-10s","["+new Timestamp(new Date().getTime())+"]", "Get", "the predecessors of vertex",(vertex.getName()+1));      
        
        if (!(vertex.getPredecessor().size() > 0))
        
            log_dfs.print(" Without predecessors");
        
        else
        
            for (int i = 0; i < vertex.getPredecessor().size(); i++)
        
                log_dfs.print(" " + (vertexList.get(vertex.getPredecessor().get(i)).getName()+1) + " ");
        
        log_dfs.println();
        
        for (int i = 0; i < vertex.getPredecessor().size(); i++) {
        
            log_dfs.printf("%-30s%-20s%-60s%-10s","["+new Timestamp(new Date().getTime())+"]", "Check", "that vertex " + (vertexList.get(vertex.getPredecessor().get(i)).getName()+1) + " is a non visited predecessor of vertex",(vertex.getName()+1));        
        
            if (!vertexList.get(vertex.getPredecessor().get(i)).isVisited()) {
        
                log_dfs.println(" Yes");
        
                return true;
        
            } else        
        
                log_dfs.println(" No");

        }
        
        return false;

    }

    /**
     * [getVisited description]
     * @param  vertex [description]
     * @return        [description]
     */
    private boolean getVisited (Vertex vertex) {

        log_dfs.printf("%-30s%-20s%-60s%-10s","["+new Timestamp(new Date().getTime())+"]", "Check", "if has already visited vertex", (vertex.getName()+1));        
        
        if (vertex.isVisited()) {
        
            log_dfs.println(" Yes");

            return true;
        
        } else {
        
            log_dfs.println(" No");

            return false;
        
        }

    }

}