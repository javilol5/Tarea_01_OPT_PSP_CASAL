package javier.casal;

// Un buzón guarda un único mensaje.
// Dos hilos escritores mandan 3 mensajes cada uno y dos hilos lectores leen 3 cada uno.
// Un escritor solo puede escribir si el buzón está vacío y un lector solo puede leer si tiene un mensaje; si no, esperan.
// Muestra por pantalla quién escribe, quién lee y quién espera.

public class Buzon {

    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";

    private String mensaje;
    private boolean lleno = false;

    public synchronized void escribir(String mensaje) throws InterruptedException {
        while (lleno) {
            System.out.println(Thread.currentThread().getName() + " espera para escribir.");
            wait();
        }
        this.mensaje = mensaje;
        lleno = true;
        System.out.println(Thread.currentThread().getName() + " escribe: " + mensaje);
        notifyAll();
    }

    public synchronized String leer() throws InterruptedException {
        while (!lleno) {
            System.out.println(Thread.currentThread().getName() + " espera para leer.");
            wait();
        }
        String mensajeLeido = mensaje;
        lleno = false;
        System.out.println(Thread.currentThread().getName() + " lee: " + mensajeLeido);
        notifyAll();
        return mensajeLeido;
    }

    public static void main(String[] args) {

        Buzon buzon = new Buzon();

        String[] mensajesEscritor1 = {"Mensaje 1.1", "Mensaje 1.2", "Mensaje 1.3"};
        String[] mensajesEscritor2 = {"Mensaje 2.1", "Mensaje 2.2", "Mensaje 2.3"};

        Escritor esc1 = new Escritor(buzon, mensajesEscritor1, GREEN + "Escritor 1" + RESET);
        Escritor esc2 = new Escritor(buzon, mensajesEscritor2, GREEN + "Escritor 2" + RESET);

        Lector lec1 = new Lector(buzon, 3, BLUE + "Lector 1" + RESET);
        Lector lec2 = new Lector(buzon, 3, BLUE + "Lector 2" + RESET);

        esc1.start();
        esc2.start();
        lec1.start();
        lec2.start();

    }
}
