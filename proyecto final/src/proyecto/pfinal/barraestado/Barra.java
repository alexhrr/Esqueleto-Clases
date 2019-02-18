package proyecto.pfinal.barraestado;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import proyecto.pfinal.pjs.Monedas;
import proyecto.pfinal.pjs.Jugador;
import proyecto.main.PanelJuego;
import proyecto.main.jugabilidad.Contenido;
public class Barra {

    private BufferedImage barra;

    private BufferedImage monedas;

    private BufferedImage hachas;

    private BufferedImage bote;

    private int NumeroMonedas;

    private int yoffset;

    private Jugador player;

    private Color TexColor;

    private Font frente;

    public Barra(Jugador j, ArrayList<Monedas> m) {
    }

    public void dibujar(Graphics2D g) {
    }
}
