package tp.pr5.logica;

public class ReglasConecta4 implements ReglasJuego {

	private static final int ALTO = 6;
	private static final int ANCHO = 7;
	
	
	public ReglasConecta4(){}
	
	@Override
	public Tablero iniciaTablero() {
		Tablero tab = new Tablero(ANCHO, ALTO);
		return tab;
	}

	//por defecto empiezan jugando las blancas
	@Override
	public Ficha jugadorInicial() {
		return Ficha.BLANCA;
	}

	//funcion que devuelve el color de la ficha ganadora (vacia si no hay ganador)
	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {
		Ficha ganador;
		
		int columna = ultimoMovimiento.getDonde() - 1, fila = Utilidades.primerVacio(columna, t) + 1;
		boolean diagonal = (Utilidades.comprobarDiagonalC(columna, fila, t, ultimoMovimiento.getJugador()) 
				|| Utilidades.comprobarDiagonalD(columna, fila, t, ultimoMovimiento.getJugador()));
		
		//si ese movimiento forma grupo se devuelve el color que lo hab�a ejecutado
		if(Utilidades.comprobarFila(columna, fila, t, ultimoMovimiento.getJugador()) || 
				Utilidades.comprobarColumna(columna, fila, t, ultimoMovimiento.getJugador()) || diagonal){
			ganador = ultimoMovimiento.getJugador();
		}
		else
			ganador = Ficha.VACIA;
		
		return ganador;
	}

	//funcion que comprueba si hay tablas viendo si el tablero esta lleno
	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {
		
		boolean colIncompleta = false;
		int i = 0;
		
		while(i < t.getAncho() && !colIncompleta){
			if(Utilidades.primerVacio(i, t) != -1)
				colIncompleta = true;
			i++;
		}
		
		return !colIncompleta;
	}

	//funcion que devuelve el color de la ficha a la que le toca jugar en el siguiente turno
	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) {
		Ficha siguienteTurno;
		
		if (ultimoEnPoner == Ficha.BLANCA)
			siguienteTurno = Ficha.NEGRA;
		else
			siguienteTurno = Ficha.BLANCA;
			
		return siguienteTurno;
	}

	@Override
	public TipoJuego getTipoJuego() {
		return TipoJuego.CONECTA4;
	}

	@Override
	public boolean[][] pistas(TableroSoloLectura tab, Ficha color) {
		// TODO Auto-generated method stub
		return null;
	}

}
