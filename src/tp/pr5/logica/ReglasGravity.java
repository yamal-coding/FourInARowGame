package tp.pr5.logica;

public class ReglasGravity implements ReglasJuego {

	private int ancho;
	private int alto;
	
	public ReglasGravity(int ancho, int alto){
		this.alto = alto;
		this.ancho = ancho;
	}
	
	@Override
	public Tablero iniciaTablero() {
		return new Tablero(ancho, alto);
	}

	@Override
	public Ficha jugadorInicial() {
		return Ficha.BLANCA;
	}

	@Override
	//se comprueba si hay algún grupo y si lo hay se devuelve el ganador(sólo puede ser el último en poner).
	//si no hay ganador se devuelve vacía
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {
		Ficha ganador = Ficha.VACIA;
		int col = ultimoMovimiento.getDonde();
		int fila = ultimoMovimiento.getFila();
		Ficha fichaJugador = ultimoMovimiento.getJugador();
		
		if (Utilidades.comprobarFila(col - 1, fila - 1, t, fichaJugador) 
				|| Utilidades.comprobarColumna(col - 1, fila - 1, t, fichaJugador)
				|| Utilidades.comprobarDiagonalC(col - 1, fila - 1, t, fichaJugador)
				|| Utilidades.comprobarDiagonalD(col - 1, fila - 1, t, fichaJugador))
			ganador = fichaJugador;
		
		return ganador;
	}

	@Override
	//solamente hay tablas si el tablero está completo
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {
		boolean colIncompleta = false;
		int i = 0;
		
		while (i < t.getAncho() && !colIncompleta){
			if (Utilidades.primerVacio(i, t) != -1)
				colIncompleta = true;
			else
				i++;
		}
		
		return !colIncompleta;
	}

	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) {
		Ficha siguienteTurno;
		
		if (ultimoEnPoner == Ficha.BLANCA)
			siguienteTurno = Ficha.NEGRA;
		else
			siguienteTurno = Ficha.BLANCA;
			
		return siguienteTurno;
	}
	
	public TipoJuego getTipoJuego(){
		return TipoJuego.GRAVITY;
	}

	@Override
	public boolean[][] pistas(TableroSoloLectura tab, Ficha color) {
		// TODO Auto-generated method stub
		return null;
	}


}
