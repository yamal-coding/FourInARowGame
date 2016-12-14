package tp.pr5.logica;

public class MovimientoConecta4 extends Movimiento {

	public MovimientoConecta4(int donde, Ficha color){
		super(color, donde);
	}
	
	@Override
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido{
		int col = getDonde();
		
		//comprobamos que el movimiento es posible
		if((col > tab.getAncho()) || (col < 1)){
			throw new MovimientoInvalido("Columna incorrecta. Debe estar entre 1 y 7.");//constructor aun por elegir
		}
		else {
			//igualamos pos a la primera ficha vac�a de la columna dada
			int pos = Utilidades.primerVacio(col - 1, tab);
			//si la columna no est� llena realizamos el movimiento
			if (pos != -1){
				tab.setCasilla(col, pos + 1, getJugador());
			}
			else
				throw new MovimientoInvalido("Columna llena.");//constructor aun por elegir
		}
	}

	@Override
	public void undo(Tablero tab) {
		//igualamos pos a la primera ficha vacia de la columna dada
		int pos = Utilidades.primerVacio(getDonde() - 1, tab);
					
		//sabemos que siempre va a haber al menos una ficha en la columna
		tab.setCasilla(getDonde(), pos + 2, Ficha.VACIA);
	}
}
