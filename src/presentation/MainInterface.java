package presentation;

import javax.swing.*;

public class MainInterface extends JFrame {

    public MainInterface(){
        setSize(500,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new MainInterface();
    }
}
