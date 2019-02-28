// Animacion Brillo
package entidad;

import java.awt.Graphics2D;

import Jugabilidad.Contenido;
import cuadricula.cuadriculasMapa;

public class brillo extends entidad {

    private boolean quitar;

    public brillo(cuadriculasMapa tm) {
        super(tm);
        animacion.setFrames(Contenido.brillo[0]);
        animacion.setRetraso(5);
        ancho = alto = 16;
    }

    public boolean shouldRemove() {
        return quitar;
    }

    public void actualizar() {
        animacion.actualizar();
        if (animacion.primeraVez()) {
            quitar = true;
        }
    }

    public void dibujar(Graphics2D g) {
        super.dibujar(g);
    }

}
