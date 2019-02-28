// contiene las cuadriculas del mapa
package cuadricula;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Main.PanelJuego;

public class cuadriculasMapa {

    // posicion
    private int x;
    private int y;
    private int xdest;
    private int ydest;
    private int velocidad;
    private boolean movimiento;

    // limites
    private int xmin;
    private int ymin;
    private int xmax;
    private int ymax;

    // mapa
    private int[][] mapa;
    private int tamaño;
    private int numfil;
    private int numCol;
    private int ancho;
    private int altura;

    // colocar cuadriculas
    private BufferedImage colocarcuadriculas;
    private int numCuadriculaCruzar;
    private cuadricula[][] cuadriculas;

    // dibujar
    private int filaOffset;
    private int colOffset;
    private int numFilADibujar;
    private int numColsADibujar;

    public cuadriculasMapa(int tamañoCuadricula) {
        this.tamaño = tamañoCuadricula;
        numFilADibujar = PanelJuego.ALTURA / tamañoCuadricula + 2;
        numColsADibujar = PanelJuego.ANCHO / tamañoCuadricula + 2;
        velocidad = 4;
    }

    public void cargarCuadriculas(String s) {

        try {

            colocarcuadriculas = ImageIO.read(
                    getClass().getResourceAsStream(s)
            );
            numCuadriculaCruzar = colocarcuadriculas.getWidth() / tamaño;
            cuadriculas = new cuadricula[2][numCuadriculaCruzar];

            BufferedImage Subimagen;
            for (int col = 0; col < numCuadriculaCruzar; col++) {
                Subimagen = colocarcuadriculas.getSubimage(col * tamaño, 0, tamaño, tamaño);
                cuadriculas[0][col] = new cuadricula(Subimagen, cuadricula.NORMAL);
                Subimagen = colocarcuadriculas.getSubimage(col * tamaño, tamaño, tamaño, tamaño);
                cuadriculas[1][col] = new cuadricula(Subimagen, cuadricula.BLOQUEADO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void cargarMapa(String s) {

        try {

            InputStream in = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            numCol = Integer.parseInt(br.readLine());
            numfil = Integer.parseInt(br.readLine());
            mapa = new int[numfil][numCol];
            ancho = numCol * tamaño;
            altura = numfil * tamaño;

            xmin = PanelJuego.ANCHO - ancho;
            xmin = -ancho;
            xmax = 0;
            ymin = PanelJuego.ALTURA - altura;
            ymin = -altura;
            ymax = 0;

            String delims = "\\s+";
            for (int fila = 0; fila < numfil; fila++) {
                String linea = br.readLine();
                String[] tokens = linea.split(delims);
                for (int col = 0; col < numCol; col++) {
                    mapa[fila][col] = Integer.parseInt(tokens[col]);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int getTamaño() {
        return tamaño;
    }

    public int getx() {
        return x;
    }

    public int gety() {
        return y;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAltura() {
        return altura;
    }

    public int getNumfil() {
        return numfil;
    }

    public int getNumCol() {
        return numCol;
    }

    public int getTipo(int fila, int col) {
        int rc = mapa[fila][col];
        int r = rc / numCuadriculaCruzar;
        int c = rc % numCuadriculaCruzar;
        return cuadriculas[r][c].getTipo();
    }

    public int getIndex(int fila, int col) {
        return mapa[fila][col];
    }

    public boolean esMovimiento() {
        return movimiento;
    }

    public void setCuadricula(int fila, int col, int dimension) {
        mapa[fila][col] = dimension;
    }

    public void reemplazar(int i1, int i2) {
        for (int fil = 0; fil < numfil; fil++) {
            for (int col = 0; col < numCol; col++) {
                if (mapa[fil][col] == i1) {
                    mapa[fil][col] = i2;
                }
            }
        }
    }

    public void setPosicion(int x, int y) {
        xdest = x;
        ydest = y;
    }

    public void setPosicionImmediata(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void arreglarTamaño() {
        if (x < xmin) {
            x = xmin;
        }
        if (y < ymin) {
            y = ymin;
        }
        if (x > xmax) {
            x = xmax;
        }
        if (y > ymax) {
            y = ymax;
        }
    }

    public void actualizar() {
        if (x < xdest) {
            x += velocidad;
            if (x > xdest) {
                x = xdest;
            }
        }
        if (x > xdest) {
            x -= velocidad;
            if (x < xdest) {
                x = xdest;
            }
        }
        if (y < ydest) {
            y += velocidad;
            if (y > ydest) {
                y = ydest;
            }
        }
        if (y > ydest) {
            y -= velocidad;
            if (y < ydest) {
                y = ydest;
            }
        }

        arreglarTamaño();

        colOffset = -this.x / tamaño;
        filaOffset = -this.y / tamaño;

        if (x != xdest || y != ydest) {
            movimiento = true;
        } else {
            movimiento = false;
        }

    }

    public void dibujar(Graphics2D g) {

        for (int row = filaOffset; row < filaOffset + numFilADibujar; row++) {

            if (row >= numfil) {
                break;
            }

            for (int col = colOffset; col < colOffset + numColsADibujar; col++) {

                if (col >= numCol) {
                    break;
                }
                if (mapa[row][col] == 0) {
                    continue;
                }

                int rc = mapa[row][col];
                int r = rc / numCuadriculaCruzar;
                int c = rc % numCuadriculaCruzar;

                g.drawImage(cuadriculas[r][c].getImagen(), x + col * tamaño, y + row * tamaño, null);

            }

        }

    }

}
