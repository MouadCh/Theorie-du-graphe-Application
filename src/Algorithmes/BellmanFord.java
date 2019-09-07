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
public class BellmanFord extends Algorithm{

    private Vector sommetsBelmman;
    private String affichage;
    public Graph graphSup;
    
    public BellmanFord(TheorieDuGraphe fr, int[][] mat, int V,int E,Vector<Sommet> sommets,Graphics2D gr){
        adjMat=mat;
        this.fr=fr;
        this.V=V;
        this.sommets=sommets;
        
        sommetsBelmman=new Vector();
        affichage=new String();        
        gr2d=gr;
        graphSup=new Graph(V, E);
        
        int p=0;
        for (int i = 0; i < V; i++) {
             for (int j = 0; j < V; j++) {
                 if (adjMat[i][j]!=0) {
                    graphSup.edge[p].src = i; 
                    graphSup.edge[p].dest = j; 
                    graphSup.edge[p].weight = adjMat[i][j]; 
                    p++;
                 }
             }
        }        
    }
    
        public class Graph 
        { 
            // A class to represent a weighted edge in graph 
            class Edge { 
                int src, dest, weight; 
                Edge() { 
                    src = dest = weight = 0; 
                } 
            }; 

            int V, E; 
            Edge edge[]; 

            // Creates a graph with V vertices and E edges 
            Graph(int v, int e) 
            { 
                V = v; 
                E = e; 
                edge = new Edge[e]; 
                for (int i=0; i<e; ++i) 
                    edge[i] = new Edge(); 
            } 

            // The main function that finds shortest distances from src 
            // to all other vertices using Bellman-Ford algorithm.  The 
            // function also detects negative weight cycle 
            public String BellmanFord(int src) 
            { 
                Graph graph=graphSup;
                int V = graph.V, E = graph.E; 
                int dist[] = new int[V]; 

                // Step 1: Initialize distances from src to all other 
                // vertices as INFINITE 
                for (int i=0; i<V; ++i) 
                    dist[i] = Integer.MAX_VALUE; 
                dist[src] = 0; 

                // Step 2: Relax all edges |V| - 1 times. A simple 
                // shortest path from src to any other vertex can 
                // have at-most |V| - 1 edges 
                for (int i=1; i<V; ++i) 
                { 
                    for (int j=0; j<E; ++j) 
                    { 
                        int u = graph.edge[j].src; 
                        int v = graph.edge[j].dest; 
                        int weight = graph.edge[j].weight; 
                        if (dist[u]!=Integer.MAX_VALUE && 
                            dist[u]+weight<dist[v]) 
                            dist[v]=dist[u]+weight; 
                    } 
                } 

                // Step 3: check for negative-weight cycles.  The above 
                // step guarantees shortest distances if graph doesn't 
                // contain negative weight cycle. If we get a shorter 
                //  path, then there is a cycle. 
                for (int j=0; j<E; ++j) 
                { 
                    int u = graph.edge[j].src; 
                    int v = graph.edge[j].dest; 
                    int weight = graph.edge[j].weight; 
                    if (dist[u] != Integer.MAX_VALUE && 
                        dist[u]+weight < dist[v]) 
                      System.out.println("Graph contains negative weight cycle"); 
                } 
                return printArr(dist, V,src); 
            } 

                // A utility function used to print the solution 
            String printArr(int dist[], int V,int src) 
            {   
                affichage="\n|------------------------Bellman-Ford------------------------|";
                affichage+="\n     Sommet de départ est :  "+(src+1);
//                affichage+="Vertex   Distance from Source";
                affichage += "\n\tChemin \t  Distance Source";
                System.out.println("Vertex   Distance from Source"); 
                for (int i=0; i<V; ++i) 
                {
                    affichage+="\n\t"+(src+1)+"->"+(i+1)+"\t\t"+dist[i];
                    sommetsBelmman.add(dist[i]);
                }
                if(!fr.isAlgorithme())
                    drawPanel(src);
                return affichage;
            }
            
            private void drawPanel(int start) {
                Sommet src= sommets.get(start);
                for(int i=0;i<=sommetsBelmman.size()-1;i++)
                { 
                    Sommet dest= sommets.get(i);
                    int destCout =(int) sommetsBelmman.get(i);

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
}
