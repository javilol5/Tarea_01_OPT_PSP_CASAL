package javier.casal;

public class Lector extends Thread {
    private Buzon buzon;
    private int numMensajesALeer;

    //constructor
    public Lector(Buzon buzon, int numMensajesALeer, String nombreHilo) {
        super(nombreHilo);
        this.buzon = buzon;
        this.numMensajesALeer = numMensajesALeer;
    }

    // Metodo run que lee los mensajes del buzon
    @Override
    public void run() {
        try {
            for (int i = 0; i < numMensajesALeer; i++) {
                buzon.leer();
                Thread.sleep(200); // simular tiempo de lectura
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}


