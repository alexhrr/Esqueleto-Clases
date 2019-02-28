// Menu del juego
package estadojuego;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Jugabilidad.Contenido;
import Jugabilidad.EstadosDeJuego;
import Jugabilidad.Controles;

public class Menu extends Estado {

    private BufferedImage bg;
    private BufferedImage moneda;

    private int opcionActual = 0;
    private String[] opciones = {
        "EMPEZAR",
        "SALIR"
    };

    public Menu(EstadosDeJuego edj) {
        super(edj);
    }

    public void iniciar() {
        bg = Contenido.MENUBG[0][0];
        moneda = Contenido.Monedas[0][0];
    }

    public void actualizar() {
        entradaTeclado();
    }

    public void dibujar(Graphics2D g) {

        g.drawImage(bg, 0, 0, null);

        Contenido.dibujarPalabra(g, opciones[0], 44, 90);
        Contenido.dibujarPalabra(g, opciones[1], 48, 100);

        if (opcionActual == 0) {
            g.drawImage(moneda, 25, 86, null);
        } else if (opcionActual == 1) {
            g.drawImage(moneda, 25, 96, null);
        }

    }

    public void entradaTeclado() {
        if (Controles.estaPresionado(Controles.ABAJO) && opcionActual < opciones.length - 1) {
            opcionActual++;
        }
        if (Controles.estaPresionado(Controles.ARRIBA) && opcionActual > 0) {
            opcionActual--;
        }
        if (Controles.estaPresionado(Controles.ENTER)) {
            seleccionarOpcion();
        }
    }

    private void seleccionarOpcion() {
        if (opcionActual == 0) {
            edj.colocarEstado(EstadosDeJuego.PLAY);
        }
        if (opcionActual == 1) {
            System.exit(0);
        }
    }

}
