package tp.pr5.logica;

public class ReglasComplica implements ReglasJuego {
	
	private static final int ALTO = 7;
	private static final int ANCHO = 4;

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
		boolean grupoBlancas, grupoNegras;
		
		//buscamos grupos de ambos colores
		grupoBlancas = Utilidades.hayGrupo(t, Ficha.BLANCA);
		grupoNegras = Utilidades.hayGrupo(t, Ficha.NEGRA);
		
		//si hay grupo de ambos colores o si no hay ningun grupo, no hay ganador
		if((grupoBlancas && grupoNegras) || !(grupoBlancas || grupoNegras))
			ganador = Ficha.VACIA;
		else if(grupoBlancas)
			ganador = Ficha.BLANCA;
		else
			ganador = Ficha.NEGRA;
		
		return ganador;
	}

	//en complica nunca hay tablas, por lo que siempre devolvera false
	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {
		return false;
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
		return TipoJuego.COMPLICA;
	}

	@Override
	public boolean[][] pistas(TableroSoloLectura tab, Ficha color) {
		// TODO Auto-generated method stub
		return null;
	}

}
