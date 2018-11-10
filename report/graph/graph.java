package graph;
import java.util.*; 
import java.io.*;
public class Graph {
    private ArrayList<Vertex> vertexList;
    private ArrayList<String> matrix_adj;
    private int order = 0;
    private String id;
    private PrintWriter pw;
    private StringBuilder sb;
    public Graph (String file) throws IOException {
        Scanner scf = new Scanner(new File (file));
        id = file.split("/")[1].split("\\.")[0];
        vertexList = new ArrayList<Vertex>();
        matrix_adj = new ArrayList<String>();
        File folder = new File("output");
        if (!folder.exists())
            folder.mkdirs();
        File dir = new File(folder + "/" + id);
        if (!dir.exists())
            dir.mkdir();
        pw = new PrintWriter(new File(folder + "/" + id + "/adj.csv"));
        sb = new StringBuilder(); 
        while (scf.hasNextLine()) {
            matrix_adj.add(scf.nextLine());
            vertexList.add(new Vertex(order));       
            order++;
        }
        scf.close();
        init();
    }
    private void init () {
        String columnName = " ,1";
        for (int g = 1; g < order; g++)
            columnName += "," + (g+1);
        sb.append(columnName);
        sb.append("\n");
        int j = 0;
        while (j < matrix_adj.size()) {
            sb.append(j+1);
            String [] line = matrix_adj.get(j).toString().split("\\s+");
            for (int k = 0; k < line.length; k++) {
                sb.append(",");
                sb.append(line[k]);
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
    public ArrayList<Vertex> getVertexList () {
        return vertexList;
    }
    public int getOrder () {
        return order;
    } 
    public boolean isNeighbor (int k, int y) {
        assert (k >= 0 && y >= 0); 
        return (vertexList.get(k).isNeighbor(y) || vertexList.get(y).isNeighbor(k));
    }
    public String getId () {
        return id;    
    }
    public void sortVertexByDegree () {
        ArrayList<Vertex> temp = new ArrayList<Vertex>();
        int max = 0;
        for (int i = 0; i < vertexList.size(); i++)
            if (vertexList.get(i).getDegree() >= max) 
                max = vertexList.get(i).getDegree();
        while (max >= 0) {
            for (int i = 0; i < vertexList.size(); i++) {
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