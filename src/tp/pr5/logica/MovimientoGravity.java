package tp.pr5.logica;

public class MovimientoGravity extends Movimiento {
	
	public MovimientoGravity(int columna, int fila, Ficha color){
		super(color, columna);
		setFila(fila);
	}
	
	@Override
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido{
		
		//comprobamos que la posición elegida se encuentre dentro de los límites del tablero
		//y si lo está, comprobamos que la ficha no se coloque en una casilla ocupada.
		//Si todo es correcto colocamos la ficha
		if (getFila() < 1 || getFila() > tab.getAlto() || getDonde() > tab.getAncho()
				|| getDonde() < 1){
			throw new MovimientoInvalido("Posición incorrecta.");
		}
		else if (tab.getCasilla(getDonde(), getFila()) != Ficha.VACIA)
			throw new MovimientoInvalido("Casilla ocupada.");
		else{
			colocarGravity(tab);
		}
		
	}

	@Override
	//Como al ejecutar el movimiento se guarda la posición final de la ficha, 
	//undo simplemente coloca vacío en esa posición
	public void undo(Tablero tab) {
		tab.setCasilla(getDonde(), getFila(), Ficha.VACIA);
	}
	
	//procedimiento al que sólo se llama desde ejecuta movimiento y que decide 
	//hacia dónde se desplazará la ficha en función de su posición
	private void colocarGravity(Tablero tab){
		int arriba = getFila() - 1;
		int abajo = tab.getAlto() - getFila();
		int izda = getDonde() - 1;
		int dcha = tab.getAncho() - getDonde();
		int verticalMenor = 0, horizontalMenor = 0;
		//estas variables de tipo boolean indican que la ficha se desplazará en ese sentido a la hora de colocarla
		//si valen true
		boolean up = true, down = true, left = true, right = true;
		//estas dos varibles indican si habrá desplazamiento vertical y horizontal
		boolean vertical = true, horizontal = true;
		
		//aquí se deciden las direcciones y se guarda el menor valor horizontal y vertical
		if (arriba > abajo){
			verticalMenor  = abajo;
			up = false;
		}else if (arriba < abajo){
			verticalMenor = arriba;
			down = false;	
		}else{
			up = down = vertical = false;
		}
		
		if (dcha > izda){
			horizontalMenor = izda;
			right = false;
		}else if (izda > dcha){
			horizontalMenor = dcha;
			left = false;
		}
		else
			left = right = horizontal = false;
		
		//si es posible que la ficha se mueva tanto verticalmente como horizontalmente, comprobamos cual es menor y ponermos
		//a false la otra dirección(si son iguales no se cambia nada ya que estaría en una diagonal)
		if (vertical && horizontal){
			if (verticalMenor > horizontalMenor)
				up = down = false; //no sabemos cual de los dos estaba a true
			else if (verticalMenor < horizontalMenor)
				left = right = false;
		}
		
		desplazarFichaGravity(tab, up, down, left, right);
	}
	
	//procedimiento que a partir de los datos obtenidos en colocarGravity coloca la ficha en su posición final
	//y actualiza el valor de sus atributos a los finales
	private void desplazarFichaGravity(Tablero tab, boolean up, boolean down, boolean left, boolean right){
		int colFinal = getDonde(), filaFinal = getFila();
		boolean vacia = true;
		
		//comprobamos que alguna de las direcciones valga true, y si no no se hace nada. Sólo ocurre en tableros
		//con ambas dimensiones impares cuando se coloca en la ficha del medio
		if (down || up || left || right){
			while (vacia && colFinal <= tab.getAncho() && colFinal >= 1 && filaFinal >= 1 && filaFinal <= tab.getAlto()){
				if (tab.getCasilla(colFinal, filaFinal) != Ficha.VACIA)
					vacia = false;
				if(vacia){
					if (up)
						filaFinal -= 1;
					if (down)
						filaFinal += 1;
					if (left)
						colFinal -= 1;
					if (right)
						colFinal += 1;
				}
			}
		
			//como al salir del bucle el valor de colFinal y filaFinal es el sigueinte al que debría lo reducimos en 1
			if (up)
				filaFinal += 1;
			if (down)
				filaFinal -= 1;
			if (left)
				colFinal += 1;
			if (right)
				colFinal -= 1;
		}
			
		setFila(filaFinal);
		setDonde(colFinal);
		tab.setCasilla(colFinal, filaFinal, getJugador());
	}
	

}
