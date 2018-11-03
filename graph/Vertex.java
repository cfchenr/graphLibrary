package graph;

import java.util.*; 

public class Vertex {

    /**
     * index (name) of this vertex
     */
    private int vertex;
    /**
     * color of this vertex
     */
    private int color;
    /**
     * status of this vertex (visited OR non visited)
     */
    private boolean visited;
    /**
     * order by which was visited
     */
    private int order;    
    /**
     * list (ArrayList) of integers that indicates the indexes (names) of sucessors of this vertex
     */
    private ArrayList<Integer> successorList;
    /**
     * list (ArrayList) of integers that indicates the indexes (names) of predecessors of this vertex
     */
    private ArrayList<Integer> predecessorList;

    /**
     * @param vertex (index of one new vertex)
     * instance an index @param vertex, a color (initially 0), visited status (initially 0) and a list of neighbors for this new vertex
     */
    public Vertex (int vertex) {

        this.vertex = vertex;
        color = 0;
        visited = false;
        order = 0;
        successorList = new ArrayList<Integer>();
        predecessorList = new ArrayList<Integer>();
    
    }

    /** 
     * @param color
     * defines the color of this vertex as color @param color
     */
    public void setColor (int color) {

        this.color = color;

    }

    /**
     * @return the color of this vertex
     */
    public int getColor () {

        return color;

    }

    /** 
     * @return the index (name) of this vertex
     */
    public int getName () {

        return vertex;

    }

    /**
     * @param k (index of one vertex)
     * defines the vertex @param k as the neighbor of this vertex
     */
    public void setNeighbors (int k, int ps) { 

        if (ps == 1) 
            successorList.add(k);
        else if (ps == -1) 
            predecessorList.add(k);
    }

    /** 
     * @param k (index of one vertex)
     * @return true if the vertex @param k is neighbor of this vertex OR false if the vertex @param k isn't neighbor of this vertex
     */
    public boolean isNeighbor (int k) {

        return (isSucessor(k) || isPredecessor(k));

    }

    /** 
     * @param k (index of one vertex)
     * @return true if the vertex @param k is sucessor of this vertex OR false if the vertex @param k isn't sucessor of this vertex
     */
    private boolean isSucessor (int k) {

        return successorList.contains(k);

    }
    
    /** 
     * @param k (index of one vertex)
     * @return true if the vertex @param k is predecessor of this vertex OR false if the vertex @param k isn't predecessor of this vertex
     */
    private boolean isPredecessor (int k) {

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
    
    /**
     * set this vertex as visited
     */
    public void setVisited () {

        visited = true;

    }

    /**
     * @return true if this vertex is visited OR false if this vertex isn't visited
     */
    public boolean isVisited () {

        return visited;

    }

    /**
     * set order in which it was visited
     */
    public void setOrder (int order) {

       this.order = order;

    }

    /**
     * @return return order in wich it was visited
     */
    public int getOrder () {

        return order;

    }

}