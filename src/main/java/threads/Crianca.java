//A classe Crianca. Java é a classe principal de controle das crianças que irão brincar com a bola

package threads;
import gui.resources.CriancaConcrete;

import java.util.List;
import java.util.concurrent.Semaphore;
// javax.swing is a package that contains classes for creating graphical user interfaces
import javax.swing.*;

import static threads.Cesto.*;

public class Crianca extends Thread {


    public static List<Crianca> criancas;

    private String nome;
    public static boolean temBola;
    private int tempoBrincadeira; //tb
    public static int tempoQuieta; //tq
    // public static int totalCrianca = 0;
    private long time = System.currentTimeMillis();


    //Construtor
    public Crianca(String nome, boolean temBola, int tempoBrincadeira, int tempoQuieta){
        this.nome = nome;
        this.temBola = temBola;
        this.tempoBrincadeira = tempoBrincadeira*1000;
        this.tempoQuieta = tempoQuieta*1000;

    }




    //Método que inicia a brincadeira das crianças. OBS: O método é temporário para testes
    public void run(){
        try{
            while(true){
                if(temBola){
                    brincar();
                    colocarBola();
                    descansar();
                }else{
                    esperarBola();
                    pegarBola();
                    brincar();
                    colocarBola();
                    descansar();
                }
            }
        }catch(InterruptedException e){
            System.out.println("A criança " + nome + " foi interrompida");
        }
    }

    private void brincar() throws InterruptedException{
        System.out.println("Criança " + nome + " está brincando com a bola");
        Thread.sleep(tempoBrincadeira);
    }

   /* private void descansar() throws InterruptedException{
        if (temBola == false) {

            time = System.currentTimeMillis();
            System.out.println("A " + nome + " está descansando");

        }
    }*/
   private void descansar() throws InterruptedException{
       System.out.println("A " + nome + " está quieta");
       Thread.sleep(tempoQuieta);
   }

    private void esperarBola() throws InterruptedException{
        // Esperar para pegar a bola do cesto
        if (bolas.availablePermits() == 0){
            System.out.println("Criança " + nome + " está esperando para pegar a bola do cesto");
        }
        else{bolas.acquire();}
    }

    private void pegarBola() throws InterruptedException{
      if (cesto.availablePermits() == 0){
          System.out.println("Criança " + nome + " está esperando para pegar a bola do cesto");}
        else{

            cesto.acquire();
        System.out.println("Criança " + nome + " pegou a bola do cesto");
        temBola = true;

       }
    }


    private void colocarBola() throws InterruptedException{
        // Colocar a bola no cesto
        if (cesto.availablePermits() == K){
            System.out.println("Criança " + nome + " está esperando para colocar a bola no cesto");
        }
        else{
            System.out.println("Criança " + nome + " está colocando a bola no cesto");
            temBola = false;
            bolas.release();
            cesto.release();
        }

    }



    public static void main(String[] args){
        //javax.swing.SwingUtilities is a class that contains static methods for creating and managing top-level windows
        //set_tamCesto();
        SwingUtilities.invokeLater(()->{


               CriancaConcrete brincar = new CriancaConcrete();

               brincar.setVisible(true);

        });
        System.out.println("Olá mundo");
    }

}
