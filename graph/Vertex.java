package graph;
import java.util.*; 

public class Vertex {

    /**
     * id, color and order of this vertex
     */
    private int id, color, order;
    
    /**
     * status of this vertex (visited or non visited)
     */
    private boolean visited;
    
    /**
     * list (ArrayList) of integers that indicates the ids of sucessors and predecessors of this vertex
     */
    private ArrayList<Integer> successorList, predecessorList;
    
    /**
     * @param vertex (index of one new vertex)
     * instance an id vertex, a color (initially 0), visited status (initially false) and a list of neighbors (sucessors and predecessors) 
     * for this new vertex
     */
    public Vertex (int id) {
    
        this.id = id;
    
        color = 0;
    
        visited = false;
    
        order = 0;
    
        successorList = new ArrayList<Integer>();
    
        predecessorList = new ArrayList<Integer>();
    
    }

    /** 
     * @return the id of this vertex
     */
    public int getId () {
    
        return id;
    
    }
    
    /** 
     * @param color
     * defines the color of this vertex
     */
    public void setColor (int color) {

        assert (color > 0);
    
        this.color = color;

        assert (this.color > 0);
    
    }
    
    /**
     * @return the color of this vertex
     */
    public int getColor () {
    
        return color;
    
    }

    /**
     * set this vertex as visited
     */
    public void setVisited () {
    
        visited = true;
    
        assert visited;

    }
    
    /**
     * @return true if this vertex is visited or false if this vertex isn't visited
     */
    public boolean isVisited () {
    
        return visited;
    
    }
    
    /**
     * set order in which it was visited
     */
    public void setOrder (int order) {

       assert (order > 0);
    
       this.order = order;

       assert (this.order > 0);
    
    }
    
    /**
     * @return return order in wich it was visited
     */
    public int getOrder () {
    
        return order;
    
    }
    
    /**
     * @param k (index of one vertex)
     * defines the vertex k as the neighbor of this vertex
     */
    public void setNeighbors (int k, int ps) {

        assert (k >= 0 && (ps == -1 || ps == 1)); 
    
        if (ps == 1) {
            
            if (!successorList.contains(k))
                
                successorList.add(k);
        }
    
        else if (ps == -1) {

            if (!predecessorList.contains(k))
    
                predecessorList.add(k);
        }

        assert (successorList.contains(k) || predecessorList.contains(k));
    
    }
    
    /** 
     * @param k (index of one vertex)
     * @return true if the vertex k is neighbor of this vertex or false if the vertex k isn't neighbor of this vertex
     */
    public boolean isNeighbor (int k) {

        assert (k >= 0);
    
        return (isSucessor(k) || isPredecessor(k));
    
    }
    
    /** 
     * @param k (index of one vertex)
     * @return true if the vertex k is sucessor of this vertex or false if the vertex k isn't sucessor of this vertex
     */
    private boolean isSucessor (int k) {

        assert (k >= 0);
    
        return successorList.contains(k);
    
    }
    
    /** 
     * @param k (index of one vertex)
     * @return true if the vertex k is predecessor of this vertex or false if the vertex k isn't predecessor of this vertex
     */
    private boolean isPredecessor (int k) {

        assert (k >= 0);
    
        return predecessorList.contains(k);
    
    }
    
    /**
     * @return a list of the neighbors of this vertex
     */
    public ArrayList<Integer> getNeighbor () {
    
        Set<Integer> set = new HashSet<Integer>();
    
        set.addAll(successorList);
    
        set.addAll(predecessorList);
    
        return new ArrayList<Integer>(set);
    
    }
    
    /** 
     * @return a list of the sucessors of this vertex
     */
    public ArrayList<Integer> getSucessor () {
    
        return successorList;
    
    }
    
    /** 
     * @return a list of the predecessores of this vertex
     */
    public ArrayList<Integer> getPredecessor () {
    
        return predecessorList;
    
    }
    
    /**
     * @return the degree of this vertex
     */
    public int getDegree () {
    
        Set<Integer> set = new HashSet<Integer>();
    
        set.addAll(successorList);
    
        set.addAll(predecessorList);
    
        return new ArrayList<Integer>(set).size();
    
    }

}