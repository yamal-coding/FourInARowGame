package tp.pr5.GUI;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import tp.pr5.Observer.Observer;
import tp.pr5.control.ControladorGUI;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.ReglasJuego;
import tp.pr5.logica.TableroSoloLectura;
import tp.pr5.logica.TipoJuego;
import tp.pr5.logica.TipoTurno;

public class PanelBotones extends JPanel implements Observer, ActionListener {
	
	public class TipoJugador extends JComboBox<String> {
		
		private Ficha color;
		
		public TipoJugador(String[] j, Ficha f){
			super(j);
			color = f;
			this.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					String tipo = (String) getSelectedItem();
					if (tipo == TipoTurno.HUMANO.getNombre())
						c.cambiarJugador(color, TipoTurno.HUMANO);
					else if (tipo == TipoTurno.AUTOMATICO.getNombre())
						c.cambiarJugador(color, TipoTurno.AUTOMATICO);
				}
			});
		}
		
	}
	
	JPanel pCambiaJuego, panelDimensiones, pOpciones, pJugadores;
	private JButton bDeshacer, bReiniciar, bSalir, bCambiaJuego;
	private JComboBox tipoJuego;
	private TipoJugador tipoJugadorBlancas, tipoJugadorNegras;
	private JLabel labelFilas, labelCols, labelBlancas, labelNegras;
	private JTextField campoFilas, campoCols;

	ControladorGUI c;
	
	
	public PanelBotones(ControladorGUI c){
		this.c = c;
		c.addObservador(this);
		
		initGUI();
	}
	
	private void initGUI(){
		setLayout(new BorderLayout());
		pCambiaJuego = new JPanel();
		panelDimensiones = new JPanel();
		pOpciones = new JPanel();
		
		construyePanelOpciones();
		construyePanelJugadores();
		construyePanelCambio();
		
		JPanel aux = new JPanel();
		aux.setLayout(new BoxLayout(aux,BoxLayout.Y_AXIS));
		
		aux.add(pOpciones);
		aux.add(pJugadores);
		aux.add(pCambiaJuego);
		
		bSalir = new JButton(Botones.SALIR.getNombre());
		bSalir.setName("salir");
		bSalir.addActionListener(this);
		bSalir.setIcon(Botones.SALIR.getIcono());
		
		JPanel jp = new JPanel();
		jp.add(bSalir);
		
		add(aux, BorderLayout.NORTH);
		add(jp, BorderLayout.SOUTH);
	}
	
	private void construyePanelOpciones(){
		pOpciones.setLayout(new FlowLayout());
		pOpciones.setBorder(BorderFactory.createTitledBorder("Partida"));
		
		bDeshacer = new JButton(Botones.DESHACER.getNombre());
		bDeshacer.setIcon(Botones.DESHACER.getIcono());
		bDeshacer.setName("deshacer");
		bDeshacer.addActionListener(this);
		bDeshacer.setEnabled(false);
		
		bReiniciar = new JButton(Botones.REINICIAR.getNombre());
		bReiniciar.setIcon(Botones.REINICIAR.getIcono());
		bReiniciar.setName("reiniciar");
		bReiniciar.addActionListener(this);
		bReiniciar.setEnabled(false);
		
		pOpciones.add(bDeshacer);
		pOpciones.add(bReiniciar);
	}
	
	private void construyePanelJugadores(){
		pJugadores = new JPanel();
		pJugadores.setLayout(new GridLayout(2, 1));
		pJugadores.setBorder(BorderFactory.createTitledBorder("Gestión de Jugadores"));
		
		JPanel aux1 = new JPanel(new GridLayout(1, 2)), aux2 = new JPanel(new GridLayout(1, 2));
		
		labelBlancas = new JLabel("Jugador de Blancas");
		labelNegras = new JLabel("Jugador de Negras");
		
		String[] jugadores = {TipoTurno.HUMANO.getNombre(), TipoTurno.AUTOMATICO.getNombre()};
		
		tipoJugadorBlancas = new TipoJugador(jugadores, Ficha.BLANCA);
		tipoJugadorNegras = new TipoJugador(jugadores, Ficha.NEGRA);
		
		aux1.add(labelBlancas);
		aux1.add(tipoJugadorBlancas);
		aux2.add(labelNegras);
		aux2.add(tipoJugadorNegras);
		
		pJugadores.add(aux1);
		pJugadores.add(aux2);
	}
	
	private void construyePanelCambio(){
		pCambiaJuego.setLayout(new GridLayout(2, 1));
		pCambiaJuego.setBorder(BorderFactory.createTitledBorder("Cambio de Juego"));
		
		String [] juegos = {TipoJuego.CONECTA4.getNombre(), TipoJuego.COMPLICA.getNombre(), 
				TipoJuego.GRAVITY.getNombre(), TipoJuego.REVERSI.getNombre()};
		
		tipoJuego = new JComboBox<String>(juegos);
		tipoJuego.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JComboBox cb = (JComboBox)e.getSource();
		        String tipoJuego = (String)cb.getSelectedItem();
		        if(tipoJuego.equals("Gravity"))
		        	panelDimensiones.setVisible(true);
		        else
		        	panelDimensiones.setVisible(false);
			}
		});
		
		bCambiaJuego = new JButton(Botones.CAMBIA_JUEGO.getNombre());
		bCambiaJuego.setName("cambiaJuego");
		bCambiaJuego.addActionListener(this);
		bCambiaJuego.setIcon(Botones.CAMBIA_JUEGO.getIcono());
		
		labelFilas = new JLabel("Filas:");
		campoFilas = new JTextField(8);
		labelCols = new JLabel("Columnas:");
		campoCols = new JTextField(8);
		
		panelDimensiones.setLayout(new FlowLayout());
		panelDimensiones.add(labelFilas);
		panelDimensiones.add(campoFilas);
		panelDimensiones.add(labelCols);
		panelDimensiones.add(campoCols);
		panelDimensiones.setVisible(false);
		
		JPanel aux1 = new JPanel();
		aux1.setLayout(new GridLayout(2, 1));
		aux1.add(tipoJuego);
		aux1.add(panelDimensiones);
		
		JPanel aux2 = new JPanel();
		aux2.setLayout(new FlowLayout());
		aux2.add(bCambiaJuego);
		
		pCambiaJuego.add(aux1);
		pCambiaJuego.add(aux2);
	}
	
	public void actionPerformed(ActionEvent e) {
		JButton j = (JButton)e.getSource();
		String opcion = j.getName();
		
		if (opcion.equals("salir")){
			c.detenerPartida();
			int i = JOptionPane.showConfirmDialog(null, "¿Realmente quieres salir?", "Saliendo de la aplicación",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(i == JOptionPane.YES_OPTION)
				System.exit(0);
			else
				c.continuarPartida();
		}
		if (opcion.equals("cambiaJuego")){
			String tipoJuego = (String) this.tipoJuego.getSelectedItem();
	        
	        if (tipoJuego.equals("Conecta 4") || tipoJuego.equals("Complica") || tipoJuego.equals("Reversi")){
	        	campoFilas.setText("");
				campoCols.setText("");
	        	c.cambiaJuego(0, 0, tipoJuego);
	        }
	        else if (tipoJuego.equals("Gravity")){
	        	try {
					int filas = Integer.parseInt(campoFilas.getText());
					int cols = Integer.parseInt(campoCols.getText());
					
					if (filas > 20 || cols > 20)
						throw new Exception();
					c.cambiaJuego(filas, cols, tipoJuego);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Valores incorrectos para las dimensiones. "
							+ "deben contener un número entero menor o igual que 20." , "Ha habido un problema", JOptionPane.WARNING_MESSAGE);
				}
	        }
		}
		if (opcion.equals("deshacer")){
			c.deshacer();
		}
		if (opcion.equals("reiniciar")){
			c.reiniciar();
		}
	}	
	
	public void onReset(TableroSoloLectura tab, Ficha turno){
		bReiniciar.setEnabled(false);
		bDeshacer.setEnabled(false);
		bCambiaJuego.setEnabled(true);
		tipoJugadorBlancas.setSelectedIndex(0);
		tipoJugadorNegras.setSelectedIndex(0);
	}
	
	public void onMovimientoEnd(TableroSoloLectura tablero,
		Ficha jugador, Ficha turno){
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				if (!bReiniciar.isEnabled())
					bReiniciar.setEnabled(true);
				if (!bDeshacer.isEnabled())
					bDeshacer.setEnabled(true);
			}
		});
		
		
	}
	
	public void onUndo(TableroSoloLectura tablero, Ficha turno, boolean hayMas){
		if (!hayMas)
			bDeshacer.setEnabled(false);
	}
	
	public void onCambioJuego(TableroSoloLectura tab, Ficha turno, TipoJuego tipo){
		onReset(tab, turno);
	}
	
	public void onPartidaTerminada(TableroSoloLectura tab, Ficha ganador){
		SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				if (bDeshacer.isEnabled())
					bDeshacer.setEnabled(false);
				if(bCambiaJuego.isEnabled())
					bCambiaJuego.setEnabled(false);
			}
		});
		
	}

	public void onUndoNotPossible(TableroSoloLectura tablero, Ficha turno){
		
	}
	
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException){
		
	}
	
	public void onStart(TableroSoloLectura tablero, Ficha turno, TipoJuego juegoActual){
		if (juegoActual == TipoJuego.CONECTA4)
			this.tipoJuego.setSelectedIndex(0);
		else if (juegoActual == TipoJuego.COMPLICA)
			this.tipoJuego.setSelectedIndex(1);
		else if(juegoActual == TipoJuego.GRAVITY){
			this.tipoJuego.setSelectedIndex(2);
			campoFilas.setText(Integer.toString(tablero.getAlto()));
			campoCols.setText(Integer.toString(tablero.getAncho()));
			panelDimensiones.setVisible(true);
		}
		else if (juegoActual == TipoJuego.REVERSI)
			this.tipoJuego.setSelectedIndex(3);
	}

	@Override
	public void onMovStart(Ficha turno, TableroSoloLectura tab, boolean[][] pistas) {
		// TODO Auto-generated method stub
		
	}

}
