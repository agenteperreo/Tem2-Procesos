package HilosDurmientes;

public class HilosDurmientes extends Thread{

    public static void main(String[] args) {
        //Creamos un HiloDurmiente
        HilosDurmientes hilo=new HilosDurmientes();
        //Creamos 10 hilos con sus nombres y los iniciamos
        for (int i=0; i<5; i++) {
            Thread hilos=new Thread(hilo,"Hilo "+(i+1));
            hilos.start();
        }
    }

    @Override
    public void run() {
        //Creamos un bucle infinito
        while(true) {
            try {
                //Mostramos que hilos es y que esta trabajando
                System.out.println("Soy el bucle "+Thread.currentThread().getName()+" y estoy trabajando");
                //Esperamos entre 1 y 10 segundos
                Thread.sleep((long)(Math.random()*10000)+1);
            //Control de excepciones
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }

}
