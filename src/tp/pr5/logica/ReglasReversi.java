package tp.pr5.logica;

public class ReglasReversi implements ReglasJuego {
	
	private static final int ALTO = 8;
	private static final int ANCHO = 8;

	@Override
	public Tablero iniciaTablero() {
		Tablero tab = new Tablero(ANCHO, ALTO);
		
		tab.setCasilla(4, 4, Ficha.BLANCA);
		tab.setCasilla(5, 4, Ficha.NEGRA);
		tab.setCasilla(4, 5, Ficha.NEGRA);
		tab.setCasilla(5, 5, Ficha.BLANCA);
		
		return tab;
	}

	@Override
	public Ficha jugadorInicial() {
		return Ficha.NEGRA;
	}

	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {
		boolean hayVacio = false;
		int numBlancas = 0, numNegras = 0;
		Ficha ficha = Ficha.VACIA;
		
		for (int y = 1; y <= t.getAlto() && !hayVacio; y++){
			for (int x = 1; x <= t.getAncho() && !hayVacio; x++){
				ficha = t.getCasilla(x, y);
				
				if (ficha == Ficha.VACIA)
					hayVacio = true;
				else if (ficha == Ficha.NEGRA)
					numNegras++;
				else if (ficha == Ficha.BLANCA)
					numBlancas++;
			}
		}
		if ((!hayVacio) || (!Utilidades.tieneMovimiento(Ficha.BLANCA, t) && !Utilidades.tieneMovimiento(Ficha.NEGRA, t))){
			if (numNegras > numBlancas)
				ficha = Ficha.NEGRA;
			else if (numBlancas > numNegras)
				ficha = Ficha.BLANCA;
		}
		
		return ficha;
	}

	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {
		boolean tablas = true, vacia = false;
		int numBlancas = 0, numNegras = 0;
		Ficha ficha;
		
		for (int y = 1; y <= t.getAlto() && tablas; y++){
			for (int x = 1; x <= t.getAncho() && tablas; x++){
				ficha = t.getCasilla(x, y);
				
				if (ficha == Ficha.VACIA){
					tablas = false;
					vacia = true;
				}
				else if (ficha == Ficha.NEGRA)
					numNegras++;
				else if (ficha == Ficha.BLANCA)
					numBlancas++;
			}
		}
		
		if (tablas && numNegras != numBlancas)
			tablas = false;
		
		return tablas;
	}

	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) {
		Ficha siguienteTurno = ultimoEnPoner;
		
		if (ultimoEnPoner == Ficha.NEGRA){
			if (Utilidades.tieneMovimiento(Ficha.BLANCA, t))
				siguienteTurno = Ficha.BLANCA;
		}
		else{
			if (Utilidades.tieneMovimiento(Ficha.NEGRA, t))
				siguienteTurno = Ficha.NEGRA;
		}
				
		return siguienteTurno;
	}
	
	@Override
	public TipoJuego getTipoJuego() {
		return TipoJuego.REVERSI;
	}

	@Override
	public boolean [][] pistas(TableroSoloLectura tab, Ficha color) {
		boolean [][] tableroB = new boolean [ALTO][ANCHO];
		
		for (int i = 0; i < ALTO; i++){
			for (int j = 0; j < ANCHO; j++){
				tableroB[i][j] = false;
			}
		}
		
		for (int i = 1; i <= ALTO; i++){
			for (int j = 1; j <= ANCHO; j++){
				if(tab.getCasilla(j, i) == Ficha.VACIA)
					tableroB[i - 1][j - 1] = Utilidades.buscaMovArriba(color, tab, j, i) || Utilidades.buscaMovAbajo(color, tab, j, i)
					|| Utilidades.buscaMovDcha(color, tab, j, i) || Utilidades.buscaMovIzda(color, tab, j, i)
					|| Utilidades.buscaMovDiagonalCDer(color, tab, j, i) || Utilidades.buscaMovDiagonalCIzq(color, tab, j, i)
					|| Utilidades.buscaMovDiagonalDDer(color, tab, j, i) || Utilidades.buscaMovDiagonalDIzq(color, tab, j, i);
			}
		}
		
		return tableroB;
	}
	
}