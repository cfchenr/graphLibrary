package graph;
import java.util.*; 
import java.io.*;

public class Graph {

    /**
     * list (ArrayList) of the vertexes in this graph
     */
    private ArrayList<Vertex> vertexList;

    /**
     * adjacency matrix (ArraList) of this graph
     */
    private ArrayList<String> matrix_adj;

    /**
     * number of vertexes in this graph
     */
    private int size = 0;

    /**
     * name of graph
     */
    private String name;

    //========================================================================================//
                                    private PrintWriter pw;
                                    private StringBuilder sb;
    //========================================================================================//

    /**
     * [Graph description]
     * @param  file                  [description]
     * @return                       [description]
     * @throws FileNotFoundException [description]
     */
    public Graph (String file) throws FileNotFoundException {
   
        Scanner scf = new Scanner(new File (file));
   
        name = file.split("/")[1].split("\\.")[0];
   
        this.vertexList = new ArrayList<Vertex>();
   
        this.matrix_adj = new ArrayList<String>();
   
        File dir = new File(name);
   
        if (!dir.exists())
   
            dir.mkdir();
   
        this.pw = new PrintWriter(new File(name + "/adj.csv"));
   
        this.sb = new StringBuilder(); 
   
        while (scf.hasNextLine()) {
   
            matrix_adj.add(scf.nextLine());
   
            this.size++;
   
        }
   
        scf.close();
   
        init();
   
    }

    /**
     * [init description]
     */
    private void init () {
   
        /**
         * creates the vertexes of adjacency matrix and add this vertexes on this graph
         * p.s. the number of vertexes is equals to the number of lines in adjacency matrix
         */
        for(int i = 0; i < matrix_adj.size(); i++)
     
            this.vertexList.add(new Vertex(i));       
     
        //========================================================================================//
     
                                        String columnName = " ,1";
     
                                        for (int g = 1; g < size; g++)
     
                                            columnName += "," + (g+1);
     
                                        sb.append(columnName);
     
                                        sb.append("\n");
     
        //========================================================================================//
     
        int j = 0;
     
        /**
         * reads line by line to set as neighbors of the vertex considered the cells in which the number is different 0
         */
        while (j < matrix_adj.size()) {
     
            /**
             * split by spaces the line in different cells
             */
     
            String [] line = matrix_adj.get(j).toString().split("\\s+");
     
            //========================================================================================//
     
                                            sb.append(j+1);
     
            //========================================================================================//
     
            for (int k = 0; k < line.length; k++) {
     
                //========================================================================================//
     
                                                sb.append(",");
     
                                                sb.append(line[k]);
     
                //========================================================================================//
     
                /**
                 * if this cell number is different 0 then this vertex is neighbor of the vertex with index of this cell
                 * if is 1 then this vertex is sucessor of the vertex with index of this cell
                 * else if is -1 then this vertex is predecessor of the vertex with index of this cell
                 */
                if (line[k].equals("1"))
     
                    vertexList.get(j).setNeighbors(k, 1);
     
                else if (line[k].equals("-1"))
     
                    vertexList.get(j).setNeighbors(k, -1);
     
            }
     
            //========================================================================================//
     
                                            sb.append("\n");
     
            //========================================================================================//
     
            j++;
     
        }
     
        //========================================================================================//
     
                                        pw.write(sb.toString());
     
                                        pw.close();
     
        //========================================================================================//
    
    }
    
    /**
     * @return the list (ArrayList) of vertexes in this graph
     */
    public ArrayList<Vertex> getVertexList () {
    
        return this.vertexList;
    
    }
    
    /**
     * @return the number of vertexes in this graph
     */
    public int getVertexNumber () {
    
        return this.size;
    
    } 
    
    /**
     * @param k vertex
     * @param y vertex
     * @return true if k and y are neighbors OR false if k and y aren't neighbors
     *         p.s k is a neighbor of y OR y is a neighbor of k is the same thing
     */
    public boolean isNeighbor (int k, int y) {
    
        return (vertexList.get(k).isNeighbor(y) || vertexList.get(y).isNeighbor(k));
    
    }
    
    /**
     * @return the name of this graph
     */
    public String getName () {
    
        return name;
    
    }

}