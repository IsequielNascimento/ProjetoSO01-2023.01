//A classe Crianca.Java é a classe principal de controle das crianças que irão brincar com a bola

package threads;
import gui.resources.CriancaConcrete;


import java.util.concurrent.Semaphore;

// javax.swing is a package that contains classes for creating graphical user interfaces
import javax.swing.*;

public class Crianca extends Thread {

    private String nome;
    private boolean temBola;
    private int tempoBrincadeira; //tb
    private int tempoQuieta; //tq

    public Crianca(String nome, boolean tembola, int tempoBrincadeira, int tempoQuieta){
        this.nome = nome;
        this.temBola = tembola;
        this.tempoBrincadeira = tempoBrincadeira;
        this.tempoQuieta = tempoQuieta;
    }

    public void run(){

    }

    public void brincar() throws InterruptedException{
        System.out.println("A" + nome + " está brincando com a bola");
        Thread.sleep(tempoBrincadeira);
    }

    public void descansar() throws InterruptedException{
        System.out.println("A" + nome + " está quieta");
        Thread.sleep(tempoQuieta);
    }

    public void pegarBola() throws InterruptedException{
        System.out.println("A"+nome+ "vai pegar uma bola");
        Semaforo cesto = new Semaforo();
        cesto.cestoLim.acquire();
        System.out.println("A criança pegou a bola");
        //mutex iria para 0 porque dessa forma nenhuma criança pega no cesto
        temBola = true;
        //mutex iria para 1 porque assim outra criança pega a bola no cesto
    }

    public static void main(String[] args){
        //javax.swing.SwingUtilities is a class that contains static methods for creating and managing top-level windows
        SwingUtilities.invokeLater(()->{

                //CriancaForm is a class that represents a window
               CriancaConcrete brincar = new CriancaConcrete();
               //inicializar is a method that initializes the window
               brincar.setVisible(true);

        });
        System.out.println("Olá mundo");
    }

    private void setVisible(boolean b) {
    }

}
