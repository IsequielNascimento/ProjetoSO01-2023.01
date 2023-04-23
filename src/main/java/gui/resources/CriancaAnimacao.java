package gui.resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CriancaAnimacao extends JPanel implements ActionListener {

    private Timer timer;
    private int x = 0;
    private int y = 0;
    private int deltaX = 5;
    private Image image;
    private int panelWidth = 400;
    private int panelHeight;

    public CriancaAnimacao() {
        // Carrega a imagem
        image = new ImageIcon("src/main/java/gui/resources/assets//criancaComBola/crianca1LadoD1.png").getImage();

        // Configura o temporizador para atualizar a posição da imagem
        timer = new Timer(50, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // Desenha a imagem na posição atual
        g2d.drawImage(image, x, y, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Atualiza a posição da imagem
        x += deltaX;

        // Verifica se a imagem ultrapassou o limite direito do painel
        if (x + image.getWidth(null) > getWidth() / 2) {
            // Inverte a direção do movimento da imagem
            deltaX = -deltaX;
            image = new ImageIcon("src/main/java/gui/resources/assets//criancaComBola/crianca1LadoE1.png").getImage();
            // Reposiciona a imagem para o meio do painel
            x = (getWidth() / 2 -25) - image.getWidth(null);
        }

        // Verifica se a imagem ultrapassou o limite esquerdo do painel
        if (x < 0) {
            // Inverte a direção do movimento da imagem
            deltaX = -deltaX;
            image = new ImageIcon("src/main/java/gui/resources/assets//criancaComBola/crianca1LadoD1.png").getImage();
            // Reposiciona a imagem para a borda esquerda do painel
            x = 0;
        }

        // Redesenha o painel com a nova posição da imagem
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Animated Image");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        CriancaAnimacao panel = new CriancaAnimacao();
        frame.add(panel);

        frame.setVisible(true);
    }
}

