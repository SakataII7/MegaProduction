package MegaCasting.classes;

import Interface.PaginationObserver;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 *
 * @author Ikiuchi
 */
public class Pagination<T> extends JPanel{
    
    //Attributs
    private List<PaginationObserver> filterObserverList;
    private List<T> data;
    private JLabel pageDisplayer, label;
    private JComboBox displayRange;
    private JButton first, prev, next, last;
    private int totalPages, currentPage;
    private ActionListener listener;

    //Constructeur
    public Pagination(List<T> data) {
        this.data = data;
        filterObserverList = new ArrayList<PaginationObserver>();
        this.setPreferredSize(new Dimension(350, 50));
        this.setOpaque(false);
        
        this.initComponents();
    }

    private void initComponents() {
        listener = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if(command.equals("next"))
                    next();
                if(command.equals("prev"))
                    previous();
                if(command.equals("first"))
                    toStart();
                if(command.equals("last"))
                    toEnd();
                if(command.equals("range")){
                    try{
                        reset(data);
                    }catch(Exception ex){
                        
                    }
                }
            }
            
        };
        label = new JLabel("#Par page");
        label.setPreferredSize(new Dimension(65, 30));
        this.add(label);
        
        displayRange = new JComboBox();
        displayRange.addItem(5);
        displayRange.addItem(10);
        displayRange.addItem(15);
        displayRange.addItem(20);
        displayRange.addItem(50);
        displayRange.addItem(100);
        displayRange.addItem("Tout");
        displayRange.setPreferredSize(new Dimension(60, 25));
        displayRange.setActionCommand("range");
        displayRange.addActionListener(listener);
        this.add(displayRange);
        
        label = new JLabel("");
        label.setPreferredSize(new Dimension(35, 30));
        this.add(label);
        
        first = new JButton();
        first.setPreferredSize(new Dimension(25, 25));
        first.setIcon(new ImageIcon(getClass().getResource("/miu/openc/pagination/first.png")));
        first.setBorderPainted(false);
        first.setFocusPainted(false);
        first.setActionCommand("first");
        first.addActionListener(listener);
        first.setContentAreaFilled(false);
        this.add(first);
        
        prev = new JButton();
        prev.setPreferredSize(new Dimension(25, 25));
        prev.setIcon(new ImageIcon(getClass().getResource("/miu/openc/pagination/prev.png")));
        prev.setBorderPainted(false);
        prev.setFocusPainted(false);
        prev.setActionCommand("prev");
        prev.addActionListener(listener);
        prev.setContentAreaFilled(false);
        this.add(prev);
        
        pageDisplayer = new JLabel(currentPage+"/"+totalPages);
        pageDisplayer.setOpaque(true);
        pageDisplayer.setBackground(new Color(250, 250, 250));
        pageDisplayer.setBorder(BorderFactory.createEtchedBorder());
        pageDisplayer.setHorizontalAlignment(JLabel.CENTER);
        pageDisplayer.setPreferredSize(new Dimension(60, 30));
        this.add(pageDisplayer);
        
        next = new JButton();
        next.setPreferredSize(new Dimension(25, 25));
        next.setIcon(new ImageIcon(getClass().getResource("/miu/openc/pagination/next.png")));
        next.setBorderPainted(false);
        next.setFocusPainted(false);
        next.setActionCommand("next");
        next.addActionListener(listener);
        next.setContentAreaFilled(false);
        this.add(next);
        
        last = new JButton();
        last.setPreferredSize(new Dimension(25, 25));
        last.setIcon(new ImageIcon(getClass().getResource("/miu/openc/pagination/last.png")));
        last.setBorderPainted(false);
        last.setFocusPainted(false);
        last.setActionCommand("last");
        last.addActionListener(listener);
        last.setContentAreaFilled(false);
        this.add(last);
    }
    
    public void addPaginationObserver(PaginationObserver obs){
        filterObserverList.add(obs);
    }
    
    public void removePaginationObserver(PaginationObserver obs){
        filterObserverList.remove(obs);
    }
    
    public void next(){
        calculate();
        if(currentPage < totalPages){
            currentPage++;
            notify(getCurrentData());
            updateComponent();
        }
    }
    
    public void previous(){
        calculate();
        if(currentPage > 1){
            currentPage--;
            notify(getCurrentData());
            updateComponent();
        }
    }
    
    public void toStart(){
        calculate();
        if(currentPage != 1){
            currentPage = 1;
            notify(getCurrentData());
            updateComponent();
        }
    }
    
    public void toEnd(){
        calculate();
        if(currentPage != totalPages){
            currentPage = totalPages;
            notify(getCurrentData());
            updateComponent();
        }
    }
    
    public void updateComponent(){
        if(currentPage == 1){
            prev.setIcon(new ImageIcon(getClass().getResource("/miu/openc/pagination/prev_dea.png")));
            first.setIcon(new ImageIcon(getClass().getResource("/miu/openc/pagination/first_dea.png")));
            if(totalPages > 1){
                next.setIcon(new ImageIcon(getClass().getResource("/miu/openc/pagination/next.png")));
                last.setIcon(new ImageIcon(getClass().getResource("/miu/openc/pagination/last.png")));
            }else{
                next.setIcon(new ImageIcon(getClass().getResource("/miu/openc/pagination/next_dea.png")));
                last.setIcon(new ImageIcon(getClass().getResource("/miu/openc/pagination/last_dea.png")));
            }
        }else{
            if(currentPage == totalPages){
                next.setIcon(new ImageIcon(getClass().getResource("/miu/openc/pagination/next_dea.png")));
                last.setIcon(new ImageIcon(getClass().getResource("/miu/openc/pagination/last_dea.png")));
                if(totalPages > 1){
                    prev.setIcon(new ImageIcon(getClass().getResource("/miu/openc/pagination/prev.png")));
                    first.setIcon(new ImageIcon(getClass().getResource("/miu/openc/pagination/first.png")));
                }else{
                    prev.setIcon(new ImageIcon(getClass().getResource("/miu/openc/pagination/prev_dea.png")));
                    first.setIcon(new ImageIcon(getClass().getResource("/miu/openc/pagination/first_dea.png")));
                }
            }else{
                prev.setIcon(new ImageIcon(getClass().getResource("/miu/openc/pagination/prev.png")));
                first.setIcon(new ImageIcon(getClass().getResource("/miu/openc/pagination/first.png")));
                next.setIcon(new ImageIcon(getClass().getResource("/miu/openc/pagination/next.png")));
                last.setIcon(new ImageIcon(getClass().getResource("/miu/openc/pagination/last.png")));
            }
        }
        pageDisplayer.setText(currentPage+"/"+totalPages);
    }
    
    private void notify(List<T> data){
        for(PaginationObserver obs : filterObserverList)
            obs.update(data);
    }
    
    public void reset(List<T> data){
        this.data = data;
        currentPage = 1;
        calculate();
        notify(getCurrentData());
        updateComponent();
    }
    
    public void reset(){
        currentPage = 1;
        calculate();
        notify(getCurrentData());
        updateComponent();
    }
    
    private void calculate(){
        if(displayRange.getSelectedIndex() == 6)
            totalPages = 1;
        else{
            try{
                totalPages = Math.round(data.size() / Integer.parseInt(displayRange.getSelectedItem().toString()));
                if(data.size() > Integer.parseInt(displayRange.getSelectedItem().toString()) * totalPages)
                    totalPages++;
                if(totalPages == 0)
                    totalPages++;
            }catch(Exception e){
                totalPages = 1;
            }
        }
    }
    
    private List<T> getCurrentData(){
        if(totalPages > currentPage)
            return (data.subList((currentPage - 1) * Integer.parseInt(displayRange.getSelectedItem().toString()), 
                    (currentPage) * Integer.parseInt(displayRange.getSelectedItem().toString())));
        else{
            if(totalPages == 1)
                return data;
            else
                return (data.subList((currentPage - 1) * Integer.parseInt(displayRange.getSelectedItem().toString()), 
                    data.size()));
        }
    }
    
    public int getCurrentPage() {
        return currentPage;
    }

    public List<T> getData() {
        return data;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
