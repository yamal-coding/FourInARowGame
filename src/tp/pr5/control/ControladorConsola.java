package tp.pr5.control;



import java.util.Scanner;

import tp.pr5.Observer.Observer;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Partida;
import tp.pr5.logica.ReglasJuego;
import tp.pr5.logica.TipoJuego;

public class ControladorConsola {
	private Partida moPartida;
	private FactoriaTipoJuego factoria;
	private ReglasJuego reglas;
	private Jugador jugadorBlancas;
	private Jugador jugadorNegras;
	private Scanner in;
	
	public ControladorConsola(FactoriaTipoJuego f, Partida p, Scanner in){
		this.in = in;
		moPartida = p;
		factoria = f;
		reglas = f.creaReglas();
		jugadorBlancas = f.creaJugadorHumanoConsola(in);
		jugadorNegras = f.creaJugadorHumanoConsola(in);
	}
	
	public void reiniciar(){
		moPartida.reiniciar(reglas);
	}
	
	public void poner(){
		Movimiento mov;
		if (moPartida.getTurno() == Ficha.BLANCA)
			mov = moPartida.siguenteMovimiento(jugadorBlancas, Ficha.BLANCA);
		else
			mov = moPartida.siguenteMovimiento(jugadorNegras, Ficha.NEGRA);
		
		moPartida.ejecutaMovimiento(mov);
	}
	
	public void cambioJuego(int alto, int ancho, TipoJuego tipoJuego){
		if (tipoJuego == TipoJuego.CONECTA4)
			factoria = new FactoriaConecta4();
		else if (tipoJuego == TipoJuego.COMPLICA)
			factoria = new FactoriaComplica();
		else if (tipoJuego == TipoJuego.GRAVITY)
			factoria = new FactoriaGravity(ancho, alto);
		else if (tipoJuego == TipoJuego.REVERSI)
			factoria = new FactoriaReversi();
		
		reglas = factoria.creaReglas();
		
		jugadorBlancas = factoria.creaJugadorHumanoConsola(in);
		jugadorNegras = factoria.creaJugadorHumanoConsola(in);
		
		moPartida.cambioJuego(reglas);
		
	}
	
	public void undo(){
		moPartida.undo();
	}
	
	public void addObservador(Observer obs){
		moPartida.addObserver(obs);
	}

	public void start() {
		moPartida.start();
	}
	
	public void cambiarJugadorAHumano(Ficha color){
		if (color == Ficha.BLANCA)
			jugadorBlancas = factoria.creaJugadorHumanoConsola(in);
		else if (color == Ficha.NEGRA)
			jugadorNegras = factoria.creaJugadorHumanoConsola(in);
	}
	
	public void cambiarJugadorAAleatorio(Ficha color){
		if (color == Ficha.BLANCA)
			jugadorBlancas = factoria.creaJugadorAleatorio();
		else if (color == Ficha.NEGRA)
			jugadorNegras = factoria.creaJugadorAleatorio();
	}
}
