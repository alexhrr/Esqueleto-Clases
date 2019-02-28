// toma un arreglo de imagenes y llama la imagen que se necesita
package entidad;

import java.awt.image.BufferedImage;

public class Animacion {

    private BufferedImage[] frames;
    private int frameActual;
    private int numFrames;

    private int contador;
    private int retraso;

    private int intentos;

    public Animacion() {
        intentos = 0;
    }

    public void setFrames(BufferedImage[] frames) {
        this.frames = frames;
        frameActual = 0;
        contador = 0;
        intentos = 0;
        retraso = 2;
        numFrames = frames.length;
    }

    public void setRetraso(int i) {
        retraso = i;
    }

    public void setFrame(int i) {
        frameActual = i;
    }

    public void setNumFrames(int i) {
        numFrames = i;
    }

    public void actualizar() {

        if (retraso == -1) {
            return;
        }

        contador++;

        if (contador == retraso) {
            frameActual++;
            contador = 0;
        }
        if (frameActual == numFrames) {
            frameActual = 0;
            intentos++;
        }

    }

    public int getFrame() {
        return frameActual;
    }

    public int getContador() {
        return contador;
    }

    public BufferedImage getImage() {
        return frames[frameActual];
    }

    public boolean primeraVez() {
        return intentos > 0;
    }

    public boolean nVez(int i) {
        return intentos == i;
    }

}
