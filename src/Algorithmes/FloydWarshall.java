/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithmes;

import java.awt.Graphics2D;
import java.util.Vector;
import theorie.du.graphe.Sommet;
import theorie.du.graphe.TheorieDuGraphe;

/**
 *
 * @author MouadC
 */
public class FloydWarshall extends Algorithm{

    private Vector<Object> sommetsPrim;
    private int acm;
    private String affichage;
//    Graphics2D gr2d;
    private Vector<Sommet> sommetsSequence;
    final static int INF = 99999; 
    int graph[][];
    
    public FloydWarshall(TheorieDuGraphe fr,int[][] mat, int V,Vector<Sommet> sommets,Graphics2D gr){
        adjMat=mat;
        this.fr=fr;
        this.V=V;
        this.sommets=sommets;
        
        sommetsSequence=new Vector<>();
        affichage=new String();
        
        gr2d=gr;
        
        graph=adjMat;
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if(i!=j && graph[i][j]==0)
                    graph[i][j]=INF;
            }
        }
    }
  
    public String floydWarshall() 
    { 
        int dist[][] = new int[V][V]; 
        int i, j, k; 
  
        /* Initialize the solution matrix same as input graph matrix. 
           Or we can say the initial values of shortest distances 
           are based on shortest paths considering no intermediate 
           vertex. */
        for (i = 0; i < V; i++) 
            for (j = 0; j < V; j++) 
                dist[i][j] = graph[i][j]; 
  
        /* Add all vertices one by one to the set of intermediate 
           vertices. 
          ---> Before start of an iteration, we have shortest 
               distances between all pairs of vertices such that 
               the shortest distances consider only the vertices in 
               set {0, 1, 2, .. k-1} as intermediate vertices. 
          ----> After the end of an iteration, vertex no. k is added 
                to the set of intermediate vertices and the set 
                becomes {0, 1, 2, .. k} */
        for (k = 0; k < V; k++) 
        { 
            // Pick all vertices as source one by one 
            for (i = 0; i < V; i++) 
            { 
                // Pick all vertices as destination for the 
                // above picked source 
                for (j = 0; j < V; j++) 
                { 
                    // If vertex k is on the shortest path from 
                    // i to j, then update the value of dist[i][j] 
                    if (dist[i][k] + dist[k][j] < dist[i][j]) 
                        dist[i][j] = dist[i][k] + dist[k][j]; 
                } 
            } 
        } 
  
        // Print the shortest distance matrix 
        return printSolution(dist); 
    } 
  
    String printSolution(int dist[][]) 
    { 
        affichage="\n|------------------------Floyd-Warshall------------------------|";
        affichage+="\nLa matrice s'affiche les plus courts chemins entre 2 sommets :\n"; 
        for (int i=0; i<V; ++i) 
        { 
            affichage+="\t";
            for (int j=0; j<V; ++j) 
            { 
                if (dist[i][j]==INF) 
                    affichage+="INF  |      "; 
                else
                    affichage+=dist[i][j]+"      |      "; 
            } 
            affichage+="\n";
        } 
        return affichage; 
    } 

}
