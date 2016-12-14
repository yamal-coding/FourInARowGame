package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoReversi;
import tp.pr5.logica.ReglasJuego;
import tp.pr5.logica.ReglasReversi;

public class FactoriaReversi implements FactoriaTipoJuego {

	@Override
	public ReglasJuego creaReglas() {
		return new ReglasReversi();
	}

	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoReversi(col, fila, color);
	}

	@Override
	public Jugador creaJugadorHumanoConsola(Scanner in) {
		
		return new JugadorHumanoReversi(this, in);
	}

	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioReversi(this);
	}

}
