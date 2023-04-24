package threads;

import java.util.concurrent.Semaphore;

public class Cesto{

    public static int K;
    public static Semaphore bolas = new Semaphore(0);
    public static Semaphore cesto = new Semaphore(2);
    public Cesto(){
        this.cesto = cesto;
        this.bolas = bolas;
    }
}