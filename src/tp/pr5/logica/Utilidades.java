package tp.pr5.logica;

public class Utilidades {
	//funci�n que comprueba si hay al menos un hueco en una columna de un tablero dado,
	//y te devuelve su posici�n (el primero que encuentra empezando por abajo del tablero)
	public static int primerVacio(int col, TableroSoloLectura tablero){
		int pos = tablero.getAlto() - 1;
		boolean encontrado = false;
		
		while (!encontrado && pos >= 0){
			if (tablero.getCasilla(col + 1, pos + 1) == Ficha.VACIA)
				encontrado = true;
			else
				pos--;
		}	
			
		//si no se ha encontrado ninguna ficha vac�a pos valdr� -1
			
		return pos;
	}

	//----------------------------GRUPOS CONECTA 4 Y GRAVITY--------------------------------------
	
	//funci�n que s�lo devuelve true si encuentra un grupo de fichas del mismo color
	//diferente a vac�o en una determinada columna y a partir de una fila dada
	public static boolean comprobarColumna(int columna, int fila, Tablero tablero, Ficha color){
		boolean grupo = false, distinto = false;
		int contador = 1, filaOriginal = fila;
		
		//avanzamos a la siguiente posici�n
		fila++;
		
		//comprobamos las siguientes posiciones
		while (!grupo && !distinto && fila < tablero.getAlto()){
			
			if(tablero.getCasilla(columna + 1, fila + 1) == color)
				contador++;
			else
				distinto = true;
			
			//si se han encontrado cuatro fichas iguales seguidas, entonces hay grupo
			if(contador == 4)
				grupo = true;
			
			fila++;
		}
		
		fila = filaOriginal - 1;
		distinto = false;
		
		while(!grupo && !distinto && fila >= 0){
			if(tablero.getCasilla(columna + 1, fila + 1) == color)
				contador++;
			else
				distinto = true;
			
			if(contador == 4)
				grupo = true;
			
			fila--;
		}
		
		return grupo;
	}
	
	//funci�n que s�lo devuelve true si encuentra un grupo de fichas del mismo color
	//diferente a vac�o en una determinada columna y a partir de una fila dada
	public static boolean comprobarFila(int columna, int fila, Tablero tablero, Ficha color){
		boolean grupo = false, distinto = false;
		int contador = 1, columnaOriginal = columna;
		
		columna++;
		
		while(!grupo && !distinto && columna < tablero.getAncho()){
			
			if(tablero.getCasilla(columna + 1, fila + 1) == color)
				contador++;
			else
				distinto = true;
			
			if(contador == 4)
				grupo = true;
			
			columna++;
		}
		
		//hasta aqu� el funcionamiento es igual a comprobar columna
		//pero como las filas hay que comprobarlas en ambas direcciones volvemos a la posici�n original
		//y cambiamos de direcci�n
		columna = columnaOriginal - 1;
		
		distinto = false;
		
		while(!grupo && !distinto && columna >= 0){
			
			if(tablero.getCasilla(columna + 1, fila + 1) == color)
				contador++;
			else
				distinto = true;
			
			if(contador == 4)
				grupo = true;
			
			columna--;
		}
		
		return grupo;
	}
	
	//funci�n que s�lo devuelve true si encuentra un grupo de fichas del mismo color
	//diferente a vac�o en la diagonal ascendente y a partir de una fila dada
	//el funcionamiento es el mismo que el de comprobar fila
	public static boolean comprobarDiagonalC(int columna, int fila, Tablero tablero, Ficha color){
		boolean grupo = false, distinto = false;
		int contador = 1, columnaOriginal = columna, filaOriginal = fila;
		
		columna++;
		fila--;
		
		while(!grupo && !distinto && columna < tablero.getAncho() && fila >= 0){
			
			if(tablero.getCasilla(columna + 1, fila + 1) == color)
				contador++;
			else
				distinto = true;
			if(contador == 4)
				grupo = true;
			
			columna++;
			fila--;
		}
		
		columna = columnaOriginal - 1;
		fila = filaOriginal + 1;
		distinto = false;
		
		while(!grupo && !distinto && columna >= 0 && fila < tablero.getAlto()){
			
			if(tablero.getCasilla(columna + 1, fila + 1) == color)
				contador++;
			else
				distinto = true;
			if(contador == 4)
				grupo = true;
			
			columna--;
			fila++;
		}
		
		return grupo;
	}
		
	//funci�n que s�lo devuelve true si encuentra un grupo de fichas del mismo color
	//diferente a vac�o en la diagonal descendente y a partir de una fila dada
	//el funcionamiento es el mismo que el de comprobar fila
	public static boolean comprobarDiagonalD(int columna, int fila, Tablero tablero, Ficha color){
		boolean grupo = false, distinto = false;
		int contador = 1, columnaOriginal = columna, filaOriginal = fila;
		
		fila--;
		columna--;
		
		while(!grupo && !distinto && columna >= 0 && fila >= 0){
			
			if(tablero.getCasilla(columna + 1, fila + 1) == color)
				contador++;
			else
				distinto = true;
			if(contador == 4)
				grupo = true;
			
			columna--;
			fila--;
		}
		
		fila = filaOriginal + 1;
		columna = columnaOriginal + 1;
		
		distinto = false;
		
		while(!grupo && !distinto && columna < tablero.getAncho() && fila < tablero.getAlto()){
			
			if(tablero.getCasilla(columna + 1, fila + 1) == color)
				contador++;
			else
				distinto = true;
			if(contador == 4)
				grupo = true;
			
			columna++;
			fila++;
		}
		
		return grupo;
	}
	
	//---------------------------GRUPOS COMPLICA----------------------------------------
	
	//funcion booleana busca un grupo de un color dado en una fila del tablero
	public static boolean comprobarFilasComplica(Tablero tab, Ficha color){
		boolean grupo = false, seguir;
		int i = tab.getAlto() - 1, j; //miramos de abajo a arriba y de izquierda a derecha
		
		while (!grupo && i >= 0){
			j = 0;
			seguir = true;
			while (seguir && j < tab.getAncho()){
				if (tab.getCasilla(j + 1, i + 1) == color)
					j++;
				else
					seguir = false;
			}
			if (j == 4)
				grupo = true;
			else	
				i--;
		}
		
		return grupo;
	}
	
	
	//funcion que busca un grupo de un color dado en vertical empezando desde la 
	//primera ficha de la columna hacia abajo
	public static boolean comprobarColumnasComplica(Tablero tab, Ficha color){
		boolean grupo = false, seguir;
		int i = 0, j, cont; //miramos de izquierda a derecha y de abajo a arriba
		
		while (!grupo && i < tab.getAncho()){
			j = primerVacio(i, tab) + 1;
			cont = 0;
			seguir = true;
			while (seguir && j < tab.getAlto() && cont < 4){
				if(tab.getCasilla(i + 1, j + 1) == color)
					cont++;
				else
					seguir = false;
				j++;
			}
			
			if (cont == 4)
				grupo = true;
			else
				i++;
		}
		
		return grupo;
	}
	
	//funcion que busca un grupo de un color dado en todas las diagonales posibles 
	//decrecientes en el tablero
	public static boolean comprobarDiagonalDComplica(Tablero tab, Ficha color){
		boolean grupo = false, seguir;
		int i = 3, j, k, cont; //miramos de abajo a arriba de izquierda a derecha
		
		while(!grupo && i >= 0){
			j = 0;
			cont = 0;
			k = i;
			seguir = true;
			while (seguir && j < tab.getAncho() && k < tab.getAlto()){
				if(tab.getCasilla(j + 1, k + 1) == color)
					cont++;
				else
					seguir = false;
				j++;
				k++;
			}
			if (cont == 4)
				grupo = true;
			else
				i--;
		}
		
		return grupo;
	}
	
	//funcion que busca un grupo de un color dado en todas las diagonales posibles 
	//crecientes en el tablero
	public static boolean comprobarDiagonalCComplica(Tablero tab, Ficha color){
		boolean grupo = false, seguir;
		int i = 3, j, k, cont; //miramos de abajo a arriba de izquierda a derecha
		
		while(!grupo && i < tab.getAlto()){
			j = 0;
			cont = 0;
			k = i;
			seguir = true;
			while (seguir && j < tab.getAncho() && k >= 0){
				if(tab.getCasilla(j + 1, k + 1) == color)
					cont++;
				else
					seguir = false;
				j++;
				k--;
			}
			if (cont == 4)
				grupo = true;
			else
				i++;
		}
		
		return grupo;
	}
	
	//funcion booleana que agrupa todas las comprobaciones de grupos para decir si
	//hay al menos un grupo de un color dado
	public static boolean hayGrupo(Tablero tab, Ficha color){
		boolean grupo;
		
		grupo = (comprobarFilasComplica(tab, color) || comprobarColumnasComplica(tab, color) || 
				comprobarDiagonalDComplica(tab, color) || comprobarDiagonalCComplica(tab, color));
		
		return grupo;
	}
	
	//----------------------------------FUNCIONES REVERSI-------------------------

	public static boolean buscaMovArriba(Ficha color, TableroSoloLectura tab, int x, int y){
		boolean ok = false;
		
		if (!(y - 1 <= 0 || x <= 0 || x > tab.getAncho() || y <= 0 || y > tab.getAlto())){
			y--;
			if (tab.getCasilla(x, y) != Ficha.VACIA && tab.getCasilla(x, y) != color){
				y--;
				while (y > 0 && !ok && tab.getCasilla(x, y) != Ficha.VACIA){
					if (tab.getCasilla(x, y) == color)
						ok = true;
					else
						y--;
				}
			}
		}
		
		return ok;
	}
	
	public static boolean buscaMovAbajo(Ficha color, TableroSoloLectura tab, int x, int y){
		boolean ok = false;
		
		if (!(y + 1 > tab.getAlto() || x <= 0 || x > tab.getAncho() || y <= 0 || y > tab.getAlto())){
			y++;
			if (tab.getCasilla(x, y) != Ficha.VACIA && tab.getCasilla(x, y) != color){
				y++;
				while (y <= tab.getAlto() && !ok && tab.getCasilla(x, y) != Ficha.VACIA){
					if (tab.getCasilla(x, y) == color)
						ok = true;
					else
						y++;
				}
			}
		}
		
		return ok;
	}
	
	public static boolean buscaMovDcha(Ficha color, TableroSoloLectura tab, int x, int y){
		boolean ok = false;
		
		if (!(x + 1 > tab.getAncho() || x <= 0 || x > tab.getAncho() || y <= 0 || y > tab.getAlto())){
			x++;
			if (tab.getCasilla(x, y) != Ficha.VACIA && tab.getCasilla(x, y) != color){
				x++;
				while (x <= tab.getAncho() && !ok && tab.getCasilla(x, y) != Ficha.VACIA){
					if (tab.getCasilla(x, y) == color)
						ok = true;
					else
						x++;
				}
			}
		}
		
		return ok;
	}
	
	public static boolean buscaMovIzda(Ficha color, TableroSoloLectura tab, int x, int y){
		boolean ok = false;
		
		if (!(x - 1 <= 0 || x <= 0 || x > tab.getAncho() || y <= 0 || y > tab.getAlto())){
			x--;
			if (tab.getCasilla(x, y) != Ficha.VACIA && tab.getCasilla(x, y) != color){
				x--;
				while (x > 0 && !ok && tab.getCasilla(x, y) != Ficha.VACIA){
					if (tab.getCasilla(x, y) == color)
						ok = true;
					else
						x--;
				}
			}
		}
		
		return ok;
	}
	
	public static boolean buscaMovDiagonalCDer(Ficha color, TableroSoloLectura tab, int x, int y){
		boolean ok = false;
		
		if (!(x <= 0 || x > tab.getAncho() || y <= 0 || y > tab.getAlto() || y - 1 <= 0 || x + 1 > tab.getAncho())){
			y--;
			x++;
			if (tab.getCasilla(x, y) != Ficha.VACIA && tab.getCasilla(x, y) != color){
				y--;
				x++;
				while (x <= tab.getAncho() && y > 0 && !ok && tab.getCasilla(x, y) != Ficha.VACIA){
					if (tab.getCasilla(x, y) == color)
						ok = true;
					else{
						y--;
						x++;
					}
				}
			}
		}
		
		return ok;
	}
	
	public static boolean buscaMovDiagonalCIzq(Ficha color, TableroSoloLectura tab, int x, int y){
		boolean ok = false;
		
		if (!(x <= 0 || x > tab.getAncho() || y <= 0 || y > tab.getAlto()|| y + 1 > tab.getAlto() || x - 1 <= 0)){
			y++;
			x--;
			if (tab.getCasilla(x, y) != Ficha.VACIA && tab.getCasilla(x, y) != color){
				y++;
				x--;
				while (x > 0 && y <= tab.getAlto() && !ok && tab.getCasilla(x, y) != Ficha.VACIA){
					if (tab.getCasilla(x, y) == color)
						ok = true;
					else{
						y++;
						x--;
					}
				}
			}
		}
		
		return ok;
	}
	
	/*public static boolean buscaMovDiagonalC(Ficha color, TableroSoloLectura tab, int x, int y){
		boolean ok = false;
		
		if (!(x <= 0 || x > tab.getAncho() || y <= 0 || y > tab.getAlto())){
			int auxX = x, auxY = y;
			//primero miramos en la diagonal creciente hacia la derecha
			if (!(y - 1 <= 0 || x + 1 > tab.getAncho())){
				y--;
				x++;
				if (tab.getCasilla(x, y) != Ficha.VACIA && tab.getCasilla(x, y) != color){
					y--;
					x++;
					while (x <= tab.getAncho() && y > 0 && !ok){
						if (tab.getCasilla(x, y) == color)
							ok = true;
						else{
							y--;
							x++;
						}
					}
				}
			}
			
			//si no se ha encontrado en la primera direccion miramos hacia la izquierda
			if (!ok){
				y = auxY;
				x = auxX;
				if (!(y + 1 > tab.getAlto() || x - 1 <= 0)){
					y++;
					x--;
					if (tab.getCasilla(x, y) != Ficha.VACIA && tab.getCasilla(x, y) != color){
						y++;
						x--;
						while (x > 0 && y <= tab.getAlto() && !ok){
							if (tab.getCasilla(x, y) == color)
								ok = true;
							else{
								y++;
								x--;
							}
						}
					}
				}
			}
		}
		
		return ok;
	}*/
	
	public static boolean buscaMovDiagonalDIzq(Ficha color, TableroSoloLectura tab, int x, int y){
		boolean ok = false;
		
		if (!(x <= 0 || x > tab.getAncho() || y <= 0 || y > tab.getAlto() || y - 1 <= 0 || x - 1 <= 0)){
			y--;
			x--;
			if (tab.getCasilla(x, y) != Ficha.VACIA && tab.getCasilla(x, y) != color){
				y--;
				x--;
				while (x > 0 && y > 0 && !ok && tab.getCasilla(x, y) != Ficha.VACIA){
					if (tab.getCasilla(x, y) == color)
						ok = true;
					else{
						y--;
						x--;
					}
				}
			}
		}
		
		return ok;
	}
	
	public static boolean buscaMovDiagonalDDer(Ficha color, TableroSoloLectura tab, int x, int y){
		boolean ok = false;
		
		if (!(x <= 0 || x > tab.getAncho() || y <= 0 || y > tab.getAlto()
				|| y + 1 > tab.getAlto() || x + 1 > tab.getAncho())){
			y++;
			x++;
			if (tab.getCasilla(x, y) != Ficha.VACIA && tab.getCasilla(x, y) != color){
				y++;
				x++;
				while (x <= tab.getAncho() && y <= tab.getAlto() && !ok && tab.getCasilla(x, y) != Ficha.VACIA){
					if (tab.getCasilla(x, y) == color)
						ok = true;
					else{
						y++;
						x++;
					}
				}
			}
		}
		
		return ok;
	}
	
	/*public static boolean buscaMovDiagonalD(Ficha color, TableroSoloLectura tab, int x, int y){
		boolean ok = false;
		
		if (!(x <= 0 || x > tab.getAncho() || y <= 0 || y > tab.getAlto())){
			int auxX = x, auxY = y;
			//primero miramos en la diagonal decreciente hacia la izquierda
			if (!(y - 1 <= 0 || x - 1 <= 0)){
				y--;
				x--;
				if (tab.getCasilla(x, y) != Ficha.VACIA && tab.getCasilla(x, y) != color){
					y--;
					x--;
					while (x > 0 && y > 0 && !ok){
						if (tab.getCasilla(x, y) == color)
							ok = true;
						else{
							y--;
							x--;
						}
					}
				}
			}
			
			//si no se ha encontrado en la primera direccion miramos hacia la derecha
			if (!ok){
				y = auxY;
				x = auxX;
				if (!(y + 1 > tab.getAlto() || x + 1 > tab.getAncho())){
					y++;
					x++;
					if (tab.getCasilla(x, y) != Ficha.VACIA && tab.getCasilla(x, y) != color){
						y++;
						x++;
						while (x <= tab.getAncho() && y <= tab.getAlto() && !ok){
							if (tab.getCasilla(x, y) == color)
								ok = true;
							else{
								y++;
								x++;
							}
						}
					}
				}
			}
		}
		
		return ok;
	}*/
	
	public static boolean tieneMovimiento(Ficha color, TableroSoloLectura tab){
		boolean ok = false;
		
		for (int i = 1; i <= tab.getAlto() && !ok; i++){
			for (int j = 1; j <= tab.getAncho() && !ok; j++){
				if (tab.getCasilla(j, i) == Ficha.VACIA){
					ok = Utilidades.buscaMovArriba(color, tab, j, i) || Utilidades.buscaMovAbajo(color, tab, j, i)
							|| Utilidades.buscaMovDcha(color, tab, j, i) || Utilidades.buscaMovIzda(color, tab, j, i)
							|| Utilidades.buscaMovDiagonalCDer(color, tab, j, i) || Utilidades.buscaMovDiagonalCIzq(color, tab, j, i)
							|| Utilidades.buscaMovDiagonalDDer(color, tab, j, i) || Utilidades.buscaMovDiagonalDIzq(color, tab, j, i);
				}
			}
		}
		
		return ok;
	}
}