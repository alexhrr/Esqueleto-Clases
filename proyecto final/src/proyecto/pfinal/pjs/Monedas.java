package proyecto.pfinal.pjs;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import proyecto.pfinal.titulo.TituloDeMapa;

public class Monedas extends Entidad {

    private BufferedImage[] sprites;

    private ArrayList<int[]> cambios;

    public Monedas(TituloDeMapa tl) {
        super(tl);
    }

    

    public void actualizar() {
    }

    public void dibujar(Graphics2D g) {
    }

    public void anadircambios(int[] i) {
    }

    public ArrayList<int[]> obtenerCambios() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
