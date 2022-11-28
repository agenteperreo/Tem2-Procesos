package sincronizacionDatos;

import java.util.ArrayList;
import java.util.List;

public class SincronizacionDatos extends Thread{
    //Creamos una lista de strings
    private static List<String> lista = new ArrayList<String>();

    @Override
    public void run() {
        //Creamos 10 Strings y los añadimos a la lista de strings
        for (int i = 0; i < 10; i++) {
            lista.add(new String("Texto" + i));
        }

        //Mostramos la lista con un foreach
        for (String textos : lista) {
            System.out.println(textos);
        }
    }

    public static void main(String[] args) {
        //Creamos 10 Sincronizaciones y las iniciamos
        for (int i = 0; i < 10; i++) {
            new SincronizacionDatos().start();
        }

    }
}
