package filosofos;

import java.util.concurrent.Semaphore;

public class Filosofos  implements Runnable{

    Semaphore filosofos=new Semaphore(2);

    public static void main(String[] args) {
        Filosofos tontos=new Filosofos();
        for(int i=0;i<5; i++) {
            Thread hilo=new Thread(tontos,"Filosofo "+i);
            hilo.start();
        }
    }
    @Override
    public void run() {
        boolean comido=false;
        while(!comido) {
            if(filosofos.availablePermits()>0) {
                try {
                    System.out.println("El "+Thread.currentThread().getName()+ " está comiendo.");
                    filosofos.acquire();
                    Thread.sleep(5000);
                    System.out.println("El "+Thread.currentThread().getName()+ " ha terminado de comer");
                    comido=true;
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }
}
