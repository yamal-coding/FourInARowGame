package tp.pr5.control;

import java.util.Random;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.TableroSoloLectura;

public class JugadorAleatorioComplica implements Jugador {
	
	private FactoriaComplica f;
	
	public JugadorAleatorioComplica(FactoriaComplica fact){
		f = fact;
	}
	
	@Override
	public Movimiento getMovimiento(TableroSoloLectura tab, Ficha color) {
		Random r = new Random();
		int columna;
		
		columna = r.nextInt(tab.getAncho()) + 1;
		
		return f.creaMovimiento(columna, 0, color);
	}

}
