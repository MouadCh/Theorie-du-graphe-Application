/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithmes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Vector;
import javax.swing.JFrame;
import theorie.du.graphe.Sommet;
import theorie.du.graphe.TheorieDuGraphe;

/**
 *
 * @author MouadC
 */
public class Algorithm extends JFrame{
    protected int[][] adjMat;	// adjacency matrix
    protected int V;
    TheorieDuGraphe fr;
    protected Vector<Sommet> sommets;
    protected ArrayList<Prim.Edge> MST ;
    Graphics2D gr2d;
    
    static int INF = Integer.MAX_VALUE; 
    
    
    static class Edge implements Comparable<Algorithm.Edge>{
		Integer vert1;
		Integer vert2;
		Integer edgeWt;
		public Edge(int vert1, int vert2, int edgeWt) {
			this.vert1 = vert1;
			this.vert2 = vert2;
			this.edgeWt = edgeWt;
		}
		public String toString() {
			return "(" + (vert1+1) + "-->" + (vert2+1) + ", " + edgeWt + ")";
		}
                
                public String getVert1(){
                    return ""+vert1;
                }
                
                public String getVert2(){
                    return ""+vert2;
                }
                
                public String getedgeWt(){
                    return ""+edgeWt;
                }
                
		public int compareTo(Edge other) {
                        
			return this.edgeWt.compareTo(other.edgeWt);
		}
        }
    
    public ArrayList<Integer> getNeighbors(int vertex) {
		ArrayList<Integer> neighbors = new ArrayList<Integer>();
		for (int i=0; i<V; i++) {
			if (getEdgeWt(vertex, i) != 0) {
				neighbors.add(i);
			}
		}
		return neighbors;
	}
    
    public int getEdgeWt(int outVert, int inVert) {
		return adjMat[outVert][inVert];
	}
    
    public ArrayList<Edge> getEdges(boolean isDirected) {
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for (int i=0; i<V; i++) {
			for (int j=0; j<V; j++) {
				if (adjMat[i][j] != 0) {
					/* if undirected graph, ignore bottom half */
					if (!isDirected && j<=i) {
						continue;
					}
					Edge edge = new Edge(i, j, adjMat[i][j]);
					edges.add(edge);
				}
			}
		}
		return edges;
	}
    
        
        public void mergeSets(ArrayList<HashSet<Integer>> sets,
						  HashSet<Integer> vert1Set,
						  HashSet<Integer> vert2Set) {
		vert1Set.addAll(vert2Set);
		for (Integer vertex: vert1Set) {
			sets.set(vertex, vert1Set);
		}
	}
        
        static class Pair implements Comparable<Algorithm.Pair> {
		Integer vertex;
		Integer pathWt;
		public Pair(int vertex, int pathWt) {
			this.vertex = vertex;
			this.pathWt = pathWt;
		}
		public int compareTo(Pair other) {
			return this.pathWt.compareTo(other.pathWt);
		}
	}
        
        public void relaxEdge(Integer[] SSSP, Integer[] predecessor,
						  int outVert, int inVert) {
		int weight = getEdgeWt(outVert, inVert);
		Integer value = SSSP[inVert];
		/* if distance is currently at infinity */
		if (value == null) {
			SSSP[inVert] = SSSP[outVert] + weight;
			predecessor[inVert] = outVert;
		}
		else if (value > SSSP[outVert] + weight) {
			SSSP[inVert] = SSSP[outVert] + weight;
			predecessor[inVert] = outVert;
		}
	}
        
    
    public void drawPanel(Vector sommets){  //Pour redissiner toutes les sommets 
        for(int i=0;i<=sommets.size()-1;i++)
        { 
            Sommet src=(Sommet) ((Vector) sommets.get(i)).get(0);
            Sommet dest=(Sommet) ((Vector) sommets.get(i)).get(1);
            int cout=(int) ((Vector) sommets.get(i)).get(2);
            
            drawSommet(src);
                try {
                    Thread.sleep(300);
                } catch (Exception e) {
                    System.out.println("NOoooooooooooooooooooooo");
                }
            drawArc(src,dest,cout);
                try {
                    Thread.sleep(180);
                } catch (Exception e) {
                    System.out.println("NOoooooooooooooooooooooo");
                }
            drawSommet(dest);
                try {
                Thread.sleep(250);
                } catch (Exception e) {
                    System.out.println("NOoooooooooooooooooooooo");
                }
        }
    }
        
    public void drawSommet(Sommet temp){
        int x=temp.getX();
        int y=temp.getY();
        int WIDTH=Sommet.WIDTH;
        
        gr2d.setStroke(new BasicStroke(1));
        gr2d.setColor(Color.ORANGE);
        gr2d.fillOval(x, y,WIDTH,WIDTH);
        gr2d.setColor(Color.BLACK);
        gr2d.drawOval(x, y,WIDTH,WIDTH);
        gr2d.setColor(Color.BLUE);
        gr2d.drawString(""+temp.getIndice(), x+WIDTH/3, y+WIDTH/2);
    }
    
    public void drawArc(Sommet src,Sommet dest,int cout){
        int x=src.getX();
        int y=src.getY();
        int xf=dest.getX();
        int yf=dest.getY();
        int width=Sommet.WIDTH;
        gr2d.setStroke(new BasicStroke(2));
        gr2d.setColor(Color.ORANGE);
        gr2d.drawLine(x+width/2, y+width/2,xf+width/2,yf+width/2);
//        gr2d.setColor(Color.PINK);
//        gr2d.drawString(""+cout, (xf+x+width)/2, (yf+y+width)/2);
    }
}
