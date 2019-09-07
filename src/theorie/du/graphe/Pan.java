/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theorie.du.graphe;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author MouadC
 */
public class Pan extends JPanel  {
    private TheorieDuGraphe fr;
    public static Color BackndColor=new Color(153, 153, 153);
    
    public Pan(TheorieDuGraphe fr) {
        this.fr=fr;
        this.setSize(this.getWidth(), this.getHeight());
        setBackground(BackndColor);
  //      this.setLayout(null);
//        img=new ImageIcon("bg.jpg").getImage();
//        buffImg= new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
//        
    }
  
    @Override
    protected  void paintComponent(Graphics g)
    {   
        super.paintComponent(g);
        if(fr.getV()!=0 || TheorieDuGraphe.PATH.length()!=0)
            removeAll();
        ImageIcon img;
        img=new ImageIcon(fr.getPath());
        if(fr.getPath().length()>0)
            g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), fr);
        else 
            this.setBackground(BackndColor);
//        for(int i=0;i<fr.sommets.size();i++){
//            fr.sommets.get(i).setGraphics(g);
//            fr.sommets.get(i).run();
//        }
        for(int i=0;i<fr.getSommets().size();i++)            
            fr.getSommets().get(i).drawArc(g);
        for(int i=0;i<fr.getSommets().size();i++)
            fr.getSommets().get(i).drawSommet(g);
        fr.getNbrE().setText("| E | = "+fr.getE());
    
 //       this.setBackground(Color.red);
        
        }
}
