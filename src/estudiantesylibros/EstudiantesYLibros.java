package estudiantesylibros;

import java.util.Random;

public class EstudiantesYLibros implements Runnable{
    //Creamos 9 libros
    public static boolean[] libros = new boolean[9];
    //Creamos un objeto para notifyAll
    public static Object obj = new Object();

    public static void main(String[] args) {
        //Creamos una nueva clase
        EstudiantesYLibros estud = new EstudiantesYLibros();
        //Creamos 4 estudiantes
        for (int i = 0; i < 4; i++) {
            Thread hilo = new Thread(estud);
            hilo.setName("Estudiante " + (i+1));
            hilo.start();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Elegimos un libro aleatorio entre 9 para cada libro por estudiante
                int libro = new Random().nextInt(9);
                int libro1 = new Random().nextInt(9);

                //Si el libro 1 y 2 son el mismo, se cambia el libro 2 hast que sean diferentes
                while (libro == libro1) {
                    libro1 = new Random().nextInt(9);
                }

                //Solo lo puede ejecutar un hilo al mismo tiempo
                synchronized (obj) {
                    // Mientras uno de los libros se esté usando
                    while (libros[libro] == true || libros[libro1] == true) {
                        try {
                            //Detenemos el hilo
                            obj.wait();
                        //Control de excepciones
                        } catch (InterruptedException e) {
                            System.err.println(e.getMessage());
                        }
                    }

                    // Los libros estan siendo usados
                    libros[libro] = true;
                    libros[libro1] = true;
                }

                //Mostramos el nombre del hilo y los libros que esta utilizando
                System.out.printf(Thread.currentThread().getName() + " tiene los libros %d y %d\n",libro,libro1);
                //Esperamos entre 3 y 5 segundos a que terminen
                Thread.sleep((int) (Math.random() * 5000)+3000);
                //Mostramos el nombre del hilo junto a que ha terminado
                System.out.println(Thread.currentThread().getName() + " ha terminado con los libros.");

                //Solo lo puede ejecutar un hilo al mismo tiempo
                synchronized (obj) {
                    //Los libros no estan siendo usados
                    libros[libro] = false;
                    libros[libro1] = false;
                    //Libros liberados
                    obj.notifyAll();
                }

            }
        //Control de excepciones
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }

    }
}
