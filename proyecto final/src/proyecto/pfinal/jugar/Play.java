package proyecto.pfinal.jugar;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import proyecto.pfinal.pjs.Item;
import proyecto.pfinal.pjs.Brillo;
import proyecto.pfinal.pjs.Monedas;
import proyecto.pfinal.pjs.Jugador;
import proyecto.pfinal.barraestado.Barra;
import proyecto.main.PanelJuego;
import proyecto.main.jugabilidad.Controles;
import proyecto.main.jugabilidad.Tiempo;
import proyecto.main.jugabilidad.EstadosDeJuego;
import proyecto.pfinal.titulo.TituloDeMapa;

public class Play extends Estado {

    private Jugador jugador;

    private TituloDeMapa titulo;

    private ArrayList<Monedas> monedas;

    private ArrayList<Item> item;

    private ArrayList<Brillo> brillo;

    private int posX;

    private int posY;

    private int tamano;

    private Barra barra;

    private boolean PararEntrada;

    private boolean empezarInteraccion;

    private boolean terminarInteraccion;

    public Play(Estado es) {
        super(es);
    }

    

    public void iniciar() {
    }

    public void actualizar() {
    }

    public void dibujar(Graphics2D g) {
    }

    public void entrada() {
    }

    public void ColocarItems() {
    }

    public void ColocarMonedas() {
    }

    public void empezarinteraccion() {
    }

    public void TerminarInteraccion() {
    }
}
