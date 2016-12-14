package tp.pr5.logica;

public interface ReglasJuego {

	Tablero iniciaTablero();
	
	Ficha jugadorInicial();
	
	Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t);
	
	boolean tablas(Ficha ultimoEnPoner, Tablero t);
	
	Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t);
	
	public TipoJuego getTipoJuego();
	
	public boolean [][] pistas(TableroSoloLectura tab, Ficha color);
}
