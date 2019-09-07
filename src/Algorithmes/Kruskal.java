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
public class Kruskal extends Algorithm{

    private Vector<Object> sommetsKruskal;
    private int acm;
    private String affichage;
//     gr2d;
    static int[] parent ;
    
    public Kruskal(TheorieDuGraphe fr,int[][] mat, int V,Vector<Sommet> sommets,Graphics2D gr){
        adjMat=mat;
        this.fr=fr;
        this.V=V;
        this.sommets=sommets;
        
        sommetsKruskal=new Vector<>();
        acm=0;
        affichage=new String();
        gr2d=gr;
        
        parent = new int[V]; 
        for (int i = 0; i < sommets.size(); i++) {
            for (int j = 0; j < sommets.size(); j++) {
                if(adjMat[i][j]==0)
                    adjMat[i][j]=INF;
            }
        }
    }
    
    static int find(int i) 
{ 
    while (parent[i] != i) 
        i = parent[i]; 
    return i; 
} 
  
// Does union of i and j. It returns 
// false if i and j are already in same 
// set. 
static void union1(int i, int j) 
{ 
    int a = find(i); 
    int b = find(j); 
    parent[a] = b; 
} 
  
// Finds MST using Kruskal's algorithm 
public String kruskalMST() 
{   
    try{
        int cost[][]=adjMat;
        sommetsKruskal.clear();
        acm=0;

        affichage="\n|------------------------Kruskal------------------------|";
        affichage+="\n\tArrêtes \t Côuts\n";
        int mincost = 0; // Cost of min MST. 

        // Initialize sets of disjoint sets. 
        for (int i = 0; i < V; i++) 
            parent[i] = i; 

        // Include minimum weight edges one by one 
        int edge_count = 0; 
        while (edge_count < V - 1) 
        { 
            int min = INF, a = -1, b = -1; 
            for (int i = 0; i < V; i++) 
            { 
                for (int j = 0; j < V; j++)  
                { 
                    if (find(i) != find(j) && cost[i][j] < min)  
                    { 
                        min = cost[i][j]; 
                        a = i; 
                        b = j; 
                    } 
                } 
            } 
            union1(a, b); 
    //        affichage+="\tArrête "+(edge_count+1)+":("+ (a+1) +","+ (b+1) +") côut:"+min+" \n"; 
            affichage+="\t-"+(edge_count+1)+"- :  "+(a+1)+ " - " + (b+1) + "\t    " +min+"\n";
            Vector tempVect = new Vector();
            tempVect.add(sommets.get(a));
            tempVect.add(sommets.get(b));
            tempVect.add(min);
            sommetsKruskal.add(tempVect);
            edge_count++; 
            acm += min; 
        } 
        System.out.printf("\n Minimum cost= %d \n", mincost); 
    //    Affichage.setText(affichage);
    //    ACM.setText("ACM = "+mincost);
        affichage+="\t\t=> ACM = "+acm;
        if(!fr.isAlgorithme())
            drawPanel(this.sommetsKruskal);
        return affichage;
    }catch(Exception e){
        return "\n|------------------------Kruskal------------------------|\n==> Graphe n'est pas simple !!";
    }
} 
    
}
