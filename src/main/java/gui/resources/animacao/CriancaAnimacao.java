package gui.resources.animacao;

import threads.Crianca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class CriancaAnimacao extends JPanel implements ActionListener {

    private Timer timer;


    private int numCrianca = 2;
    private int x = 100;
    private int y = 400;
    private int panelY = 150;
    private int deltaX = 5;

    private int panelWidth;
    private int panelHeight;
    private Image image;
    private Image cestoImage;
    private static Semaphore cesto;

    // define o tamanho da imagem
    int newWidth = 80;
    int newHeight = 80;


    public CriancaAnimacao() {
        // Carrega a imagem
        image = new ImageIcon("src/main/java/gui/resources/assets//criancaComBola/crianca"+numCrianca+"LadoD1.png").getImage();

        cestoImage = new ImageIcon("src/main/java/gui/resources/assets/cesto/cesto5.png").getImage();
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


        g2d.drawImage(cestoImage, getWidth() / 2 -50, y, newWidth, newHeight, null);

        // Desenha a imagem na posição atual
        g2d.drawImage(image, x, y,newWidth,newHeight, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Atualiza a posição da imagem
        x += deltaX;

        // Verifica se a imagem ultrapassou o limite direito do painel
        if (x + image.getWidth(null) > getWidth() / 2 -50) {
            // Inverte a direção do movimento da imagem
            deltaX = -deltaX;
            image = new ImageIcon("src/main/java/gui/resources/assets//criancaComBola/crianca"+numCrianca+"LadoE1.png").getImage();
            // Reposiciona a imagem para o meio do painel
            x = (getWidth() / 2 -50) - image.getWidth(null);
        }

        // Verifica se a imagem ultrapassou o limite esquerdo do painel
        if (x < 100) {
            // Inverte a direção do movimento da imagem
            deltaX = -deltaX;
            image = new ImageIcon("src/main/java/gui/resources/assets//criancaComBola/crianca"+numCrianca+"LadoD1.png").getImage();
            // Reposiciona a imagem para a borda esquerda do painel
            x = 100;
        }

        // Redesenha o painel com a nova posição da imagem
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Animated Image");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(1200, 700);

        // Cria o painel principal que envolve a animação
        JPanel criancaAnimacao = new CriancaAnimacao();
        //criancaAnimacao.setMaximumSize(new Dimension(1200, 700));

        // Cria os dois novos painéis
        // Painel Log
        JPanel logCrianca = new LogCrianca();
        logCrianca.setBackground(Color.RED);

        // Painel Inserir Criança
        JPanel criarCriancaPanel = new CriarCriancaPanel() {
            public java.util.List<Crianca> criancas = new ArrayList<Crianca>();
            //Funcionalidade do botão Criar Criança
            @Override
            protected void btnCriarCriancaCLick(ActionEvent event) {
                //Cria uma nova criança
                Crianca crianca = new Crianca(nomeTextField.getText(), checkBoxBola.isSelected(), Integer.parseInt(textFieldTempoBrincadeira.getText()), Integer.parseInt(textFieldTempoQuieta.getText()));
                //Adiciona a criança na lista de crianças
                criancas.add(crianca);
                //Inicia a thread da criança
                crianca.start();


            }
        };
        criarCriancaPanel.setBackground(Color.BLUE);

        // Cria um novo painel que conterá os dois novos painéis
        JPanel painelInferior = new JPanel();
        painelInferior.setLayout(new GridLayout(1, 2));

        painelInferior.add(logCrianca);
        painelInferior.add(criarCriancaPanel);
        painelInferior.setSize(1200, 200);
        // Adiciona o painel existente acima do novo painel
        frame.add(criancaAnimacao, BorderLayout.CENTER);
        // Adiciona o novo painel abaixo do painel existente
        frame.add(painelInferior, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

}

