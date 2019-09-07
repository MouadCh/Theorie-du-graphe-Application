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
import java.util.LinkedList;
import java.util.Vector;
import theorie.du.graphe.Sommet;
import theorie.du.graphe.TheorieDuGraphe;

/**
 *
 * @author MouadC
 */
public class BFS extends Algorithm{

    private Vector<Object> sommetsPrim;
    private int acm;
    private String affichage;
//    Graphics2D gr2d;
    private Vector<Sommet> sommetsSequence;
    
    public BFS(TheorieDuGraphe fr,int[][] mat, int V,Vector<Sommet> sommets,Graphics2D gr){
        adjMat=mat;
        this.fr=fr;
        this.V=V;
        this.sommets=sommets;
        
        sommetsSequence=new Vector<>();
        affichage=new String("");
        
        gr2d=gr;
    }
    
    public String bfs(int start) {
                affichage="\n|------------------------BFS------------------------|";
                affichage+="\n     Sommet de départ est :  "+(start+1);
                sommetsSequence.clear();
		ArrayList<Integer> sequence = new ArrayList<Integer>();
		boolean[] visited = new boolean[V];
		LinkedList<Integer> q = new LinkedList<Integer>();
		q.offer(start);
		visited[start] = true;
		while (!q.isEmpty()) {
			Integer currVert = q.poll();
			sequence.add(currVert);
			for (Integer i: getNeighbors(currVert)) {
				if (!visited[i]) {
					q.offer(i);
					visited[i] = true;
				}
			}
		}
		Iterator<Integer> itbfs = sequence.iterator();
                String ress="[";
                
                while (itbfs.hasNext()) {
                    Integer s = itbfs.next();
                    sommetsSequence.add(sommets.get(Integer.parseInt(s.toString())));
                    ress=ress+" "+((Integer.parseInt(s.toString())+1)+"  ");
                }
                ress=ress+"]";
                System.out.println(ress);
                affichage+="\n\t"+ress;
//                this.Affichage.setText(ress); 
                if(!fr.isAlgorithme()){
                    for(int i=0;i<=sommetsSequence.size()-1;i++)
                    { 
                        Sommet src=(Sommet) sommetsSequence.get(i);            
                        drawSommet(src,i);
                            try {
                                Thread.sleep(350);
                            } catch (Exception e) {
                                System.out.println("NOoooooooooooooooooooooo");
                            }
                    }
                }
        return affichage;
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
