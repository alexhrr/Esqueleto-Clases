// hay 2 tipos de item: tijra y carro

package entidad;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Jugabilidad.Contenido;
import cuadricula.cuadriculasMapa;

public class Item extends entidad{
	
	private BufferedImage sprite;
	private int tipo;
	public static final int CARRO = 0;
	public static final int TIJERAS = 1;
	
	public Item(cuadriculasMapa tm) {
		super(tm);
		tipo = -1;
		ancho = alto = 16;
		ancho2 = alto2 = 12;
	}
	
	public void setTipo(int i) {
		tipo = i;
		if(tipo == CARRO) {
			sprite = Contenido.ITEMS[1][0];
		}
		else if(tipo == TIJERAS) {
			sprite = Contenido.ITEMS[1][1];
		}
	}
	
	public void collected(Jugador p) {
		if(tipo == CARRO) {
			p.recogeCarro();
		}
		if(tipo == TIJERAS) {
			p.recogeTijeras();
		}
	}
	
	public void dibujar(Graphics2D g) {
		setPosicionMapa();
		g.drawImage(sprite, x + xmapa - ancho / 2, y + ymapa - alto / 2, null);
	}
	
}
