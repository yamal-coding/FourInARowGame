package tp.pr5.logica;

public class MovimientoComplica extends Movimiento {

	private Ficha eliminada;//en caso de que al ejecutar un movimiento con columna llena
							//se guarda la ficha eliminada del tablero por si se quiere deshacer
	
	public MovimientoComplica(int donde, Ficha color){
		super(color, donde);
		eliminada = Ficha.VACIA;
	}
	
	@Override
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		int col = getDonde();
		
		//comprobamos que el movimiento es posible
		if((col > tab.getAncho()) || (col < 1)){
			throw new MovimientoInvalido("Columna incorrecta. Debe estar entre 1 y 4.");//constructor aun por elegir
		}
		else{
			//si la columna elegida esta llena, se guarda la ficha eliminada, se bajan las fichas
			//y se coloca la nueva ficha
			if (Utilidades.primerVacio(col - 1, tab) == -1){
				eliminada = tab.getCasilla(col, tab.getAlto());
				
				bajarFichas(tab);
				
				tab.setCasilla(col, 1, getJugador());
			}
			else{
				//igualamos pos a la primera ficha vacía de la columna dada
				int pos = Utilidades.primerVacio(col - 1, tab);
				
				tab.setCasilla(col, pos + 1, getJugador());
			}
		}
		
	}

	@Override
	public void undo(Tablero tab) {
		int col = getDonde();
		
		if (eliminada == Ficha.VACIA){
			//igualamos pos a la primera ficha vacia de la columna dada
			int pos = Utilidades.primerVacio(col - 1, tab);
						
			//sabemos que siempre va a haber al menos una ficha en la columna
			tab.setCasilla(col, pos + 2, Ficha.VACIA);
		}
		else{
			//si hay guardada una ficha eliminada, se suben las fichas de la columna y
			//se inserta abajo la ficha guardada
			subirFichas(tab);
			
			tab.setCasilla(col, tab.getAlto(), eliminada);
		}
	}
	
	//procedimiento que desplaza una posicion hacia abajo las fichas de una columna
		private void bajarFichas(Tablero tab){
			Ficha aux;
			
			for (int i = tab.getAlto() - 1; i > 0; i--){
				aux = tab.getCasilla(getDonde(), i);
				tab.setCasilla(getDonde(), i + 1, aux);
			}
			
			//la ficha de arriba aun no se ha colocado
		}
		
		//procedimiento que desplaza una posicion hacia arriba las fichas de una columna
		private void subirFichas(Tablero tab){
			Ficha aux;
			
			for (int i = 0; i < tab.getAlto() - 1; i++){
				aux = tab.getCasilla(getDonde(), i + 2);
				tab.setCasilla(getDonde(), i + 1, aux);
			}
			
			//la ficha de abajo aun no se ha colocado
		}
}
