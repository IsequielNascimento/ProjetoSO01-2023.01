package projetoThreads;

import java.util.concurrent.Semaphore;


public class Semaforo {

    private static final int cesto = 5;
    public static Semaphore cestoLim = new Semaphore(cesto); //Cesto no maximo

    public static Semaphore cestoVazio = new Semaphore(0); // Cesto vazio

    public static Semaphore mutex = new Semaphore(1); 

}
