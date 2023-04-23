package gui.resources;

import javax.swing.*;
import java.awt.*;

public class MeuCanvas extends JPanel {
    public MeuCanvas(){
        setPreferredSize(new Dimension(1200, 700));


    }
    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(50, 50, 10, 10);
    }
}
