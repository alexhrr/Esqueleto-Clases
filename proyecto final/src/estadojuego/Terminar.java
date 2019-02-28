// Establece el rango
// menos de 2 minutos= oro
// menos de 3 minutos = plata
// menos de 4 minutos = bronce
// 4 minutos o mas = carton
package estadojuego;

import java.awt.Color;
import java.awt.Graphics2D;

import Main.PanelJuego;
import Jugabilidad.Contenido;
import Jugabilidad.Tiempos;
import Jugabilidad.EstadosDeJuego;
import Jugabilidad.Controles;

public class Terminar extends Estado {

    private Color color;

    private int rango;
    private long ticks;

    public Terminar(EstadosDeJuego edj) {
        super(edj);
    }

    public void iniciar() {
        color = new Color(164, 198, 222);
        ticks = Tiempos.getTiempo();
        if (ticks < 3600) {
            rango = 1;
        } else if (ticks < 5400) {
            rango = 2;
        } else if (ticks < 7200) {
            rango = 3;
        } else {
            rango = 4;
        }
    }

    public void actualizar() {
    }

    public void dibujar(Graphics2D g) {

        g.setColor(color);
        g.fillRect(0, 0, PanelJuego.ANCHO, PanelJuego.ALTURA2);

        Contenido.dibujarPalabra(g, "Tiempo: ", 20, 36);

        int minutos = (int) (ticks / 1800);
        int segundos = (int) ((ticks / 30) % 60);
        if (minutos < 10) {
            if (segundos < 10) {
                Contenido.dibujarPalabra(g, "0" + minutos + ":0" + segundos, 44, 48);
            } else {
                Contenido.dibujarPalabra(g, "0" + minutos + ":" + segundos, 44, 48);
            }
        } else {
            if (segundos < 10) {
                Contenido.dibujarPalabra(g, minutos + ":0" + segundos, 44, 48);
            } else {
                Contenido.dibujarPalabra(g, minutos + ":" + segundos, 44, 48);
            }
        }

        Contenido.dibujarPalabra(g, "Rango", 48, 66);
        if (rango == 1) {
            Contenido.dibujarPalabra(g, "Oro", 20, 78);
        } else if (rango == 2) {
            Contenido.dibujarPalabra(g, "Plata", 24, 78);
        } else if (rango == 3) {
            Contenido.dibujarPalabra(g, "Bronce", 32, 78);
        } else if (rango == 4) {
            Contenido.dibujarPalabra(g, "Carton", 8, 78);
        }

        Contenido.dibujarPalabra(g, "oprima una tecla", 12, 110);

    }

    public void entradaTeclado() {
        if (Controles.estaPresionado(Controles.ENTER)) {
            edj.colocarEstado(EstadosDeJuego.MENU);
        }
    }

}
