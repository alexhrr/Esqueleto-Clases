package proyecto.pfinal.pjs;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import proyecto.pfinal.titulo.TituloDeMapa;
import proyecto.main.jugabilidad.Contenido;
public class Item extends Entidad {

    private BufferedImage sprite;

    private int tipo;

    public final int bote=0;

    public final int hacha=1;

    public Item(TituloDeMapa tl) {
        super(tl);
    }

    

    public void Settipo() {
    }

    public void recogido(Jugador p) {
    }

    public void dibujar(Graphics2D g) {
    }
}
