package tp.pr5.Observer;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.ReglasJuego;
import tp.pr5.logica.TableroSoloLectura;
import tp.pr5.logica.TipoJuego;

public interface Observer {
	
	public void onReset(TableroSoloLectura tab, Ficha turno);
	
	public void onPartidaTerminada(TableroSoloLectura tab, Ficha ganador);
	
	public void onCambioJuego(TableroSoloLectura tab, Ficha turno, TipoJuego tipo);
	
	public void onUndoNotPossible(TableroSoloLectura tablero, Ficha turno);
	
	public void onUndo(TableroSoloLectura tablero, Ficha turno, boolean hayMas);
	
	public void onMovimientoEnd(TableroSoloLectura tablero,
	Ficha jugador, Ficha turno);
	
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException);
	
	public void onStart(TableroSoloLectura tablero, Ficha turnoPartida, TipoJuego tipo);
	
	public void onMovStart(Ficha turno, TableroSoloLectura tab, boolean[][] pistas);
}
