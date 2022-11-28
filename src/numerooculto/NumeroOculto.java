package numerooculto;

public class NumeroOculto extends Thread {

    //Variables estaticas
    public static int numOcu; //Variable donde se guarda el numero oculto
    public static int compr = 0; //Para comprobar el numero oculto con el aleatorio

    //Clase sincronizada para comprobar si el hilo ha acertado
    public synchronized int intentoNum(int intento) {
        //Si la variable comprobante es 1 la cambiamos a -1
        if (compr == 1) {
            compr = -1;
        }
        //Si el numero oculto es el mismo que el aleatorio cambiamos la variable comprobación a 1
        if (numOcu == intento) {
            compr = 1;
        }

        //Devolvemos la variable de comprobacion
        return compr;
    }

    @Override
    public void run() {
        //El hilo genera un numero aleatorio entre 1 y 100
        int num = (int) (Math.random() * 100 + 1);
        //Mientras que la funcion intentoNum no devuelva -1
        while (intentoNum(num) != -1) {
            //Si la función intentoNum devuelve 1
            if (intentoNum(num) == 1) {
                //Msotramos que hilo ha adivinado el numero
                System.out.println("Encontrado por el " + Thread.currentThread().getName());
                //Interrupimos el hilo activo
                Thread.currentThread().interrupt();
            }
            //Generamos otro numero aleatorio
            num = (int) (Math.random() * 100 + 1);
        }
        //Interrumpimos el hilo activo
        Thread.currentThread().interrupt();
    }

    public static void main(String[] args) {
        //Generamos el numero oculto
        numOcu = (int) (Math.random() * 100 + 1);

        //Creamos 10 hilos y les ponemos nombre
        NumeroOculto hilo1 = new NumeroOculto();
        hilo1.setName("Hilo 1");
        NumeroOculto hilo2 = new NumeroOculto();
        hilo2.setName("Hilo 2");
        NumeroOculto hilo3 = new NumeroOculto();
        hilo3.setName("Hilo 3");
        NumeroOculto hilo4 = new NumeroOculto();
        hilo4.setName("Hilo 4");
        NumeroOculto hilo5 = new NumeroOculto();
        hilo5.setName("Hilo 5");
        NumeroOculto hilo6 = new NumeroOculto();
        hilo6.setName("Hilo 6");
        NumeroOculto hilo7 = new NumeroOculto();
        hilo7.setName("Hilo 7");
        NumeroOculto hilo8 = new NumeroOculto();
        hilo8.setName("Hilo 8");
        NumeroOculto hilo9 = new NumeroOculto();
        hilo9.setName("Hilo 9");
        NumeroOculto hilo10 = new NumeroOculto();
        hilo10.setName("Hilo 10");

        //Iniciamos los 10 hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
        hilo6.start();
        hilo7.start();
        hilo8.start();
        hilo9.start();
        hilo10.start();

    }
}

