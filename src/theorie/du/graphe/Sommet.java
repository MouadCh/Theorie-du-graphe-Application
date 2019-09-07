package theorie.du.graphe;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import java.util.Vector;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MouadC
 */
public class Sommet extends Component implements MouseListener,MouseMotionListener,Serializable,Cloneable,Runnable{
    
    transient TheorieDuGraphe fr;
    transient Graphics2D gr;          //Recçoit Graphics de notre JPanel pour dessiner
    Vector<Vector> voisins;   //Contient chaque voisin avec leur côut
    public static String COUT_NAME;
    private String name;
    private int indice;
    private int x,y;
    public static int WIDTH=28;
    public static Color sommetCOLOR=Color.PINK;
    public static Color defaultSommetCOLOR=Color.PINK;
    public static Color indiceCOLOR=Color.DARK_GRAY;
    public static Color arcCOLOR=Color.lightGray;
    public static Color indiceArcCOLOR=Color.darkGray;
    private boolean pressed;   //Est ce qu'il est pressé pour pouvoir draguer
    private boolean draw;      //Est ce qu'il est pressé pour pouvoir dessiner l'arc entre 2 sommets 
    private boolean hover;

    @Override
    public Object clone() throws CloneNotSupportedException {
        Sommet o=null;
        try {
            o=(Sommet) super.clone();
        } catch (Exception e) {
            throw e;
        }
        o.voisins=(Vector<Vector>) o.voisins.clone();
        return o; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    public Sommet(int ind,TheorieDuGraphe fr) {
        pressed=false;
        this.fr=fr;
        this.indice=ind;
        
        voisins=new Vector<>();
        name="";
        
        fr.getPanel().addMouseMotionListener(this);
        fr.getPanel().addMouseListener(this);
        
    }
    
    public void setFr(TheorieDuGraphe fr){
        this.fr=fr;
        fr.getPanel().addMouseMotionListener(this);
        fr.getPanel().addMouseListener(this);
    }
    
    

    public void drawSommet(Graphics panel) {
        gr=(Graphics2D) panel;
        
        if(this.hover==true)
            gr.setColor(sommetCOLOR);
        else
            gr.setColor(defaultSommetCOLOR);
        gr.fillOval(x, y,WIDTH,WIDTH);
//        gr2d.setStroke(new BasicStroke(1));
        gr.setColor(Color.BLACK);
        gr.drawOval(x, y,WIDTH,WIDTH);
        Font font=gr.getFont();
        gr.setFont(new Font(Font.SERIF, Font.BOLD, WIDTH/2));
        gr.setColor(indiceCOLOR);
        if(name.length()>0)
            gr.drawString(""+indice+": "+name, x,y);
        else
            gr.drawString(""+indice, x+WIDTH/3,y+WIDTH/2);            
        gr.setFont(font);
    }
    public void drawArc(Graphics panel) {
        gr=(Graphics2D) panel;
        for(int i=0;i<voisins.size();i++)
        {
            int xf=( (Sommet) ((Vector)voisins.get(i)).get(0)).x+WIDTH;
            int yf=( (Sommet) ((Vector)voisins.get(i)).get(0)).y+WIDTH/2;
            BasicStroke bs=new BasicStroke(1); //Size du ligne
            
            if(!fr.isOriented()){//NonOriented
                gr.setColor(arcCOLOR);
                gr.drawLine(x+WIDTH/2, y+WIDTH/2,xf-WIDTH/2,yf);
//                gr.setColor(sommetCOLOR);  
//                gr.drawLine(x+WIDTH/2, y+WIDTH/2,xf-WIDTH/2,yf);
//                gr2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                gr.setColor(indiceArcCOLOR);
                if(fr.isWeighted())
                    drawWeight(xf,yf,i);
//                gr2d.drawString(""+( (Integer) ((Vector)voisins.get(i)).get(1)), (xf+x)/2, (yf+y+WIDTH)/2);
            }
            if(fr.isOriented()){     //Oriented   
                gr.setColor(arcCOLOR);
                if(xf-WIDTH-70<x && x<xf-WIDTH+70)
                {
                    if(y>yf-WIDTH)
                        drawOrientedArrow(gr, x+WIDTH/2, y+WIDTH/2, xf-WIDTH/2, yf+WIDTH/2,i);
                    else if(y<yf)
                        drawOrientedArrow(gr, x+WIDTH/2, y+WIDTH/2, xf-WIDTH/2, yf-WIDTH/2,i);
                }
                else{
                    if(x<xf-WIDTH){
                        if(y>yf+76)
                            drawOrientedArrow(gr, x+WIDTH/2, y+WIDTH/2, xf-WIDTH*3/4, yf+WIDTH/2,i);
                        else if(y<yf-76)
                            drawOrientedArrow(gr, x+WIDTH/2, y+WIDTH/2, xf-WIDTH*3/4, yf-WIDTH/2,i);
                        else
                            drawOrientedArrow(gr, x+WIDTH/2, y+WIDTH/2, xf-WIDTH, yf,i);
                    }
                    else if(x>xf-WIDTH){
                        if(y>yf+76)
                            drawOrientedArrow(gr, x+WIDTH/2, y+WIDTH/2, xf-WIDTH*1/4, yf+WIDTH/2,i);
                        else if(y<yf-76)
                            drawOrientedArrow(gr, x+WIDTH/2, y+WIDTH/2, xf-WIDTH*1/4, yf-WIDTH/2,i);
                        else
                            drawOrientedArrow(gr, x+WIDTH/2, y+WIDTH/2, xf, yf,i);
                    }
                }
            }
        }
    }
    
//    void drawOrienftedArrow(Graphics g1, int x1, int y1, int x2, int y2,int i) {
//                Graphics2D g = (Graphics2D) g1.create();
//                g.setStroke(new BasicStroke(1));
//                double dx = x2 - x1, dy = y2 - y1;
//                double angle = Math.atan2(dy, dx);
//                int len = (int) Math.sqrt(dx*dx + dy*dy);
//                AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
//                at.concatenate(AffineTransform.getRotateInstance(angle));
//                g.transform(at);
//                
//
//                // Draw horizontal arrow starting in (0, 0)
//                g.drawLine(0, 0, len+3, 0);
////                g.drawArc(x1-len ,-width, len, 50, 0, -100);
//                g.fillPolygon(new int[] {len, len-20, len-20, len+5},
//                              new int[] {0, -6, 6, 0}, 4);
//                gr2d.setColor(indiceArcCOLOR);
//                gr2d.drawString(""+( (Integer) ((Vector)voisins.get(i)).get(1)), (x2+x1)/2, (y2+y1)/2);
//    }
    
    public void drawOrientedArrow(Graphics g, int x, int y, int xf, int yf,int i) {
        Graphics2D g1=(Graphics2D) g;
        Point from = new Point(x, y);
        Point to = new Point(xf,yf);
        int x1, y1,  x2,  y2;
        x1=from.x;
        y1=from.y;
        x2=to.x;
        y2=to.y;
        double dx = x2 - x1, dy = y1 - y2;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx * dx + dy * dy);

        int p1X = (int) (x2 + Math.cos(angle + Math.PI * 3 / 4) * 8);
        int p1Y = (int) (y2 - Math.sin(angle + Math.PI * 3 / 4) * 8);
        int p2X = (int) (x2 + Math.cos(angle - Math.PI * 3 / 4) * 8);
        int p2Y = (int) (y2 - Math.sin(angle - Math.PI * 3 / 4) * 8);
        // Draw horizontal arrow starting in (0, 0)
        g1.setColor(arcCOLOR);
        g1.drawLine(x1, y1, x2, y2);
        if(fr.isWeighted())
            drawWeight(xf,yf,i);
        Polygon polygon = new Polygon();
        polygon.addPoint(x2, y2);
        polygon.addPoint(p1X, p1Y);
        polygon.addPoint(p2X, p2Y);
        g1.setColor(arcCOLOR);
        g1.fillPolygon(polygon);
    }
    
    public void drawWeight(int xf,int yf,int i) {
        Point from = new Point(x, y);
        Point to = new Point(xf,yf);
        int x = (from.x + to.x)/2;
        int y = (from.y + to.y)/2;
        int radius=WIDTH/2;
        int rad = radius/2;
        
        Font font=gr.getFont();
        gr.setFont(new Font(Font.SERIF, Font.BOLD, WIDTH/2));
        gr.setColor(indiceArcCOLOR);
        gr.drawString(String.valueOf(""+( (Integer) ((Vector)voisins.get(i)).get(1)))+" "+COUT_NAME,x, y);
        gr.setFont(font);
            
//        gr.setColor(arcCOLOR);
//        gr.fillOval(x-rad, y-rad, 2*rad, 2*rad);
//        drawWeightText(String.valueOf(""+( (Integer) ((Vector)voisins.get(i)).get(1))), x, y);
    }
    
    public void drawWeightText(String text, int x, int y) {
        gr.setColor(indiceArcCOLOR);
        FontMetrics fm = gr.getFontMetrics();
        double t_width = fm.getStringBounds(text, gr).getWidth();
        gr.drawString(text, (int) (x - t_width / 2), (y + fm.getMaxAscent() / 2));
    }
    
    @Override
    public String toString() {
        return ""+indice; //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getX()>this.x && e.getX()<this.x+WIDTH && e.getY()>this.y && e.getY()<this.y+WIDTH )
        {
            
            for(int i=0;i<fr.getSommets().size();i++){
                Sommet temp=fr.getSommets().get(i);
                if(e.getX()>temp.x && e.getX()<temp.x+WIDTH && e.getY()>temp.y && e.getY()<temp.y+WIDTH ){
                    temp.pressed=true;
                    temp.draw=true;
                    fr.Sommets_start_end.add(0, this);
                    mouseDragged(e);
                    break;
                }
            }  
        }
              
    }
    
    @Override
    public void mouseDragged(MouseEvent evt) {
        Pan pan=(Pan) fr.getPanel();
        int panX=pan.getX()-pan.getWidth()/2-40;
        int panXf=pan.getX()+pan.getWidth()/2-64;
        int panY=pan.getY()-fr.getNavPanel().getHeight();
        int panYf=pan.getY()-fr.getNavPanel().getHeight() - WIDTH + pan.getHeight();
        if(evt.getY()>panY && evt.getY()<panYf && evt.getX()>panX && evt.getX()<panXf){
            if(pressed && !fr.isDessiner())
            {
                for (int i = 0; i < fr.getSommets().size(); i++) {
                    Sommet temp = fr.getSommets().get(i);
                    if(temp.indice==this.indice){
                        temp.hover=true;
                        sommetCOLOR=Color.red;
                        break;
                    }
                }
                int eX=evt.getX()-WIDTH/2;
                int eY=evt.getY()-WIDTH/2;
                this.setLocation(eX,eY);            
            }else{
                if(fr.isDessiner()){
                    if(draw){
                        int X=evt.getX();
                        int Y=evt.getY();
                        fr.getPanel().repaint();
                        gr=(Graphics2D) (Graphics) fr.getGraphPanel();
                        gr.setColor(Color.black);
                        gr.drawLine(x+WIDTH /2, y+WIDTH /2, X, Y);
                    }
                }       
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        pressed=false;
        draw=false;
//        System.out.println("Moved");
    }

    @Override
    public void setLocation(int eX, int eY) {
        this.x=eX;
        this.y=eY;
        fr.repaint();                           //Pour afficher les sommets
    }
    
    public void setPosition(int x,int y){
        this.x=x;
        this.y=y;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {        //Supprimer un noeud
        if(e.getX()>this.x && e.getX()<this.x+WIDTH && e.getY()>this.y && e.getY()<this.y+WIDTH ){
            for(int i=0;i<fr.getSommets().size();i++){
                if(fr.getSommets().get(i).indice==this.indice)
                {
                    Sommet asupprimer=fr.getSommets().get(i);
                    for (int j = 0; j < fr.getSommets().size(); j++) {
                        for (int k = 0; k < fr.getSommets().get(j).voisins.size(); k++) {
                            if(( (Sommet) fr.getSommets().get(j).voisins.get(k).get(0)).indice==asupprimer.indice) 
                                fr.getSommets().get(j).voisins.remove(k);
                        }
                    }
                    asupprimer.setPosition(-100, -100);
                    fr.getSommets().remove(i);
                    fr.setV(-1);
                    fr.setNbrV(fr.getV());
//                    fr.paint(fr.getGraphics());
                    fr.getPanel().repaint();
                }
            }
            for (int i = 0; i < fr.getSommets().size(); i++) {
                fr.getSommets().get(i).indice=i+1;
                
            }
        }
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public String getCoutName() {
        return COUT_NAME;
    }

    public void setCoutName(String coutName) {
        this.COUT_NAME = coutName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if( fr.isDessiner() && draw )
        {
        boolean released = false ;
            for(int i=0;i<fr.getSommets().size();i++)
            {
                Sommet temp= (Sommet) fr.getSommets().get(i);
                if(e.getX()>temp.x && e.getX()<temp.x + temp.WIDTH && e.getY()>temp.y && e.getY()<temp.y+temp.WIDTH )
                {
                    System.out.println("Worked"+temp.indice);
                    if(fr.Sommets_start_end.size()>=1)
                        fr.Sommets_start_end.add(1,temp);
                    released=true;
                    break;
                }
            }
            if(released && fr.Sommets_start_end.size()>=1)// Si oui on va ajouter un nouveau voisin
                {
                    boolean exist=false;
                    boolean ok=true;
                    
                    Vector temp=new Vector();       //Vector temporel pour stocker le voisn et son cout
                    temp.add(fr.Sommets_start_end.get(1)); //Pour l'inséerer au Vector voisins
                    String tempString=""+fr.Sommets_start_end.get(0).indice+" et  "+fr.Sommets_start_end.get(1).indice;
                    int cout=0;
                    try {
                        if(fr.Sommets_start_end.get(0).indice != fr.Sommets_start_end.get(1).indice)
                            cout=Integer.parseInt(JOptionPane.showInputDialog("Entrer le côut entre "+tempString));
                        else{
                            String nom=JOptionPane.showInputDialog(fr, "Donner le nom de ce sommet", "Nom de sommet", JOptionPane.INFORMATION_MESSAGE);
                            if(nom.length()>0 && !nom.contains(" ")){
                                this.name=nom;
                                ok=false;
                            }else
                                JOptionPane.showMessageDialog(fr, "nom incorrect  :/  !!","Warning",JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(fr, "Le côut est un nombre -_-  !!");
                        throw ex;
                    }
                    temp.add(cout);
                    if(ok){
                        for(int i=0;i<fr.Sommets_start_end.get(0).voisins.size();i++) // Pour modification
                            if(temp.get(0)==fr.Sommets_start_end.get(0).voisins.get(i).get(0)){
                                exist=true;         //Vérifier si ce somet existe ou nn
                                fr.Sommets_start_end.get(0).voisins.remove(i);
                                fr.Sommets_start_end.get(0).voisins.add(new Vector(temp));
                                break;
                            }

                        if(!exist){ //Sinon
                            fr.Sommets_start_end.get(0).voisins.add(new Vector(temp));
                            System.out.println(""+fr.Sommets_start_end.get(0)+"--->"+fr.Sommets_start_end.get(1));
                        }

                        if(!fr.isOriented()){  //S'il est nonOriented on va ajouter dans les sommets !
                            exist=false;
                            temp.clear();
                            temp.add(fr.Sommets_start_end.get(0));
                            temp.add(cout);
                            for(int i=0;i<fr.Sommets_start_end.get(1).voisins.size();i++)
                                if(temp.get(0)==fr.Sommets_start_end.get(1).voisins.get(i).get(0)){
                                    exist=true;         //Vérifier si ce somet existe ou nn
                                    fr.Sommets_start_end.get(1).voisins.remove(i);
                                    fr.Sommets_start_end.get(1).voisins.add(new Vector(temp));
                                    break;
                                }

                            if(!exist){ //Sinon
                            fr.Sommets_start_end.get(1).voisins.add(new Vector(temp));
    //                        fr.setE(1);
                            System.out.println(fr.getE()+" : "+fr.Sommets_start_end.get(1)+"--->"+fr.Sommets_start_end.get(0));
                            } 
                        }
                    }
                    temp.clear();
                    fr.Sommets_start_end.clear();
                    fr.RemplirMatrix(fr.getV()+1);
                }
                else
                {
                    fr.Sommets_start_end.clear();
                    fr.getPanel().repaint();
                }
            }
            for (int i = 0; i < fr.getSommets().size(); i++) {
                Sommet temp = fr.getSommets().get(i);
                    temp.hover=false;
            }
            pressed=false;
            draw=false;
            fr.repaint();
        }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Sommet)
            if(((Sommet) obj).indice==this.indice)
                 return true; //To change body of generated methods, choose Tools | Templates.
        return false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }   

//    @Override
//    public void run() {
////         this.drawArc(gr2d);
////         this.drawSommet(gr2d);
//    }

    void setGraphics(Graphics gr2d){
        this.gr=(Graphics2D) gr2d;
    }

    @Override
    public void run() {
        this.drawArc(gr);
        this.drawSommet(gr);
    }
    
}
