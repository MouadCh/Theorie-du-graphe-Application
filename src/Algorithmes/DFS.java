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
import java.util.Iterator;
import java.util.Vector;
import theorie.du.graphe.Sommet;
import theorie.du.graphe.TheorieDuGraphe;

/**
 *
 * @author MouadC
 */
public class DFS extends Algorithm{

    private String affichage;
//    Graphics2D gr2d;
    private Vector<Sommet> sommetsSequence;
    
    public DFS(TheorieDuGraphe fr,int[][] mat, int V,Vector<Sommet> sommets,Graphics2D gr){
        adjMat=mat;
        this.fr=fr;
        this.V=V;
        this.sommets=sommets;
        
        sommetsSequence=new Vector<>();
        affichage=new String();
        
        gr2d=gr;
    }
    
    public String dfs(int start) {
        affichage="\n|------------------------DFS------------------------|";
        affichage+="\n     Sommet de départ est :  "+(start+1);
        sommetsSequence.clear();
        boolean[] visited = new boolean[V];
        ArrayList<Integer> sequence = new ArrayList<Integer>();
        dfs(start, visited, sequence);
        Iterator<Integer> itdfs = sequence.iterator();
        String ress1="[";
        int i=0;
        while (itdfs.hasNext()) {
            Integer s = itdfs.next();
            sommetsSequence.add(sommets.get( ( Integer.parseInt(s.toString()) ) ) );
            ress1=ress1+" "+( (sommetsSequence.get(i).getIndice())+"  ");
            i++;
        }
        ress1=ress1+"]";
        System.out.println(ress1);
        affichage+="\n\t"+ress1;
        if(!fr.isAlgorithme()){
            for(int j=0;j<=sommetsSequence.size()-1;j++)
            { 
                Sommet src=(Sommet) sommetsSequence.get(j);            
                drawSommet(src,j);
                    try {
                        Thread.sleep(350);
                    } catch (Exception e) {
                        System.out.println("NOoooooooooooooooooooooo");
                    }
            }
        }
        return affichage;
	}
	public void dfs(int currVert, boolean[] visited,ArrayList<Integer> sequence) {
		visited[currVert] = true;
		sequence.add(currVert);
		for (Integer i: getNeighbors(currVert)) {
			if (!visited[i]) {
                            dfs(i, visited, sequence);
			}
		}
	}
        
        public void drawSommet(Sommet temp,int i){
        int x=temp.getX();
        int y=temp.getY();
        int WIDTH=Sommet.WIDTH;
        
        gr2d.setStroke(new BasicStroke(1));
        gr2d.setColor(Color.getHSBColor((float) 0.87 * i/75 ,(float) 1.0, (float) 1.0));
        gr2d.fillOval(x, y,WIDTH,WIDTH);
        gr2d.setColor(Color.BLACK);
        gr2d.drawOval(x, y,WIDTH,WIDTH);
        gr2d.setColor(Color.BLUE);
        gr2d.drawString(""+temp.getIndice(), x+WIDTH/3, y+WIDTH/2);
    }
        

      
}
