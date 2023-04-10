package crianca_Brincadeira;

import java.util.concurrent.Semaphore;


public class Semaforo {

    public static Semaphore cestoLim = new Semaphore(cesto.K); //Cesto no maximo

    public static Semaphore cestoVazio = new Semaphore(0); // Cesto vazio

    public static Semaphore mutex = new Semaphore(1);

}
