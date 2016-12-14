package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoGravity;
import tp.pr5.logica.ReglasGravity;
import tp.pr5.logica.ReglasJuego;

public class FactoriaGravity implements FactoriaTipoJuego {
	private int ancho;
	private int alto;
	
	public FactoriaGravity(int x, int y){
		ancho = x;
		alto = y;
	}

	@Override
	public ReglasJuego creaReglas() {
		
		return new ReglasGravity(ancho, alto);
	}

	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
	
		return new MovimientoGravity(col, fila, color);
	}

	@Override
	public Jugador creaJugadorHumanoConsola(Scanner in) {
	
		return new JugadorHumanoGravity(this, in);
	}

	@Override
	public Jugador creaJugadorAleatorio() {
	
		return new JugadorAleatorioGravity(this);
	}

}
