/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithmes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Vector;
import theorie.du.graphe.Sommet;
import theorie.du.graphe.TheorieDuGraphe;

/**
 *
 * @author MouadC
 */
public class Dijiktra extends Algorithm{

    private String affichage;
    Vector sommetsDijikstra;
    
    public Dijiktra(TheorieDuGraphe fr,int[][] mat, int V,Vector<Sommet> sommets,Graphics2D gr){
        adjMat=mat;
        this.fr=fr;
        this.V=V;
        this.sommets=sommets;
        
        sommetsDijikstra=new Vector();
        affichage=new String();
        gr2d=gr;
    }
    
    public String dijkstra( int src) 
    { 
        affichage="\n|------------------------Dijikstra------------------------|";
        affichage+="\n     Sommet de départ est :  "+(src+1);
        int graph[][]=adjMat;
        int dist[] = new int[V]; // The output array. dist[i] will hold 
                                 // the shortest distance from src to i 
  
        // sptSet[i] will true if vertex i is included in shortest 
        // path tree or shortest distance from src to i is finalized 
        Boolean sptSet[] = new Boolean[V]; 
  
        // Initialize all distances as INFINITE and stpSet[] as false 
        for (int i = 0; i < V; i++) 
        { 
            dist[i] = Integer.MAX_VALUE; 
            sptSet[i] = false; 
        } 
  
        // Distance of source vertex from itself is always 0 
        dist[src] = 0; 
  
        // Find shortest path for all vertices 
        for (int count = 0; count < V-1; count++) 
        { 
            // Pick the minimum distance vertex from the set of vertices 
            // not yet processed. u is always equal to src in first 
            // iteration. 
            int u = minDistance(dist, sptSet); 
  
            // Mark the picked vertex as processed 
            sptSet[u] = true; 
  
            // Update dist value of the adjacent vertices of the 
            // picked vertex. 
            for (int v = 0; v < V; v++) 
  
                // Update dist[v] only if is not in sptSet, there is an 
                // edge from u to v, and total weight of path from src to 
                // v through u is smaller than current value of dist[v] 
                if (!sptSet[v] && graph[u][v]!=0 && 
                        dist[u] != Integer.MAX_VALUE && 
                        dist[u]+graph[u][v] < dist[v]) 
                    dist[v] = dist[u] + graph[u][v]; 
        } 
  
        // print the constructed distance array 
        return printSolution(dist, V,src); 
    }
    
    public String printSolution(int dist[],int V, int src) 
    { 
        affichage += "\n\tChemin \t  Distance Source";
        System.out.println("Sommet \t  Distance from Source"); 
        for (int i = 0; i < V; i++) {
            System.out.println((1+i)+" \t\t "+dist[i]);
            affichage+="\n\t"+(src+1)+"->"+(1+i)+" \t\t "+dist[i] ;
            sommetsDijikstra.add(dist[i]);
        }
//        Affichage.setText(affichage);
        if(!fr.isAlgorithme())
            drawPanel(src);
        return affichage;
    } 
      
    int minDistance(int dist[], Boolean sptSet[]) 
    { 
        // Initialize min value 
        int min = Integer.MAX_VALUE, min_index=-1; 
  
        for (int v = 0; v < V; v++) 
            if (sptSet[v] == false && dist[v] <= min) 
            { 
                min = dist[v]; 
                min_index = v; 
            } 
  
        return min_index; 
    } 

    private void drawPanel(int start) {
        Sommet src= sommets.get(start);
        for(int i=0;i<=sommetsDijikstra.size()-1;i++)
        { 
            Sommet dest= sommets.get(i);
            int destCout =(int) sommetsDijikstra.get(i);
            
            drawSommet(src);
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    System.out.println("NOoooooooooooooooooooooo");
                }
            drawComment(dest,destCout);
                try {
                    Thread.sleep(250);
                } catch (Exception e) {
                    System.out.println("NOoooooooooooooooooooooo");
                }
        }
    }

    private void drawComment(Sommet dest, int cout) {
        int x=dest.getX();
        int y=dest.getY();
        int WIDTH=Sommet.WIDTH;
        
        gr2d.setStroke(new BasicStroke(2));
        gr2d.setColor(Color.WHITE);
        gr2d.drawString("distance ="+cout, x-2*WIDTH, y-WIDTH/3);
    }
}
