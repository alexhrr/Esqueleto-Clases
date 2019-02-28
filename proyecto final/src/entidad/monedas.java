// Pone las monedas
package entidad;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Jugabilidad.Contenido;
import cuadricula.cuadriculasMapa;

public class monedas extends entidad {

    BufferedImage[] sprites;

    private ArrayList<int[]> cambioCuadricula;

    public monedas(cuadriculasMapa tm) {

        super(tm);

        ancho = 16;
        alto = 16;
        ancho2 = 12;
        alto2 = 12;

        sprites = Contenido.Monedas[0];
        animacion.setFrames(sprites);
        animacion.setRetraso(10);

        cambioCuadricula = new ArrayList<int[]>();

    }

    public void anadirCambio(int[] i) {
        cambioCuadricula.add(i);
    }

    public ArrayList<int[]> getCambios() {
        return cambioCuadricula;
    }

    public void actualizar() {
        animacion.actualizar();
    }

    public void dibujar(Graphics2D g) {
        super.dibujar(g);
    }

}
