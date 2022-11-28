package carniceria;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Carniceria implements Runnable{

    //Creamos un semaforo con 4 huecos
    Semaphore semaforoCarne=new Semaphore(4);

    public static void main(String[] args) {
        //Creamos un cliente
        Carniceria cliente = new carniceria.Carniceria();
        //Creamos 10 hilos/clientes
        for (int i=1; i<=10; i++) {
            //Le ponemos los nombres a los hilos y los creamos
            Thread hilo = new Thread(cliente, "Cliente "+i);
            //Iniciamos los hilos
            hilo.start();
        }

    }

    @Override
    public void run() {
        try {
            //Entramos en el hueco del semaforo
            semaforoCarne.acquire();
            //Mostramos que uno de los hilo/clientes esta en el semaforo
            System.out.println("El "+Thread.currentThread().getName()+" está siendo atendido");
            //Esperamos entre 0 y 10 segundos en salir del semaforo
            Thread.sleep((long)(Math.random()*10000));
            //Mostramos que ha salido del semaforo
            System.out.println("El "+Thread.currentThread().getName()+" ha terminado de ser atendido");
            //Sacamos el hilo del semaforo
            semaforoCarne.release();
        //Control de excepciones
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }

    }
}
