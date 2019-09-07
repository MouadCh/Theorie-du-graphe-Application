/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithmes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.Vector;
import theorie.du.graphe.Sommet;
import theorie.du.graphe.TheorieDuGraphe;

/**
 *
 * @author MouadC
 */
public class FordFulkerson extends Algorithm{

    private String affichage;
    int [][] graph;

    
    public FordFulkerson(TheorieDuGraphe fr,int[][] mat, int V,Vector<Sommet> sommets,Graphics2D gr){
        adjMat=mat;
        this.fr=fr;
        this.V=V;
        this.sommets=sommets;
        
        affichage=new String("");        
        gr2d=gr;
        
        graph=adjMat;
    }
    
      
    /* Returns true if there is a path from source 's' to sink 
      't' in residual graph. Also fills parent[] to store the 
      path */
    
  
    /* Returns true if there is a path from source 's' to sink 
      't' in residual graph. Also fills parent[] to store the 
      path */
    boolean bfs(int rGraph[][], int s, int t, int parent[]) 
    { 
        // Create a visited array and mark all vertices as not 
        // visited 
        boolean visited[] = new boolean[V]; 
        for(int i=0; i<V; ++i) 
            visited[i]=false; 
  
        // Create a queue, enqueue source vertex and mark 
        // source vertex as visited 
        LinkedList<Integer> queue = new LinkedList<Integer>(); 
        queue.add(s); 
        visited[s] = true; 
        parent[s]=-1; 
  
        // Standard BFS Loop 
        while (queue.size()!=0) 
        { 
            int u = queue.poll(); 
  
            for (int v=0; v<V; v++) 
            { 
                if (visited[v]==false && rGraph[u][v] > 0) 
                { 
                    queue.add(v); 
                    parent[v] = u; 
                    visited[v] = true; 
                } 
            } 
        } 
  
        // If we reached sink in BFS starting from source, then 
        // return true, else false 
        return (visited[t] == true); 
    } 
  
    // Returns tne maximum flow from s to t in the given graph 
    public String fordFulkerson(int s, int t) 
    { 
        affichage="\n|------------------------Ford-Fulkerson------------------------|";
        affichage+="\n     Sommet de départ est :  "+(s+1);
        affichage+="\n     Sommet de destination est :  "+(t+1);
        int u, v; 
  
        // Create a residual graph and fill the residual graph 
        // with given capacities in the original graph as 
        // residual capacities in residual graph 
  
        // Residual graph where rGraph[i][j] indicates 
        // residual capacity of edge from i to j (if there 
        // is an edge. If rGraph[i][j] is 0, then there is 
        // not) 
        int rGraph[][] = new int[V][V]; 
  
        for (u = 0; u < V; u++) 
            for (v = 0; v < V; v++) 
                rGraph[u][v] = graph[u][v]; 
  
        // This array is filled by BFS and to store path 
        int parent[] = new int[V]; 
  
        int max_flow = 0;  // There is no flow initially 
  
        // Augment the flow while tere is path from source 
        // to sink 
        while (bfs(rGraph, s, t, parent)) 
        { 
            // Find minimum residual capacity of the edhes 
            // along the path filled by BFS. Or we can say 
            // find the maximum flow through the path found. 
            int path_flow = Integer.MAX_VALUE; 
            for (v=t; v!=s; v=parent[v]) 
            { 
                u = parent[v]; 
                path_flow = Math.min(path_flow, rGraph[u][v]); 
            } 
  
            // update residual capacities of the edges and 
            // reverse edges along the path 
            for (v=t; v != s; v=parent[v]) 
            { 
                u = parent[v]; 
                rGraph[u][v] -= path_flow; 
                rGraph[v][u] += path_flow; 
            } 
  
            // Add path flow to overall flow 
            max_flow += path_flow; 
        } 
  
        // Return the overall flow 
        if(max_flow!=0){
            drawPanel(s, t, max_flow);
            affichage+="\n\t--> Le flux maximal est : "+max_flow;
        }else
            affichage="";
        return affichage; 
    }  

        private void drawPanel(int s,int d,int maxflow) {
            Sommet src= sommets.get(s);
            Sommet dest= sommets.get(d);
            drawSommet(src);
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    System.out.println("NOoooooooooooooooooooooo");
                }
            drawSommet(dest);
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    System.out.println("NOoooooooooooooooooooooo");
                }
            drawComment(dest,maxflow);
                try {
                    Thread.sleep(250);
                } catch (Exception e) {
                    System.out.println("NOoooooooooooooooooooooo");
                }
        }

        private void drawComment(Sommet dest, int cout) {
            int x=dest.getX();
            int y=dest.getY();
            int WIDTH=Sommet.WIDTH;

            gr2d.setStroke(new BasicStroke(2));
            gr2d.setColor(Color.WHITE);
            gr2d.drawString("Flux Maximal ="+cout, x-2*WIDTH, y-WIDTH/3);
        }
}
