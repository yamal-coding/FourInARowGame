package tp.pr5.control;

import tp.pr5.Observer.Observer;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Modo;
import tp.pr5.logica.ModoAutomatico;
import tp.pr5.logica.ModoHumano;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Partida;
import tp.pr5.logica.ReglasJuego;
import tp.pr5.logica.TipoJuego;
import tp.pr5.logica.TipoTurno;

public class ControladorGUI {
	
	private Partida moPartida;
	private ReglasJuego reglasJuego;
	private FactoriaTipoJuego factoria;
	
	private Jugador jugadorAleatorio;
	
	public ControladorGUI(FactoriaTipoJuego f, Partida partida){
		factoria = f;
		reglasJuego = f.creaReglas();
		moPartida = partida;
		
		jugadorAleatorio = factoria.creaJugadorAleatorio();
	}
	
	public void addObservador(Observer obs){
		moPartida.addObserver(obs);
	}
	
	public void cambiaJuego(int alto, int ancho, String tipoJuego){//CAMBIAR POR EL ENUM TIPOJUEGO!!!
		if (tipoJuego.equals(TipoJuego.CONECTA4.getNombre()))
			factoria = new FactoriaConecta4();
		else if (tipoJuego.equals(TipoJuego.COMPLICA.getNombre()))
			factoria = new FactoriaComplica();
		else if (tipoJuego.equals(TipoJuego.GRAVITY.getNombre()))
			factoria = new FactoriaGravity(ancho, alto);
		else if (tipoJuego.equals(TipoJuego.REVERSI.getNombre()))
			factoria = new FactoriaReversi();
		
		reglasJuego = factoria.creaReglas();
		jugadorAleatorio = factoria.creaJugadorAleatorio();
		moPartida.cambioJuego(reglasJuego);
	}
	
	public void deshacer(){
		moPartida.detenerPartida();
		moPartida.undo();
		moPartida.continuarPartida();
	}
	
	public void reiniciar(){
		moPartida.detenerPartida();
		moPartida.reiniciar(reglasJuego);
		moPartida.continuarPartida();
	}
	
	public void poner(int col, int fila){
		
		Movimiento mov = factoria.creaMovimiento(col, fila, moPartida.getTurno());
		
		moPartida.ejecutaMovimiento(mov);
		
	}
	
	public void ponerAleatorio(){
	
		Movimiento mov = moPartida.siguenteMovimiento(jugadorAleatorio, moPartida.getTurno());
		
		moPartida.ejecutaMovimiento(mov);
		
	}
	
	public void start(){
		moPartida.start();
	}
	
	public void cambiarJugador(Ficha color, TipoTurno tipo){
		Modo m;
		
		moPartida.detenerPartida();
		if(tipo == TipoTurno.HUMANO)
			m = new ModoHumano();
		else
			m = new ModoAutomatico(this);
		
		color.cambiarModo(tipo, m);
		moPartida.continuarPartida();
	}
	
	public void detenerPartida(){
		moPartida.detenerPartida();
	}
	
	public void continuarPartida(){
		moPartida.continuarPartida();
	}
}
