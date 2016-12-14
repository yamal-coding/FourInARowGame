package tp.pr5.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import tp.pr5.control.ControladorGUI;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.TableroSoloLectura;

public class PanelTablero extends JPanel {
	
	public class MyButton extends JButton{
		
		private int col;
		private int fila;
		
		public MyButton(int col, int fila){
			this.col = col;
			this.fila = fila;
		}
		
		public int getCol(){
			return col;
		}
		
		public int getFila(){
			return fila;
		}
	}
	
	private MyButton [][] tablero;
	private ControladorGUI controlador;
	
	//Construye el tablero de botones a partir del que le entra como parametro
	public PanelTablero(TableroSoloLectura tab, ControladorGUI c){
		int alto = tab.getAlto();
		int ancho = tab.getAncho();
		
		this.setLayout(new GridLayout(alto, ancho));
		tablero = new MyButton[alto][ancho];
		controlador = c;
		for (int i = 0; i < alto; i++){
			for (int j = 0; j < ancho; j++){
				tablero[i][j] = new MyButton(i, j);
				tablero[i][j].setBackground(tab.getCasilla(j + 1, i + 1).getColor());
				tablero[i][j].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
				tablero[i][j].setPreferredSize(new Dimension(10, 10));
				tablero[i][j].addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						MyButton boton;
						boton = (MyButton) e.getSource();
						controlador.poner(boton.getFila() + 1, boton.getCol() + 1);
					}
				});
				this.add(tablero[i][j]);
			}
		}
	}
	
	//Dibuja el tablero a partir del que le entra como parametro pintando los botones
	public void dibujarTablero(TableroSoloLectura tab){
		for (int i = 0; i < tab.getAlto(); i++){
			for (int j = 0; j < tab.getAncho(); j++){
				if (tab.getCasilla(j + 1, i+ 1) == Ficha.VACIA)
					tablero[i][j].setBackground(Ficha.VACIA.getColor());
				else if (tab.getCasilla(j + 1, i+ 1) == Ficha.BLANCA)
					tablero[i][j].setBackground(Ficha.BLANCA.getColor());
				else if (tab.getCasilla(j + 1, i+ 1) == Ficha.NEGRA)
					tablero[i][j].setBackground(Ficha.NEGRA.getColor());
				
				tablero[i][j].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
				tablero[i][j].setPreferredSize(new Dimension(10, 10));
			}
		}	
	}
	
	//Activa todos los botones del tablero
	public void activar(TableroSoloLectura tab){
		for (int i = 0; i < tab.getAlto(); i++){
			for (int j = 0; j < tab.getAncho(); j++){
				if (!tablero[i][j].isEnabled())
					tablero[i][j].setEnabled(true);
			}
		}
	}
	
	//Desactiva todos los botones del tablero
	public void desactivar(TableroSoloLectura tab){
		for (int i = 0; i < tab.getAlto(); i++){
			for (int j = 0; j < tab.getAncho(); j++){
				if (tablero[i][j].isEnabled())
					tablero[i][j].setEnabled(false);
			}
		}
	}
	
	//Dibuja las pistas sobre el tablero de botones en las posiciones a true del tablero de boolean
	public void dibujarPistas(boolean[][] pistas, TableroSoloLectura tab){
		
		if (pistas != null){
			
			for (int i = 0; i < tab.getAlto(); i++){
				for (int j = 0; j < tab.getAncho(); j++){
					if (pistas[i][j])
						this.tablero[i][j].setBackground(Color.MAGENTA);
				}
			}
		}
	}
	
}
