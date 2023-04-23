//A classe Crianca.Java é a classe principal de controle das crianças que irão brincar com a bola

package threads;
import gui.resources.CriancaConcrete;
import gui.resources.assets.MainGUI;
import java.util.List;
import java.util.concurrent.Semaphore;
// javax.swing is a package that contains classes for creating graphical user interfaces
import javax.swing.*;

public class Crianca extends Thread {

    public static int K;
    public static Semaphore bolas = new Semaphore(0);
    public static Semaphore cesto = new Semaphore(2);
    public static List<Crianca> criancas;
    private String nome;
    private boolean temBola;
    private int tempoBrincadeira; //tb
    private int tempoQuieta; //tq
    // public static int totalCrianca = 0;


    //Construtor
    public Crianca(String nome, boolean temBola, int tempoBrincadeira, int tempoQuieta, Semaphore cesto, Semaphore bolas){
        this.nome = nome;
        this.temBola = temBola;
        this.tempoBrincadeira = tempoBrincadeira;
        this.tempoQuieta = tempoQuieta;
        this.cesto = cesto;
        this.bolas = bolas;
    }

    public static void set_tamCesto(){
        K = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a capacidade do cesto",
                "Entrada inicial", JOptionPane.INFORMATION_MESSAGE));
    }

    public static int get_tamCesto(){
        return K;
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

    private void descansar() throws InterruptedException{
        System.out.println("A " + nome + " está quieta");
        Thread.sleep(tempoQuieta);
    }

    private void esperarBola() throws InterruptedException{
        // Esperar para pegar a bola do cesto
        System.out.println("Criança " + nome + " está esperando para pegar a bola do cesto");
        bolas.acquire();
    }

    private void pegarBola() throws InterruptedException{
        cesto.acquire();
        System.out.println("Criança " + nome + " pegou a bola do cesto");
        temBola = true;
        cesto.release();
    }


    private void colocarBola() throws InterruptedException{
        // Colocar a bola no cesto
        cesto.acquire();
        System.out.println("Criança " + nome + " está colocando a bola no cesto");
        temBola = false;
        bolas.release();
        cesto.release();
    }

    private void setVisible(boolean b) {}

    public static void main(String[] args){
        //javax.swing.SwingUtilities is a class that contains static methods for creating and managing top-level windows
        //set_tamCesto();
        SwingUtilities.invokeLater(()->{

                //CriancaForm is a class that represents a window
               CriancaConcrete brincar = new CriancaConcrete();
               //inicializar is a method that initializes the window
               brincar.setVisible(true);

        });
        System.out.println("Olá mundo");
    }

}
