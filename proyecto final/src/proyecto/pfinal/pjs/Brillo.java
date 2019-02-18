package proyecto.pfinal.pjs;

import java.awt.Graphics2D;
import proyecto.main.jugabilidad.Contenido;
import proyecto.pfinal.titulo.TituloDeMapa;

public class Brillo extends Entidad {

    private boolean remover;

    public Brillo(TituloDeMapa tl) {
        super(tl);
    }

    public void actualizar() {
    }

    public void dibujar(Graphics2D g) {
    }

    public boolean removible() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
