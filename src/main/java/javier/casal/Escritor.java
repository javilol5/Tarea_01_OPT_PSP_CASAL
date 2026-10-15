package javier.casal;

public class Escritor extends Thread {
    private Buzon buzon;
    private String[] mensajes;

    //constructor
    public Escritor(Buzon buzon, String[] mensajes, String nombre) {
        super(nombre);
        this.buzon = buzon;
        this.mensajes = mensajes;
    }

    // Metodo run que envia los mensajes al buzon
    @Override
    public void run() {
        try {
            for (String mensaje : mensajes) {
                buzon.escribir(mensaje);
                Thread.sleep(200); // simular tiempo entre envios
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
