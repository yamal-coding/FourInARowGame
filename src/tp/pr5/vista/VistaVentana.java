package tp.pr5.vista;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import tp.pr5.GUI.PanelBotones;
import tp.pr5.GUI.PanelJuego;
import tp.pr5.Observer.Observer;
import tp.pr5.control.ControladorGUI;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.ReglasJuego;
import tp.pr5.logica.TableroSoloLectura;
import tp.pr5.logica.TipoJuego;

public class VistaVentana extends JFrame implements Observer {
	Container _panelPrincipal;
	PanelJuego _panelTablero;
	PanelBotones _panelBotones;
	
	ControladorGUI c;
	
	public VistaVentana(ControladorGUI c){
		super("PRACTICA 5");
		this.c = c;

		initGUI();
		c.start();
		c.addObservador(this);
		
		this.setLocation(50, 100);
		this.setSize(650, 500);
		this.setMinimumSize(new Dimension(650, 500));
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void initGUI(){
		
		_panelPrincipal = getContentPane();
		_panelPrincipal.setLayout(new GridLayout(1, 2));
		_panelTablero = new PanelJuego(c);
		_panelBotones = new PanelBotones(c);
		
		_panelPrincipal.add(_panelTablero);
		_panelPrincipal.add(_panelBotones);
		this.pack();
	}
	
	public void onReset(TableroSoloLectura tab, Ficha turno){
		
	}
	
	public void onPartidaTerminada(TableroSoloLectura tab, Ficha ganador){
		String mensaje;
		if(ganador == Ficha.VACIA)
			mensaje = "Partida terminada en tablas.";
		else
			mensaje = "Ganan las " + ganador.getNombre() + ".";
		JOptionPane.showMessageDialog(null, mensaje, "Partida terminada", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void onCambioJuego(TableroSoloLectura tab, Ficha turno, TipoJuego tipo){
		this.pack();
	}
	
	public void onUndoNotPossible(TableroSoloLectura tablero, Ficha turno){
		
	}
	
	public void onUndo(TableroSoloLectura tablero, Ficha turno, boolean hayMas){
		
	}
	
	public void onMovimientoEnd(TableroSoloLectura tablero,Ficha jugador, Ficha turno){
		
	}
	
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException){
		JOptionPane.showMessageDialog(null, movimientoException.getMessage(), "Ha habido un error", JOptionPane.ERROR_MESSAGE);
	}
	
	public void onStart(TableroSoloLectura tablero, Ficha turnoPartida, TipoJuego tipo){
		
	}

	@Override
	public void onMovStart(Ficha turno, TableroSoloLectura tab, boolean[][] pistas) {
		// TODO Auto-generated method stub
		
	}
	
}
