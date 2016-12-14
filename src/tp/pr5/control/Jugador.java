package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.TableroSoloLectura;

public interface Jugador {
	
	public abstract Movimiento getMovimiento(TableroSoloLectura tablero, Ficha color);
}
