package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoComplica;
import tp.pr5.logica.ReglasComplica;
import tp.pr5.logica.ReglasJuego;

public class FactoriaComplica implements FactoriaTipoJuego {

	@Override
	public ReglasJuego creaReglas() {

		return new ReglasComplica();
	}

	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		
		return new MovimientoComplica(col, color);
	}

	@Override
	public Jugador creaJugadorHumanoConsola(Scanner in) {
		
		return new JugadorHumanoComplica(this, in);
	}

	@Override
	public Jugador creaJugadorAleatorio() {
		
		return new JugadorAleatorioComplica(this);
	}

}
