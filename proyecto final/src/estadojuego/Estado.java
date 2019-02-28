//Plantilla para guiarse con otros estados de juego
package estadojuego;

import java.awt.Graphics2D;

import Jugabilidad.EstadosDeJuego;

public abstract class Estado {

    protected EstadosDeJuego edj;

    public Estado(EstadosDeJuego edj) {
        this.edj = edj;
    }

    public abstract void iniciar();

    public abstract void actualizar();

    public abstract void dibujar(Graphics2D g);

    public abstract void entradaTeclado();

}
