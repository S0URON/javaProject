package presentation;

import javax.swing.*;
import java.awt.*;

public class AddPlantInterface extends JFrame {

    JPanel inputFields = new JPanel(new GridLayout(4,4));



    public AddPlantInterface(){


        this.pack();
        setVisible(true);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new AddPlantInterface();
    }
}