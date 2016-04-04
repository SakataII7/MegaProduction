/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MegaCasting.classes;


import Interface.PaginationObserver;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TestPagination extends JFrame{
    //Notre Panneau de pagination
    private Pagination paginationPanel;
    //Un observateur
    private PaginationObserver paginationObserver;
    //Le panneau qui va afficher les données et le panneau principal
    private JPanel dataLayer, contentPane;
    
    public TestPagination(){
        this.setTitle("Test du système de pagination");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.initComponents();
    }

    private void initComponents() {
        dataLayer = new JPanel();
        
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        
        /*
         * Construction de notre système de pagination pour la liste
         * fournie par la méthode getList()
         */
        paginationPanel = new Pagination<String>(getList());
        //Instanciation avec classe anonyme de notre observateur
        paginationObserver = new PaginationObserver<String>(){

            /*
             * Implémentation de la méthode update de l'interface
             */
            @Override
            public void update(List<String> data) {
                dataLayer.removeAll();
                dataLayer.repaint();
                dataLayer.setPreferredSize(new Dimension(360, data.size()*30));
                JLabel label;
                for(String st : data){
                    label = new JLabel(st);
                    label.setPreferredSize(new Dimension(360, 25));
                    label.setBorder(BorderFactory.createEtchedBorder());
                    dataLayer.add(label);
                }
                dataLayer.repaint();
                dataLayer.updateUI();
            }
            
        };
        //Ajout de l'observateur
        paginationPanel.addPaginationObserver(paginationObserver);
        
        contentPane.add(new JScrollPane(dataLayer));
        contentPane.add(paginationPanel, BorderLayout.SOUTH);
        this.setContentPane(contentPane);
        paginationPanel.reset();
    }
    
    /*
     * Création d'une liste de données à paginer
     */
    private List<String> getList(){
        ArrayList<String> list = new ArrayList<String>();
        for(int i = 1; i <= 500; i++)
            list.add(i + " - Element numéro " + i + " de la liste");
        return list;
    }
   
}