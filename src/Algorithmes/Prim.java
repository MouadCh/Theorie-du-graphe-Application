/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithmes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Vector;
import theorie.du.graphe.Sommet;
import theorie.du.graphe.TheorieDuGraphe;

/**
 *
 * @author MouadC
 */
public class Prim extends Algorithm{

    private Vector<Object> sommetsPrim;
    private int acm;
    private String affichage;
//    Graphics2D gr2d;
    
    public Prim(TheorieDuGraphe fr,int[][] mat, int V,Vector<Sommet> sommets,Graphics2D gr){
        adjMat=mat;
        this.fr=fr;
        this.V=V;
        this.sommets=sommets;
        
        sommetsPrim=new Vector<>();
        acm=0;
        affichage=new String();
        
        gr2d=gr;
    }
    
    public String primMST() 
    { 
        try{
            int graph[][]=adjMat;
            // Array to store constructed MST 
            int parent[] = new int[V]; 

            // Key values used to pick minimum weight edge in cut 
            int key[] = new int[V]; 

            // To represent set of vertices not yet included in MST 
            Boolean mstSet[] = new Boolean[V]; 

            // Initialize all keys as INFINITE 
            for (int i = 0; i < V; i++) { 
                key[i] = Integer.MAX_VALUE; 
                mstSet[i] = false; 
            } 

            // Always include first 1st vertex in MST. 
            key[0] = 0; // Make key 0 so that this vertex is 
            // picked as first vertex 
            parent[0] = -1; // First node is always root of MST 

            // The MST will have V vertices 
            for (int count = 0; count < V - 1; count++) { 
                // Pick thd minimum key vertex from the set of vertices 
                // not yet included in MST 
                int u = minKey(key, mstSet); 

                // Add the picked vertex to the MST Set 
                mstSet[u] = true; 

                // Update key value and parent index of the adjacent 
                // vertices of the picked vertex. Consider only those 
                // vertices which are not yet included in MST 
                for (int v = 0; v < V; v++) 

                    // graph[u][v] is non zero only for adjacent vertices of m 
                    // mstSet[v] is false for vertices not yet included in MST 
                    // Update the key only if graph[u][v] is smaller than key[v] 
                    if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) { 
                        parent[v] = u; 
                        key[v] = graph[u][v]; 
                    } 
            } 

            // print the constructed MST 
            String resultat=printMST(parent, graph); 
            if(!fr.isAlgorithme() && resultat.length()>0)
                drawPanel(this.sommetsPrim);
            return resultat;
        }catch(Exception e){
            return "\n|------------------------Prim------------------------|\n==> Graphe n'est pas simple !!";
        }
    }
    
    public String printMST(int parent[], int graph[][]) 
    {   
        affichage="\n|------------------------Prim------------------------|";
        affichage+="\n\tArrêtes \t Côuts\n";
        System.out.println("Edge \t Weight"); 
        for (int i = 1; i < V; i++) 
        {   
            Vector tempVect = new Vector();
            tempVect.add(sommets.get(parent[i]));
            tempVect.add(sommets.get(i));
            tempVect.add(graph[i][parent[i]]);
            sommetsPrim.add(tempVect);
            System.out.println("\t"+((Sommet)tempVect.get(0)).getIndice() + " - " + (((Sommet)tempVect.get(1)).getIndice())
                    + "\t=>" + tempVect.get(2));
            acm+=(int)tempVect.get(2);
            affichage+=("\t-"+i+"- :  "+((Sommet)tempVect.get(0)).getIndice() )+ " - " + (((Sommet)tempVect.get(1)).getIndice())
                    + "\t    " + tempVect.get(2)+"\n";
        } 
        affichage+="\t\t=> ACM = "+acm;
        
        //Pour Filtrer les graphe n'ont simple
        for (int i = 0; i < sommetsPrim.size(); i++) {
            int get =(int) ( (Vector) sommetsPrim.get(i)).get(2);
            if(get==0)
                return "";
        }
        return affichage;
    }
    
    int minKey(int key[], Boolean mstSet[]) 
    { 
        // Initialize min value 
        int min = Integer.MAX_VALUE, min_index = -1; 
  
        for (int v = 0; v < V; v++) 
            if (mstSet[v] == false && key[v] < min) { 
                min = key[v]; 
                min_index = v; 
            } 
  
        return min_index; 
    } 
    
            
    
}
