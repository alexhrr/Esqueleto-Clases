
package entidad;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Jugabilidad.Contenido;
import cuadricula.cuadriculasMapa;

public class Jugador extends entidad {

    // sprites
    private BufferedImage[] abajoSprites;
    private BufferedImage[] izquierdaSprites;
    private BufferedImage[] derechaSprites;
    private BufferedImage[] arribaSprites;
    private BufferedImage[] carroAbajoSprites;
    private BufferedImage[] carroIzquierdaSprites;
    private BufferedImage[] carroDerechaSprites;
    private BufferedImage[] carroArribaSprites;

    // animacion
    private final int ABAJO = 0;
    private final int IZQUIERDA = 1;
    private final int DERECHA = 2;
    private final int ARRIBA = 3;
    private final int CARROABAJO = 4;
    private final int CARROIZQ = 5;
    private final int CARRODER = 6;
    private final int CARROARRIBA = 7;

    // Objetos de juego
    private int numMonedas;
    private int totalMonedas;
    private boolean tieneCarro;
    private boolean tieneTijeras;
    private boolean sobreCarretera;
    private long ticks;

    public Jugador(cuadriculasMapa tm) {

        super(tm);

        ancho = 16;
        alto = 16;
        ancho2 = 12;
        alto2 = 12;

        velMovimiento = 5;

        numMonedas = 0;

        abajoSprites = Contenido.Jugador[0];
        izquierdaSprites = Contenido.Jugador[1];
        derechaSprites = Contenido.Jugador[2];
        arribaSprites = Contenido.Jugador[3];
        carroAbajoSprites = Contenido.Jugador[4];
        carroIzquierdaSprites = Contenido.Jugador[5];
        carroDerechaSprites = Contenido.Jugador[6];
        carroArribaSprites = Contenido.Jugador[7];

        animacion.setFrames(abajoSprites);
        animacion.setRetraso(10);

    }

    private void setAnimacion(int i, BufferedImage[] bi, int d) {
        animacionActual = i;
        animacion.setFrames(bi);
        animacion.setRetraso(d);
    }

    public void monedasRecogidas() {
        numMonedas++;
    }

    public int numMonedas() {
        return numMonedas;
    }

    public int getTotalMonedas() {
        return totalMonedas;
    }

    public void setTotalMonedas(int i) {
        totalMonedas = i;
    }

    public void recogeCarro() {
        tieneCarro = true;
        cuadriculasmapa.reemplazar(22, 4);
    }

    public void recogeTijeras() {
        tieneTijeras = true;
    }

    public boolean tieneCarro() {
        return tieneCarro;
    }

    public boolean tieneTijeras() {
        return tieneTijeras;
    }

    // Actualiza el tiempo
    public long getTicks() {
        return ticks;
    }

    // teclas que mueven al jugador
    public void setDown() {
        super.setDown();
    }

    public void setLeft() {
        super.setLeft();
    }

    public void setRight() {
        super.setRight();
    }

    public void setUp() {
        super.setUp();
    }

    // Entrada por teclado.
    // Corta rejas con las tijeras
    public void setAccion() {
        if (tieneTijeras) {
            if (animacionActual == ARRIBA && cuadriculasmapa.getIndex(filaCuadricula - 1, colCuadricula) == 21) {
                cuadriculasmapa.setCuadricula(filaCuadricula - 1, colCuadricula, 1);
            }
            if (animacionActual == ABAJO && cuadriculasmapa.getIndex(filaCuadricula + 1, colCuadricula) == 21) {
                cuadriculasmapa.setCuadricula(filaCuadricula + 1, colCuadricula, 1);
            }
            if (animacionActual == IZQUIERDA && cuadriculasmapa.getIndex(filaCuadricula, colCuadricula - 1) == 21) {
                cuadriculasmapa.setCuadricula(filaCuadricula, colCuadricula - 1, 1);
            }
            if (animacionActual == DERECHA && cuadriculasmapa.getIndex(filaCuadricula, colCuadricula + 1) == 21) {
                cuadriculasmapa.setCuadricula(filaCuadricula, colCuadricula + 1, 1);
            }
        }
    }

    public void actualizar() {

        ticks++;

        // Revisa si esta en la carretera
        if (cuadriculasmapa.getIndex(ydest / tamañoCuadricula, xdest / tamañoCuadricula) == 4) {
            sobreCarretera = true;
        } else {
            sobreCarretera = false;
        }

        // set animacion
        if (abajo) {
            if (sobreCarretera && animacionActual != CARROABAJO) {
                setAnimacion(CARROABAJO, carroAbajoSprites, 10);
            } else if (!sobreCarretera && animacionActual != ABAJO) {
                setAnimacion(ABAJO, abajoSprites, 10);
            }
        }
        if (izquierda) {
            if (sobreCarretera && animacionActual != CARROIZQ) {
                setAnimacion(CARROIZQ, carroIzquierdaSprites, 10);
            } else if (!sobreCarretera && animacionActual != IZQUIERDA) {
                setAnimacion(IZQUIERDA, izquierdaSprites, 10);
            }
        }
        if (derecha) {
            if (sobreCarretera && animacionActual != CARRODER) {
                setAnimacion(CARRODER, carroDerechaSprites, 10);
            } else if (!sobreCarretera && animacionActual != DERECHA) {
                setAnimacion(DERECHA, derechaSprites, 10);
            }
        }
        if (arriba) {
            if (sobreCarretera && animacionActual != CARROARRIBA) {
                setAnimacion(CARROARRIBA, carroArribaSprites, 10);
            } else if (!sobreCarretera && animacionActual != ARRIBA) {
                setAnimacion(ARRIBA, arribaSprites, 10);
            }
        }

        // actualizar posicion
        super.actualizar();

    }

    // Dibujar Jugador
    public void dibujar(Graphics2D g) {
        super.dibujar(g);
    }

}

