package presentation;

import dao.GererPlante;
import metier.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;

public class MainInterface extends JFrame {

    JPanel container = new JPanel();
    BoxLayout bl = new BoxLayout(container, BoxLayout.Y_AXIS);
    JPanel btns = new JPanel(new FlowLayout());
    JPanel topPanel = new JPanel(new GridLayout(1,4));
    JPanel bottomPanel = new JPanel(new GridLayout(1,1));

    JPanel categoryRadioGroup = new JPanel(new GridLayout(Category.types.size() + 2,1));
    JPanel solRadioGroup = new JPanel(new GridLayout(Sol.types.size() + 2,1));
    JPanel feuillageRadioGroup = new JPanel(new GridLayout(Feuillage.types.size() + 2,1));
    JPanel expositionRadioGroup = new JPanel(new GridLayout(Exposition.types.size() + 2,1));
    JTextField searchBox = new JTextField();
    JButton searchBtn = new JButton("search");
    JButton filterBtn = new JButton("filter");
    JButton supprimerBtn = new JButton("supprimer");

    PlantTableModel tm = new PlantTableModel();
    JTable table = new JTable(tm);
    JScrollPane jsp = new JScrollPane(table);
    JMenu menu = new JMenu("plant");
    JMenuItem addPlant = new JMenuItem("add");
    JMenuBar menuBar = new JMenuBar();

    GestionPlante plantApi = new GestionPlante();

    int selectedRow = -1;
    public MainInterface(){
//        setSize(500,300);
        topPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.blue, 1)));
        bottomPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red, 1), "liste des plantes"));

        categoryRadioGroup.add(new JLabel("category :"));
        ButtonGroup categroryGrp = new ButtonGroup();
        JRadioButton allCateg = new JRadioButton("all");
        allCateg.doClick();
        categroryGrp.add(allCateg);
        categoryRadioGroup.add(allCateg);
        for (int i = 0; i < Category.types.size(); i++) {
            JRadioButton btn = new JRadioButton(Category.types.get(i));
            categroryGrp.add(btn);
            categoryRadioGroup.add(btn);
        }

        solRadioGroup.add(new JLabel("type sol :"));
        ButtonGroup solGrp = new ButtonGroup();
        JRadioButton allSol = new JRadioButton("all");
        allSol.doClick();
        solGrp.add(allSol);
        solRadioGroup.add(allSol);
        for (int i = 0; i < Sol.types.size(); i++) {
            JRadioButton btn = new JRadioButton(Sol.types.get(i));
            solGrp.add(btn);
            solRadioGroup.add(btn);
        }

        feuillageRadioGroup.add(new JLabel("feuillage :"));
        ButtonGroup fGrp = new ButtonGroup();
        JRadioButton allF= new JRadioButton("all");
        allF.doClick();
        fGrp.add(allF);
        feuillageRadioGroup.add(allF);
        for (int i = 0; i < Feuillage.types.size(); i++) {
            JRadioButton btn = new JRadioButton(Feuillage.types.get(i));
            fGrp.add(btn);
            feuillageRadioGroup.add(btn);
        }

        expositionRadioGroup.add(new JLabel("expostion :"));
        ButtonGroup eGrp = new ButtonGroup();
        JRadioButton allE= new JRadioButton("all");
        allE.doClick();
        eGrp.add(allE);
        expositionRadioGroup.add(allE);
        for (int i = 0; i < Exposition.types.size(); i++) {
            JRadioButton btn = new JRadioButton(Exposition.types.get(i));
            eGrp.add(btn);
            expositionRadioGroup.add(btn);
        }


        topPanel.add(categoryRadioGroup);
        topPanel.add(solRadioGroup);
        topPanel.add(feuillageRadioGroup);
        topPanel.add(expositionRadioGroup);
        searchBox.setPreferredSize(new Dimension(150,30));
        btns.add(searchBox);
        btns.add(searchBtn);
        btns.add(filterBtn);
        btns.add(supprimerBtn);
        bottomPanel.add(jsp);

        menu.add(addPlant);
        menuBar.add(menu);
        menuBar.setSize(new Dimension(getWidth(), 40));
        container.setLayout(bl);
        container.add(menuBar);
        container.add(topPanel);
        this.add(container, BorderLayout.NORTH);
        btns.setPreferredSize(new Dimension(200, 40));
        this.add(btns, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

        this.pack();
        setVisible(true);


        tm.charger(plantApi.listeDesPlante());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        filterBtn.addActionListener(e -> {

            String selectedCategory = getSelectedButtonText(categroryGrp);
            String selectedSol = getSelectedButtonText(solGrp);
            String selectedFeuilltage = getSelectedButtonText(fGrp);
            String selectedExposition = getSelectedButtonText(eGrp);

            tm.charger(plantApi.filtrerPlante(selectedCategory,selectedFeuilltage,selectedSol,selectedExposition));
        });

        searchBtn.addActionListener(e -> {
            String mc = searchBox.getText();
            tm.charger(plantApi.chercherPlanteParMC(mc));
        });

        supprimerBtn.addActionListener(e -> {
            if(selectedRow > -1){
                int id = (int) tm.getValueAt(selectedRow,0);
                plantApi.supprimerPlante(id);
                tm.charger(plantApi.listeDesPlante());
            }
            selectedRow = -1;
        });

        table.addMouseListener(new MListener());
    }

    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
    public static void main(String[] args) {
        new MainInterface();
    }

    class MListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent event) {
            selectedRow = table.getSelectedRow();
            if (event.getClickCount() == 2 && event.getButton() == MouseEvent.BUTTON1) {
                String selectedPlant = (String) tm.getValueAt(selectedRow, 1);
                new BuySellInterface(selectedPlant, tm,plantApi);
                selectedRow = -1;
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
