package tp.pr5.logica;

public class MovimientoReversi extends Movimiento {
	
	private int arriba;
	private int abajo;
	private int izq;
	private int der;
	private int diagCD;
	private int diagCI;
	private int diagDD;
	private int diagDI;

	public MovimientoReversi(int columna, int fila, Ficha color) {
		super(color, columna);
		setFila(fila);
		arriba = abajo = izq = der = diagCD = diagCI = diagDD = diagDI = 0;
	}

	@Override
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		
		boolean ok;
		
		if (getFila() < 1 || getFila() > tab.getAlto() || getDonde() > tab.getAncho()
				|| getDonde() < 1){
			throw new MovimientoInvalido("Posición incorrecta.");
		}
		else if (tab.getCasilla(getDonde(), getFila()) != Ficha.VACIA)
			throw new MovimientoInvalido("Casilla ocupada.");
		else{
			//se cuentan las casillas a voltear en cada una de las direcciones
			ok = buscaMovimientos(tab);
			
			if(!ok)
				throw new MovimientoInvalido("Movimiento imposible.");
			else{
				tab.setCasilla(getDonde(), getFila(), getJugador());
				volteaFichas(tab, getJugador());
			}
		}	
	}

	private void volteaFichas(Tablero tab, Ficha color) {
		//arriba
		for (int i = 1; i <= arriba; i++)
			tab.setCasilla(getDonde(), getFila() - i, color);
		
		//abajo
		for (int i = 1; i <= abajo; i++)
			tab.setCasilla(getDonde(), getFila() + i, color);
		
		//derecha
		for (int i = 1; i <= der; i++)
			tab.setCasilla(getDonde() + i, getFila(), color);
		
		//izqiuerda
		for (int i = 1; i <= izq; i++)
			tab.setCasilla(getDonde() - i, getFila(), color);
		
		//diagonal creciente derecha
		for (int i = 1; i <= diagCD; i++)
			tab.setCasilla(getDonde() + i, getFila() - i, color);
		
		//diagonal creciente izquierda
		for (int i = 1; i <= diagCI; i++)
			tab.setCasilla(getDonde() - i, getFila() + i, color);
		
		//diagonal decreciente derecha
		for (int i = 1; i <= diagDD; i++)
			tab.setCasilla(getDonde() + i, getFila() + i, color);
		
		//diagonal decreciente izuiqerda
		for (int i = 1; i <= diagDI; i++)
			tab.setCasilla(getDonde() - i, getFila() - i, color);
	}

	public boolean buscaMovimientos(TableroSoloLectura tab) {
		boolean ok = false;
		if (Utilidades.buscaMovArriba(getJugador(), tab, getDonde(), getFila())){
			ok = true;
			arriba(tab);
		}
		if (Utilidades.buscaMovAbajo(getJugador(), tab, getDonde(), getFila())){
			ok = true;
			abajo(tab);
		}
		if (Utilidades.buscaMovDcha(getJugador(), tab, getDonde(), getFila())){
			ok = true;
			derecha(tab);
		}
		if (Utilidades.buscaMovIzda(getJugador(), tab, getDonde(), getFila())){
			ok = true;
			izquierda(tab);
		}
		if (Utilidades.buscaMovDiagonalCDer(getJugador(), tab, getDonde(), getFila())){
			ok = true;
			diagonalCD(tab);
		}
		if (Utilidades.buscaMovDiagonalCIzq(getJugador(), tab, getDonde(), getFila())){
			ok = true;
			diagonalCI(tab);
		}
		if (Utilidades.buscaMovDiagonalDDer(getJugador(), tab, getDonde(), getFila())){
			ok = true;
			diagonalDD(tab);
		}
		if (Utilidades.buscaMovDiagonalDIzq(getJugador(), tab, getDonde(), getFila())){
			ok = true;
			diagonalDI(tab);
		}
		return ok;
	}

	private void diagonalDI(TableroSoloLectura tab) {
		int x = getDonde() - 1, y = getFila() - 1 ;
		
		while (tab.getCasilla(x, y) != getJugador() && x > 0 && y > 0){
			diagDI++;
			x--;
			y--;
		}
	}

	private void diagonalDD(TableroSoloLectura tab) {
		int x = getDonde() + 1, y = getFila() + 1 ;
		
		while (tab.getCasilla(x, y) != getJugador() && x <= tab.getAncho() && y <= tab.getAlto()){
			diagDD++;
			x++;
			y++;
		}
	}

	private void diagonalCI(TableroSoloLectura tab) {
		int x = getDonde() - 1, y = getFila() + 1 ;
		
		while (tab.getCasilla(x, y) != getJugador() && x > 0 && y <= tab.getAlto()){
			diagCI++;
			x--;
			y++;
		}
	}

	private void diagonalCD(TableroSoloLectura tab) {
		int x = getDonde() + 1, y = getFila() - 1 ;
		
		while (tab.getCasilla(x, y) != getJugador() && x <= tab.getAncho() && y > 0){
			diagCD++;
			x++;
			y--;
		}
	}

	private void izquierda(TableroSoloLectura tab) {
		int x = getDonde() - 1;
		
		while (tab.getCasilla(x, getFila()) != getJugador() && x <= tab.getAncho()){
			izq++;
			x--;
		}
	}

	private void derecha(TableroSoloLectura tab) {
		int x = getDonde() + 1;
		
		while (tab.getCasilla(x, getFila()) != getJugador() && x <= tab.getAncho()){
			der++;
			x++;
		}
	}

	private void abajo(TableroSoloLectura tab) {
		int y = getFila() + 1;
		
		while (tab.getCasilla(getDonde(), y) != getJugador() && y <= tab.getAlto()){
			abajo++;
			y++;
		}
	}

	private void arriba(TableroSoloLectura tab) {
		int y = getFila() - 1;
		
		while (tab.getCasilla(getDonde(), y) != getJugador() && y > 0){
			arriba++;
			y--;
		}
	}

	@Override
	public void undo(Tablero tab) {
		tab.setCasilla(getDonde(), getFila(), Ficha.VACIA);
		Ficha color;
		
		if (getJugador() == Ficha.BLANCA)
			color = Ficha.NEGRA;
		else
			color = Ficha.BLANCA;
		
		volteaFichas(tab, color);
	}

}
