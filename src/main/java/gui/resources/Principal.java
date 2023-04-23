package gui.resources;

import javax.swing.*;
import java.awt.*;

public class Principal {
    public static JFrame janela;

    public static BackgroundPanel bp;

    public static void main(String[] args) {
        janela = new JFrame("Teste");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(1200, 700);
        janela.setLocationRelativeTo(null);
        janela.setResizable(false);


        bp = new BackgroundPanel("src/main/java/gui/resources/assets/quadra.jpg");

        janela.add(bp);


        janela.setVisible(true);
    }



}