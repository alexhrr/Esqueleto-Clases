// cargar los sprites

package Jugabilidad;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Contenido {

    public static BufferedImage[][] MENUBG = Cargar("/Imagenes/menuscreen.gif", 128, 144);
    public static BufferedImage[][] BARRA = Cargar("/Imagenes/barra.gif", 128, 16);

    public static BufferedImage[][] Jugador = Cargar("/Imagenes/Spritejugador.gif", 16, 16);
    public static BufferedImage[][] Monedas = Cargar("/Imagenes/monedas.gif", 16, 16);
    public static BufferedImage[][] brillo = Cargar("/Imagenes/Brillo.gif", 16, 16);
    public static BufferedImage[][] ITEMS = Cargar("/Imagenes/items.gif", 16, 16);

    public static BufferedImage[][] fuente = Cargar("/Imagenes/fuente.gif", 8, 8);

    public static BufferedImage[][] Cargar(String s, int w, int h) {
        BufferedImage[][] ret;
        try {
            BufferedImage hojassprites = ImageIO.read(Contenido.class.getResourceAsStream(s));
            int ancho = hojassprites.getWidth() / w;
            int alto = hojassprites.getHeight() / h;
            ret = new BufferedImage[alto][ancho];
            for (int i = 0; i < alto; i++) {
                for (int j = 0; j < ancho; j++) {
                    ret[i][j] = hojassprites.getSubimage(j * w, i * h, w, h);
                }
            }
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al cargar imagenes");
            System.exit(0);
        }
        return null;
    }

    public static void dibujarPalabra(Graphics2D g, String s, int x, int y) {
        s = s.toUpperCase();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 47) {
                c = 36; // slash
            }
            if (c == 58) {
                c = 37; // colon
            }
            if (c == 32) {
                c = 38; // space
            }
            if (c >= 65 && c <= 90) {
                c -= 65; // letters
            }
            if (c >= 48 && c <= 57) {
                c -= 22; // numbers
            }
            int row = c / fuente[0].length;
            int col = c % fuente[0].length;
            g.drawImage(fuente[row][col], x + 8 * i, y, null);
        }
    }

}
