package graph;

import java.util.*; 
import java.io.*;
import java.util.Date;
import java.sql.Timestamp;

public class V3 {

	private ArrayList<Vertex> vertexList;
    private HashMap<Integer, Integer> vertexDegree = new HashMap<Integer, Integer>();
    private ArrayList<Integer> vertexDegreeSort = new ArrayList<Integer>();
    private Queue<Integer> colors;

    private PrintWriter log_v3;

    private PrintWriter pw;
    private StringBuilder sb = new StringBuilder();
    
	public V3 (Graph graph) throws FileNotFoundException {

        log_v3 = new PrintWriter(new File(graph.getName() + "/log_v3_.txt"));

        pw = new PrintWriter(new File(graph.getName() + "/id_color.csv"));
		
        vertexList = graph.getVertexList();

        log_v3.printf("%-30s%-20s%-60s%-10s%s\n","[timestamp]", "action","details","vertex"," observations");      

        sortVertexByDegree();

        setColorVertex(vertexDegreeSort.size()-1, 1);

        for (int j = vertexDegreeSort.size()-2; j >= 0; j--) {

            saveNeighborColors(j);

            setColorVertex(vertexDegreeSort.get(j), getFirstColorAvailable());

        }

        sb.append("id,Color\n");

        for (int p = 0; p < graph.getVertexNumber(); p++)
            sb.append((vertexList.get(p).getName()+1) + "," + (vertexList.get(p).getColor()) + "\n");

        pw.write(sb.toString());
        pw.close();

        log_v3.close();

    }


    private void setAllVertexDegree () {

        for (int i = 0; i < vertexList.size(); i++)
            vertexDegree.put(i, vertexList.get(i).getDegree());

    }

    private void sortVertexByDegree () {

        setAllVertexDegree();

        List<Integer> mapKeys = new ArrayList<>(vertexDegree.keySet());
        List<Integer> mapValues = new ArrayList<>(vertexDegree.values());
        Collections.sort(mapValues);
        Collections.sort(mapKeys);

        Iterator<Integer> valueIt = mapValues.iterator();

        while (valueIt.hasNext()) {

            Integer val = valueIt.next();
            Iterator<Integer> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {

                Integer key = keyIt.next();
                Integer comp1 = vertexDegree.get(key);
                Integer comp2 = val;

                if (comp1.equals(comp2)) {

                    keyIt.remove();
                    vertexDegreeSort.add(key);
                    break;
                
                }

            }

        }

    }

    private void setColorVertex (Integer vertex, Integer color) {

        log_v3.printf("%-30s%-20s%-60s%-10s\n","["+new Timestamp(new Date().getTime())+"]", "Set", "in " + color + " the color of vertex ", (vertexList.get(vertex).getName()+1));        

        vertexList.get(vertex).setColor(color);

    }

    private void saveNeighborColors (Integer j) {

        colors = new LinkedList<Integer>();

        for (int i = vertexDegreeSort.size()-1; i > j; i--)
            if (vertexList.get(vertexDegreeSort.get(j)).isNeighbor(vertexList.get(vertexDegreeSort.get(i)).getName()))
                colors.add(vertexList.get(vertexDegreeSort.get(i)).getColor());

    }

    private int getFirstColorAvailable () {

        int k = 1;

        while(colors.contains(k))
            k++;

        return k;

    }

}