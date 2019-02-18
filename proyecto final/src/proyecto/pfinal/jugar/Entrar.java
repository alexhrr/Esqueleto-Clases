package proyecto.pfinal.jugar;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import proyecto.main.PanelJuego;
import proyecto.main.jugabilidad.EstadosDeJuego;
import proyecto.main.jugabilidad.Controles;

public class Entrar extends Estado {

    private BufferedImage presentacion;

    private int alfa;

    private int tiempo;

    private final int aparecer=60;

    private final int desaparecer=60;

    private final int largo=60;

    public Entrar(Estado es) {
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
}
