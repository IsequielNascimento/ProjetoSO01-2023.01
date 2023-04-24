//A classe Crianca. Java é a classe principal de controle das crianças que irão brincar com a bola

package threads;
import gui.resources.CestoForm;
import gui.resources.CriancaConcrete;
import gui.resources.animacao.CriancaAnimacao;
import gui.resources.animacao.CriarCriancaPanel;
import gui.resources.animacao.LogCrianca;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
// javax.swing is a package that contains classes for creating graphical user interfaces
import javax.swing.*;

import static threads.Cesto.*;

public class Crianca extends Thread {

    static int numCrianca;

    private static int brincar;
    public static List<Crianca> criancas;
 private static int controle = 0;
    private String nome;
    public static boolean temBola;
    public static int tempoBrincadeira; //tb
    public static int tempoQuieta; //tq
    // public static int totalCrianca = 0;
    private long time = System.currentTimeMillis();


    //Construtor
    public Crianca(String nome, boolean temBola, int tempoBrincadeira, int tempoQuieta) {
        this.nome = nome;
        this.temBola = temBola;
        this.tempoBrincadeira = tempoBrincadeira * 1000;
        this.tempoQuieta = tempoQuieta * 1000;

    }


    //Método que inicia a brincadeira das crianças. OBS: O método é temporário para testes
    public void run() {
        try {
            while (true) {
                if (temBola) {
                    brincar();
                    colocarBola();
                    descansar();
                } else {
                    esperarBola();
                    pegarBola();
                    brincar();
                    colocarBola();
                    descansar();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("A criança " + nome + " foi interrompida");
        }
    }

    private void brincar() throws InterruptedException {
        System.out.println("Criança " + nome + " está brincando com a bola");
        Thread.sleep(tempoBrincadeira);
    }

    /* private void descansar() throws InterruptedException{
         if (temBola == false) {

             time = System.currentTimeMillis();
             System.out.println("A " + nome + " está descansando");

         }
     }*/
    private void descansar() throws InterruptedException {
        System.out.println("A " + nome + " está quieta");
        Thread.sleep(tempoQuieta);
    }

    private void esperarBola() throws InterruptedException {
        // Esperar para pegar a bola do cesto
        if (bolas.availablePermits() == 0) {
            System.out.println("Criança " + nome + " está esperando para pegar a bola do cesto");
        } else {
            bolas.acquire();
        }
    }

    private void pegarBola() throws InterruptedException {
        if (cesto.availablePermits() == 0) {
            System.out.println("Criança " + nome + " está esperando para pegar a bola do cesto");
        } else {

            cesto.acquire();
            System.out.println("Criança " + nome + " pegou a bola do cesto");
            temBola = true;

        }
    }


    private void colocarBola() throws InterruptedException {
        // Colocar a bola no cesto
        if (cesto.availablePermits() == K) {
            System.out.println("Criança " + nome + " está esperando para colocar a bola no cesto");
        } else {
            System.out.println("Criança " + nome + " está colocando a bola no cesto");
            temBola = false;
            bolas.release();
            cesto.release();
        }

    }


    public static void main(String[] args) {
        //javax.swing.SwingUtilities is a class that contains static methods for creating and managing top-level windows
        JFrame frame = new JFrame("Imagem Animada");
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
                //Crianca crianca = new Crianca(nomeTextField.getText(), checkBoxBola.isSelected(), Integer.parseInt(textFieldTempoBrincadeira.getText()), Integer.parseInt(textFieldTempoQuieta.getText()));
                //Adiciona a criança na lista de crianças
                //criancas.add(crianca);
                //Inicia a thread da criança

                    Crianca novaCrianca = new Crianca(nomeTextField.getText(), checkBoxBola.isSelected(), Integer.parseInt(textFieldTempoBrincadeira.getText()), Integer.parseInt(textFieldTempoQuieta.getText()));

                criancas.add(novaCrianca);
                    System.out.println(criancas.size());
                    System.out.println("Criança criada com sucesso!");

                if (numCrianca == 2) {
                    numCrianca--;
                } else {
                    numCrianca++;
                }



            }
            @Override
            protected void btnBrincarClick(ActionEvent event) {
                //Cria uma nova criança
                if (Cesto.K == 0) {
                    System.out.println("Crie um cesto antes de brincar");

                } else {
                    System.out.println(criancas.size());
                    Crianca.criancas = criancas;
                    Crianca.criancas.forEach(Crianca::start);
                    for (int i = 0; i < Crianca.criancas.size(); i++) {
                        ((CriancaAnimacao) criancaAnimacao).adicionarCrianca(Crianca.criancas.get(i));
                    }


                }
            }

        };
        JPanel cesto = new CestoForm() {
            @Override
            protected void btnCriarCestoCLick(ActionEvent event) {
                //Cria um novo cesto
                Cesto.K = Integer.parseInt(textFieldQuantidadeCesto.getText());
                Cesto.cesto = new Semaphore(Cesto.K);
                System.out.println("Cesto criado com sucesso");
            }


        };
        criarCriancaPanel.setBackground(Color.BLUE);

        // Cria um novo painel que conterá os dois novos painéis
        JPanel painelInferior = new JPanel();
        painelInferior.setLayout(new GridLayout(1, 3));

        painelInferior.add(logCrianca);
        painelInferior.add(criarCriancaPanel);
        painelInferior.add(cesto);
        painelInferior.setMaximumSize(new Dimension(1200, 200));
        // Adiciona o painel existente acima do novo painel
        frame.add(criancaAnimacao, BorderLayout.CENTER);
        // Adiciona o novo painel abaixo do painel existente
        frame.add(painelInferior, BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

    }

}
