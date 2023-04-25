package gui.resources.animacao;

import gui.resources.CestoForm;
import threads.Cesto;
import threads.Crianca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import static threads.Crianca.*;

public class CriancaAnimacao extends JPanel implements ActionListener {

    public static Timer timer;

    private static int numCrianca = 1;
    public int brincar = 0;
    private int x = 100;
    private int y = 400;
    private int panelY = 150;
    private int deltaX = 5;
    public int i = 0;

    private int panelWidth;
    private int panelHeight;
    private static Image imageCrianca;


    private Image cestoImage;
    private static Semaphore cesto;

    // define o tamanho da imagem
    int newWidth = 80;
    int newHeight = 80;
    private ArrayList<Crianca> criancas = new ArrayList<>();

    public void adicionarCrianca(Crianca crianca) {
        criancas.add(crianca);

    }

    public CriancaAnimacao() {
        // Carrega a imagem
        cestoImage = new ImageIcon("src/main/java/gui/resources/assets/cesto/cesto5.png").getImage();

            imageCrianca = new ImageIcon("src/main/java/gui/resources/assets//criancaComBola/crianca" + numCrianca + "LadoD1.png").getImage();
        // Configura o temporizador para atualizar a posição da imagem


        timer = new Timer(25, this);
        timer.start();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // Desenha a imagem de fundo
        Image bp = new ImageIcon("src/main/java/gui/resources/assets/quadra.jpg").getImage();
        g2d.drawImage(bp, 0, 0, getWidth(), getHeight(), null);

        g2d.drawImage(cestoImage, getWidth() / 2 - 50, y, newWidth, newHeight, null);
        for (Crianca crianca : criancas) {
            for ( i = 0; i < criancas.size(); i++) {

                    g2d.drawImage(imageCrianca, x + (i *25), y, newWidth, newHeight, null);
            }
        }



        // Desenha a imagem na posição atual

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Atualiza a posição da imagem
        x += deltaX;
if(temBola) {
    // Verifica se a imagem ultrapassou o limite direito do painel
    if (x + imageCrianca.getWidth(null) > getWidth() - (45 * i)) {
        // Inverte a direção do movimento da imagem
        deltaX = -deltaX;
        imageCrianca = new ImageIcon("src/main/java/gui/resources/assets//criancaComBola/crianca" + numCrianca + "LadoE1.png").getImage();
        // Reposiciona a imagem para o meio do painel
        x = (getWidth() - (45 * i)) - imageCrianca.getWidth(null);
    }

    // Verifica se a imagem ultrapassou o limite esquerdo do painel
    if (x < 100 + (25 * i)) {
        // Inverte a direção do movimento da imagem
        deltaX = -deltaX;
        imageCrianca = new ImageIcon("src/main/java/gui/resources/assets//criancaComBola/crianca" + numCrianca + "LadoD1.png").getImage();
        // Reposiciona a imagem para a borda esquerda do painel
        x = 100 + (25 * i);
    }
}
else {
    // Verifica se a imagem ultrapassou o limite direito do painel
    if (x + imageCrianca.getWidth(null) > getWidth() - (45 * i)) {
        // Inverte a direção do movimento da imagem
        deltaX = -deltaX;
        imageCrianca = new ImageIcon("src/main/java/gui/resources/assets//criancaSemBola/crianca" + numCrianca + "LadoE1.png").getImage();
        // Reposiciona a imagem para o meio do painel
        x = (getWidth() - (45 * i)) - imageCrianca.getWidth(null);
    }

    // Verifica se a imagem ultrapassou o limite esquerdo do painel
    if (x < 100 + (25 * i)) {
        // Inverte a direção do movimento da imagem
        deltaX = -deltaX;
        imageCrianca = new ImageIcon("src/main/java/gui/resources/assets//criancaSemBola/crianca" + numCrianca + "LadoD1.png").getImage();
        // Reposiciona a imagem para a borda esquerda do painel
        x = 100 + (25 * i);
    }
        }

        // Redesenha o painel com a nova posição da imagem
        repaint();
    }
}


