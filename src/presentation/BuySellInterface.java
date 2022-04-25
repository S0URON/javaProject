package presentation;

import metier.GestionPlante;
import metier.Plante;
import metier.PlanteException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BuySellInterface extends JFrame {
    String selected;
    PlantTableModel table;
    GestionPlante api;

    JPanel container = new JPanel(new FlowLayout());
    JLabel qte = new JLabel("qte :");
    JTextField qteField = new JTextField();
    JButton sell = new JButton("vendre");
    JButton buy = new JButton("acheter");
    JButton cancel = new JButton("annuler");

    public BuySellInterface(String selected, PlantTableModel m, GestionPlante api){
        this.selected = selected;
        this.table = m;
        this.api = api;

        container.add(qte);
        qteField.setPreferredSize(new Dimension(150,30));
        container.add(qteField);
        container.add(sell);
        container.add(buy);
        container.add(cancel);

        this.add(container);

        cancel.addActionListener(e -> {
            this.dispose();
        });

        qteField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });

        sell.addActionListener(e -> {
            try{
                if(!qteField.getText().equals("")){
                    int selectedQuantity = Integer.parseInt(qteField.getText());
                    this.api.vendrePlante(selected,selectedQuantity);
                    table.charger(api.listeDesPlante());
                    this.dispose();
                }
            }catch (PlanteException pe){
                JOptionPane.showMessageDialog(this, pe.getMessage());
            }
        });
        buy.addActionListener(e -> {
            try{
                if(!qteField.getText().equals("")){
                    int selectedQuantity = Integer.parseInt(qteField.getText());
                    this.api.acheterPlante(selected,selectedQuantity);
                    table.charger(api.listeDesPlante());
                    this.dispose();
                }
            }catch (PlanteException pe){
                JOptionPane.showMessageDialog(this, pe.getMessage());
            }
        });

        this.pack();
        setVisible(true);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }




}
