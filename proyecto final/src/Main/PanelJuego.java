// dibuja el lienzo y ejecuta el juego
package Main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Jugabilidad.EstadosDeJuego;
import Jugabilidad.Controles;

@SuppressWarnings("serial")
public class PanelJuego extends JPanel implements Runnable, KeyListener {

    // dimensiones
    // ALTURA es la altura de juego
    // ALTURA2 inbluye la ventana de juego
    public static final int ANCHO = 128;
    public static final int ALTURA = 128;
    public static final int ALTURA2 = ALTURA + 16;
    public static final int ESCALA = 3;

    
    private Thread thread;
    private boolean corriendo;
    private final int FPS = 30;
    private final int tiempoejecucion = 1000 / FPS;

    
    private BufferedImage imagen;
    private Graphics2D g;

    // Estado de juego
    private EstadosDeJuego edj;

    // constructor
    public PanelJuego() {
        setPreferredSize(new Dimension(ANCHO * ESCALA, ALTURA2 * ESCALA));
        setFocusable(true);
        requestFocus();
    }

    
    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            addKeyListener(this);
            thread = new Thread(this);
            thread.start();
        }
    }

    // correr el hilo
    public void run() {

        iniciar();

        long empezar;
        long transcurso;
        long espera;

        // ciclo que define el transcurso del juego
        while (corriendo) {

            empezar = System.nanoTime();

            actualizar();
            dibujar();
            dibujarEnPantalla();

            transcurso = System.nanoTime() - empezar;

            espera = tiempoejecucion - transcurso / 1000000;
            if (espera < 0) {
                espera = tiempoejecucion;
            }

            try {
                Thread.sleep(espera);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    // inicializa el campo
    private void iniciar() {
        corriendo = true;
        imagen = new BufferedImage(ANCHO, ALTURA2, 1);
        g = (Graphics2D) imagen.getGraphics();
        edj = new EstadosDeJuego();
    }

    // actualiza el juego
    private void actualizar() {
        edj.actualizar();
        Controles.actualizar();
    }

    // dibuja 
    private void dibujar() {
        edj.dibujar(g);
    }

    // dibuja en pantalla
    private void dibujarEnPantalla() {
        Graphics g2 = getGraphics();
        g2.drawImage(imagen, 0, 0, ANCHO * ESCALA, ALTURA2 * ESCALA, null);
        g2.dispose();
    }

    
    @Override
    public void keyTyped(KeyEvent key) {
    }

    public void keyPressed(KeyEvent key) {
        Controles.controlesSet(key.getKeyCode(), true);
    }

    public void keyReleased(KeyEvent key) {
        Controles.controlesSet(key.getKeyCode(), false);
    }

}
