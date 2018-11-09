package graph;
import java.util.*; 
import java.io.*;

public class Graph {

    /**
     * list that contains all the vertices of the graph
     */
    private ArrayList<Vertex> vertexList;

    /**
     * adjacency matrix of this graph
     */
    private ArrayList<String> matrix_adj;

    /**
     * order of this graph
     */
    private int order = 0;

    /**
     * id of graph
     */
    private String id;

    private PrintWriter pw;
    
    private StringBuilder sb;
    
    /**
     * [Graph description]
     * @param  file                  [description]
     * @return                       [description]
     * @throws IOException           [description]
     */
    public Graph (String file) throws IOException {
   
        Scanner scf = new Scanner(new File (file));
   
        id = file.split("/")[1].split("\\.")[0];
   
        this.vertexList = new ArrayList<Vertex>();

        this.matrix_adj = new ArrayList<String>();
        
        File folder = new File("output");

        if (!folder.exists())

            folder.mkdirs();

        File dir = new File(folder + "/" + id);
   
        if (!dir.exists())

            dir.mkdir();

        this.pw = new PrintWriter(new File(folder + "/" + id + "/adj.csv"));
   
        this.sb = new StringBuilder(); 
   
        while (scf.hasNextLine()) {
   
            matrix_adj.add(scf.nextLine());
   
            this.order++;
   
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
     
        String columnName = " ,1";

        for (int g = 1; g < order; g++)

            columnName += "," + (g+1);

        sb.append(columnName);

        sb.append("\n");
     
        int j = 0;
     
        /**
         * reads line by line to set as neighbors of the vertex considered the cells in which the number is different 0
         */
        while (j < matrix_adj.size()) {
     
            sb.append(j+1);
     
            /**
             * split by spaces the line in different cells
             */
            String [] line = matrix_adj.get(j).toString().split("\\s+");
          
            for (int k = 0; k < line.length; k++) {
     
                sb.append(",");

                sb.append(line[k]);

                /**
                 * if this cell number is different 0 then this vertex is neighbor of the vertex with index of this cell:
                 * if is 1 then this vertex is sucessor of the vertex with index of this cell
                 * else if is -1 then this vertex is predecessor of the vertex with index of this cell
                 */
                if (line[k].equals("1")) {
     
                    vertexList.get(j).setNeighbors(k, 1);
            
                    vertexList.get(k).setNeighbors(j, -1);

                } else if (line[k].equals("-1")) {
     
                    vertexList.get(j).setNeighbors(k, -1);

                    vertexList.get(k).setNeighbors(j, 1);

                }
     
            }
     
            sb.append("\n");
     
            j++;
     
        }
     
        pw.write(sb.toString());

        pw.close();
     
    }
    
    /**
     * @return the list (ArrayList) of vertexes in this graph
     */
    public ArrayList<Vertex> getVertexList () {
    
        return vertexList;
    
    }
    
    /**
     * @return the number of vertexes in this graph
     */
    public int getOrder () {
    
        return order;
    
    } 
    
    /**
     * @param k vertex
     * @param y vertex
     * @return true if k and y are neighbors or false if k and y aren't neighbors
     *         *k is a neighbor of y or y is a neighbor of k is the same thing
     */
    public boolean isNeighbor (int k, int y) {

        assert (k >= 0 && y >= 0); 
    
        return (vertexList.get(k).isNeighbor(y) || vertexList.get(y).isNeighbor(k));
    
    }
    
    /**
     * @return the id of this graph
     */
    public String getId () {
    
        return id;
    
    }

    /**
     * order the vertices by degree (increasing direction)
     */
    public void sortVertexByDegree () {

        ArrayList<Vertex> temp = new ArrayList<Vertex>();

        int max = 0;

        /**
         * save the highest degree
         */
        for (int i = 0; i < vertexList.size(); i++)

            if (vertexList.get(i).getDegree() >= max) 

                max = vertexList.get(i).getDegree();

        /**
         * identify the degrees of the vertices in descending order and add to the beginning of the temporary list
         */
        while (max >= 0) {

            /**
             * scroll through all the vertices in the list
             */
            for (int i = 0; i < vertexList.size(); i++) {

                /**
                 * if the vertex has the desired degree we add to the temporary list and remove from the list that we are analyzing
                 */
                if (vertexList.get(i).getDegree() == max){

                    temp.add(0, vertexList.get(i));

                    vertexList.remove(i);

                    i--;

                }

            }       

            max--;

        }

        vertexList = temp;

    }

}