//Carga todos lo necesario para jugar
package estadojuego;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import entidad.monedas;
import entidad.Item;
import entidad.Jugador;
import entidad.brillo;
import barra.Barra;
import Main.PanelJuego;
import Jugabilidad.Tiempos;
import Jugabilidad.EstadosDeJuego;
import Jugabilidad.Controles;
import cuadricula.cuadriculasMapa;

public class Play extends Estado {

    private Jugador player;

    private cuadriculasMapa mapaCuadriculas;

    private ArrayList<monedas> monedas;

    private ArrayList<Item> items;

    private ArrayList<brillo> brillos;

    //Zona visible
    private int xsector;
    private int ysector;
    private int tamSeccion;

    private Barra barra;

    // evento
    private boolean bloqEntrada;
    private boolean iniciaEvento;
    private boolean terminaEvento;
    private int eventoTick;

    private ArrayList<Rectangle> caja;

    public Play(EstadosDeJuego edj) {
        super(edj);
    }

    public void iniciar() {

        // crear arreglos
        monedas = new ArrayList<monedas>();
        brillos = new ArrayList<brillo>();
        items = new ArrayList<Item>();

        // cargar mapa
        mapaCuadriculas = new cuadriculasMapa(16);
        mapaCuadriculas.cargarCuadriculas("/Imagenes/Fondos.gif");
        mapaCuadriculas.cargarMapa("/Imagenes/testmap.map");

        // crea jugador
        player = new Jugador(mapaCuadriculas);

        // llenar arreglos
        ponerMonedas();
        ponerItems();

        // inicializar jugador
        player.setPosicionCuadricula(17, 20);// Pone la posicion inicial del jugador
        player.setTotalMonedas(monedas.size());

        // Centra el mapa en el jugador
        tamSeccion = PanelJuego.ANCHO;
        xsector = player.getx() / tamSeccion;
        ysector = player.gety() / tamSeccion;
        mapaCuadriculas.setPosicionImmediata(-xsector * tamSeccion, -ysector * tamSeccion);

        // cargar barra
        barra = new Barra(player, monedas);

        // iniciar evento
        caja = new ArrayList<Rectangle>();
        iniciaEvento = true;
        iniciarEvento();

    }

    private void ponerMonedas() {

        monedas d;

        d = new monedas(mapaCuadriculas);
        d.setPosicionCuadricula(21, 21);
        d.anadirCambio(new int[]{23, 19, 1});
        d.anadirCambio(new int[]{23, 20, 1});
        monedas.add(d);
        d = new monedas(mapaCuadriculas);
        d.setPosicionCuadricula(12, 36);
        d.anadirCambio(new int[]{31, 17, 1});
        monedas.add(d);
        d = new monedas(mapaCuadriculas);
        d.setPosicionCuadricula(28, 4);
        d.anadirCambio(new int[]{27, 7, 1});
        d.anadirCambio(new int[]{28, 7, 1});
        monedas.add(d);
        d = new monedas(mapaCuadriculas);
        d.setPosicionCuadricula(4, 34);
        d.anadirCambio(new int[]{31, 21, 1});
        monedas.add(d);

        d = new monedas(mapaCuadriculas);
        d.setPosicionCuadricula(28, 19);
        monedas.add(d);
        d = new monedas(mapaCuadriculas);
        d.setPosicionCuadricula(35, 26);
        monedas.add(d);
        d = new monedas(mapaCuadriculas);
        d.setPosicionCuadricula(38, 36);
        monedas.add(d);
        d = new monedas(mapaCuadriculas);
        d.setPosicionCuadricula(27, 28);
        monedas.add(d);
        d = new monedas(mapaCuadriculas);
        d.setPosicionCuadricula(20, 30);
        monedas.add(d);
        d = new monedas(mapaCuadriculas);
        d.setPosicionCuadricula(14, 25);
        monedas.add(d);
        d = new monedas(mapaCuadriculas);
        d.setPosicionCuadricula(4, 21);
        monedas.add(d);
        d = new monedas(mapaCuadriculas);
        d.setPosicionCuadricula(9, 14);
        monedas.add(d);
        d = new monedas(mapaCuadriculas);
        d.setPosicionCuadricula(4, 3);
        monedas.add(d);
        d = new monedas(mapaCuadriculas);
        d.setPosicionCuadricula(20, 14);
        monedas.add(d);
        d = new monedas(mapaCuadriculas);
        d.setPosicionCuadricula(13, 20);
        monedas.add(d);

    }

    private void ponerItems() {

        Item item;

        item = new Item(mapaCuadriculas);
        item.setTipo(Item.TIJERAS);
        item.setPosicionCuadricula(26, 37);
        items.add(item);

        item = new Item(mapaCuadriculas);
        item.setTipo(Item.CARRO);
        item.setPosicionCuadricula(12, 4);
        items.add(item);

    }

    public void actualizar() {

        // revisar teclas
        entradaTeclado();

        // inicializa o finaliza evento
        if (iniciaEvento) {
            iniciarEvento();
        }
        if (terminaEvento) {
            finalizarEvento();
        }

        if (player.numMonedas() == player.getTotalMonedas()) {
            terminaEvento = bloqEntrada = true;
        }

        // actualizar camara
        int antxs = xsector;
        int antys = ysector;
        xsector = player.getx() / tamSeccion;
        ysector = player.gety() / tamSeccion;
        mapaCuadriculas.setPosicion(-xsector * tamSeccion, -ysector * tamSeccion);
        mapaCuadriculas.actualizar();

        if (mapaCuadriculas.esMovimiento()) {
            return;
        }

        // actualizar jugador
        player.actualizar();

        // actualizar monedas
        for (int i = 0; i < monedas.size(); i++) {

            monedas d = monedas.get(i);
            d.actualizar();

            // jugador recoge monedas
            if (player.intersectos(d)) {

                // quita del arreglo la moneda
                monedas.remove(i);
                i--;

                // incrementa cantidad de monedas recogidas
                player.monedasRecogidas();

               // anima un brillo
                brillo s = new brillo(mapaCuadriculas);
                s.setPosicion(d.getx(), d.gety());
                brillos.add(s);

                // hace cambios a la cuadricula del mapa
                ArrayList<int[]> ali = d.getCambios();
                for (int[] j : ali) {
                    mapaCuadriculas.setCuadricula(j[0], j[1], j[2]);
                }
            }
        }

        // actualizar brillo
        for (int i = 0; i < brillos.size(); i++) {
            brillo s = brillos.get(i);
            s.actualizar();
            if (s.shouldRemove()) {
                brillos.remove(i);
                i--;
            }
        }

        // actualizar items
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (player.intersectos(item)) {
                items.remove(i);
                i--;
                item.collected(player);
                brillo s = new brillo(mapaCuadriculas);
                s.setPosicion(item.getx(), item.gety());
                brillos.add(s);
            }
        }

    }

    public void dibujar(Graphics2D g) {

        // dibujar cuadricula del mapa
        mapaCuadriculas.dibujar(g);

        // dibujar jugador
        player.dibujar(g);

        // dibujar monedas
        for (monedas d : monedas) {
            d.dibujar(g);
        }

        // dibujar brillo
        for (brillo s : brillos) {
            s.dibujar(g);
        }

        // dibujar items
        for (Item i : items) {
            i.dibujar(g);
        }

        // dibujar barra
        barra.dibujar(g);

        // dibujar transicion de la barra 
        g.setColor(java.awt.Color.MAGENTA);
        for (int i = 0; i < caja.size(); i++) {
            g.fill(caja.get(i));
        }

    }

    public void entradaTeclado() {
        if (Controles.estaPresionado(Controles.ESCAPE)) {
            edj.setPausado(true);
        }
        if (bloqEntrada) {
            return;
        }
        if (Controles.oprimido(Controles.IZQUIERDA)) {
            player.setLeft();
        }
        if (Controles.oprimido(Controles.DERECHA)) {
            player.setRight();
        }
        if (Controles.oprimido(Controles.ARRIBA)) {
            player.setUp();
        }
        if (Controles.oprimido(Controles.ABAJO)) {
            player.setDown();
        }
        if (Controles.estaPresionado(Controles.ESPACIO)) {
            player.setAccion();
        }
    }

    //===============================================
    private void iniciarEvento() {
        eventoTick++;
        if (eventoTick == 1) {
            caja.clear();
            for (int i = 0; i < 9; i++) {
                caja.add(new Rectangle(0, i * 16, PanelJuego.ANCHO, 16));
            }
        }
        if (eventoTick > 1 && eventoTick < 32) {
            for (int i = 0; i < caja.size(); i++) {
                Rectangle r = caja.get(i);
                if (i % 2 == 0) {
                    r.x -= 4;
                } else {
                    r.x += 4;
                }
            }
        }
        if (eventoTick == 33) {
            caja.clear();
            iniciaEvento = false;
            eventoTick = 0;
        }
    }

    private void finalizarEvento() {
        eventoTick++;
        if (eventoTick == 1) {
            caja.clear();
            for (int i = 0; i < 9; i++) {
                if (i % 2 == 0) {
                    caja.add(new Rectangle(-128, i * 16, PanelJuego.ANCHO, 16));
                } else {
                    caja.add(new Rectangle(128, i * 16, PanelJuego.ANCHO, 16));
                }
            }

        }
        if (eventoTick > 1) {
            for (int i = 0; i < caja.size(); i++) {
                Rectangle r = caja.get(i);
                if (i % 2 == 0) {
                    if (r.x < 0) {
                        r.x += 4;
                    }
                } else {
                    if (r.x > 0) {
                        r.x -= 4;
                    }
                }
            }
        }
        if (eventoTick > 33) {
            Tiempos.setTiempo(player.getTicks());
            edj.colocarEstado(EstadosDeJuego.terminar);
        }
    }

}
