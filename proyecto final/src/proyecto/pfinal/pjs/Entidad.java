package proyecto.pfinal.pjs;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import proyecto.pfinal.titulo.TituloDeMapa;
public abstract class Entidad {

    protected int width;

    protected int width2;

    protected int height;

    protected int height2;

    protected int x;

    protected int y;

    protected int x1;

    protected int y1;

    protected int fil;

    protected int col;

    protected boolean movimiento;

    protected boolean arriba;

    protected boolean abajo;

    protected boolean derecha;

    protected boolean izquierda;

    protected int velocidadMovimiento;

    protected TituloDeMapa mapa;

    protected Object tamano;

    protected Object posX;

    protected Object Posy;

    protected Animacion animacion;

    protected int animacionActual;

    public Entidad(TituloDeMapa tl) {
    }

    public int getX() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getY() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getCol() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getFil() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void SetPosicion(int i, int j) {
    }

    public void SetPosicionMapa() {
    }

    public void SetPosicionTitulo() {
    }

    public void SetArriba() {
    }

    public void SetAbajo() {
    }

    public void SetDerecha() {
    }

    public void SetIzquierda() {
    }

    public boolean interseccion() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    

    public void ValidarPosicion() {
    }

    public int getSigPosicion() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void actualizar() {
    }

    public void dibujar(Graphics2D g) {
    }
}
