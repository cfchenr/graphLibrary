import java.util.*; 
import java.io.*;

public class Graph {

    /**
     * adjacency matrix (ArraList) of this graph
     */
    private ArrayList<String> matrix_adj;
    /**
     * list (ArrayList) of the vertexes in this graph
     */
    private ArrayList<Vertex> vertexList;
    /**
     * number of vertexes in this graph
     */
    private int size;

    //========================================================================================//

                                    private PrintWriter pw;
                                    private StringBuilder sb;

    //========================================================================================//

    public Graph (String file) throws FileNotFoundException {

        this.vertexList = new ArrayList<>();

        File fin = new File (file);

        Scanner scf = new Scanner(fin);

        this.matrix_adj = new ArrayList<String>();

        this.size = 0;

        pw = new PrintWriter(new File("matrix_adj.csv"));
        sb = new StringBuilder(); 

        while (scf.hasNextLine()) {

            matrix_adj.add(scf.nextLine());

            this.size++;

        }

        scf.close();

        init();

    }

    private void init () {

        /**
         * creates the vertexes of adjacency matrix and add this vertexes on this graph
         * p.s. the number of vertexes is equals to the number of lines in adjacency matrix
         */
        for(int i = 0; i < matrix_adj.size(); i++) {

            Vertex v = new Vertex(i);
            this.vertexList.add(v);       

        }

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

}