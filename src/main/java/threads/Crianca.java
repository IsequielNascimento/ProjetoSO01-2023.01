//A classe Crianca.Java é a classe principal de controle das crianças que irão brincar com a bola

package threads;
import gui.resources.CriancaConcrete;


import java.util.List;
import java.util.concurrent.Semaphore;

// javax.swing is a package that contains classes for creating graphical user interfaces
import javax.swing.*;

public class Crianca extends Thread {




    private String nome;
    private boolean temBola;
    private int tempoBrincadeira; //tb
    private int tempoQuieta; //tq
    // public static int totalCrianca = 0;

    public Crianca(String nome, boolean temBola, int tempoBrincadeira, int tempoQuieta){
        this.nome = nome;
        this.temBola = temBola;
        this.tempoBrincadeira = tempoBrincadeira;
        this.tempoQuieta = tempoQuieta;
       // totalCrianca++;
    }

    public static List<Crianca> criancas;
    public static Semaphore mutex = new Semaphore(1);
    public static Semaphore cesto = new Semaphore(0);



    //Método que inicia a brincadeira das crianças. OBS: O método é temporário para testes
    public void run(){

        try{
            while(true){
                if(temBola){
                    brincar();
                    //mutex iria para 0 porque dessa forma nenhuma criança pega no cesto
                    temBola = false;
                    //mutex iria para 1 porque assim outra criança pega a bola no cesto
                }else{
                    pegarBola();
                }
                descansar();
            }
        }catch(InterruptedException e){
            System.out.println("A criança " + nome + " foi interrompida");
        }


    }

    public void brincar() throws InterruptedException{
        System.out.println("A " + nome + " está brincando com a bola");

        Thread.sleep(tempoBrincadeira);
    }

    public void descansar() throws InterruptedException{
        System.out.println("A " + nome + " está quieta");
        Thread.sleep(tempoQuieta);
    }

    public void pegarBola() throws InterruptedException{
        System.out.println("A "+nome+ " vai pegar uma bola");
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
