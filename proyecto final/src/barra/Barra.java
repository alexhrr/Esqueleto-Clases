//MUESTRA LO QUE TIENE EL JUGADOR
package barra;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entidad.monedas;
import entidad.Jugador;
import Main.PanelJuego;
import Jugabilidad.Contenido;

public class Barra {

    private int yoffset;

    private BufferedImage barra;
    private BufferedImage moneda;
    private BufferedImage carro;
    private BufferedImage tijera;

    private Jugador player;

    private int numMonedas;

    private Font fuente;
    private Color colorTexto;

    public Barra(Jugador p, ArrayList<monedas> d) {

        player = p;
        numMonedas = d.size();
        yoffset = PanelJuego.ALTURA;

        barra = Contenido.BARRA[0][0];
        moneda = Contenido.Monedas[0][0];
        carro = Contenido.ITEMS[0][0];
        tijera = Contenido.ITEMS[0][1];

        fuente = new Font("Arial", Font.PLAIN, 10);
        colorTexto = new Color(252,195,0);

    }

    public void dibujar(Graphics2D g) {

        // dibujar barra
        g.drawImage(barra, 0, yoffset, null);

        // dibujar barra monedas
        g.setColor(colorTexto);
        g.fillRect(8, yoffset + 6, (int) (28.0 * player.numMonedas() / numMonedas), 4);

        // dibujar cantidad de monedas 
        
        g.setColor(colorTexto);
        g.setFont(fuente);
        String s = player.numMonedas() + "/" + numMonedas;
        Contenido.dibujarPalabra(g, s, 40, yoffset + 3);
        if (player.numMonedas() >= 10) {
            g.drawImage(moneda, 80, yoffset, null);
        } else {
            g.drawImage(moneda, 72, yoffset, null);
        }

        // dibujar items
        if (player.tieneCarro()) {
            g.drawImage(carro, 100, yoffset, null);
        }
        if (player.tieneTijeras()) {
            g.drawImage(tijera, 112, yoffset, null);
        }

        // dibujar tiempo
        int minutos = (int) (player.getTicks() / 1800);
        int segundos = (int) ((player.getTicks() / 30) % 60);
        if (minutos < 10) {
            if (segundos < 10) {
                Contenido.dibujarPalabra(g, "0" + minutos + ":0" + segundos, 85, 3);
            } else {
                Contenido.dibujarPalabra(g, "0" + minutos + ":" + segundos, 85, 3);
            }
        } else {
            if (segundos < 10) {
                Contenido.dibujarPalabra(g, minutos + ":0" + segundos, 85, 3);
            } else {
                Contenido.dibujarPalabra(g, minutos + ":" + segundos, 85, 3);
            }
        }

    }

}
