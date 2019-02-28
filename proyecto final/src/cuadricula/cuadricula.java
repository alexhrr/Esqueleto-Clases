// contiene las cuadriculas usadas y las que no
package cuadricula;

import java.awt.image.BufferedImage;

public class cuadricula {

    private BufferedImage imagen;
    private int tipo;

    // TIPOS DE CUADRICULAS
    public static final int NORMAL = 0;
    public static final int BLOQUEADO = 1;

    public cuadricula(BufferedImage imagenes, int tipos) {
        this.imagen = imagenes;
        this.tipo = tipos;
    }

    public BufferedImage getImagen() {
        return imagen;
    }

    public int getTipo() {
        return tipo;
    }

}
