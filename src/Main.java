import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.concurrent.Semaphore;

class Main {

    public static Integer MAX_PERMITIONS = 0;

    // SETA A QUANTIDADE DE BOLAS NO CESTO K
    public static void setMaxPermitions(Integer maxPermitions) {
        MAX_PERMITIONS = maxPermitions;
    }

    //CLASSE QUE ENVOLVE OS SEMAFOROS
    static class SemaphoreWrapper {
        //QUANTIDADE DE BOLAS NO CESTO K
        Semaphore cestoCheio = new Semaphore(0);
        //QUANTIDADE DE ESPAÇOS VAZIOS NO CESTO K
        Semaphore cestoVazio = new Semaphore(MAX_PERMITIONS );
    }

    public static void main(String[] args) {
        SemaphoreWrapper wrapper = new SemaphoreWrapper();

        //JANELA INICIAL DA APLICAÇÃO
        JFrame frameAnimacao = new JFrame("Janela da Animação");
        frameAnimacao.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameAnimacao.setSize(1600, 900);

        JPanel panelAplicacao = new JPanel(new BorderLayout());
        URL imageURL = Main.class.getResource("/image/background.png"); // carrega a imagem
        assert imageURL != null;
        ImageIcon backgroundIcon = new ImageIcon(imageURL); // cria um ImageIcon com a imagem carregada
        JLabel backgroundAplicacao = new JLabel(backgroundIcon); // cria um JLabel com o ImageIcon
        backgroundAplicacao.setSize(frameAnimacao.getSize());

        //ADICIONANDO COMPONENTES NA JANELA INICIAL DA APLICAÇÃO
        panelAplicacao.add(backgroundAplicacao);
        //  panelAplicacao.add(botaoAbrirModalCriarThread);

        JPanel logCrianca = new LogCrianca();
        JPanel criarCriancaPanel = new CriarCriancaPanel();
        JPanel cestoPanel = new CestoForm();

        JPanel painelInferior = new JPanel();
        painelInferior.setLayout(new GridLayout(1, 3));
        painelInferior.add(logCrianca);
        painelInferior.add(criarCriancaPanel);
        painelInferior.add(cestoPanel);
        painelInferior.setMaximumSize(new Dimension(1600, 300));


        frameAnimacao.add(panelAplicacao, BorderLayout.CENTER);
        frameAnimacao.add(painelInferior, BorderLayout.SOUTH);
        frameAnimacao.setVisible(true);
        frameAnimacao.setLayout(new BorderLayout());


        JLabel quantidadeMaximaBolas = new JLabel("Quantidade máxima do Cesto: 0");
        JLabel gambiarra = new JLabel(" ");
        quantidadeMaximaBolas.setFont( new Font("Arial", Font.PLAIN, 20) );
        quantidadeMaximaBolas.setForeground(Color.WHITE);


        quantidadeMaximaBolas.setSize(350, 150);
        quantidadeMaximaBolas.setLocation(150, 450);

        gambiarra.setSize(350, 150);
        gambiarra.setLocation(450, 350);

        panelAplicacao.add(quantidadeMaximaBolas);
        panelAplicacao.add(gambiarra);


        int componentCount2 = panelAplicacao.getComponentCount();
        panelAplicacao.setComponentZOrder(backgroundAplicacao, componentCount2 - 1);
        panelAplicacao.repaint();

        // INICIALIZAR THREADS
        CestoForm.btnCriarCesto.addActionListener(e -> {
            if (CestoForm.textFieldQuantidadeCesto.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Preencha o campo de quantidade de bolas");

            }else {
                // INICIALIZA O CESTO COM K ESPAÇOS VAZIOS
                setMaxPermitions(Integer.parseInt(CestoForm.textFieldQuantidadeCesto.getText()));
                wrapper.cestoVazio = new Semaphore(MAX_PERMITIONS);
              quantidadeMaximaBolas.setText("Quantidade máxima do Cesto: " + MAX_PERMITIONS);
            }
        });

        //CLASSE QUE ENVOLVE OS SEMAFOROS DAS CRIANÇAS
        class ChildSpacer {

            int spacer;

            public ChildSpacer(int spacer) {
                this.spacer = spacer;
            }
        }

        // CLASSE QUE ENVOLVE OS COMPONENTES DAS CRIANÇAS (IMAGEM, NOME E TEMPO)
        ChildSpacer childSpacer = new ChildSpacer(0);

        //INSTANCIA UMA THREAD CRIANÇA E ADICIONA NA JANELA
        CriarCriancaPanel.buttonCriarThread.addActionListener((e -> {
            if (MAX_PERMITIONS == null){
                JOptionPane.showMessageDialog(null, "Crie o cesto primeiro");
            }else {
                ImageIcon childIcon = getChildIcon();
                JLabel childLabel = new JLabel();
                JLabel childName = new JLabel(CriarCriancaPanel.nomeTextField.getText());
                JLabel childTime = new JLabel();

                childLabel.setIcon(childIcon);
                Dimension size = childLabel.getPreferredSize();

                // SETA A POSIÇÃO DOS COMPONENTES NA JANELA DE ACORDO COM A QUANTIDADE DE CRIANÇAS
                childLabel.setBounds(childSpacer.spacer, 370, size.width, size.height);
                childName.setBounds(childSpacer.spacer + 350, 150, size.width, size.height);
                childTime.setBounds(childSpacer.spacer + 350, 170, size.width, size.height);


                // SETA A POSIÇÃO DOS COMPONENTES NA JANELA DE ACORDO COM A QUANTIDADE DE CRIANÇAS
                childSpacer.spacer = childSpacer.spacer + 250;

                // ADICIONA OS COMPONENTES NA JANELA
                panelAplicacao.add(childLabel);
                panelAplicacao.add(childName);

                childLabel.setVisible(true);
                childName.setVisible(true);

                int componentCount = panelAplicacao.getComponentCount();
                panelAplicacao.setComponentZOrder(backgroundAplicacao, componentCount - 1);
                panelAplicacao.repaint();
                JLabelWrapper JLabelWrapper = new JLabelWrapper(childTime, childLabel, panelAplicacao,
                        backgroundAplicacao);

                Crianca thread = new Crianca(CriarCriancaPanel.nomeTextField.getText(),
                        Integer.parseInt(CriarCriancaPanel.textFieldTempoBrincadeira.getText()), Integer.parseInt(CriarCriancaPanel.textFieldTempoQuieta.getText()),
                        CriarCriancaPanel.checkBoxBola.isSelected(), wrapper.cestoCheio, wrapper.cestoVazio, JLabelWrapper
                );
                thread.start();
            }
        }));
    }

    private static ImageIcon getChildIcon() {
        URL imageChildURL = Main.class.getResource("/image/cesto_cheio.png");
        assert imageChildURL != null;
        return new ImageIcon(
                imageChildURL
        );
    }
}