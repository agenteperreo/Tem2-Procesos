package filosofos;

public class Filosofos  extends Thread{

    //Creamos la variable mesa tipo Mesa
    private Mesa mesa;
    //Creamos variable int filosofo
    private int filosofo;
    //Variable tipo int numero filosofo
    private int numFilosofo;

    //Contructor de las variables
    public Filosofos(Mesa m, int filosofo){
        this.mesa = m;
        this.filosofo = filosofo;
        this.numFilosofo = filosofo - 1;
    }

    //Ejecutamos el metodo run() para los hilos
    public void run(){
        //Bucle infinito
        while(true){
            this.pensando();
            this.mesa.cogerTenedores(this.numFilosofo);
            this.comiendo();
            //Mostramos que filosofo deja de comer y que tenedores dejamos libre
            System.out.println("Filosofo " + filosofo +  " deja de comer, tenedores libres " + (this.mesa.tenedorIzquierda(this.numFilosofo) + 1) + ", " + (this.mesa.tenedorDerecha(this.numFilosofo) + 1) );
            this.mesa.dejarTenedores(this.numFilosofo);
        }

    }

    public void pensando(){

        //Mostramso que filosofo esta pensando
        System.out.println("Filosofo " + filosofo + " esta pensando");
        try {
            //Esperamos hast que deje de pensar
            Thread.sleep((long) (Math.random() * 4000));
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }

    }

    public void comiendo(){
        //Mostramos que filosofo esta comiendo
        System.out.println("Filosofo " + filosofo + " esta comiendo");
        try {
            //Esperamos hast que termine de comer
            Thread.sleep((long) (Math.random() * 4000));
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

}
