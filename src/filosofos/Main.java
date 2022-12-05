package filosofos;

public class Main {
    public static void main(String[] args) {
        //Creamos un objeto mesa
        Mesa m = new Mesa(5);
        //Creamos los 5 filosofos
        for (int i = 1; i <= 5; i++) {
            Filosofos filo = new Filosofos(m, i);
            filo.start();

        }
    }
}