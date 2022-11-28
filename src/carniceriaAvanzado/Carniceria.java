package carniceriaAvanzado;

import java.util.concurrent.Semaphore;

public class Carniceria implements Runnable{

    //Creamos los 2 semaforos uno con 4 y otro con 2 huecos
    Semaphore semaforoCarne=new Semaphore(4);
    Semaphore semaforoCharcuteria = new Semaphore(2);

    public static void main(String[] args) {
        //Creamos el cliente
        carniceriaAvanzado.Carniceria cliente = new carniceriaAvanzado.Carniceria();
        //Creamos 10 clientes/hilos
        for (int i=1; i<=10; i++) {
            Thread hilo = new Thread(cliente, "Cliente "+i);
            //Iniciamos los hilos
            hilo.start();
        }

    }

    public void vezCarniceria() {
        try {
            //Entramos en el hueco del semaforo
            semaforoCarne.acquire();
            //Mustro que uno de los hilos esta en el semaforo
            System.out.println("El "+Thread.currentThread().getName()+" está siendo atendido en la carniceria.");
            //Esperamos entre 0 y 10 segundos en salir del semaforo
            Thread.sleep((long)(Math.random()*10000));
            //Mostramos que ha salido del semaforo
            System.out.println("El "+Thread.currentThread().getName()+" ha terminado de ser atendido en la carniceria.");
            //Sacamos el hilo del semaforo
            semaforoCarne.release();
        //Control de excepciones
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    public void vezCharcuteria() {
        try {
            //Entramos en el hueco del semaforo
            semaforoCharcuteria.acquire();
            //Muestro que uno de los hilos esta en el semaforo
            System.out.println("El "+Thread.currentThread().getName()+" está siendo atendido en la charcuteria.");
            //Esperamos entre 0 y 10 segundos en salir del semaforo
            Thread.sleep((long)(Math.random()*10000));
            //Mostramos que ha salido del semaforo
            System.out.println("El "+Thread.currentThread().getName()+" ha terminado de ser atendido en la charcuteria.");
            //Sacamos el hilo del semaforo
            semaforoCharcuteria.release();
        //Control de excepciones
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
    @Override
    public void run() {
        //Creamos dos variables booleanas
        boolean atendidoCarniceria=false;
        boolean atendidoCharcuteria=false;
        //Mientras que no haya entrado en la carniceria o en la charcuteria
        while (!atendidoCarniceria || !atendidoCharcuteria) {
            //Si hay hueco en la carniceria y no ha sido atendido en al carniceria
            if(semaforoCarne.availablePermits()>0 && !atendidoCarniceria) {
                //Ejecutamos la funcion vezCarniceria
                vezCarniceria();
                //Cambiamos la variable booleana a true para saber que ya ha entrado
                atendidoCarniceria=true;
            }
            //Si hay hueco en la charcuteria y no ha sido atendido en la charcutería
            if (semaforoCharcuteria.availablePermits()>0 && !atendidoCharcuteria){
                //Ejecutamos la función vezCharcuteria
                vezCharcuteria();
                //Cambiamos  la variable booleana a true para saber que ya ha entrado
                atendidoCharcuteria=true;
            }
        }

    }
}
