package tp.pr5.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import tp.pr5.Observer.Observer;
import tp.pr5.control.ControladorGUI;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.ReglasJuego;
import tp.pr5.logica.TableroSoloLectura;
import tp.pr5.logica.TipoJuego;
import tp.pr5.logica.TipoTurno;

public class PanelJuego extends JPanel implements Observer {

	private JButton bPonerAleatorio;
	private JPanel panelPartida;
	private PanelTablero panelTablero;
	private JTextField turnoPartida;
	
	private ControladorGUI c;
	
	public PanelJuego(ControladorGUI c){
		this.c = c;
		c.addObservador(this);
		initGUI();
	}
	
	private void initGUI(){
		this.setLayout(new BorderLayout());
		
		panelPartida = new JPanel();	
		panelPartida.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		turnoPartida = new JTextField(20);
		turnoPartida.setFont(new Font("Calibri", 3, 15));
		turnoPartida.setForeground(Color.BLUE);
		turnoPartida.setBackground(Color.WHITE);
		turnoPartida.setHorizontalAlignment(SwingConstants.CENTER);
		turnoPartida.setEditable(false);
		
		bPonerAleatorio = new JButton(Botones.ALEATORIO.getNombre());
		bPonerAleatorio.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				c.ponerAleatorio();
			}
		});
		bPonerAleatorio.setIcon(Botones.ALEATORIO.getIcono());
	
		panelPartida.setLayout(new BorderLayout());
		panelPartida.add(turnoPartida, BorderLayout.SOUTH);
		
		JPanel aux = new JPanel(new FlowLayout());
		aux.add(bPonerAleatorio);
		
		add(panelPartida, BorderLayout.CENTER);
		add(aux, BorderLayout.SOUTH);
	}

	public void onReset(TableroSoloLectura tab, Ficha turno){
		panelTablero.dibujarTablero(tab);
		
		this.turnoPartida.setText("Juegan " + turno.getNombre());
		
		if(!bPonerAleatorio.isEnabled())
			bPonerAleatorio.setEnabled(true);
	}
	
	public void onMovimientoEnd(final TableroSoloLectura tablero,
		Ficha jugador, final Ficha turno){
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				panelTablero.dibujarTablero(tablero);
				turnoPartida.setText("Juegan " + turno.getNombre());
			}
		});
		
	}
	
	public void onUndo(TableroSoloLectura tablero, Ficha turno, boolean hayMas){
		panelTablero.dibujarTablero(tablero);
		
		this.turnoPartida.setText("Juegan " + turno.getNombre());
	}
	
	public void onCambioJuego(TableroSoloLectura tab, Ficha turno, TipoJuego tipo){
		panelPartida.remove(panelTablero);
		onStart(tab, turno, tipo);
	}
	
	public void onPartidaTerminada(final TableroSoloLectura tab, Ficha ganador){
		SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				panelTablero.dibujarTablero(tab);
				bPonerAleatorio.setEnabled(false);
			}
		});
		
		
	}
	
	public void onUndoNotPossible(TableroSoloLectura tablero, Ficha turno){
		
	}
	
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException){
		
	}
	
	public void onStart(TableroSoloLectura tablero, Ficha turno, TipoJuego tipo){
		panelTablero = new PanelTablero(tablero, c);
		panelPartida.add(panelTablero,BorderLayout.CENTER);
		panelPartida.updateUI();
	
		this.turnoPartida.setText("Juegan " + turno.getNombre());
		//c.continuarPartida();
	}

	@Override
	public void onMovStart(Ficha turno, final TableroSoloLectura tab, final boolean[][] pistas) {
		if (turno.getTipoTurno() == TipoTurno.HUMANO){

			SwingUtilities.invokeLater(new Runnable() {
				public void run(){
					panelTablero.dibujarPistas(pistas, tab);
				}
			});
			
			if (!bPonerAleatorio.isEnabled())
				bPonerAleatorio.setEnabled(true);
			
			panelTablero.activar(tab);
		}
		else{
			if (bPonerAleatorio.isEnabled())
				bPonerAleatorio.setEnabled(false);
			
			panelTablero.desactivar(tab);
		}
		turno.getModo().comenzar();
	}
	
	
}
