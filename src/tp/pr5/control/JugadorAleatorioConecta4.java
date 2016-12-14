package tp.pr5.control;

import java.util.Random;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.TableroSoloLectura;
import tp.pr5.logica.Utilidades;

public class JugadorAleatorioConecta4 implements Jugador {

	private FactoriaConecta4 f;
	
	public JugadorAleatorioConecta4(FactoriaConecta4 fact){
		f = fact;
	}
	
	@Override
	public Movimiento getMovimiento(TableroSoloLectura tab, Ficha color) {
		Random r = new Random();
		int columna;
		
		do{
			columna = r.nextInt(tab.getAncho()) + 1;
		}while (Utilidades.primerVacio(columna - 1, tab) == -1);
		
		return f.creaMovimiento(columna, 0, color);
	}

}
