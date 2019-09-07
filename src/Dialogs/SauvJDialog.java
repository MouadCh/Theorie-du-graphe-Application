/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dialogs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import theorie.du.graphe.Pan;
import theorie.du.graphe.Sommet;
import theorie.du.graphe.TheorieDuGraphe;

/**
 *
 * @author MouadC
 */
public class SauvJDialog extends javax.swing.JDialog {
    private static final String DOSSIERPRJ ="./prj/";
    private LinkedList listePlaces;
    TheorieDuGraphe fr;
    Pan Panel;
    DefaultListModel listeProjets;
     // Nom du projet en cours d'édition
    private String nomProjet = " ";
    String message;
    /**
     * Creates new form Sauv
     */
    public SauvJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        fr=(TheorieDuGraphe) parent;
        Panel=(Pan) fr.getPanel();
        setTitle("Sauvgarder un projet ");
        initComponents();
        setLocationRelativeTo(parent);
        listeProjets = new DefaultListModel();
        File dossier = new File(DOSSIERPRJ);
        File[] fichiers = dossier.listFiles();
        for(int i=0;i<fichiers.length;i++)
              listeProjets.addElement(fichiers[i].getName());
        projetsJList.setModel(listeProjets); 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        projetsJList = new javax.swing.JList<>();
        Charger = new javax.swing.JButton();
        Annuler = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Creer = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        projetsJList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(projetsJList);

        Charger.setText("Sauvgarder");
        Charger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChargerActionPerformed(evt);
            }
        });

        Annuler.setText("Annuler");
        Annuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnnulerActionPerformed(evt);
            }
        });

        jLabel1.setText(" votre projet.");

        jLabel2.setText("Choisir un projet et sauvegarder");

        jLabel3.setText("Ou bien creer votre nouveau projet :");

        Creer.setText("Creer ");
        Creer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Charger))
                            .addComponent(jLabel2))
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addGap(12, 12, 12))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Creer, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Annuler)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(Charger))
                        .addGap(25, 25, 25)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Creer)
                            .addComponent(Annuler))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                        .addGap(28, 28, 28))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnnulerActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_AnnulerActionPerformed

    private void ChargerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChargerActionPerformed
        // TODO add your handling code here:
        int index = projetsJList.getSelectedIndex();
        Vector sauvVector=new Vector();
        sauvVector.add(fr.isOriented()); //1  Type-Graph
        sauvVector.add(fr.details());   //2  Historique details
        sauvVector.add(fr.getSommets());  //3  Sommets
        
        Vector colorsVector=new Vector();
        colorsVector.add(Sommet.sommetCOLOR);
        colorsVector.add(Sommet.indiceCOLOR);
        colorsVector.add(Sommet.arcCOLOR);
        colorsVector.add(Sommet.indiceArcCOLOR);
        sauvVector.add(colorsVector);  //4  COLORS
        
        sauvVector.add(Sommet.WIDTH);  //5 WITDH
        sauvVector.add(Sommet.COUT_NAME);  //6 WITDH
        sauvVector.add(TheorieDuGraphe.PATH);  //7 Background Path
        
        
        try{
          FileOutputStream sortie = new FileOutputStream(DOSSIERPRJ+listeProjets.get(index));
          ObjectOutputStream s = new ObjectOutputStream(sortie);
          s.writeObject(sauvVector);
          s.close();
          this.dispose();
          } catch (FileNotFoundException ex) {
            Logger.getLogger(SauvJDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SauvJDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        TheorieDuGraphe.NOM_PROJET=(String) listeProjets.get(index);
        fr.setTITRE_PROJET();
        this.dispose();
    }//GEN-LAST:event_ChargerActionPerformed

    private void CreerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreerActionPerformed
        // TODO add your handling code here:
        CreerJDialog creerJDialog=new CreerJDialog(fr, true);
        creerJDialog.setVisible(true);
        listeProjets = new DefaultListModel();
        File dossier = new File(DOSSIERPRJ);
        File[] fichiers = dossier.listFiles();
        for(int i=0;i<fichiers.length;i++)
              listeProjets.addElement(fichiers[i].getName());
        projetsJList.setModel(listeProjets); 
        this.repaint();
    }//GEN-LAST:event_CreerActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SauvJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SauvJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SauvJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SauvJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SauvJDialog dialog = new SauvJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Annuler;
    private javax.swing.JButton Charger;
    private javax.swing.JButton Creer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JList<String> projetsJList;
    // End of variables declaration//GEN-END:variables
}