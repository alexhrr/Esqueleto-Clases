// Muestra la presentacion
package estadojuego;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Main.PanelJuego;
import Jugabilidad.EstadosDeJuego;
import Jugabilidad.Controles;

public class Entrar extends Estado {

    private BufferedImage presentacion;

    private int alpha;
    private int ticks;

    private final int APARECER = 60;
    private final int TAMAÑO = 60;
    private final int DESAPARECER = 60;

    public Entrar(EstadosDeJuego edj) {
        super(edj);
    }

    public void iniciar() {
        ticks = 0;
        try {
            presentacion = ImageIO.read(getClass().getResourceAsStream("/Imagenes/presentacion.gif"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizar() {
        entradaTeclado();
        ticks++;
        if (ticks < APARECER) {
            alpha = (int) (255 - 255 * (1.0 * ticks / APARECER));
            if (alpha < 0) {
                alpha = 0;
            }
        }
        if (ticks > APARECER + TAMAÑO) {
            alpha = (int) (255 * (1.0 * ticks - APARECER - TAMAÑO) / DESAPARECER);
            if (alpha > 255) {
                alpha = 255;
            }
        }
        if (ticks > APARECER + TAMAÑO + DESAPARECER) {
            edj.colocarEstado(EstadosDeJuego.MENU);
        }
    }

    public void dibujar(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, PanelJuego.ANCHO, PanelJuego.ALTURA2);
        g.drawImage(presentacion, 0, 0, PanelJuego.ANCHO, PanelJuego.ALTURA2, null);
        g.setColor(new Color(0, 0, 0, alpha));
        g.fillRect(0, 0, PanelJuego.ANCHO, PanelJuego.ALTURA2);
    }

    public void entradaTeclado() {
        if (Controles.estaPresionado(Controles.ENTER)) {
            edj.colocarEstado(EstadosDeJuego.MENU);
        }
    }

}
