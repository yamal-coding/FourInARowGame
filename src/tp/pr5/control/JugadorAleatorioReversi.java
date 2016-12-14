package tp.pr5.control;

import java.util.Random;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoReversi;
import tp.pr5.logica.TableroSoloLectura;

public class JugadorAleatorioReversi implements Jugador {
	
	private FactoriaReversi factoria;
	
	public JugadorAleatorioReversi(FactoriaReversi f){
		factoria = f;
	}

	@Override
	public Movimiento getMovimiento(TableroSoloLectura tablero, Ficha color) {
		Random r = new Random();
		int columna, fila;
		MovimientoReversi mov;
		do{
			do{
				columna = r.nextInt(tablero.getAncho()) + 1;
				fila = r.nextInt(tablero.getAlto()) + 1;
			} while(tablero.getCasilla(columna, fila) != Ficha.VACIA);
			
			mov = (MovimientoReversi) factoria.creaMovimiento(columna, fila, color);
			
		} while (!mov.buscaMovimientos(tablero));
		
		return factoria.creaMovimiento(columna, fila, color);
	}

}
