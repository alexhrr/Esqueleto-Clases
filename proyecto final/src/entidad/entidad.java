// Guarda la logica para moverse en el mapa
package entidad;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import cuadricula.cuadricula;
import cuadricula.cuadriculasMapa;

public abstract class entidad {

    // dimensiones
    protected int ancho;
    protected int alto;
    protected int ancho2;
    protected int alto2;

    // posicion
    protected int x;
    protected int y;
    protected int xdest;
    protected int ydest;
    protected int filaCuadricula;
    protected int colCuadricula;

    // movimiento
    protected boolean moviendose;
    protected boolean izquierda;
    protected boolean derecha;
    protected boolean arriba;
    protected boolean abajo;

    // atributos
    protected int velMovimiento;

    // cuadricula
    protected cuadriculasMapa cuadriculasmapa;
    protected int tamañoCuadricula;
    protected int xmapa;
    protected int ymapa;

    // animacion
    protected Animacion animacion;
    protected int animacionActual;

    public entidad(cuadriculasMapa tm) {
        cuadriculasmapa = tm;
        tamañoCuadricula = cuadriculasmapa.getTamaño();
        animacion = new Animacion();
    }

    public int getx() {
        return x;
    }

    public int gety() {
        return y;
    }

    public int getFila() {
        return filaCuadricula;
    }

    public int getCol() {
        return colCuadricula;
    }

    public void setPosicion(int i1, int i2) {
        x = i1;
        y = i2;
        xdest = x;
        ydest = y;
    }

    public void setPosicionMapa() {
        xmapa = cuadriculasmapa.getx();
        ymapa = cuadriculasmapa.gety();
    }

    public void setPosicionCuadricula(int i1, int i2) {
        y = i1 * tamañoCuadricula + tamañoCuadricula / 2;
        x = i2 * tamañoCuadricula + tamañoCuadricula / 2;
        xdest = x;
        ydest = y;
    }

    public void setLeft() {
        if (moviendose) {
            return;
        }
        izquierda = true;
        moviendose = validarProxPosicion();
    }

    public void setRight() {
        if (moviendose) {
            return;
        }
        derecha = true;
        moviendose = validarProxPosicion();
    }

    public void setUp() {
        if (moviendose) {
            return;
        }
        arriba = true;
        moviendose = validarProxPosicion();
    }

    public void setDown() {
        if (moviendose) {
            return;
        }
        abajo = true;
        moviendose = validarProxPosicion();
    }

    public boolean intersectos(entidad o) {
        return getRectangle().intersects(o.getRectangle());
    }

    public Rectangle getRectangle() {
        return new Rectangle(x, y, ancho2, alto2);
    }

    // Returns whether or not the entity can
    // move into the next position.
    public boolean validarProxPosicion() {

        if (moviendose) {
            return true;
        }

        filaCuadricula = y / tamañoCuadricula;
        colCuadricula = x / tamañoCuadricula;

        if (izquierda) {
            if (colCuadricula == 0 || cuadriculasmapa.getTipo(filaCuadricula, colCuadricula - 1) == cuadricula.BLOQUEADO) {
                return false;
            } else {
                xdest = x - tamañoCuadricula;
            }
        }
        if (derecha) {
            if (colCuadricula == cuadriculasmapa.getNumCol() || cuadriculasmapa.getTipo(filaCuadricula, colCuadricula + 1) == cuadricula.BLOQUEADO) {
                return false;
            } else {
                xdest = x + tamañoCuadricula;
            }
        }
        if (arriba) {
            if (filaCuadricula == 0 || cuadriculasmapa.getTipo(filaCuadricula - 1, colCuadricula) == cuadricula.BLOQUEADO) {
                return false;
            } else {
                ydest = y - tamañoCuadricula;
            }
        }
        if (abajo) {
            if (filaCuadricula == cuadriculasmapa.getNumfil() - 1 || cuadriculasmapa.getTipo(filaCuadricula + 1, colCuadricula) == cuadricula.BLOQUEADO) {
                return false;
            } else {
                ydest = y + tamañoCuadricula;
            }
        }

        return true;

    }

    // calcular coordenadas destino
    public void getProxPosicion() {

        if (izquierda && x > xdest) {
            x -= velMovimiento;
        } else {
            izquierda = false;
        }
        if (izquierda && x < xdest) {
            x = xdest;
        }

        if (derecha && x < xdest) {
            x += velMovimiento;
        } else {
            derecha = false;
        }
        if (derecha && x > xdest) {
            x = xdest;
        }

        if (arriba && y > ydest) {
            y -= velMovimiento;
        } else {
            arriba = false;
        }
        if (arriba && y < ydest) {
            y = ydest;
        }

        if (abajo && y < ydest) {
            y += velMovimiento;
        } else {
            abajo = false;
        }
        if (abajo && y > ydest) {
            y = ydest;
        }

    }

    public void actualizar() {

        // obtiene siguiente posicion
        if (moviendose) {
            getProxPosicion();
        }

        // Revisa si se detuvo
        if (x == xdest && y == ydest) {
            izquierda = derecha = arriba = abajo = moviendose = false;
            filaCuadricula = y / tamañoCuadricula;
            colCuadricula = x / tamañoCuadricula;
        }

        // actualizar animacion
        animacion.actualizar();

    }

    // Dibujar.
    public void dibujar(Graphics2D g) {
        setPosicionMapa();
        g.drawImage(animacion.getImage(),
                x + xmapa - ancho / 2,
                y + ymapa - alto / 2,
                null
        );
    }

}
