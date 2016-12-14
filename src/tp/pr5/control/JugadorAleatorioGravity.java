package tp.pr5.control;

import java.util.Random;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.TableroSoloLectura;

public class JugadorAleatorioGravity implements Jugador {

	private FactoriaGravity f;
	
	public JugadorAleatorioGravity(FactoriaGravity fact){
		f = fact;
	}
	
	@Override
	public Movimiento getMovimiento(TableroSoloLectura tab, Ficha color) {
		Random r = new Random();
		int columna, fila;
		do{
			columna = r.nextInt(tab.getAncho()) + 1;
			fila = r.nextInt(tab.getAlto()) + 1;
			
		} while (tab.getCasilla(columna, fila) != Ficha.VACIA);
		
		return f.creaMovimiento(columna, fila, color);
	}

}
