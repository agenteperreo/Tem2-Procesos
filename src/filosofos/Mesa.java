package filosofos;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Mesa {

    //Creamos un array de tenedores
    private boolean[] tenedores;

    public Mesa(int numTenedores) {
        //creamos un nuevo boolean
        this.tenedores = new boolean[numTenedores];
    }

    public int tenedorIzquierda(int i) {
        //devolvemos i
        return i;
    }

    public int tenedorDerecha(int i) {
        //Si la i es 0 devolvemos el array - 1
        if (i == 0) {
            return this.tenedores.length - 1;
        } else { //Si no
            //Devolvemos i - 1
            return i - 1;
        }
    }

    public synchronized void cogerTenedores(int filosofo) {

        //Mientras no se esté utilizando un tenedor o el otro
        while (tenedores[tenedorIzquierda(filosofo)] || tenedores[tenedorDerecha(filosofo)]) {
            try {
                //Esperamos
                wait();
            } catch (InterruptedException e) {
                Logger.getLogger(Mesa.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        //Cerramos los tenedores
        tenedores[tenedorIzquierda(filosofo)] = true;
        tenedores[tenedorDerecha(filosofo)] = true;
    }

    public synchronized void dejarTenedores(int filosofo) {
        //Abrimos los tenedroes
        tenedores[tenedorIzquierda(filosofo)] = false;
        tenedores[tenedorDerecha(filosofo)] = false;
        //Mostrammos a los demas hilos
        notifyAll();
    }
}