package proyecto.main.jugabilidad;

import java.awt.Graphics2D;
import proyecto.pfinal.jugar.Entrar;
import proyecto.pfinal.jugar.Pausa;
import proyecto.pfinal.jugar.Estado;
import proyecto.pfinal.jugar.Menu;
import proyecto.pfinal.jugar.Play;
import proyecto.pfinal.jugar.Terminar;

public final class EstadosDeJuego {

    private boolean pausado;

    private Pausa estadopausado;

    private EstadosDeJuego[] EstadosJuegos;

    private int estadoActual;

    private int EstadoPrevio;

    public static final int estados=4;

    public static final int intro=0;

    public static final int menu=1;

    public static final int play=2;
    
    public static final int finalizar=3;

    public EstadosDeJuego() {
    }

    public void SetEstados(int i) {
    }

    public void SetPausa(boolean b) {
    }

    public void Actualizar() {
    }

    public void dibujar(Graphics2D g) {
    }

    public void deshacerEstado(int i) {
    }
}
