// escoje que estado de juego se implementa de acuerdo a las necesidades

package Jugabilidad;

import java.awt.Graphics2D;

import estadojuego.Terminar;
import estadojuego.Estado;
import estadojuego.Entrar;
import estadojuego.Menu;
import estadojuego.Pausa;
import estadojuego.Play;

public class EstadosDeJuego {

    private boolean pausado;
    private Pausa estadopausado;

    private Estado[] estadosjuego;
    private int estadoactual;
    private int estadoantiguo;

    public static final int NUM_ESTADO = 4;
    public static final int entrar = 0;
    public static final int MENU = 1;
    public static final int PLAY = 2;
    public static final int terminar = 3;

    public EstadosDeJuego() {

        pausado = false;
        estadopausado = new Pausa(this);

        estadosjuego = new Estado[NUM_ESTADO];
        colocarEstado(entrar);

    }

    public void colocarEstado(int i) {
        estadoantiguo = estadoactual;
        quitarEstado(estadoantiguo);
        estadoactual = i;
        if (i == entrar) {
            estadosjuego[i] = new Entrar(this);
            estadosjuego[i].iniciar();
        } else if (i == MENU) {
            estadosjuego[i] = new Menu(this);
            estadosjuego[i].iniciar();
        } else if (i == PLAY) {
            estadosjuego[i] = new Play(this);
            estadosjuego[i].iniciar();
        } else if (i == terminar) {
            estadosjuego[i] = new Terminar(this);
            estadosjuego[i].iniciar();
        }
    }

    public void quitarEstado(int i) {
        estadosjuego[i] = null;
    }

    public void setPausado(boolean b) {
        pausado = b;
    }

    public void actualizar() {
        if (pausado) {
            estadopausado.actualizar();
        } else if (estadosjuego[estadoactual] != null) {
            estadosjuego[estadoactual].actualizar();
        }
    }

    public void dibujar(Graphics2D g) {
        if (pausado) {
            estadopausado.dibujar(g);
        } else if (estadosjuego[estadoactual] != null) {
            estadosjuego[estadoactual].dibujar(g);
        }
    }

}
