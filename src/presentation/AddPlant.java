package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import dao.GererPlante;
import metier.*;

public class AddPlant extends JFrame{

    GestionPlante api;

	JComboBox CategoryBox = new JComboBox(Category.types.toArray());
	JComboBox SolBox = new JComboBox(Sol.types.toArray());
	JComboBox ExpositionBox = new JComboBox(Exposition.types.toArray());
	JComboBox FeuillageBox = new JComboBox(Feuillage.types.toArray());
	
	JPanel topPanel = new JPanel(new GridLayout(3,4));
	JPanel bottomPanel = new JPanel(new GridLayout(2,2));
	JPanel centerPanel = new JPanel(new GridLayout(3,3));
	
	JPanel categoryPanel = new JPanel(new GridLayout(3,1));
	JPanel solPanel = new JPanel(new GridLayout(3,1));
	JPanel expositionPanel = new JPanel(new GridLayout(3,1));
	JPanel feuillagePanel = new JPanel(new GridLayout(3,1));
	
	
	JButton addBtn = new JButton("Ajouter");
	JButton cancelBtn = new JButton("Annuler");
	
	JTextField nameBox = new JTextField();
	JTextField prixBox = new JTextField();
	JTextField qteBox = new JTextField();
	

	
	public AddPlant(GestionPlante api) {
        this.api = api;
		topPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.blue, 1)));
		centerPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.blue, 1)));
        bottomPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red, 1), "Chose wisely"));
        
        categoryPanel.add(new JLabel("Category :"));
        categoryPanel.add(CategoryBox);
        solPanel.add(new JLabel("Type Sol :"));
        solPanel.add(SolBox);
        feuillagePanel.add(new JLabel("Feuillage :"));
        feuillagePanel.add(FeuillageBox);
        expositionPanel.add(new JLabel("Exposition :"));
        expositionPanel.add(ExpositionBox);
        		
        topPanel.add(categoryPanel);
        topPanel.add(solPanel);
        topPanel.add(feuillagePanel);
        topPanel.add(expositionPanel);
        
        centerPanel.add(new JLabel("Nom :"));
        centerPanel.add(nameBox);
        
        centerPanel.add(new JLabel("Prix :"));
        centerPanel.add(prixBox);
        
        centerPanel.add(new JLabel("Quantité :"));
        centerPanel.add(qteBox);
        
        nameBox.setPreferredSize(new Dimension(150,30));
        prixBox.setPreferredSize(new Dimension(150,30));
        qteBox.setPreferredSize(new Dimension(150,30));
        
        bottomPanel.add(addBtn);
        bottomPanel.add(cancelBtn);
        addBtn.setPreferredSize(new Dimension(200, 40));
        this.add(topPanel, BorderLayout.CENTER);
        this.add(centerPanel, BorderLayout.NORTH);
        this.add(bottomPanel, BorderLayout.SOUTH);

        prixBox.addKeyListener(new KeyAdapter() {
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

        qteBox.addKeyListener(new KeyAdapter() {
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

        addBtn.addActionListener(e -> {

        });

        cancelBtn.addActionListener(e -> {
            this.dispose();
        });
        
        this.pack();
        setVisible(true);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	
}
