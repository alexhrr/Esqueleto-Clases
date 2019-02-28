// Pausa del juego
package estadojuego;

import java.awt.Graphics2D;

import Jugabilidad.Contenido;
import Jugabilidad.EstadosDeJuego;
import Jugabilidad.Controles;

public class Pausa extends Estado {

    public Pausa(EstadosDeJuego edj) {
        super(edj);
    }

    public void iniciar() {
    }

    public void actualizar() {
        entradaTeclado();
    }

    public void dibujar(Graphics2D g) {

        Contenido.dibujarPalabra(g, "Pausa", 40, 20);

        Contenido.dibujarPalabra(g, "Mover:", 12, 50);
        Contenido.dibujarPalabra(g, " Flechas", 52, 62);

        Contenido.dibujarPalabra(g, "Accion:", 12, 76);
        Contenido.dibujarPalabra(g, " Espacio", 52, 90);

        Contenido.dibujarPalabra(g, "Salir:", 12, 102);
        Contenido.dibujarPalabra(g, "F1", 62, 106);

    }

    public void entradaTeclado() {
        if (Controles.estaPresionado(Controles.ESCAPE)) {
            edj.setPausado(false);
        }
        if (Controles.estaPresionado(Controles.F1)) {
            edj.setPausado(false);
            edj.colocarEstado(EstadosDeJuego.MENU);
        }
    }

}
