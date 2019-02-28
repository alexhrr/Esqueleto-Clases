// Contiene los controles y regresa verdadero si se mantiene oprimido

package Jugabilidad;

import java.awt.event.KeyEvent;

public class Controles {

    public static final int NUM_TECLAS = 8;

    public static boolean[] EstadoControl = new boolean[NUM_TECLAS];
    public static boolean[] EstadoPrevio = new boolean[NUM_TECLAS];

    public static int ARRIBA = 0;
    public static int IZQUIERDA = 1;
    public static int ABAJO = 2;
    public static int DERECHA = 3;
    public static int ESPACIO = 4;
    public static int ENTER = 5;
    public static int ESCAPE = 6;
    public static int F1 = 7;

    public static void controlesSet(int i, boolean b) {//Estableciendo Teclas
        if (i == KeyEvent.VK_UP) {
            EstadoControl[ARRIBA] = b;
        } else if (i == KeyEvent.VK_LEFT) {
            EstadoControl[IZQUIERDA] = b;
        } else if (i == KeyEvent.VK_DOWN) {
            EstadoControl[ABAJO] = b;
        } else if (i == KeyEvent.VK_RIGHT) {
            EstadoControl[DERECHA] = b;
        } else if (i == KeyEvent.VK_SPACE) {
            EstadoControl[ESPACIO] = b;
        } else if (i == KeyEvent.VK_ENTER) {
            EstadoControl[ENTER] = b;
        } else if (i == KeyEvent.VK_ESCAPE) {
            EstadoControl[ESCAPE] = b;
        } else if (i == KeyEvent.VK_F1) {
            EstadoControl[F1] = b;
        }
    }

    public static void actualizar() {
        for (int i = 0; i < NUM_TECLAS; i++) {
            EstadoPrevio[i] = EstadoControl[i];
        }
    }

    public static boolean estaPresionado(int i) {
        return EstadoControl[i] && !EstadoPrevio[i];
    }

    public static boolean oprimido(int i) {
        return EstadoControl[i];
    }

    public static boolean cualquierOprimido() {
        for (int i = 0; i < NUM_TECLAS; i++) {
            if (EstadoControl[i]) {
                return true;
            }
        }
        return false;
    }

    public static boolean algunaTeclaOprimida() {
        for (int i = 0; i < NUM_TECLAS; i++) {
            if (EstadoControl[i] && !EstadoPrevio[i]) {
                return true;
            }
        }
        return false;
    }

}
