package Main;

// entra al juego cargando el Jframe y lo implementa a PanelJuego

import Main.PanelJuego;

import javax.swing.JFrame;

public class ProyectoFinal {
	
	public static void main(String[] args) {
		
		JFrame window = new JFrame("GOLD EXPERIENCE");
		
		window.add(new PanelJuego());
		
		window.setResizable(false);
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
}
